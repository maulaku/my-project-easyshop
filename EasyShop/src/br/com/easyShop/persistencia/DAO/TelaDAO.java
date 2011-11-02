package br.com.easyShop.persistencia.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.easyShop.model.Tela;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class TelaDAO extends BaseDAO{
	 public List<Tela> getTelas(){
	    	List<Tela> telas = new ArrayList<Tela>();
	    	try {
				telas = obtemLista(Tela.class, "select * from tela");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return telas;
	    }

}
