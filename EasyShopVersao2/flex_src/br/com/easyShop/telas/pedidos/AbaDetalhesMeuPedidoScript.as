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

[Bindable]
public var dados:ArrayCollection = new ArrayCollection();

public static var pedidoProduto:PedidoProduto;


public function construtor(PedidoProduto pedido):void
{
	pedidoProduto = pedido;
    inserirDados();
}

public function inserirDados()void:{

	var temp:Object;

		temp = new Object();
		temp.codigo = pedidoProduto.pkPedidoProduto;
		temp.produto = pedidoProduto.produto.nome;
		temp.quantidade = pedidoProduto.quantidade;
		temp.valor = pedidoProduto.pedido.total;
		
		dados.addItem(temp);	
}

