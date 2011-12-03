package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.model.UsuarioTela;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class UsuarioTelaDAO extends BaseDAOAtta{
	
	public List<UsuarioTela> getUsuarioTelas(Usuario usuario, int profundidade) throws Exception  
	 {
		 QuerySQL query = new QuerySQL();
			
		 query.add("SELECT *");
		 query.add(" FROM UsuarioTela");
		 query.add(" WHERE fkUsuario = ?", usuario.getPkUsuario());
		 
		 return obtem(UsuarioTela.class, query, profundidade);
    }
	
	public UsuarioTela getUsuarioTelasSelecionado(Usuario usuario, Long fkTela, Long fkTipoPermissao, int profundidade) throws Exception  
	 {
		 Long num = Long.parseLong("2");
		 
		 QuerySQL query = new QuerySQL();
			
		 query.add("SELECT *");
		 query.add(" FROM UsuarioTela");
		 query.add(" WHERE fkUsuario = ?", usuario.getPkUsuario());
		 query.add(" AND fkTela = ?", fkTela);
		 query.add(" AND fkTipoPermissao = ?", fkTipoPermissao);
		 query.add(" OR fkTipoPermissao = ?", num);
		 
		 return obtemUnico(UsuarioTela.class, query, profundidade);
   }
}
