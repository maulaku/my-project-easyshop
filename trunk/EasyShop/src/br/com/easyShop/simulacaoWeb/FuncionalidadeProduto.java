package br.com.easyShop.simulacaoWeb;

import br.com.easyShop.model.Carrinho;
import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.model.Produto;
import br.com.easyShop.service.CarrinhoProdutoService;
import br.com.easyShop.service.ProdutoService;

public class FuncionalidadeProduto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		inserirProduto();
	}

	private static void inserirProduto(){
		Produto produto = new Produto();
        ProdutoService produtoService = new ProdutoService(produto);
        produto = produtoService.getProduto(1);

        Carrinho carrinho = new Carrinho();
        carrinho.setPkCarrinho(1);

        CarrinhoProduto  carrinhoProduto = new CarrinhoProduto();
        carrinhoProduto.setCarrinho(carrinho);
        carrinhoProduto.setProduto(produto);
        carrinhoProduto.setQuantidade(4);

        CarrinhoProdutoService carrinhoProdutoService = new CarrinhoProdutoService();
        carrinhoProdutoService.inserir(carrinhoProduto);
	}

	private static void removerProdutoCarrinho(){

	}

}
