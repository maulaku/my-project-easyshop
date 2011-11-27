package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;

	[RemoteClass(alias="br.com.easyShop.model.CarrinhoProduto")]
	public class CarrinhoProduto
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkCarrinhoProduto:Number;
		public var quantidade:Number;
		public var status:Number = Constantes.instance.STATUS_ATIVO;
		public var carrinho:Carrinho;
		public var produto:Produto;
	}
}