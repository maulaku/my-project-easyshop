package br.com.easyShop.componentes.navegacao
{
	import flash.events.MouseEvent;
	
	import mx.controls.SWFLoader;
	import mx.core.UIComponent;
	import mx.events.DragEvent;
	import mx.managers.DragManager;
	
	import spark.components.Group;
	import spark.components.HGroup;
	import spark.components.Scroller;

	/**
	 * Classe que implementa um painel de navegacao que contem as janelas abertas do sistema
	 */ 
	public class PainelNavegacaoSample extends Group
	{
		/*-*-*-* Constantes Publicas *-*-*-*/
		/**
		 * Numero de modulos visiveis ao mesmo tempo na barra de navegacao
		 */ 
		public static var MAX_MODULOS:int = 5;
		
		/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
		private var _btVoltar:SWFLoader = new SWFLoader();
		private var _btAvancar:SWFLoader = new SWFLoader();
		private var _btHome:SWFLoader = new SWFLoader();
		private var _fundoHome:SWFLoader = new SWFLoader();
		private var _fundoItens:SWFLoader = new SWFLoader();
		private var _scNavegacao:Scroller = new Scroller();
		private var _hgrNavegacaoItens:HGroup = new HGroup();
		private var _funcaoHome:Function = null;
		
		
		/*-*-*-* Construtores *-*-*-*/
		/**
		 * Inicializa os componentes e objetos
		 */ 
		public function PainelNavegacaoSample()
		{
			this.width=998;
			this.height=62;
			
			_fundoItens.left = 110;
			_fundoItens.verticalCenter = 0;
			_fundoItens.width=845;
			_fundoItens.height=60;
			_fundoItens.scaleContent = false;
			this.addElement(_fundoItens);
			
			_fundoHome.left = 45;
			_fundoHome.verticalCenter = 0;
			_fundoHome.width=60;
			_fundoHome.height=60;
			_fundoHome.scaleContent = false;
			this.addElement(_fundoHome);

			_scNavegacao.left = 122;
			_scNavegacao.verticalCenter = 0;
			_scNavegacao.width=820;
			_scNavegacao.height=48;
			_scNavegacao.setStyle("horizontalScrollPolicy", "off");
			_scNavegacao.setStyle("verticalScrollPolicy", "off");
			this.addElement(_scNavegacao);
			
			_hgrNavegacaoItens.width=820;
			_hgrNavegacaoItens.height=48;
			_hgrNavegacaoItens.gap=0;
			_hgrNavegacaoItens.verticalAlign="middle";
			_hgrNavegacaoItens.addEventListener(DragEvent.DRAG_ENTER, escutaDrag);
			_hgrNavegacaoItens.addEventListener(DragEvent.DRAG_OVER, escutaDrag);
			_hgrNavegacaoItens.addEventListener(DragEvent.DRAG_DROP, escutaDrag);
			_scNavegacao.viewport = _hgrNavegacaoItens;
			
			_btVoltar.left = 2;
			_btVoltar.verticalCenter = 0;
			_btVoltar.addEventListener(MouseEvent.CLICK, escutaBotoes);
			this.addElement(_btVoltar);
			
			_btAvancar.right = 2;
			_btAvancar.verticalCenter = 0;
			_btAvancar.addEventListener(MouseEvent.CLICK, escutaBotoes);
			this.addElement(_btAvancar);
			
			_btHome.left = 55;
			_btHome.verticalCenter = 0;
			_btHome.toolTip = "Exibe a area de modulos";
			_btHome.setStyle("verticalAlign", "middle");
			_btHome.setStyle("horizontalAlign", "center");
			_btHome.setStyle("scaleContent", "false");
			_btHome.addEventListener(MouseEvent.CLICK, escutaBotoes);
			this.addElement(_btHome);

			botoesNavegacao(false);
		}
		
		/*-*-*-* Metodos Publicos *-*-*-*/
		/**
		 * Limpa os itens da barra de navegacao
		 */ 
		public function limpar():void
		{
			_hgrNavegacaoItens.removeAllElements();
			recalculaIndiceModulos();
		}
		
		/**
		 * Define os botoes de navegacao
		 * @param valor booleano para ativo ou inativo
		 */ 
		public function botoesNavegacao(valor:Boolean):void 
		{
			_btVoltar.visible = valor;
			_btAvancar.visible = valor;
		}
		
		/**
		 * Adiciona um modulo na barra de navegacao
		 * @param navegacaoItem
		 */ 
		public function addModulo(navegacaoItem:NavegacaoItem):void
		{
			_hgrNavegacaoItens.addElement(navegacaoItem);
			navegacaoItem.lbNumeroModulo.text = String(_hgrNavegacaoItens.numChildren);
			botoesNavegacao(_hgrNavegacaoItens.numChildren>MAX_MODULOS);	
			callLater(avancarBarra);
		}
		
		/**
		 * Remove um item da barra de navegacao
		 * @param navegacaoItem a ser removido
		 */ 
		public function removerModulo(navegacaoItem:NavegacaoItem):void
		{
			if(navegacaoItem!=null)
			{
				_hgrNavegacaoItens.removeElement(navegacaoItem);
				recalculaIndiceModulos();
			}
		}
		
		/**
		 * Seleciona um modulo referente ao valor passado como parametro 
		 * na barra de navegacao, e carrega
		 * @param index do modulo
		 */ 
		public function selecionaModulo(index:int):void
		{
			if(index!=-1 && _hgrNavegacaoItens.numElements>index)
			{
				(_hgrNavegacaoItens.getElementAt(index) as NavegacaoItem).selecionar(true, true);
			}
		}
		
		/**
		 * Recalcula o indice de cada modulo no painel de navegacao 
		 */ 
		public function recalculaIndiceModulos():void
		{
			var tamanho:int = _hgrNavegacaoItens.numChildren;
			botoesNavegacao(tamanho>MAX_MODULOS);
			for(var i:int=0; i<tamanho; i++)
			{
				(_hgrNavegacaoItens.getElementAt(i) as NavegacaoItem).lbNumeroModulo.text = String(i + 1);
			}
		}
		
		/**
		 * Percorre os itens (Modulos) que estao na barra de navegacao para o lado direito 
		 */
		public function avancarBarra():void
		{
			_scNavegacao.viewport.horizontalScrollPosition += 164;
		}
		
		/**
		 * Percorre os itens (Modulos) que estao na barra de navegacao para o lado esquerdo
		 */
		public function retornarBarra():void
		{
			_scNavegacao.viewport.horizontalScrollPosition -= 164;
		}
		
		
		/*-*-*-* Listeners *-*-*-*/
		/**
		 * Metodo responsavel por escutar eventos ocorridos em botoes
		 * @param botao que recebeu o evento
		 */
		public function escutaBotoes(event:MouseEvent):void
		{
			if(event.currentTarget==_btHome)
			{
				if(_funcaoHome!=null) { _funcaoHome(); }
			}
			else if(event.currentTarget==_btVoltar)
			{
				retornarBarra();
			}
			else if(event.currentTarget==_btAvancar)
			{
				avancarBarra();	
			}
		}
		
		/**
		 * Escuta eventos de mouse sobre o componente que contem os modulos
		 * @param event com as informacoes do evento
		 */ 
		public function escutaDrag(event:DragEvent):void
		{
			var index:int=0;
			
			if(event.type==DragEvent.DRAG_ENTER)
			{
				DragManager.acceptDragDrop(event.currentTarget as UIComponent);
			}
			else if(event.type==DragEvent.DRAG_OVER)
			{
				index = (event.localX/event.dragInitiator.width);
				
				var tamanho:int = (event.currentTarget as HGroup).numChildren;
				for(var i:int=0; i<tamanho; i++)
				{
					((event.currentTarget as HGroup).getElementAt(i) as NavegacaoItem).selecionarDrag(i==index);
				}
			}
			else if(event.type==DragEvent.DRAG_DROP)
			{
				index = (event.localX/event.dragInitiator.width);
				
				if(index<(event.currentTarget as HGroup).numChildren)
				{
					((event.currentTarget as HGroup).getElementAt(index) as NavegacaoItem).selecionarDrag(false);
					(event.currentTarget as HGroup).addElementAt(event.dragInitiator as UIComponent, index);
					recalculaIndiceModulos();	
				}
			}
		}
		
		/*-*-*-* Metodos Gets e Sets *-*-*-*/
		public function get btVoltar():SWFLoader { return _btVoltar; }
		public function set btVoltar(btVoltar:SWFLoader):void { _btVoltar=btVoltar; }
		
		public function get btAvancar():SWFLoader { return _btAvancar; }
		public function set btAvancar(btAvancar:SWFLoader):void { _btAvancar=btAvancar; }
		
		public function get btHome():SWFLoader { return _btHome; }
		public function set btHome(btHome:SWFLoader):void { _btHome=btHome; }
		
		public function get funcaoHome():Function { return _funcaoHome; }
		public function set funcaoHome(funcaoHome:Function):void { _funcaoHome=funcaoHome; }
		
		public function get fundoHome():SWFLoader { return _fundoHome; }
		public function set fundoHome(fundoHome:SWFLoader):void { _fundoHome=fundoHome; }
		
		public function get fundoItens():SWFLoader { return _fundoItens; }
		public function set fundoItens(fundoItens:SWFLoader):void { _fundoItens=fundoItens; }
	}
}