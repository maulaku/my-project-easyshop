
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Produto;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.imagens.ImagensUtils;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.controls.Button;

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
			var produto:Produto = new Produto();
			produto = ((Produto) (result.item));
			nomeProduto.text = produto.nome;
			descricao.text = produto.descricao;
			caracteristica.text = produto.caracteristicas;
			especificacaoTecnica.text = produto.descricao;
			preco.text = preco.text + produto.preco;
			
			var parcelamentoString:String;
			var arr:ArrayCollection = new ArrayCollection();
			var i:int;
			for(i=1;i<=12;i++){
				parcelamentoString = i + "x de R$ " + (produto.preco/i) + " sem juros.";
				arr.addItem(parcelamentoString);
			}		
			cboParcelamento.dataProvider = arr; 
		
			//boletoValor.text = "R$ ahushua";
			//boletoDesconto.text = boletoDesconto.text + (produto.preco) + " )";
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar produtos", "EasyShop", null, null, null, ImagensUtils.INFO);
	}	
}