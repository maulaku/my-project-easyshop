package br.com.easyShop.service;

import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Produto;
import br.com.easyShop.persistencia.DAO.ProdutoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class ProdutoService extends BaseServiceAtta {

	public void inserirProduto(Produto produto) {
		try {
			new ProdutoDAO().salvar(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Produto getProduto(long id) {
		try {
			return new ProdutoDAO().getProduto(-1, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public long getCountProdutos() {
		try {
			return new ProdutoDAO().getQuantidadeProdutos();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<Produto> getProdutos() {
		try {
			return new ProdutoDAO().getProdutos(3);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo que busca os produtos atraves do nome passado como parametro
	 *
	 * @author Jean
	 * @Aba MainEasyShop
	 * @param nome
	 * @return lista de produtos
	 */
	public ResultJava getProdutosNome(String nome) {
		try {
			return new ResultJava(new ProdutoDAO().getProdutosNome(nome, -1));
		} catch (Exception e) {
			return new ResultJava(false, Arrays
					.asList(new String[] { "Erro ão buscar produtos" }));
		}
	}
}
