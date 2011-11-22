import flash.events.Event;
import flash.events.MouseEvent;
import mx.managers.PopUpManager;

protected function btnContinuarComprando_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoVoltarMeuCarrinho"));
}

protected function btnFinalizarCompra_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoConfirmarCompra"));
}