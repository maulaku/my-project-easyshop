import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.telas.Login.Login;

import flash.events.Event;

import mx.managers.PopUpManager;

public function construtor():void
{
	bemVindo.text = bemVindo.text + MainEasyShop.getUsurioGlobal().login;
}

protected function btnEntrar_clickHandler(centrado:Boolean):void
{
	var painel:Login = new Login();
	painel.showCloseButton=true;
	PopUpManager.addPopUp(painel, this, true);
	
	if(centrado==true) PopUpManager.centerPopUp(painel);
}

protected function clickadoPessoaFisica():void
{
	this.dispatchEvent(new Event("clickadoPessoaFisica"));			
}

private function clickadoLogout():void {
	this.dispatchEvent(new Event("clickadoLogout"));
}

protected function clickadoPessoaJuridica():void
{
	this.dispatchEvent(new Event("clickadoPessoaJuridica"));
}