package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Pedido")]
	public class Pedido
	{
		public var pkPedido:Number;
		public var total:Number;
		public var dataPedido:Date;
		public var dataEntrega:Date;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var endereco:Endereco;
		public var perfilPagamento:PerfilPagamento;
		public var cliente:Cliente;
		[ArrayElementType("br.com.easyShop.model.PedidoProduto")]
		public var pedidoProdutos:ArrayCollection = new ArrayCollection();
	}
}