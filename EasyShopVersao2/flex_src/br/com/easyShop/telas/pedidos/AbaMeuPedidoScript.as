import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;


public function construtor(cliente:Object):void
{
	
//	MRemoteObject.get("PedidoService.getPedidosCliente", cliente, preencherTabela);
}

public function escutaBotoes(botao:MBotao):void
{
	Alerta.abrir("Chegou");
}

public function preencherTabela(result:ResultJava):void{
	
}