package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Pedido")]
	public class Pedido
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkPedido:Number;
		public var total:Number;
		public var dataPedido:Date;
		public var dataEntrega:Date;
		public var status:Number = Constantes.instance.STATUS_ATIVO;
		public var perfilPagamento:PerfilPagamento;
		public var cliente:Cliente;
		public var endereco:Endereco;

		[ArrayElementType("br.com.easyShop.model.PedidoProduto")]
		private var _pedidoProdutos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set pedidoProdutos(pedidoProdutos:ArrayCollection):void	{ _pedidoProdutos = pedidoProdutos; }
		public function get pedidoProdutos():ArrayCollection	{ if(_pedidoProdutos==null) { _pedidoProdutos = new ArrayCollection(); } return _pedidoProdutos; }
	}
}