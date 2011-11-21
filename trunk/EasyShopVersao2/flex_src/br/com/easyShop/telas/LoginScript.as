import br.com.easyShop.telas.Login;

import flash.events.Event;

import mx.managers.PopUpManager;

protected function btnEntrar_clickHandler(centrado:Boolean):void
{
	// TODO Auto-generated method stub
	var painel:Login = new Login();
	painel.showCloseButton=true;
	PopUpManager.addPopUp(painel, this, true);
	
	if(centrado==true) PopUpManager.centerPopUp(painel);
}

protected function clickadoPessoaFisica():void
{
	this.dispatchEvent(new Event("clickadoPessoaFisica"));			
}

private function clickadoLogin():void {
	this.dispatchEvent(new Event("clickadoLogin"));
}

protected function clickadoPessoaJuridica():void
{
	this.dispatchEvent(new Event("clickadoPessoaJuridica"));
}