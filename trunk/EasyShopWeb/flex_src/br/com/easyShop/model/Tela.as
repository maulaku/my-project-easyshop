package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Tela")]
	public class Tela
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkTela:Number;
		public var nome:String;

		[ArrayElementType("br.com.easyShop.model.UsuarioTela")]
		private var _usuarioTelas:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set usuarioTelas(usuarioTelas:ArrayCollection):void	{ _usuarioTelas = usuarioTelas; }
		public function get usuarioTelas():ArrayCollection	{ if(_usuarioTelas==null) { _usuarioTelas = new ArrayCollection(); } return _usuarioTelas; }
	}
}