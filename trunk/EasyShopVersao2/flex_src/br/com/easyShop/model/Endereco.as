package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Endereco")]
	public class Endereco
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkEndereco:Number;
		public var logradouro:String;
		public var numero:String;
		public var tipo:int;
		public var bairro:String;
		public var cep:String;
		public var complemento:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;
		public var cidade:Cidade;

		[ArrayElementType("br.com.easyShop.model.Pedido")]
		private var _pedidos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set pedidos(pedidos:ArrayCollection):void	{ _pedidos = pedidos; }
		public function get pedidos():ArrayCollection	{ if(_pedidos==null) { _pedidos = new ArrayCollection(); } return _pedidos; }
	}
}