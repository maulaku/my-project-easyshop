package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Cidade;
import br.com.easyShop.model.Estado;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class CidadeDAO extends BaseDAO {
	
	public List<Cidade> getCidades(Estado estado){	
		List<Cidade> cidades = null;
		try {
			cidades = obtemLista(Cidade.class,"Select * from Cidade  where fkestado = " + estado.getPkEstado());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cidades;
	}

}
