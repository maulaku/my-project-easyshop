package br.com.easyShop.service;

import java.util.ArrayList;
import java.util.List;

import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.PaisDAO;

public class PaisService {
	
	public List<Pais> getPaises(){
		List<Pais> paises = new ArrayList<Pais>();
		PaisDAO paisDAO = new PaisDAO();
		paises = paisDAO.getPais();
		
		return paises;
	}
	
	public static Pais getPais(String nome){
		PaisDAO paisDao = new PaisDAO();
		return paisDao.getPais(nome);
	}

}
