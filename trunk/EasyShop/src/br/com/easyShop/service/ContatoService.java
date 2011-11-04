package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Contato;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.ContatoDAO;

public class ContatoService {	
	public ContatoService(){
	}

	public void inserirContato(Contato contato){
		try {
			ContatoDAO.inserir(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Contato contato){
		try {
			ContatoDAO.atualizar(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Contato> getContatos(Pessoa pessoa){
		ContatoDAO contatoDAO = new ContatoDAO();
		return contatoDAO.getContatos(pessoa);
	}

}
