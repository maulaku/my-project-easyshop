package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.TipoPermissao")]
	public class TipoPermissao
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkTipoPermissao:Number;
		public var nome:String;

		[ArrayElementType("br.com.easyShop.model.UsuarioTela")]
		private var _usuarioTelas:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set usuarioTelas(usuarioTelas:ArrayCollection):void	{ _usuarioTelas = usuarioTelas; }
		public function get usuarioTelas():ArrayCollection	{ if(_usuarioTelas==null) { _usuarioTelas = new ArrayCollection(); } return _usuarioTelas; }
	}
}