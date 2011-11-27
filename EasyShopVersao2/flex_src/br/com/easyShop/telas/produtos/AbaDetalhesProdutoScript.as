
import br.com.easyShop.aplicacao.MainEasyShop;
import br.com.easyShop.comunicacao.MRemoteObject;
import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Carrinho;
import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Produto;
import br.com.mresolucoes.componentes.mre.Alerta;
import br.com.mresolucoes.componentes.mre.MBotao;
import br.com.mresolucoes.imagens.ImagensUtils;
import br.com.mresolucoes.utils.TelaUtil;

private var produto:Produto = new Produto();

public function construtor():void
{
	novo();
}

public function novo():void
{
	try
	{
		TelaUtil.limparComponentes(this,2);
		cbParcelamento.enabled = false;
	}
	catch (e:Error)
	{
		Alerta.abrir("Ocorreu um erro, contate o administrador..", "Detalhes do Produto", null, null, null, ImagensUtils.ERRO);		
	}
}

public function carregarProduto(result:ResultJava):void
{
	
	try		
	{					
//			produto = result.item as Produto;
//			lbNomeProduto.text = produto.nome;
//			ctDescricao.text = produto.descricao;
//			ctCaracteristicas.text = produto.caracteristicas;
//			ctEspecificacaoTecnica.text = produto.descricao;
//			lbPreco.text = "R$: " + NumberUtil.toString(produto.preco, 2);
//			imagemProduto.source = Constantes.instance.ENDERECO_IMAGEM_PRODUTO+NumberUtil.toString(produto.pkProduto)+".jpg";
//			
//		var tiposParcelamento:ArrayCollection = new ArrayCollection(
//			[{nome:"1 x "+NumberUtil.toString(produto.preco, 2)+" sem juros"};
//			{nome:"1 x "+NumberUtil.toString(produto.preco, 2)+" sem juros"};
//			]);

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
		Alerta.abrir("Por favor, Faça o Login primeiro", "EasyShop", null, null, null, ImagensUtils.INFO);
	}
}

public function resultado(result:ResultJava):void
{
	try		
	{		
		Alerta.abrir("Produto inserido com sucesso!", "EasyShop", null, null, null, ImagensUtils.OK);
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