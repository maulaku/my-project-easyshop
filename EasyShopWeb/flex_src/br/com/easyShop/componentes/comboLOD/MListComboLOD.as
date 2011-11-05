package br.com.easyShop.componentes.comboLOD
{
	import flash.events.KeyboardEvent;
	
	import spark.components.List;
	
	public class MListComboLOD extends List
	{
		override protected function keyDownHandler(event:KeyboardEvent):void
		{
			super.keyDownHandler(event);
			
			if (!dataProvider || !layout || event.isDefaultPrevented()) { return; }
			adjustSelectionAndCaretUponNavigation(event); 
		}
	}
}