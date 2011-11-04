package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Produto")]
	public class Produto
	{
		public var pkProduto:Number;
		public var nome:String;
		public var codigo:String;
		public var valor:Number;
		public var quantidade:int;
		public var garantia:int;
		public var descricao:String;		
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var marca:Marca;
		public var categaria:Categoria;
		[ArrayElementType("br.com.easyShop.model.desejoProduto")]
		public var desejoProdutos:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.pedidoProduto")]
		public var pedidoProdutos:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.carrinhoProduto")]
		public var carrinhoProdutos:ArrayCollection = new ArrayCollection();
	}
}