package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Endereco")]
	public class Endereco
	{
		public var pkEndereco:Number;
		public var logradouro:String;
		public var numero:String;
		public var tipo:int;
		public var bairro:String;
		public var cep:String;
		public var complemento:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;
		public var cidade:Cidade;
		[ArrayElementType("br.com.easyShop.model.Pedido")]
		public var pedidos:ArrayCollection = new ArrayCollection();
	}
}