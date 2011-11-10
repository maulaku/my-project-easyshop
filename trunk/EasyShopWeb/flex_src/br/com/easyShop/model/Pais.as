package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Pais")]
	public class Pais
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkPais:Number;
		public var nome:String;

		[ArrayElementType("br.com.easyShop.model.Estado")]
		private var _estados:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set estados(estados:ArrayCollection):void	{ _estados = estados; }
		public function get estados():ArrayCollection	{ if(_estados==null) { _estados = new ArrayCollection(); } return _estados; }
	}
}