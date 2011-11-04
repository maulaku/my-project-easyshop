package br.com.easyShop.persistencia.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.model.UsuarioTela;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class UsuarioTelaDAO extends BaseDAO{
	
	public List<UsuarioTela> getUsuarioTelas(Usuario usuario){
    	List<UsuarioTela> usuarioTelas = new ArrayList<UsuarioTela>();
    	try {
			usuarioTelas = obtemLista(UsuarioTela.class, "select * from usuariotela where fkusuario= " + usuario.getPkUsuario());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return usuarioTelas;
    }
}
