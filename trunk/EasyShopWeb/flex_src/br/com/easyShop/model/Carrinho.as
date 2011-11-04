package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Carrinho")]
	public class Carrinho
	{
		public var pkCarrinho:Number;
		public var cliente:Cliente;
		[ArrayElementType("br.com.easyShop.model.CarrinhoProduto")]
		public var carrinhoProdutos:ArrayCollection = new ArrayCollection();
	}
}