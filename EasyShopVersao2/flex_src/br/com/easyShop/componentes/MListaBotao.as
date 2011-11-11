package br.com.easyShop.componentes
{
	import spark.components.TileGroup;

	public class MListaBotao extends TileGroup
	{
		public function MListaBotao()
		{
			this.width = 640;
			this.height = 420;
			this.verticalGap = 45;
			this.horizontalGap = 7;
		}
		
		public function addBotao(botao:ESBotaoAnimado)
		{
			this.addElement(botao);			
		}
	}
}