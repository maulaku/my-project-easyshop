
import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Carrinho;
import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Produto;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;

import flash.events.MouseEvent;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.Button;
import mx.controls.Image;

import org.flexunit.internals.namespaces.classInternal;

import spark.components.Application;

private var produto:Produto = new Produto();

public function construtor(id:int):void
{
	var arr:Array = new Array();
	arr.push(5);
	MRemoteObject.get("ProdutoService.getProdutosId", arr, preencherDetalhesDoProduto);
}

public function preencherDetalhesDoProduto(result:ResultJava):void
{
	try		
	{					
			produto = ((Produto) (result.item));
			nomeProduto.text = produto.nome;
			descricao.text = produto.descricao;
			caracteristica.text = produto.caracteristicas;
			especificacaoTecnica.text = produto.descricao;
			preco.text = "R$ " + (produto.preco);
			
			var parcelamentoString:String;
			var arr:ArrayCollection = new ArrayCollection();
			var i:int;
			for(i=1;i<=12;i++){
				parcelamentoString = i + "x de R$ " + (produto.preco/i) + " sem juros.";
				arr.addItem(parcelamentoString);
			}		
			cboParcelamento.dataProvider = arr; 
			cboParcelamento.selectedIndex = 0;
			cartaoDeCredito.selected = true;
			boletoValor.text = "R$ " + (produto.preco*0.9);
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar produtos", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}

protected function btnAdicionarAoCarrinho_clickHandler(event:MouseEvent):void
{
	var carrinho:Carrinho = new Carrinho();
	var carrinhoProduto:CarrinhoProduto = new CarrinhoProduto();
	
	carrinho.cliente = ((Cliente) (MainEasyShop.getClienteGlobal()));
	
	carrinhoProduto.carrinho = carrinho;
	carrinhoProduto.produto = produto;
	carrinhoProduto.quantidade = 1;
}






