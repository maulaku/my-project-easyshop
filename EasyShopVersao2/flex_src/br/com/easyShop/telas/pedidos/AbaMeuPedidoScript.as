import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.model.PedidoProduto;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;

import flash.events.Event;
import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.collections.ArrayList;
import mx.controls.Alert;

import spark.modules.Module;

[Bindable]
public var pedidos:ArrayCollection = new ArrayCollection();

[Bindable]
public var dadosPedido:ArrayCollection = new ArrayCollection();

public var listaPedidos:ArrayCollection = new ArrayCollection([{pedido:Pedido, pedidoProduto:PedidoProduto}]);


public function construtor():void
{
	MRemoteObject.get("PedidoProdutoService.getPedidosProduto", [MainEasyShop.getClienteGlobal()], preencherPedido);
}

public function escutaBotoes(botao:MBotao):void
{
	Alerta.abrir("Chegou");
}

public function preencherPedido(result:ResultJava):void{
	tblPedido.mreDataProvider = result.lista;
}

protected function irParaDetalhe():void {	
	
	MainEasyShop.setPedidoProduto(tblPedido.mreGetSelectedItem() as PedidoProduto);
	this.dispatchEvent(new Event("clicadoVerDetalhes"));	
}