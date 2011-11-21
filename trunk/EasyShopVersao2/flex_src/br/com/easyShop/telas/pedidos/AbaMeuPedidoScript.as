import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.model.PedidoProduto;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;

import mx.collections.ArrayCollection;
import mx.collections.ArrayList;
import mx.controls.Alert;

import spark.modules.Module;

[Bindable]
public var pedidos:ArrayCollection = new ArrayCollection();

[Bindable]
public var dadosPedido:ArrayCollection = new ArrayCollection();

public var listaPedidos:ArrayCollection = new ArrayCollection([{pedido:Pedido, pedidoProduto:PedidoProduto}]);


public function construtor(cliente:Cliente):void
{
	var arr:Array = new Array();
	arr.push(cliente);

	MRemoteObject.get("PedidoService.getPedidosCliente", arr, preencherPedido);
}

public function escutaBotoes(botao:MBotao):void
{
	Alerta.abrir("Chegou");
}

public function preencherPedido(result:ResultJava):void{

	var i:int;
	var pedido:Pedido = new Pedido();
	var arr:Array = new Array();

	dadosPedido = result.lista;

	for(i=0;i<result.lista.length;i++){
		
		pedido = ((Pedido) (result.lista[i]));  
		var temp:Object;
		
		temp=new Object();
		temp.codigo=pedido.pkPedido;
		temp.produto=pedido.total;
		temp.valor = pedido.total;
		temp.status = pedido.status;
		
		pedidos.addItem(temp);
	}	
	
	pedido = ((Pedido) (dadosPedido[i])); 
	arr.push(pedido);
	MRemoteObject.get("PedidoProdutoService.getPedidosProdutoCliente", arr, preencherTabela);
}

public function preencherTabela(result:ResultJava):void{
	
		var i:int;
		var pedido:Pedido = new Pedido();
		var pedidoProduto:PedidoProduto = new PedidoProduto();

		pedidoProduto = ((PedidoProduto) (result.item));  
		
		for(i=0;i<result.lista.length;i++){
 
			pedido = dadosPedido[i];
			
			var temp:Object;
			temp=new Object();
			temp.produto=pedidoProduto[i].produto.nome;
			temp.quantidade = pedidoProduto[i].quantidade;
			temp.codigo=pedido.pkPedido;
			temp.produto=pedido.total;
			temp.valor = pedido.total;
			temp.status = pedido.status;
			
			pedidos.addItem(temp);

			listaPedidos.setItemAt(pedido,0);
			listaPedidos.setItemAt(pedidoProduto,1);
		}	
}

private function irParaDetalhe():void {

	var main:MainEasyShop = new MainEasyShop();
	main.modulo.mreLoadModule("br/com/easyShop/telas/pedidos/AbaDetalheMeuPedido.swf");
}