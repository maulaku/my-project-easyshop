package br.com.easyShop.service;

import java.util.List;

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

}
