package br.com.easyShop.model
{
	[RemoteClass(alias="br.com.easyShop.model.UsuarioTela")]
	public class UsuarioTela
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkUsuarioTela:Number;
		public var usuario:Usuario;
		public var tela:Tela;
		public var tipoPermissao:TipoPermissao;
	}
}