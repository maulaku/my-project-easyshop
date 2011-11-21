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
public var pedidos:ArrayCollection = new ArrayCollection();

[Bindable]
public var dadosPedido:ArrayCollection = new ArrayCollection();


public function construtor(cliente:Cliente):void
{
	var arr:Array = new Array();
	arr.push(cliente);
}

}