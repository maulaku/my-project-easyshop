package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Carrinho")]
	public class Carrinho
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkCarrinho:Number;
		public var cliente:Cliente;

		[ArrayElementType("br.com.easyShop.model.CarrinhoProduto")]
		private var _carrinhoProdutos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set carrinhoProdutos(carrinhoProdutos:ArrayCollection):void	{ _carrinhoProdutos = carrinhoProdutos; }
		public function get carrinhoProdutos():ArrayCollection	{ if(_carrinhoProdutos==null) { _carrinhoProdutos = new ArrayCollection(); } return _carrinhoProdutos; }
	}
}