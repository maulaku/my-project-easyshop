package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Tela")]
	public class Tela
	{
		public var pkTela:Number;
		public var nome:String;
		[ArrayElementType("br.com.easyShop.model.UsuarioTela")]
		public var permissoes:ArrayCollection = new ArrayCollection();
	}
}