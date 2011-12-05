import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.DesejoProduto;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.model.Produto;
import br.com.easyShop.model.PedidoProduto;
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;

import mx.collections.ArrayCollection;
import mx.collections.ArrayList;
import mx.controls.Alert;
import flash.events.Event;
import flash.events.MouseEvent;
import br.com.mresolucoes.componentes.mre.Alerta;

import spark.modules.Module;

[Bindable]
public var desejos:ArrayCollection = new ArrayCollection();
public static var produto:Produto = new Produto();

public function construtor():void
{
	MRemoteObject.get("DesejoProdutoService.getDesejoProduto", [MainEasyShop.getClienteGlobal()], preencherDesejo);
}

protected function btnComprar_clickHandler(event:MouseEvent):void
{
	produto = (tblDesejo.mreGetSelectedItem() as DesejoProduto).produto;
	this.dispatchEvent(new Event("clickadoComprar"));	
}

public function preencherDesejo(result:ResultJava):void{

	tblDesejo.mreDataProvider = result.lista;
}

public function btnComprarDesejo(event:MouseEvent):void
{
//	this.dispatchEvent(new Event("detalhes"));	
	this.visible = false;
}
