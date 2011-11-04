package br.com.easyShop.service;

import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.persistencia.DAO.UsuarioDAO;

public class CarrinhoProdutoService {

	public void inserir(CarrinhoProduto carrinhoProduto){
		try {
			UsuarioDAO.inserir(carrinhoProduto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
