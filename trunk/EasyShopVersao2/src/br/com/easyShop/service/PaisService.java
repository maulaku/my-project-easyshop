package br.com.easyShop.service;

import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.PaisDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class PaisService extends BaseServiceAtta {

	public List<Pais> getPaises() {
		try {
			return new PaisDAO().getPais(-1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ResultJava getTodosPaises() {
		try
		{
			return new ResultJava(true, new PaisDAO().getPais(-1));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar pais\n" + e }));
		}
	}
}
