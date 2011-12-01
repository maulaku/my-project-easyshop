
import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.componentes.modulo.ModuloItem;
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

import flash.globalization.NumberFormatter;

import flashx.textLayout.formats.Float;

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.events.CloseEvent;

import spark.formatters.NumberFormatter;
import spark.formatters.supportClasses.NumberFormatterBase;

private var produto:Produto;

public function construtor():void
{
	novo();
	carregarProduto();
}

public function novo():void
{
	try
	{
		TelaUtil.limparComponentes(this,2);
//		cbParcelamento.enabled = false;
	}
	catch (e:Error)
	{
		Alerta.abrir("Ocorreu um erro, contate o administrador..", "Detalhes do Produto", null, null, null, ImagensUtils.ERRO);		
	}
}

public function carregarProduto():void
{
	try		
	{				
			produto = MainEasyShop.getProdutoGlobal();
			lbNomeProduto.text = produto.nome;
			ctDescricao.text = produto.descricao;
			ctCaracteristicas.text = produto.caracteristicas;
			ctEspecificacoesTecnicas.text = produto.especificacoesTecnicas;
			
			var str:String;
			var i:int;
			
			lbPreco.text = "R$: " + ((Math.round(produto.preco)*100)/100);
//			imagemProduto.source = Constantes.instance.ENDERECO_IMAGEM_PRODUTO+NumberUtil.toString(produto.pkProduto)+".jpg";
			
		    var tiposParcelamento:ArrayCollection = new ArrayCollection;
			
			for(i=1;i<13;i++){
				str = "R$ " + ((Math.round(produto.preco/i)*100)/100);
				
				tiposParcelamento.addItem(str);
			}
			
			cbParcelamento.dataProvider = tiposParcelamento;
			
//			tiposParcelamento
//			[{nome:"1 x "+NumberUtil.toString(produto.preco, 2)+" sem juros"};
//			{nome:"1 x "+NumberUtil.toString(produto.preco, 2)+" sem juros"};
//			]);
			
			txtQuantidade.text = "1";
			txtQuantidade.showInAutomationHierarchy = true;
	} 
	catch(e:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao carregar produtos", "EasyShop", null, null, null, ImagensUtils.ERRO);
	}	
}

public function adicionarCarrinho2():void
{
	if(MainEasyShop.getClienteGlobal()!=null)
	{
		var qtd:int;
		var terminar:int;
		
		try
		{
			qtd = parseInt(txtQuantidade.text);
		} 
		catch(error:Error) 
		{
			txtQuantidade.showInAutomationHierarchy = true;
			qtd = -1;
			Alerta.abrir("Por favor, digite um valor válido", "EasyShop", null, null, null, ImagensUtils.INFO);
		}
		
		if(qtd != -1 && produto.quantidade>=qtd){
			var carrinho:Carrinho = new Carrinho();
			var carrinhoProduto:CarrinhoProduto = new CarrinhoProduto();
			
			carrinho.cliente = ((Cliente) (MainEasyShop.getClienteGlobal()));
			
			carrinhoProduto.carrinho = carrinho;
			carrinhoProduto.produto = produto;
			carrinhoProduto.quantidade = qtd;
			
			MRemoteObject.get("CarrinhoProdutoService.inserirCarrinho",[carrinhoProduto],resultado);
		}else{
			Alerta.abrir("Produto indisponivél no momento. Deseja adicioná-lo em Meus Desejos?", "EasyShop",Alerta.SIM|Alerta.NAO, null, adicionarMeusDesejos, ImagensUtils.INFO);
		}
	}
	else
	{
		Alerta.abrir("Por favor, Faça o Login primeiro", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
}

public function adicionarMeusDesejos(event:CloseEvent):void
{
	if(event.detail == Alerta.SIM){
		Alerta.abrir("Produto inserido em Meus Desejos com sucesso!", "EasyShop", null, null, null, ImagensUtils.OK);
	}
}

public function resultado(result:ResultJava):void
{
	try		
	{		
		Alerta.abrir("Produto inserido em Meu Carrinho com sucesso!", "EasyShop", null, null, null, ImagensUtils.OK);
	} 
	catch(e2:Error)
	{ 
		Alerta.abrir("Ops, Ocorreu um erro ao salvar no carrinho", "EasyShop", null, null, null, ImagensUtils.TRISTE);
	}	
}

/*Listerners Componentes */
public function escutaBotoes(botao:MBotao):void
{
	try
	{
		if (botao==btContinuarComprando)
		{
			this.visible = false;
		}
	}
	catch(e:Error)
	{
		Alerta.abrir("Ocorreu um erro, contate o administrador..", "Detalhes do Produto", null, null, null, ImagensUtils.ERRO);
	}
}