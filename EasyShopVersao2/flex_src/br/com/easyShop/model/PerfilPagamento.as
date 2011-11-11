package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.PerfilPagamento")]
	public class PerfilPagamento
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkPerfilPagamento:Number;
		public var nome:String;
		public var descricao:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;

		[ArrayElementType("br.com.easyShop.model.Pedido")]
		private var _pedidos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set pedidos(pedidos:ArrayCollection):void	{ _pedidos = pedidos; }
		public function get pedidos():ArrayCollection	{ if(_pedidos==null) { _pedidos = new ArrayCollection(); } return _pedidos; }
	}
}