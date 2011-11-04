package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Produto;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class ProdutoDAO extends BaseDAO{
	
	public List<Produto> getProdutos() {
		List<Produto> produtos = null;
		try {
			produtos = obtemLista(Produto.class, "Select * from produto");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produtos;
	}

}
