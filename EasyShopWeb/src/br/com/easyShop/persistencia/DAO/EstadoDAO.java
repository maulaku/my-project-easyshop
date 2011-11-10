package br.com.easyShop.persistencia.DAO;

import java.util.List;
import br.com.easyShop.model.Estado;
import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class EstadoDAO extends BaseDAO {

	public List<Estado> getEstados(Pais pais) {
		List<Estado> estados = null;
		try {
			estados = obtemLista(Estado.class,
					"Select * from Estado where fkpais = " + pais.getPkPais());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return estados;
	}

//	public Estado getEstado(String nome) {
//		Estado estado = new Estado();
//
//		try {
//			estado = (Estado) obtemUnico(Estado.class,
//					"pkEstado = \"" + nome.toString() + "\"");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return estado;
//	}

}
