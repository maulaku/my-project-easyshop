package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.PessoaFisica")]
	public class PessoaFisica
	{
		public var pkPessoaFisica:Number;
		public var nome:String;
		public var apelido:String;
		public var rg:String;
		public var cpf:String;
		public var dataNascimento:Date;
		public var sexo:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		[ArrayElementType("br.com.easyShop.model.pessoa")]
		public var pessoas:ArrayCollection = new ArrayCollection();
	}
}