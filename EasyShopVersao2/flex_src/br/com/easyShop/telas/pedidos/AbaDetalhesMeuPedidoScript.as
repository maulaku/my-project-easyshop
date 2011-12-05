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

[Bindable]
public var dados:ArrayCollection = new ArrayCollection();

[Bindable]
public var enderecos:ArrayCollection = new ArrayCollection();

[Bindable]
public var pagamentos:ArrayCollection = new ArrayCollection();

[Bindable]
public var datas:ArrayCollection = new ArrayCollection();

public static var pedidoProduto:PedidoProduto;


public function construtor():void
{
	pedidoProduto = MainEasyShop.getPedidoProduto();
    inserirDados();
	inserirEndereco();
	inserirPagamento();

}

public function inserirDados():void
{
	var temp:Object;

		temp = new Object();
		temp.codigo = pedidoProduto.pkPedidoProduto;
		temp.produto = pedidoProduto.produto.nome;
		temp.quantidade = pedidoProduto.quantidade;
		temp.valor = pedidoProduto.pedido.total;
		
		dados.addItem(temp);	
}

public function inserirEndereco():void
{
	var temp:Object;
	
	temp = new Object();
	temp.rua = pedidoProduto.pedido.endereco.logradouro;
	temp.bairro = pedidoProduto.pedido.endereco.bairro;
	temp.cidade = pedidoProduto.pedido.endereco.cidade.nome;
	temp.numero = pedidoProduto.pedido.endereco.numero;
	temp.cep = pedidoProduto.pedido.endereco.cep;
	
	enderecos.addItem(temp);	
}


public function inserirPagamento():void
{
	var temp:Object;
	
	temp = new Object();
	temp.nome = pedidoProduto.pedido.perfilPagamento.nome;
	temp.descricao = pedidoProduto.pedido.perfilPagamento.descricao;

	pagamentos.addItem(temp);	
}

public function inserirData():void
{
	var temp:Object;
	
	temp = new Object();
	temp.datapedido = pedidoProduto.pedido.dataEntrega;
	temp.dataentrega = pedidoProduto.pedido.dataPedido;
	
	datas.addItem(temp);	
}
