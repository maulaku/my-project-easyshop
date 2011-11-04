package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.TipoPermissao")]
	public class TipoPermissao
	{
		public var pkTipoPermissao:Number;
		public var nome:String;
		[ArrayElementType("br.com.easyShop.model.UsuarioTela")]
		public var permissoes:ArrayCollection = new ArrayCollection();
	}
}