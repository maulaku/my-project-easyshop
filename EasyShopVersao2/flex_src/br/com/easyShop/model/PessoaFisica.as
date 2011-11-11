package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.PessoaFisica")]
	public class PessoaFisica
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkPessoaFisica:Number;
		public var nome:String;
		public var apelido:String;
		public var cpf:String;
		public var rg:String;
		public var dataNascimento:Date;
		public var sexo:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;

		[ArrayElementType("br.com.easyShop.model.Pessoa")]
		private var _pessoas:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set pessoas(pessoas:ArrayCollection):void	{ _pessoas = pessoas; }
		public function get pessoas():ArrayCollection	{ if(_pessoas==null) { _pessoas = new ArrayCollection(); } return _pessoas; }
	}
}