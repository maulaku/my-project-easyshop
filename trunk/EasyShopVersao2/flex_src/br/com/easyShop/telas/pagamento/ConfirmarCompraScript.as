import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.model.Endereco;
import br.com.easyShop.telas.pagamento.Pagamentos;
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.easyShop.utils.Constantes;

import flash.events.Event;
import flash.events.MouseEvent;

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
	this.dispatchEvent(new Event("clickadoConfirmarCompra"));
}
