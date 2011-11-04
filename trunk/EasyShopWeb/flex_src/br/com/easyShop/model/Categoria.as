package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Categoria")]
	public class Categoria
	{
		public var pkCategoria:Number;
		public var nome:String;
		public var tipo:int;
		public var subCategoria:Categoria;
		[ArrayElementType("br.com.easyShop.model.Preferencia")]
		public var cpreferencias:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.Produto")]
		public var produtos:ArrayCollection = new ArrayCollection();
	}
}