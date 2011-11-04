package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.PessoaJuridica")]
	public class PessoaJuridica
	{
		public var pkPessoaJuridica:Number;
		public var razaoSocial:String;
		public var nomeFantasia:String;
		public var cnpj:String;
		public var inscricaoEstadual:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		[ArrayElementType("br.com.easyShop.model.Pessoa")]
		public var pessoas:ArrayCollection = new ArrayCollection();
	}
}