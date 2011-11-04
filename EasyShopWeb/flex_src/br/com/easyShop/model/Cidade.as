package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Cidade")]
	public class Cidade
	{
		public var pkCidade:Number;
		public var nome:String;
		public var estado:Estado;
		[ArrayElementType("br.com.easyShop.model.Endereco")]
		public var enderecos:ArrayCollection = new ArrayCollection();	
	}
}