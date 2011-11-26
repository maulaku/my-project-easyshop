import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.telas.produtos.MeuCarrinho;
import br.com.mresolucoes.componentes.mre.MComboBox;

import flash.errors.IllegalOperationError;
import flash.events.Event;
import flash.events.MouseEvent;

import flashx.textLayout.formats.Float;

import mx.collections.ArrayCollection;
import mx.collections.IList;
import mx.controls.DateField;
import mx.managers.PopUpManager;

import org.flexunit.internals.namespaces.classInternal;

import spark.components.RadioButton;

[Bindable]
public static var resp:int;
[Bindable]
public static var descricaoPagamento:String;

public static function getSelecaoFormaPagamento():int{
	return resp;
}

public static function getDescricaoPagamento():String{
	return descricaoPagamento;
}

public function construtor():void
{
	var i:int;
	var valorTotal:int = MeuCarrinho.getValorCarrinho() + MeuCarrinho.getValorFrete();
	var arr:ArrayCollection = new ArrayCollection();
	var string:String;
	
	valorTotalProdutos.text = valorTotalProdutos.text + MeuCarrinho.getValorCarrinho() + ",00";
	valorFrete.text = valorFrete.text + MeuCarrinho.getValorFrete() + ",00";
	valorTotalPagar.text = valorTotalPagar.text + valorTotal + ",00";
	
	for(i=1;i<13;i++)
	{
		string = (i + "x de R$ " + (valorTotal/i) + " sem juros");
		arr.addItem(string);
	}
	
	cboParcelamento.dataProvider = arr;
	cboParcelamento.selectedIndex = 0;
	visa.selected = true;
}

protected function btnFinalizarCompra_clickHandler(event:MouseEvent):void
{
	var valor: int = MeuCarrinho.getValorCarrinho() + MeuCarrinho.getValorFrete();
	var nomeCartao:String;
	
	if(boleto.selected){
		resp 0;
		descricaoPagamento = "Parcelado em 1x de R$ " + valor + " sem juros.";
	}
	else{
		if(visa.selected) nomeCartao = "Visa";
		else if(mastercard.selected) nomeCartao = "MasterCard";
		else if(hipercard.selected) nomeCartao = "HiperCard";
		else nomeCartao = "DinesClub";
		
		resp = (cboParcelamento.selectedIndex + 1);
		descricaoPagamento = "Parcelado em " + (cboParcelamento.selectedIndex + 1) + "x de R$ " +  (valor/(cboParcelamento.selectedIndex + 1)) + " sem juros. Nome do cartão: " + nomeCartao + ". Número do Cartão: " + numeroCartao.text;
	}

	this.dispatchEvent(new Event("clickadoAvancar"));
}

protected function btnContinuarComprando_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoVoltarMeuCarrinho"));
}