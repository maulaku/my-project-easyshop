package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Estado")]
	public class Estado
	{
		public var pkEstado:Number;
		public var nome:String;
		public var sigla:String;
		public var pais:Pais;
		[ArrayElementType("br.com.easyShop.model.Endereco")]
		public var enderecos:ArrayCollection = new ArrayCollection();
	}
}