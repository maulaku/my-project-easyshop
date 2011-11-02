package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.TipoContato;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class TipoContatoDAO extends BaseDAO{
	
	public List<TipoContato> getTiposContatos(){
		
		List<TipoContato> tipos = null;
		try {
			tipos = obtemLista(TipoContato.class, "Select * from TipoContato");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tipos;
	}

}
