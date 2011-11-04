package br.com.easyShop.persistencia.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.easyShop.model.Contato;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class ContatoDAO extends BaseDAO{
	public List<Contato> getContatos(Pessoa pessoa){
    	List<Contato> contatos = new ArrayList<Contato>();
    	try {
			contatos = obtemLista(Contato.class, "select * from contato where fkpessoa = " + pessoa.getPkPessoa());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return contatos;
    }
}
