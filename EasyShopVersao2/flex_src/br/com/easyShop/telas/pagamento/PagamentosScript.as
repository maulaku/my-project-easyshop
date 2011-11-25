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

public static function getSelecaoFormaPagamento():int{
	return resp;
}

protected function btnFinalizarCompra_clickHandler(event:MouseEvent):void
{
	if(boleto.selected){
		resp 0;
	}
	else{
		resp = (cboParcelamento.selectedIndex + 1);
	}
	
	this.dispatchEvent(new Event("clickadoAvancar"));
}

protected function btnContinuarComprando_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoVoltarMeuCarrinho"));
}