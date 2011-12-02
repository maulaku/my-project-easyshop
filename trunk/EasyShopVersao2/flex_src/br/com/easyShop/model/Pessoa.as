package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Pessoa")]
	public class Pessoa
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkPessoa:Number;
		public var foto:String;
		public var status:int = Constantes.instance.STATUS_ATIVO;
		public var pessoaJuridica:PessoaJuridica;
		public var pessoaFisica:PessoaFisica;

		[ArrayElementType("br.com.easyShop.model.Contato")]
		private var _contatos:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Endereco")]
		private var _enderecos:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Usuario")]
		private var _usuarios:ArrayCollection;

		[ArrayElementType("br.com.easyShop.model.Cliente")]
		private var _clientes:ArrayCollection;


		/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/
		public function set contatos(contatos:ArrayCollection):void	{ _contatos = contatos; }
		public function get contatos():ArrayCollection	{ if(_contatos==null) { _contatos = new ArrayCollection(); } return _contatos; }

		public function set enderecos(enderecos:ArrayCollection):void	{ _enderecos = enderecos; }
		public function get enderecos():ArrayCollection	{ if(_enderecos==null) { _enderecos = new ArrayCollection(); } return _enderecos; }

		public function set usuarios(usuarios:ArrayCollection):void	{ _usuarios = usuarios; }
		public function get usuarios():ArrayCollection	{ if(_usuarios==null) { _usuarios = new ArrayCollection(); } return _usuarios; }

		public function set clientes(clientes:ArrayCollection):void	{ _clientes = clientes; }
		public function get clientes():ArrayCollection	{ if(_clientes==null) { _clientes = new ArrayCollection(); } return _clientes; }
	}
}