import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.model.PedidoProduto;
import br.com.easyShop.model.PerfilPagamento;
import br.com.easyShop.model.Produto;
import br.com.easyShop.telas.pagamento.Pagamentos;
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;

import flash.events.Event;
import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.managers.PopUpManager;

public function construtor():void
{
	valorTotalProdutos.text = valorTotalProdutos.text + MeuCarrinho.getValorCarrinho() + ",00";
	valorFrete.text = valorFrete.text + MeuCarrinho.getValorFrete() + ",00";
	valorTotalPagar.text = valorTotalPagar.text + (MeuCarrinho.getValorFrete()+MeuCarrinho.getValorCarrinho()) + ",00";
	
	if(Pagamentos.getSelecaoFormaPagamento()==0){
		formaDePagamento.text = "Boleto Bancário";
		valor.text = valor.text + (MeuCarrinho.getValorFrete()+MeuCarrinho.getValorCarrinho()) + ",00";
		quantidadeParcelamento.text = "1 parcela";
	}
	else{
		formaDePagamento.text = "Cartão de Crédito";
		valor.text = valor.text + ((MeuCarrinho.getValorFrete()+MeuCarrinho.getValorCarrinho())/Pagamentos.getSelecaoFormaPagamento());
		quantidadeParcelamento.text = (Pagamentos.getSelecaoFormaPagamento()) + " parcela(s)";
	}
	
	var endereco:Endereco = MeuCarrinho.getEnderecoEscolhido();
	logradouro.text = endereco.logradouro;
	numero.text = endereco.numero;
	complemento.text = endereco.complemento;
	bairro.text = endereco.bairro;
	cep.text = endereco.cep;
	cidade.text = endereco.cidade.nome;
	estado.text = endereco.cidade.estado.nome;
	pais.text = endereco.cidade.estado.pais.nome;
	
	if((endereco.tipo) == (Constantes.instance.TIPO_RESIDENCIA)){
		tipo.text = (Constantes.instance.RESIDENCIA);
	}
	if((endereco.tipo) == (Constantes.instance.TIPO_APARTAMENTO)){
		tipo.text = (Constantes.instance.APARTAMENTO);
	}
	if((endereco.tipo) == (Constantes.instance.TIPO_COMERCIAL)){
		tipo.text = (Constantes.instance.COMERCIAL);
	}
	
	prazoEntrega.text = "5 dias úteis";
}

protected function btnContinuarComprando_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoVoltarMeuCarrinho"));
}

protected function btnFinalizarCompra_clickHandler(event:MouseEvent):void
{
	var pedido:Pedido = new Pedido();
	var perfilPagamento:PerfilPagamento = new PerfilPagamento();
	var pedidosProdutos:ArrayCollection = new ArrayCollection();
	var i:int;
	var aux:int;
	
	if(Pagamentos.getSelecaoFormaPagamento()==0){
		perfilPagamento.nome = "Boleto Bancário";
	}
	else{
		perfilPagamento.nome = "Cartão de Crédito";
	}
	perfilPagamento.descricao = Pagamentos.getDescricaoPagamento();
	perfilPagamento.status = Constantes.instance.STATUS_ATIVO;
	
	pedido.perfilPagamento = perfilPagamento;
	pedido.cliente = MainEasyShop.getClienteGlobal();
	pedido.endereco = MeuCarrinho.getEnderecoEscolhido();
	pedido.total = (MeuCarrinho.getValorCarrinho() + MeuCarrinho.getValorFrete());
	pedido.status = Constantes.instance.STATUS_ATIVO;
	
	var dataDoPedido:Date = new Date();
	pedido.dataPedido = dataDoPedido;
	pedido.dataEntrega = dataDoPedido;
	
	aux = MeuCarrinho.getCarrinho().length;
	
	for(i=0;i<aux;i++){
		var pedidoProduto:PedidoProduto = new PedidoProduto();
		
		pedidoProduto.produto = ((Produto) (MeuCarrinho.getCarrinho()[i].campo1));
		pedidoProduto.quantidade = MeuCarrinho.getCarrinho()[i].campo2;
		pedidoProduto.pedido = pedido;
		
		pedidosProdutos.addItem(pedidoProduto);
	}
	
	pedido.pedidoProdutos = pedidosProdutos;
	
	MRemoteObject.get("PedidoService.salvarPedido", [pedido], resultPedidos);
}

public function resultPedidos(result:ResultJava):void
{
	try		
	{	
		if(result==null){
			this.dispatchEvent(new Event("clickadoConfirmarCompra"));
		}
		else{
			Alerta.abrir("Ops, Ocorreu um erro ao salvar pedidos", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
		
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao salvar pedidos", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
}
