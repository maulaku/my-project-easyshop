import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Produto;
import br.com.easyShop.telas.Login;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;

import flash.events.MouseEvent;

import mx.managers.PopUpManager;

/**
 * Inicializa os componentes e objetos
 */ 
public function construtor():void
{
	cbBusca.mreServicePesquisa = "ProdutoService.getProdutosNome";
	MRemoteObject.get("CategoriaService.getTodasCategoriasPai", [null], resultCategoria);
}

public function resultCategoria(result:ResultJava):void
{
	try
	{		
		
		if(result.item != null && (result.item as Boolean)==true)
		{				
			cbCategorias.mreDataProvider = result.lista;
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

public function lfProduto(item:Object=null, colunm:Object=null):String
{
	return item != null ? (item as Produto).nome : "null";
}

protected function btnEntrar_clickHandler(centrado:Boolean):void
{
	// TODO Auto-generated method stub
	var painel:Login = new Login();
	painel.showCloseButton=true;
	PopUpManager.addPopUp(painel, this, true);
	
	if(centrado==true) PopUpManager.centerPopUp(painel);
}

protected function mbotao1_clickHandler(event:MouseEvent):void
{
	// TODO Auto-generated method stub
	modulo.mreLoadModule("br/com/easyShop/telas/cadastros/AbaCadastroCliente.swf");
}