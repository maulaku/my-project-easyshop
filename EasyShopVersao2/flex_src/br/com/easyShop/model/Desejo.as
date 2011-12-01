package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Desejo")]
	public class Desejo
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkDesejo:Number;
		public var status:int = Constantes.instance.STATUS_ATIVO;
		public var cliente:Cliente;

		[ArrayElementType("br.com.easyShop.model.DesejoProduto")]
		private var _desejoProdutos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set desejoProdutos(desejoProdutos:ArrayCollection):void	{ _desejoProdutos = desejoProdutos; }
		public function get desejoProdutos():ArrayCollection	{ if(_desejoProdutos==null) { _desejoProdutos = new ArrayCollection(); } return _desejoProdutos; }
	}
}