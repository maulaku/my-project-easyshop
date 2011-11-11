package br.com.easyShop.componentes.comboLOD
{
	/**
	 * Classe que implmenta um mecanismo de grupo para combos sendo que todos
	 * sao regidos pelo mesmo dataprovider e são sempre alterados simultaneamente
	 */ 
	public class MGrupoComboLOD
	{
		/*-*-*-*-* Variaveis e Objetos Privados *-*-*-*-*/	
		private var _mreCombos:Array = new Array();
		public var mreChange:Function=null;
		public var mreMensagemValidacao:String = "Selecione um Registro";
		public var mreRequerido:Boolean=false;
		private var _mreFilterFunction:Function = null;
		
		/*-*-*-*-* Contrutores *-*-*-*-*/	
		public function MGrupoComboLOD() { }
		
		
		/*-*-*-*-* Metodos Publicos *-*-*-*-*/
		/**
		 * Metodo utilizado para definir os combos que compem o grupo
		 */ 		
		public function set mreCombos(combos:Array):void
		{
			_mreCombos = combos!=null ? combos : new Array();
			var comboLOD:MComboLOD;
			for(var i:int=0; i<_mreCombos.length; i++)
			{
				comboLOD = (_mreCombos[i] as MComboLOD);
				comboLOD.mreChange = escutaCombos;
				comboLOD.mreRequerido = mreRequerido;
				comboLOD.mreMensagemValidacao = mreMensagemValidacao;
				comboLOD.grupoComboLOD = true;
				comboLOD.mreFilterFunction = _mreFilterFunction;
			}
		}
		
		/**
		 * Habilita ou desabilita os componentes do grupo
		 * @param valor true para habilitar e false para desabilitar
		 */ 
		public function mreHabilitado(valor:Boolean):void
		{
			for(var i:int=0; i<_mreCombos.length; i++)
			{
				(_mreCombos[i] as MComboLOD).mreHabilitado(valor);
			}
		}
		
		/**
		 * Metodo que valida o componente verificando se existe algum item selecionado atraves do selectedIndex
		 * Este metodo tambem altera a cor da borda caso nao tenha nenhum registro selecionado
		 * Caso o combo esteja desabilitado (enabled=false), a validação retornará verdadeiro.
		 * @return true caso um item esteja selecionado e false caso esteja sem nenhum selecionado
		 */ 
		public function mreValidar():Boolean
		{
			var resultado:Boolean=true;
			for(var i:int=0; i<_mreCombos.length; i++)
			{
				resultado = resultado && (_mreCombos[i] as MComboLOD).mreValidar(); //Se um deles for false, fica false para sempre
			}
			return resultado;
		}
				
		/*-*-*-*-* Listeners *-*-*-*-*/
		/**
		 * Recebe eventos de mudanca de selecao em combos, seleciona o item nos outros componentes do grupo
		 * @param evendo com as informacoes
		 */ 
		private function escutaCombos(combo:MComboLOD):void
		{
			for(var i:int=0; i<_mreCombos.length; i++)
			{
				if(combo!=_mreCombos[i])
				{
					(_mreCombos[i] as MComboLOD).mreDataProvider = combo.mreDataProvider;
					(_mreCombos[i] as MComboLOD).mreSetSelectedItem(combo.mreGetSelectedItem(), false);
					if(combo.mreGetSelectedItem()==null)
					{
						(_mreCombos[i] as MComboLOD).text = "";
					}
					if(mreRequerido) { mreValidar(); }
				}
			}
			
			if(mreChange!=null)
			{
				mreChange(this);
			}
		}
		
		
		/**
		 * Metodo que seleciona um item no comboLOD
		 * @param object a ser selecionado no combo, caso ele seja nulo o combo sera deselecionado
		 */ 
		public function mreSetSelectedItem(object:Object):void
		{
			if(_mreCombos!=null && _mreCombos.length>0)
			{
				(_mreCombos[0] as MComboLOD).mreSetSelectedItem(object, true);
				if(object==null)
				{
					(_mreCombos[0] as MComboLOD).text = "";
				}
			}
		}
		
		/**
		 * Retorna o item selecionado no componente
		 * @return item selecionado ou null caso nao tenha item selecionado
		 */ 
		public function mreGetSelectedItem():*
		{
			if(_mreCombos!=null && _mreCombos.length>0)
			{
				return (_mreCombos[0] as MComboLOD).mreGetSelectedItem();
			}
			return null;
		}
	
		/**
		 * Define o filterFunction dos dataProviders dos combos do grupo
		 * @param mreFilterFunction
		 */ 
		public function set mreFilterFunction(mreFilterFunction:Function):void
		{
			this._mreFilterFunction = mreFilterFunction;
					
			for(var i:int=0; i<_mreCombos.length; i++)
			{
		  		(_mreCombos[i] as MComboLOD).mreFilterFunction = mreFilterFunction;
			}
		}
		
		/**
		 * Define os parametros dos services dos combos do grupo
		 * @param mreParametros
		 */ 
		public function set mreParametros(mreParametros:Array):void
		{
			for(var i:int=0; i<_mreCombos.length; i++)
			{
				(_mreCombos[i] as MComboLOD).mreParametros = mreParametros;
			}
		}
	}
	
}