package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Cidade;
import br.com.easyShop.model.Estado;
import br.com.easyShop.persistencia.DAO.CidadeDAO;

public class CidadeService {
	
	public List<Cidade> getCidades(Estado estado){
		CidadeDAO cidadeDAO = new CidadeDAO();
		return cidadeDAO.getCidades(estado);
	}

}
