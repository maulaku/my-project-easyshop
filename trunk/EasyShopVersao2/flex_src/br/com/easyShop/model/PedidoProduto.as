package br.com.easyShop.model
{
	[RemoteClass(alias="br.com.easyShop.model.PedidoProduto")]
	public class PedidoProduto
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkPedidoProduto:Number;
		public var quantidade:int;
		public var pedido:Pedido;
		public var produto:Produto;
	}
}