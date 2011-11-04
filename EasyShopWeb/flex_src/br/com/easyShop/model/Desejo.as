package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;
	
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="br.com.easyShop.model.Desejo")]
	public class Desejo
	{
		public var pkDesejo:Number;
		public var nome:String;
		public var status:int=Constantes.instance.STATUS_ATIVO;
		public var pessoa:Pessoa;
		[ArrayElementType("br.com.easyShop.model.DesejoProduto")]
		public var desejoProdutos:ArrayCollection = new ArrayCollection();
	}
}