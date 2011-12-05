import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.componentes.imagem.Imagem;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.telas.Login.Login;
import br.com.easyShop.telas.cadastros.AbaCadastroClientePessoaFisica;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;
import br.com.mresolucoes.utils.NumberUtil;

import flash.events.Event;
import flash.utils.ByteArray;

import mx.controls.Alert;
import mx.managers.PopUpManager;

public function construtor():void
{
	bemVindo.text = bemVindo.text + MainEasyShop.getUsurioGlobal().login;
	
	var i:Imagem = new Imagem;
	i.imagemSource = Constantes.instance.ENDERECO_IMAGEM_CLIENTE+NumberUtil.toString(MainEasyShop.getClienteGlobal().pkCliente)+".jpg";
	imagemDoCliente.addElement(i);
	
//	MRemoteObject.get("ClienteService.recuperaImagem", [MainEasyShop.getClienteGlobal()], pegarImagem);
	
}

//public function pegarImagem(result:ResultJava):void
//{
//	try		
//	{		
//		if(result != null)
//		{	
//			var i:Imagem = new Imagem;
//			Alert.show("chegou aqui");
//			i.imagemProduto.load((ByteArray) (result.item));
//		}
//		else
//		{ 
//			Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
//		}
//	} 
//	catch(e:Error)
//	{ 
//		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
//	}	
//}

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