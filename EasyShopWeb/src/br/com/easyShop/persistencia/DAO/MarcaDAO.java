package br.com.easyShop.persistencia.DAO;

import java.util.List;
import br.com.easyShop.model.Marca;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class MarcaDAO extends BaseDAO {

	public List<Marca> getMarcas() {
		List<Marca> marcas = null;
		try {
			marcas = obtemLista(Marca.class, "Select * from marca");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return marcas;
	}

}
