package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Pais")]
	public class Pais
	{
		public var pkPais:Number;
		public var nome:String;
		[ArrayElementType("br.com.easyShop.model.Estado")]
		public var estados:ArrayCollection = new ArrayCollection();
	}
}