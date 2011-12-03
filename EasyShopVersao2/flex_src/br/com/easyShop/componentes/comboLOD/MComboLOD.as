package br.com.easyShop.componentes.comboLOD
{
	
	import br.com.easyShop.comunicacao.MRemoteObject;
	import br.com.easyShop.comunicacao.ResultJava;
	import br.com.mresolucoes.utils.ObjetoUtil;
	
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.ui.Keyboard;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Image;
	import mx.events.FlexEvent;
	import mx.events.FlexMouseEvent;
	import mx.events.Request;
	import mx.events.SandboxMouseEvent;
	import mx.events.ValidationResultEvent;
	import mx.validators.Validator;
	
	import spark.components.Group;
	import spark.components.List;
	import spark.components.PopUpAnchor;
	import spark.components.TextInput;
	import spark.components.supportClasses.SkinnableComponent;
	import spark.events.TextOperationEvent;
	
	public class MComboLOD extends TextInput
	{
		/*-*-*-*-* Componentes do Skin *-*-*-*-*/
		[SkinPart("true", type="spark.components.Group")]
		public var dropDown:Group;
		[SkinPart("true", type="spark.components.PopUpAnchor")]
		public var popUp:PopUpAnchor;
		[SkinPart("true", type="spark.components.List")]
		public var list:List;
		[SkinPart("true", type="mx.controls.Image")]
		public var imagem:Image;
		
		/*-*-*-*-* Imagens *-*-*-*-*/
		
		[Embed(source="imagens/lupa16.png")]
		[Bindable]
		private var lupa:Class;
		
		[Bindable]
		[Embed(source="imagens/selecionado16.png")]
		private var selecionado:Class;
		
		[Bindable]
		[Embed(source="imagens/ampulheta16.png")]
		private var ampulheta:Class;
		
		/*-*-*-*-* Variaveis e objetos *-*-*-*-*/
		public var mreServicePesquisa:String = null; //service que deve reveber um parametro string
		public var mreLabelField:String = null;
		public var mreLabelFunction:Function = null;
		public var mreFilterFunction:Function = null;
		
		/*-*-*-*-* Veriaveis e Objetos de utilizacao *-*-*-*-*/
		public var mreMensagemValidacao:String="Selecione um Registro";
		public var mreImagemIcone:Boolean = true;
		public var mreChange:Function=null;
		public var mreRequerido:Boolean=false;
		public var mreLarguraDropDown:Number=NaN;
		public var mreMinimoCaracteres:int=3;
		private var _mreParametros:Array=null;
		
		/*-*-*-*-* Variaveis e objetos de controle interno *-*-*-*-*/
		private var vaItemSelecionado:Validator = new Validator();
		private var itemSelecionado:Object = null;
		private var textoUltimoEvento:String = null;
		public var grupoComboLOD:Boolean = false;
		
		
		/*-*-*-*-* Construtor *-*-*-*-*/
		public function MComboLOD()
		{
			super();
			this.setStyle("skinClass", Class(MComboLODSkin));
			
			this.focusEnabled = true;
			this.addEventListener(FocusEvent.FOCUS_OUT, escutaFocusOUT);
			this.addEventListener(KeyboardEvent.KEY_DOWN, escutaTecladoInputText);
			this.addEventListener(TextOperationEvent.CHANGE, escutaTexto);
			if(mreImagemIcone==false) { this.right = 0; }
		}
		
		/*-*-*-*-* Metodos publicos *-*-*-*-*/
		/**
		 * Metodo chamado durante a criacao do componente para que suas partes sejam inicializadas e adicionadas
		 */ 
		override protected function partAdded(partName:String, instance:Object):void
		{
			super.partAdded(partName, instance)
			
			if(instance==list)
			{
				list.focusEnabled = false;
				list.labelFunction = labelFunctionItem;
				list.addEventListener(FlexEvent.CREATION_COMPLETE, adicionaEscutaMouseLista);
				if(!isNaN(mreLarguraDropDown)) { list.width = mreLarguraDropDown; }
			}
			else if(instance==dropDown)
			{
				dropDown.addEventListener(FlexMouseEvent.MOUSE_DOWN_OUTSIDE, escutaMouseDropDown);    
				dropDown.addEventListener(FlexMouseEvent.MOUSE_WHEEL_OUTSIDE, escutaMouseDropDown);                
				dropDown.addEventListener(SandboxMouseEvent.MOUSE_DOWN_SOMEWHERE, escutaMouseDropDown);
				dropDown.addEventListener(SandboxMouseEvent.MOUSE_WHEEL_SOMEWHERE, escutaMouseDropDown);
				if(!isNaN(mreLarguraDropDown)) { dropDown.width = mreLarguraDropDown; }
			}
			else if(instance==imagem)
			{
				imagem.focusEnabled = false;
				imagem.source = lupa;
				imagem.buttonMode = true;
				imagem.addEventListener(MouseEvent.CLICK, escutaBotoes);
				imagem.visible = mreImagemIcone;
			}
		}
		
		/**
		 * Metodo utilizado pelo combo para selecionar um item
		 */ 
		private function selecionarItem(item:Object, exibirPopUp:Boolean, dispararEvento:Boolean):void
		{
			itemSelecionado = item;
			
			if(itemSelecionado==null)
			{
				list.selectedIndex = -1;
				imagem.source = lupa;
			}
			else
			{
				list.selectedItem = itemSelecionado;
				textoUltimoEvento = labelFunctionItem(itemSelecionado);
				this.text = textoUltimoEvento;
				this.selectRange(this.text.length, this.text.length);
				imagem.source = selecionado;
			}
			
			if(dispararEvento && mreChange!=null)
			{
				mreChange(this);
			}
			popUp.displayPopUp = exibirPopUp;
		}
		
		/*-*-*-*-* Listeners *-*-*-*-*/
		/**
		 * Metodo chamando quando o focus deixa o componente
		 */ 
		private function escutaFocusOUT(event:FocusEvent):void
		{
			mreValidar();
		}
		
		/**
		 * Metodo chamando quando o botao de Lupa/Selecionado é clicado
		 */ 
		private function escutaBotoes(event:MouseEvent):void
		{
			if(textoUltimoEvento==this.text && list.selectedIndex>-1 && popUp.displayPopUp) //Se o texto do ultimo evento nao mudou, algum elemento estiver selecionado e o popup estiver aberto
			{
				selecionarItem(list.selectedItem, false, true);
			}
			else if(textoUltimoEvento==this.text && list.selectedIndex>-1 && !popUp.displayPopUp) //Se o texto do ultimo evento nao mudou, a lista possuir um item selecionado e o popup estiver fechado
			{
				selecionarItem(itemSelecionado, true, grupoComboLOD);
			}
			else if(textoUltimoEvento==this.text && list.selectedIndex<=-1) //Se o texto do ultimo evento nao mudou, e nao existe item selecionado
			{
				popUp.displayPopUp = true;
			}
			else //Caso o texto tenha mudado, faz a pesquisa por registros
			{
				textoUltimoEvento = this.text;
				if(textoUltimoEvento!=null && textoUltimoEvento.length>=mreMinimoCaracteres)
				{
					if(mreServicePesquisa!=null)
					{
						popUp.displayPopUp = false;
						imagem.source = ampulheta;
						mreHabilitado(false);
						
						if(_mreParametros!=null)
						{
							var parametros:Array = [textoUltimoEvento].concat(_mreParametros);
							MRemoteObject.get(mreServicePesquisa, parametros, resultadoPesquisa, null, resultadoPesquisa);
						}
						else
						{
							MRemoteObject.get(mreServicePesquisa, [textoUltimoEvento], resultadoPesquisa, null, resultadoPesquisa);
						}
					}
				}
			}
		}
		
		/**
		 * Metodo chamando quando ocorrem mudancas de texto na caixa de texto
		 * E deseleciona o item caso o a caixa de texto fique vazia
		 */ 
		private function escutaTexto(event:TextOperationEvent):void
		{
			if(this.text.length<=0) 
			{
				selecionarItem(null, false, true);
			}
		}
		
		/**
		 * Metodo utilizado para adicionar o evento de click na lista do dropdown
		 * Este metodo sera chamando pelo evendo de CREATION_COMPLETE da list
		 */ 
		private function adicionaEscutaMouseLista(event:Event):void
		{
			list.dataGroup.addEventListener(MouseEvent.CLICK, escutaMouseLista)
		}
		
		/**
		 * Metodo que escuta eventos de mouse na list (click) para selecionar um item
		 */ 
		private function escutaMouseLista(event:MouseEvent):void
		{
			selecionarItem(list.selectedItem, false, true);
			event.stopPropagation();
		}
		
		/**
		 * Metodo utilizado para verificar se ocorreu um evento de mouse fora da area do dropdown, e em caso positivo fecha o dropdown
		 */ 
		private function escutaMouseDropDown(event:Event):void
		{
			if(event is FlexMouseEvent)
			{
				var flexMouseEvent:FlexMouseEvent = event as FlexMouseEvent;
				if(this.hitTestPoint(flexMouseEvent.stageX, flexMouseEvent.stageY)) { return; }
			}
			popUp.displayPopUp = false;
		}
		
		/**
		 * Metodo que escuta os eventos de teclado na caixa de texto
		 */ 
		private function escutaTecladoInputText(event:KeyboardEvent):void
		{
			switch(event.keyCode)
			{
				case Keyboard.UP:
				case Keyboard.DOWN:
				case Keyboard.END:
				case Keyboard.HOME:
				case Keyboard.PAGE_UP:
				case Keyboard.PAGE_DOWN:
				{
					if(popUp.displayPopUp)
					{
						//Manten o cursor no final do texto e dispara o evento na lista para que a selecao mude
						this.selectRange(this.text.length, this.text.length);
						list.dispatchEvent(event)
					}
					else
					{
						if(itemSelecionado!=null) //Se o texto nao mudou desde o ultimo evento
						{
							selecionarItem(itemSelecionado, true, grupoComboLOD); //antes de abrir o dropdown seleciona ele
						}
						else if(list.dataProvider!=null && ArrayCollection(list.dataProvider).length>0) //Se contem elementos na lista apenas abre o dropdown
						{
							popUp.displayPopUp = true;
						}
					}
					break;
				}
				case Keyboard.TAB:
				case Keyboard.ESCAPE:
				{
					popUp.displayPopUp = false;
					break;
				}
				case Keyboard.ENTER:
				{
					escutaBotoes(null); //Mesmo efeito de apertar no botao
					break;
				}
			}
		}
		
		/**
		 * Metodo chamando com o resultado de uma pesquisa ou em caso de erros
		 */ 
		private function resultadoPesquisa(resultJava:ResultJava):void
		{
			list.dataProvider = resultJava.lista;
			
			if(mreFilterFunction != null)
			{
				mreDataProvider.filterFunction = mreFilterFunction;
				mreDataProvider.refresh();
			}
			
			mreHabilitado(true);
			imagem.source = lupa;
			selecionarItem(null, ArrayCollection(list.dataProvider).length>0, true);
		}
		
		/**
		 * Funcao de Label function utilizada para imprimir os elementos na lista e na caixa de texto
		 * A funcao considera o labelFunction e o labelField com profundidade, priorizando o labelField quando os dois sao indicados
		 * @param item a ser convertido em label
		 * @return o item e formato string
		 */ 
		private function labelFunctionItem(item:Object):String
		{
			if(mreLabelField!=null)
			{
				var objeto:Object = ObjetoUtil.getObject(item, mreLabelField);
				if(objeto!=null) 	{ return String(objeto); }
				else				{ return ""; }
			}
			else if(mreLabelFunction!=null)
			{
				return mreLabelFunction(item);
			}
			else
			{
				return String(item);
			}
		}
		
		/*-*-*-*-* Metodos de Utilizacao *-*-*-*-*/
		/**
		 * Metodo que seleciona um item no comboLOD
		 * @param object a ser selecionado no combo, caso ele seja nulo o combo sera deselecionado
		 * @param dispararEvento informe se o componente deve disparar o evendo de change (o evento sera disparado mesmo que nao haja alteracao na selecao do combo
		 */ 
		public function mreSetSelectedItem(object:Object, dispararEvento:Boolean=true):void
		{
			selecionarItem(object, false, dispararEvento);
			if(object==null)
			{
				this.text = "";
			}
		}
		
		/**
		 * Retorna o item selecionado no componente
		 * @return item selecionado ou null caso nao tenha item selecionado
		 */ 
		public function mreGetSelectedItem():*
		{
			return itemSelecionado;
		}
		
		/**
		 * Metodo que habilita ou desabilita o componente (enable)
		 * @param valor true para habilitar e false para desabilitar
		 */ 
		public function mreHabilitado(valor:Boolean):void
		{
			this.enabled = valor;
			imagem.enabled = valor;
			list.enabled = valor;
		}
		
		/**
		 * Metodo que valida o componente verificando se existe algum item selecionado atraves do selectedIndex
		 * Este metodo tambem altera a cor da borda caso nao tenha nenhum registro selecionado
		 * Caso o combo esteja desabilitado (enabled=false), a validação retornará verdadeiro.
		 * @return true caso um item esteja selecionado e false caso esteja sem nenhum selecionado
		 */ 
		public function mreValidar(validarTexto:Boolean=false):Boolean
		{
			if(enabled==false) { return true; }
			vaItemSelecionado.property = validarTexto ? "text" : "selectedItem";
			vaItemSelecionado.required = mreRequerido;
			vaItemSelecionado.requiredFieldError = mreMensagemValidacao;
			vaItemSelecionado.source = this;
			return vaItemSelecionado.validate().type!=ValidationResultEvent.INVALID;
		}
		
		/**
		 * Retorna o dataprovider do dropdownlist
		 * @return ArrayCollection com o dataprovider
		 */ 
		public function get mreDataProvider():ArrayCollection
		{
			return  ArrayCollection(list.dataProvider);
		}
		
		/**
		 * Metodo utilizado para definir o dataprovider do componente, utilizado pelo grupo
		 * @param dataProvider ArrayCollection com o dataprovider
		 */ 
		public function set mreDataProvider(dataProvider:ArrayCollection):void
		{
			list.dataProvider = dataProvider;
		}
		
		/**
		 * Metodo GET retorna o item selecionado no componente, utilizado pelo validator
		 * @return item selecionado ou null caso nao tenha item selecionado
		 */ 
		public function get selectedItem():*
		{
			return itemSelecionado;
		}
				
		/**
		 * Define os parametros dos services dos combos do grupo
		 * @param mreParametros
		 */ 
		public function set mreParametros(mreParametros:Array):void
		{
			this._mreParametros = mreParametros;
			textoUltimoEvento = null;
		}		
	}
}