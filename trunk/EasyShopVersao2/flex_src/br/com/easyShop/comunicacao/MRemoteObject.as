package br.com.easyShop.comunicacao
{
	/*-*-*-*-* Imports *-*-*-*-*/
	import br.com.mresolucoes.componentes.mre.Alerta;
	import br.com.mresolucoes.imagens.ImagensUtils;
	
	import flash.utils.Dictionary;
	
	import mx.events.CloseEvent;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	/**
	 * Classe que representa um componente que tem como funcao 
	 * comunicar o flex com o java.
	 */
	public dynamic class MRemoteObject extends RemoteObject
	{
		/*-*-*-*-* Variaveis e Objetos Publicos *-*-*-*-*/
		public static var DESTINATION:String = "DJava";
		public static var MENSAGEM_ERRO:String = "Falha acessando o servidor\n";
		
		/*-*-*-*-* Variaveis e Objetos Privados *-*-*-*-*/
		private static var cacheResultJava:Dictionary = new Dictionary();
		private static var _usuario:Object = null;
		private var funcoesRetorno:Array = null;
		private var funcaoErro:Function = null;
		private var pacoteClasseMetodo:String = null;
						
		/*-*-*-*-* Construtores *-*-*-*-*/
		public function MRemoteObject()
		{
			super(DESTINATION);
		}
		
		/*-*-*-*-* Metodos Publicos *-*-*-*-*/
		/**
		 * Método responsável por fazer a comunicacao com o java. Metodo Estatico
		 * @param pacoteClasseMetodo - string que contem o endereco de onde se encontra o metodo que se deseja chamar
		 * @param parametros - vetor com os parametros do metodo que se deseja chamar
		 * @param result - funcao que devera ser chamada caso o metodo em java for retornar uma resposta ao flex
		 * @param funcaoErro - funcao que sera chamanda em caso de erros de comunicacao
		 */
		public static function get(pacoteClasseMetodo:String, parametros:Array=null, result:Function=null, usuario:Object=null, funcaoErro:Function=null):void
		{
			try
			{
				new MRemoteObject().get(pacoteClasseMetodo, parametros, [result], usuario, funcaoErro);
			}
			catch(e:Error)
			{  }
		}
		
		/**
		 * Método responsável por fazer a comunicacao com o java. Metodo Estatico
		 * @param pacoteClasseMetodo - string que contem o endereco de onde se encontra o metodo que se deseja chamar
		 * @param parametros - vetor com os parametros do metodo que se deseja chamar
		 * @param result - array de funcoes que devera ser chamada caso o metodo em java for retornar uma resposta ao flex
		 * @param funcaoErro - funcao que sera chamanda em caso de erros de comunicacao
		 */
		public static function gets(pacoteClasseMetodo:String, parametros:Array=null, result:Array=null, usuario:Object=null, funcaoErro:Function=null):void
		{
			try
			{
				new MRemoteObject().get(pacoteClasseMetodo, parametros, result, usuario, funcaoErro);
			}
			catch(e:Error)
			{  }
		}
		
		/**
		 * Método responsável por fazer a comunicacao com o java. Este metodo nao é estatico, sendo utilizado quando a instanciacao do MRemoteObject for realizada pelo usuario
		 * @param pacoteClasseMetodo - string que contem o endereco de onde se encontra o metodo que se deseja chamar
		 * @param parametros - vetor com os parametros do metodo que se deseja chamar
		 * @param result - funcao que devera ser chamada caso o metodo em java for retornar uma resposta ao flex
		 * @param funcaoErro - funcao que sera chamanda em caso de erros de comunicacao
		 */
		public function get(pacoteClasseMetodo:String, parametros:Array=null, result:Array=null, usuario:Object=null, funcaoErro:Function=null):void
		{
			try
			{
				this.pacoteClasseMetodo = pacoteClasseMetodo;
				this.funcoesRetorno = result;
				this.funcaoErro = funcaoErro;
				
				if(result!=null) { this.addEventListener(ResultEvent.RESULT, this.resultado); }
				this.addEventListener(FaultEvent.FAULT, this.erro);
				
				//Caso nao tenha sido definido o usuario, define com o usuario default
				if(usuario==null) { usuario = _usuario; }
				
				if(parametros!=null)	{ this.getJava(pacoteClasseMetodo, parametros, usuario); }
				else					{ this.getJava(pacoteClasseMetodo, usuario); }
			}
			catch(e:Error)
			{
				
			}
		}
		
		/**
		 * Método chamado quando a comunicao com o java ja foi concretizada e um 
		 * resultado de ser retornado. Este metodo avalia tambem a situacao do cache e verifica se o conteudo do cache deve ser retornado, atualizado ou desconsiderado
		 * @param event - contem o resultado esperado do metodo chamado no java
		 */
		public function resultado(event:ResultEvent):void
		{
			try
			{
				if(event!=null && event.message!=null && event.message.body!=null)
				{
					var resultJava:ResultJava = event.message.body as ResultJava;
					
										
					if(resultJava.cache)
					{
						if(resultJava.item==null && resultJava.lista.length<=0)
						{
							var objeto:Object = cacheResultJava[pacoteClasseMetodo];
							if(objeto!=null)
							{
								chamarFuncoesRetorno(objeto as ResultJava);
							}
							else
							{
								chamarFuncoesRetorno(resultJava); //Nao recomendado a utilizacao de Results Vazios Cacheados
							}
						}
						else
						{
							cacheResultJava[pacoteClasseMetodo] = resultJava;
							chamarFuncoesRetorno(resultJava);
						}
					}
					else
					{
						chamarFuncoesRetorno(resultJava);
					}
				}
				else
				{
					chamarFuncoesRetorno();
				}
			}
			catch(e:Error)
			{ }
		}
		
		/**
		 * Metodo utilizado para responder para N funcoes a chamada ao java
		 * @param resultjava
		 */ 
		public function chamarFuncoesRetorno(resultJava:ResultJava=null):void
		{
			try
			{
				if(funcoesRetorno!=null && funcoesRetorno.length>0)
				{
					for(var i:int=0; i<funcoesRetorno.length; i++)
					{
						try 			{ funcoesRetorno[i](resultJava); }
						catch(e:Error) 	{ }
					}
				}
			}
			catch(e:Error)
			{  }
		}
		
		/**
		 * Método chamado quando ocorre alguma falha na comunicao com o java
		 * @param event - contem a descricao da falha que ocorreu na comunicacao com o java
		 */
		public function erro(event:FaultEvent):void
		{
			if(funcaoErro!=null)
			{
				funcaoErro(new ResultJava(event));
			}
			else
			{ Alerta.abrir(MENSAGEM_ERRO + event.fault, "Falha", ['OK'], null, null, ImagensUtils.ERRO); }
		}
			
		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public static function get usuario():Object { return _usuario; }
		public static function set usuario(value:Object):void { _usuario = value; }
	}
}