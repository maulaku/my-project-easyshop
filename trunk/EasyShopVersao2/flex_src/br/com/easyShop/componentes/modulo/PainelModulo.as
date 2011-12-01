package br.com.easyShop.componentes.modulo
{
	import spark.components.TileGroup;

	public class PainelModulo extends TileGroup
	{
		/*-*-*-* Construtores *-*-*-*/
		public function PainelModulo()
		{
			try
			{
				this.width = 640;
				this.height = 420;
				this.verticalGap = 45;
				this.horizontalGap = 7;
			}
			catch(e:Error) { throw e; } 
		}
		
		/*-*-*-* Metodos Publicos *-*-*-*/
		/**
		 * Limpa o painel de modulos
		 */ 
		public function limpar():void
		{
			try
			{
				this.removeAllElements();
			}
			catch(e:Error) { throw e; } 
		}
		
		/**
		 * Adiciona um Modulo no painel
		 * @param painelModuloItem
		 */ 
		public function addModulo(item:ModuloItem):void
		{
			try
			{
				this.addElement(item);
			}
			catch(e:Error) { throw e; } 
		}
	}
}