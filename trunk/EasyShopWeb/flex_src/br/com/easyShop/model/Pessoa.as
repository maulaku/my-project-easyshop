package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Pessoa")]
	public class Pessoa
	{
		public var pkPessoa:Number;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var pessoaFisica:PessoaFisica;
		public var pessoaJuridica:PessoaJuridica;
		[ArrayElementType("br.com.easyShop.model.Usuario")]
		public var usuarios:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.Endereco")]
		public var enderecos:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.Cliente")]
		public var clientes:ArrayCollection = new ArrayCollection();
		[ArrayElementType("br.com.easyShop.model.Contato")]
		public var contatos:ArrayCollection = new ArrayCollection();
	}
}