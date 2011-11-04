package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.PerfilPagamento")]
	public class PerfilPagamento
	{
		public var pkPerfilPagamento:Number;
		public var nome:String;
		public var descricao:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		[ArrayElementType("br.com.easyShop.model.Pedido")]
		public var pedidos:ArrayCollection = new ArrayCollection();
	}
}