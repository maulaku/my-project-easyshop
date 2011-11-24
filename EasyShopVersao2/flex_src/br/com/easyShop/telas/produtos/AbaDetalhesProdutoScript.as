
import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Carrinho;
import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Produto;
import br.com.easyShop.utils.Constantes;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;
import br.com.mresolucoes.imagens.ImagensUtils;
import br.com.mresolucoes.utils.NumberUtil;
import br.com.mresolucoes.utils.TelaUtil;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.Button;
import mx.controls.Image;
import mx.core.UIComponent;

import org.flexunit.internals.namespaces.classInternal;

import spark.components.Application;

private var produto:Produto = new Produto();

public function construtor():void
{
//	var arr:Array = new Array();
//	arr.push(4); //Mudar no produto Service!!!!!!!!!!!!!!!!!!!
	MRemoteObject.get("ProdutoService.getProdutosId", null, carregarProduto);
	novo();
}

public function novo():void
{
	try
	{
		TelaUtil.limparComponentes(this,2);
	}
	catch (e:Error)
	{
		Alerta.abrir("Ocorreu um erro, contate o administrador..", "Detalhes do Produto", null, null, null, ImagensUtils.INFO);		
	}
}

public function carregarProduto(result:ResultJava):void
{
	try		
	{					
			produto = result.item as Produto;
			lbNomeProduto.text = produto.nome;
			ctDescricao.text = produto.descricao;
			ctCaracteristicas.text = produto.caracteristicas;
			ctEspecificacaoTecnica.text = produto.descricao;
			lbPreco.text = "R$: " + NumberUtil.toString(produto.preco, 2);
			imagemProduto.source = Constantes.instance.ENDERECO_IMAGEM_PRODUTO+NumberUtil.toString(produto.pkProduto)+".jpg";
			
//			var parcelamentoString:String;
//			var arr:ArrayCollection = new ArrayCollection();
//			var i:int;
//			for(i=1;i<=12;i++){
//				parcelamentoString = i + "x de R$ " + (produto.preco/i) + " sem juros.";
//				arr.addItem(parcelamentoString);
//			}		
//			cboParcelamento.dataProvider = arr; 
//			cboParcelamento.selectedIndex = 0;
//			cartaoDeCredito.selected = true;
//			boletoValor.text = "R$ " + (produto.preco*0.9);
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar produtos", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

public function adicionarCarrinho2():void
{
	if(MainEasyShop.getClienteGlobal()!=null)
	{
		var carrinho:Carrinho = new Carrinho();
		var carrinhoProduto:CarrinhoProduto = new CarrinhoProduto();
		
		carrinho.cliente = ((Cliente) (MainEasyShop.getClienteGlobal()));
		
		carrinhoProduto.carrinho = carrinho;
		carrinhoProduto.produto = produto;
		carrinhoProduto.quantidade = 1;
		
		MRemoteObject.get("CarrinhoProdutoService.inserirCarrinho",[carrinhoProduto],resultado);
	}
	else
	{
		Alerta.abrir("Faça o Login primeiro", "EasyShop", null, null, null, ImagensUtils.FELIZ);
	}
}

public function resultado(result:ResultJava):void
{
	try		
	{		
		Alerta.abrir("Produto inserido com sucesso!", "EasyShop", null, null, null, ImagensUtils.FELIZ);
	} 
	catch(e2:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao salvar no carrinho", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

///*Listerners Componentes */
//public function escutaBotoes(botao:MBotao):void
//{
//	try
//	{
//		if (botao==btAdicionarCarrinho)
//		{
//			adicionarCarrinho();
//		}
//		else if (botao==btContinuarComprando)
//		{
//			
//		}
//	}
//	catch(e:Error)
//	{
//		Alerta.abrir("Ocorreu um erro, contate o administrador..", "Detalhes do Produto", null, null, null, ImagensUtils.INFO);
//	}
//}




