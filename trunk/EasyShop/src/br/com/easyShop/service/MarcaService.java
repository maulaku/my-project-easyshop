package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Marca;
import br.com.easyShop.persistencia.DAO.MarcaDAO;

public class MarcaService {

	public void inserir(Marca marca) {
		try {
			MarcaDAO.inserir(marca);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Marca> getMarcas(){
		MarcaDAO marcaDao = new MarcaDAO();
		return marcaDao.getMarcas();
	}

}
