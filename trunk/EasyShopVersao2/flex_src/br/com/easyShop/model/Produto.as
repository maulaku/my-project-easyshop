package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Produto")]
	public class Produto
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkProduto:Number;
		public var nome:String;
		public var codigo:String;
		public var preco:Number;
		public var quantidade:int;
		public var promocao:Boolean;
		public var garantia:Number;
		public var descricao:String;
		public var especificacoesTecnicas:String;
		public var caracteristicas:String;
		public var status:Number = Constantes.instance.STATUS_ATIVO;
		public var marca:Marca;
		public var categoria:Categoria;

		[ArrayElementType("br.com.easyShop.model.PedidoProduto")]
		private var _pedidoProdutos:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.DesejoProduto")]
		private var _desejoProdutos:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.CarrinhoProduto")]
		private var _carrinhoProdutos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set pedidoProdutos(pedidoProdutos:ArrayCollection):void	{ _pedidoProdutos = pedidoProdutos; }
		public function get pedidoProdutos():ArrayCollection	{ if(_pedidoProdutos==null) { _pedidoProdutos = new ArrayCollection(); } return _pedidoProdutos; }

		public function set desejoProdutos(desejoProdutos:ArrayCollection):void	{ _desejoProdutos = desejoProdutos; }
		public function get desejoProdutos():ArrayCollection	{ if(_desejoProdutos==null) { _desejoProdutos = new ArrayCollection(); } return _desejoProdutos; }

		public function set carrinhoProdutos(carrinhoProdutos:ArrayCollection):void	{ _carrinhoProdutos = carrinhoProdutos; }
		public function get carrinhoProdutos():ArrayCollection	{ if(_carrinhoProdutos==null) { _carrinhoProdutos = new ArrayCollection(); } return _carrinhoProdutos; }
	}
}