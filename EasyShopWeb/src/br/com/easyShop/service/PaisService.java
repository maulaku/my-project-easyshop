package br.com.easyShop.service;

import java.util.List;

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
}
