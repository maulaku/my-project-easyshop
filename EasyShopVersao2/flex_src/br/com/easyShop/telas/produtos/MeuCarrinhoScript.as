import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.CarrinhoProduto;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;

import flash.events.Event;
import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.managers.PopUpManager;

[Bindable]
public var dados:ArrayCollection = new ArrayCollection();

public function construtor():void
{
	MRemoteObject.get("CarrinhoProdutoService.getCarrinhoProdutos", [MainEasyShop.getClienteGlobal()], resultCarrinho);
}

public function resultCarrinho(result:ResultJava):void
{
	try		
	{		
		if(result != null)
		{
			var i:int;
			var carrinhoProduto:CarrinhoProduto;
			var temp:Object;
			
			for(i=0;i<result.lista.length;i++)
			{
				carrinhoProduto = new CarrinhoProduto();
				carrinhoProduto = ((CarrinhoProduto) (result.lista[i]));
				temp = new Object();
				temp.campo1 = carrinhoProduto.produto.nome;
				temp.campo2 = "1";
				temp.campo3 = "R$ " + carrinhoProduto.produto.preco;
				temp.campo4 = "R$ " + carrinhoProduto.produto.preco;
				temp.campo5 = "1";
				
				dados.addItem(temp);
			}
		}
		else
		{ 
			Alerta.abrir(result.lista.length > 0 ? result.lista.getItemAt(0) as String : "Ops, Erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
		
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar categorias", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
	
}

protected function btnFinalizarCarrinho_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoFinalizarCarrinho"));	
}

protected function btnContinuarComprando_clickHandler(event:MouseEvent):void
{
	this.dispatchEvent(new Event("clickadoContinuarComprando"));	
}

private function inserirContatoTabela():void{
	
	
}

//private function deleteLinha(linha:int):void {
//	
//	if(linha>=0 && linha<dados.length) {
//		dados.removeItemAt(linha);
//		dados.refresh();
//	}
//	else {
//		Alert.show("Selecione a linha primeiro")
//	}
//}
