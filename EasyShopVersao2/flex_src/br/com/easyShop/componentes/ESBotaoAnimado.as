package br.com.easyShop.componentes
{
	import br.com.easyShop.model.Categoria;
	import br.com.mresolucoes.componentes.mre.MBotao;
	
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import mx.effects.Resize;

	public class ESBotaoAnimado extends MBotao
	{
		private var _aumentar:Resize = new Resize();
		private var _diminuir:Resize = new Resize();
		private var _labelBotao:String;
		
		public function ESBotaoAnimado()
		{			
			_aumentar.duration = 150;
			_aumentar.widthTo = 150;
			_aumentar.heightTo = 60;
			
			
			_diminuir.duration = 200;
			_diminuir.widthTo = 120;
			_diminuir.heightTo = 40;
			
			this.addEventListener(MouseEvent.MOUSE_OUT, diminuir);
			this.addEventListener(MouseEvent.MOUSE_OVER, aumentar);
		}		
		
		public function aumentar(evt:MouseEvent):void{
			_aumentar.play([evt.currentTarget]);
		}
		
		public function diminuir(evt:MouseEvent):void{
			_diminuir.play([evt.currentTarget]);
		}
		
		public function get labelBotao():String { return _labelBotao; }
		public function set labelBotao(label:String):void { _labelBotao = label; } 
	}
}