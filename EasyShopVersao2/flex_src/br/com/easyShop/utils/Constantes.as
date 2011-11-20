package br.com.easyShop.utils
{
	public class Constantes
	{
		public static const instance:Constantes = new Constantes();
		
		//STATUS NO BANCO
		public var STATUS_ATIVO:int=0;
		public var STATUS_REMOVIDO:int=999;
		
		//TIPOS DE PERMISS�O
		public var TIPO_PERMISSAO_LEITURA:int=2220;
		public var TIPO_PERMISSAO_LEITURA_ESCRITA:int=2221;
		
		//TIPOS DE CONTATOS
		public var TIPO_CONTATO_EMAIL:int=4020;
		public var TIPO_CONTATO_TELEFONE:int=4021;
		public var TIPO_CONTATO_CELULAR:int=4022;
		public var TIPO_CONTATO_FAX:int=4023;
		
		//CATEGORIA PAI
		public var CATEGORIA_PAI:int=4300;
		
		//TIPO DE ENDERE�OS
		public var TIPO_RESIDENCIA:int=3230;
		public var TIPO_COMERCIAL:int=3231;
		public var TIPO_APARTAMENTO:int=3232;
		
		//TIPO DE ENDERE�OS
		public var RESIDENCIA:String="Residência";
		public var COMERCIAL:String="Comercial";
		public var APARTAMENTO:String="Apartamento";
		
		//TIPOS DE CONTATOS
		public var CONTATO_EMAIL:String="Email";
		public var CONTATO_TELEFONE:String="Telefone";
		public var CONTATO_FAX:String="Fax";
		public var CONTATO_CELULAR:String="Celular";
	}
}