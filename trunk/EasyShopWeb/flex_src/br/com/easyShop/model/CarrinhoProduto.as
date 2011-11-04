package br.com.easyShop.model
{
	[RemoteClass(alias="br.com.easyShop.model.CarrinhoProduto")]
	public class CarrinhoProduto
	{
		public var pkCarrinho:Number;
		public var quantidade:int;
		public var carrinho:Carrinho;
		public var produto:Produto;
	}
}