import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.componentes.imagem.Imagem;
import br.com.easyShop.telas.Login.Login;
import br.com.easyShop.telas.cadastros.AbaCadastroClientePessoaFisica;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.utils.NumberUtil;

import flash.events.Event;

import mx.controls.Alert;
import mx.managers.PopUpManager;

public function construtor():void
{
	bemVindo.text = bemVindo.text + MainEasyShop.getUsurioGlobal().login;
	
	if(AbaCadastroClientePessoaFisica.getImagemGlobal()==null){
		var i:Imagem = new Imagem;
		i.imagemSource = Constantes.instance.ENDERECO_IMAGEM_CLIENTE+NumberUtil.toString(MainEasyShop.getClienteGlobal().pkCliente)+".jpg";
		imagemDoCliente.addElement(i);
	}
	else{
		var i2:Imagem = new Imagem;
		i2.imagemProduto.load(AbaCadastroClientePessoaFisica.getImagemGlobal());
		imagemDoCliente.addElement(i2);
	}
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