import br.com.easyShop.telas.Login.Login;

import flash.events.Event;

import mx.managers.PopUpManager;

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