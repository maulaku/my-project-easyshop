package br.com.easyShop.model
{
	import br.com.easyShop.utils.Constantes;

	[RemoteClass(alias="br.com.easyShop.model.DesejoProduto")]
	public class DesejoProduto
	{
		/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/
		public var pkDesejoProduto:Number;
		public var status:Number = Constantes.instance.STATUS_ATIVO;
		public var desejo:Desejo;
		public var produto:Produto;
	}
}