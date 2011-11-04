package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;

	[RemoteClass(alias="br.com.easyShop.model.Preferencia")]
	public class Preferencia
	{
		public var pkPreferencia:Number;
		public var nome:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var cliente:Cliente;
		public var categoria:Categoria;
	}
}