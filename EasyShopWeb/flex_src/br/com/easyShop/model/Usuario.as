package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Usuario")]
	public class Usuario
	{
		public var pkUsuario:Number;
		public var login:String;
		public var senha:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;
		[ArrayElementType("br.com.easyShop.model.UsuarioTela")]
		public var permissoes:ArrayCollection = new ArrayCollection();
	}
}