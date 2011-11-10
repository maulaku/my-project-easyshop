package br.com.easyShop.model
{
	[RemoteClass(alias="br.com.easyShop.model.CarrinhoProduto")]
	public class CarrinhoProduto
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkCarrinhoProduto:Number;
		public var quantidade:Number;
		public var carrinho:Carrinho;
		public var produto:Produto;
	}
}