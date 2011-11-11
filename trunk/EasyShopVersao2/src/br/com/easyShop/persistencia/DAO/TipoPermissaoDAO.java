package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.TipoPermissao;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class TipoPermissaoDAO extends BaseDAOAtta
{
	 public List<TipoPermissao> geTipoPermissao(int profundidade) throws Exception  
	 {
		 QuerySQL query = new QuerySQL();
			
		 query.add("SELECT *");
		 query.add(" FROM TipoPermissao");
		
		 return obtem(TipoPermissao.class, query, profundidade);
	 }

}
