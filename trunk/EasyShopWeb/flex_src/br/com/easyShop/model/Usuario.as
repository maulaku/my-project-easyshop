package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Usuario")]
	public class Usuario
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkUsuario:Number;
		public var login:String;
		public var senha:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;

		[ArrayElementType("br.com.easyShop.model.UsuarioTela")]
		private var _usuarioTelas:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set usuarioTelas(usuarioTelas:ArrayCollection):void	{ _usuarioTelas = usuarioTelas; }
		public function get usuarioTelas():ArrayCollection	{ if(_usuarioTelas==null) { _usuarioTelas = new ArrayCollection(); } return _usuarioTelas; }
	}
}