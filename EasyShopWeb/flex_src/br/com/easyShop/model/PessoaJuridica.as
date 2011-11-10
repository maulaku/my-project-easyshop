package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.PessoaJuridica")]
	public class PessoaJuridica
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkPessoaJuridica:Number;
		public var razaoSocial:String;
		public var nomeFantasia:String;
		public var cnpj:String;
		public var inscricaoEstadual:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;

		[ArrayElementType("br.com.easyShop.model.Pessoa")]
		private var _pessoas:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set pessoas(pessoas:ArrayCollection):void	{ _pessoas = pessoas; }
		public function get pessoas():ArrayCollection	{ if(_pessoas==null) { _pessoas = new ArrayCollection(); } return _pessoas; }
	}
}