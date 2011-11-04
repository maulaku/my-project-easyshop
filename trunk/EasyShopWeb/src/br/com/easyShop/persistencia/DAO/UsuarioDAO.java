package br.com.easyShop.persistencia.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;


public class UsuarioDAO extends BaseDAO {

    public List<Usuario> getUsuarios(){
    	List<Usuario> usuarios = new ArrayList<Usuario>();
    	try {
			usuarios = obtemLista(Usuario.class, "select * from usuario");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return usuarios;
    }
    
    public Usuario getUsuario(String nome){
    	Usuario usuario = new Usuario();
    	try {
			usuario = (Usuario) obtemUnico(Usuario.class,"login = \"" + nome.toString() + "\"");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta!");
		}
			return usuario;
    }
}
