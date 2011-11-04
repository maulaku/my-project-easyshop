package br.com.easyShop.model
{
	[RemoteClass(alias="br.com.easyShop.model.UsuarioTela")]
	public class UsuarioTela
	{
		public var pkUsuarioTela:Number;
		public var tipoPermissao:TipoPermissao;
		public var usuario:Usuario;
		public var tela:Tela;
	}
}