package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Cliente")]
	public class Cliente
	{
		public var pkCliente:Number;
		public var codigo:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;
		[ArrayElementType("br.com.easyShop.model.Pedido")]
		public var pedidos:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.Carrinho")]
		public var carrinhos:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.Preferencia")]
		public var preferencias:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.Desejo")]
		public var desejos:ArrayCollection = new ArrayCollection();
	}
}