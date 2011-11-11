package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Marca")]
	public class Marca
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkMarca:Number;
		public var nome:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;

		[ArrayElementType("br.com.easyShop.model.Produto")]
		private var _produtos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set produtos(produtos:ArrayCollection):void	{ _produtos = produtos; }
		public function get produtos():ArrayCollection	{ if(_produtos==null) { _produtos = new ArrayCollection(); } return _produtos; }
	}
}