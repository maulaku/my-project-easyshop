package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Estado")]
	public class Estado
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkEstado:Number;
		public var sigla:String;
		public var nome:String;
		public var pais:Pais;

		[ArrayElementType("br.com.easyShop.model.Cidade")]
		private var _cidades:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set cidades(cidades:ArrayCollection):void	{ _cidades = cidades; }
		public function get cidades():ArrayCollection	{ if(_cidades==null) { _cidades = new ArrayCollection(); } return _cidades; }
	}
}