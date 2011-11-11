package br.com.easyShop.model
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Cidade")]
	public class Cidade
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkCidade:Number;
		public var nome:String;
		public var estado:Estado;

		[ArrayElementType("br.com.easyShop.model.Endereco")]
		private var _enderecos:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set enderecos(enderecos:ArrayCollection):void	{ _enderecos = enderecos; }
		public function get enderecos():ArrayCollection	{ if(_enderecos==null) { _enderecos = new ArrayCollection(); } return _enderecos; }
	}
}