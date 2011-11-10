package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;

	[RemoteClass(alias="br.com.easyShop.model.Contato")]
	public class Contato
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkContato:Number;
		public var contato:String;
		public var tipo:int;
		public var status:int = Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;
	}
}