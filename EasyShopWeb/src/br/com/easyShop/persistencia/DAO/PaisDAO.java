package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class PaisDAO extends BaseDAO{

	public List<Pais> getPais(){
		List<Pais> paises = null;
		try {
			paises = obtemLista(Pais.class,"Select * from pais" );
		} catch (Exception e) {
			e.printStackTrace();
		}

		return paises;
	}

}
