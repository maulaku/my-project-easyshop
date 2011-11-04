package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Produto;
import br.com.easyShop.persistencia.DAO.ProdutoDAO;

public class ProdutoService {

	private static Produto produto;

	public ProdutoService(Produto produto) {
		ProdutoService.produto = produto;
	}

	public ProdutoService(){

	}

	public void inserirProduto() {
		try {
			ProdutoDAO.inserir(produto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Produto getProduto(long id){
		try {
			return (Produto) ProdutoDAO.obtemUnico(Produto.class, "pkProduto = 1 " );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getCountProdutos(){
		return ProdutoDAO.getCount(Produto.class);
	}

	public List<Produto> getProdutos(){
		ProdutoDAO produtoDao = new ProdutoDAO();
		return produtoDao.getProdutos();
	}
}
