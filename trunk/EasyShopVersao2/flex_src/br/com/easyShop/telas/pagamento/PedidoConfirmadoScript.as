import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.telas.pagamento.ConfirmarCompra;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;

import flash.events.MouseEvent;

import mx.controls.Alert;
import mx.managers.PopUpManager;

public function construtor():void
{
	MRemoteObject.get("PedidoService.getPedidosCliente",[MainEasyShop.getClienteGlobal()], resultPedido);
}
protected function btnContinuarComprando_clickHandler(event:MouseEvent):void
{
	this.visible = false;
}

public function resultPedido(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{
			var pedido:Pedido = new Pedido();
			var nome:String;
			var zeros:String = new String();
			zeros = "";
			
			pedido = result.lista[(result.lista.length)-1];
			nome = "" + pedido.pkPedido;
			
			for(;;){
				zeros = zeros + "0";
				if((zeros.length + nome.length)>=6){
					break;
				}
			}
	
			lblNumeroPedido.text = "" + zeros + "" + nome;
		}
		else
		{ 
			Alerta.abrir(result.lista.length > 0 ? result.lista.getItemAt(0) as String : "Ops, Erro ao carregar pedido", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar pedido", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
}
