package br.com.easyShop.service;

import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Estado;
import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.EstadoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class EstadoService extends BaseServiceAtta {

	public List<Estado> getEstados(Pais pais) {
		try {
			return new EstadoDAO().getEstadosPais(pais, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultJava getTodosEstados(Pais pais) {
		try
		{
			return new ResultJava(true, new EstadoDAO().getEstadosPais(pais,-1));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar estado\n" + e }));
		}
	}

}
