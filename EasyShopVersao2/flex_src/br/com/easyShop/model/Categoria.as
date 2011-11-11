package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Categoria")]
	public class Categoria
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkCategoria:Number;
		public var nome:String;
		public var imagem:String;
		public var tipo:Number;
		public var subCategoria:Categoria;

		[ArrayElementType("br.com.easyShop.model.Preferencia")]
		private var _preferencias:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Categoria")]
		private var _categorias:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Produto")]
		private var _produtos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set preferencias(preferencias:ArrayCollection):void	{ _preferencias = preferencias; }
		public function get preferencias():ArrayCollection	{ if(_preferencias==null) { _preferencias = new ArrayCollection(); } return _preferencias; }

		public function set categorias(categorias:ArrayCollection):void	{ _categorias = categorias; }
		public function get categorias():ArrayCollection	{ if(_categorias==null) { _categorias = new ArrayCollection(); } return _categorias; }

		public function set produtos(produtos:ArrayCollection):void	{ _produtos = produtos; }
		public function get produtos():ArrayCollection	{ if(_produtos==null) { _produtos = new ArrayCollection(); } return _produtos; }
	}
}