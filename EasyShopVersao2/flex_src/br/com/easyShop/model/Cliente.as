package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Cliente")]
	public class Cliente
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkCliente:Number;
		public var codigo:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;

		[ArrayElementType("br.com.easyShop.model.Preferencia")]
		private var _preferencias:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Desejo")]
		private var _desejos:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Carrinho")]
		private var _carrinhos:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Pedido")]
		private var _pedidos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set preferencias(preferencias:ArrayCollection):void	{ _preferencias = preferencias; }
		public function get preferencias():ArrayCollection	{ if(_preferencias==null) { _preferencias = new ArrayCollection(); } return _preferencias; }

		public function set desejos(desejos:ArrayCollection):void	{ _desejos = desejos; }
		public function get desejos():ArrayCollection	{ if(_desejos==null) { _desejos = new ArrayCollection(); } return _desejos; }

		public function set carrinhos(carrinhos:ArrayCollection):void	{ _carrinhos = carrinhos; }
		public function get carrinhos():ArrayCollection	{ if(_carrinhos==null) { _carrinhos = new ArrayCollection(); } return _carrinhos; }

		public function set pedidos(pedidos:ArrayCollection):void	{ _pedidos = pedidos; }
		public function get pedidos():ArrayCollection	{ if(_pedidos==null) { _pedidos = new ArrayCollection(); } return _pedidos; }
	}
}