package br.com.easyShop.persistencia.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.easyShop.model.TipoPermissao;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class TipoPermissaoDAO extends BaseDAO{
	 public List<TipoPermissao> geTipoPermissao(){
	    	List<TipoPermissao> tipoPermissoes = new ArrayList<TipoPermissao>();
	    	try {
				tipoPermissoes = obtemLista(TipoPermissao.class, "select * from tipopermissao");
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return tipoPermissoes;
	    }

}
