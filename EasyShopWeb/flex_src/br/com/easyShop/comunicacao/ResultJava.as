package br.com.easyShop.comunicacao
{
	/*-*-*-*-* Imports *-*-*-*-*/
	import mx.collections.ArrayCollection;

	/**
	 * Objteto utilizado para receber a resposta do java
	 */ 
	[RemoteClass(alias="br.com.easyShop.comunicacao.ResultJava")]
	public class ResultJava
	{
		/*-*-*-*-* Variaveis e Objetos Publicas *-*-*-*-*/
		/**
		 * Quando a resposta do java e apenas um objeto
		 */ 
		public var item:Object;
		
		/**
		 * Quando a resposta do java sao mais de um objeto
		 */ 
		public var lista:ArrayCollection = new ArrayCollection();
		
		/**
		 * Indica se o resultJava deve ser cacheado
		 */ 
		public var cache:Boolean = false;
		
		
		/**
		 * Construtor
		 * @param item
		 * @para lista
		 */ 
		public function ResultJava(item:Object=null, lista:ArrayCollection=null)
		{
			this.item = item;
			if(lista!=null) { this.lista = lista; }
		}
	}
}