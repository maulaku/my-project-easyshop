package br.com.easyShop.service;

import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Produto;
import br.com.easyShop.persistencia.DAO.ProdutoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class ProdutoService extends BaseServiceAtta
{

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
		try
		{
			return ProdutoDAO.getCount(Produto.class);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public List<Produto> getProdutos(){
		ProdutoDAO produtoDao = new ProdutoDAO();
		return produtoDao.getProdutos();
	}
	
	/**
	 * Metodo que busca os produtos atraves do nome passado como parametro
	 * @author Jean
	 * @Aba MainEasyShop
	 * @param nome
	 * @return lista de produtos
	 */
	public ResultJava getProdutosNome(String nome)
	{
		try
		{
			return new ResultJava(new ProdutoDAO().getProdutosNome(nome, -1));
		} 
		catch (Exception e)
		{
			return new ResultJava(false, Arrays.asList(new String[]{"Erro ão buscar produtos"}));
		}
	}
}
