package br.com.easyShop.service;

import br.com.easyShop.model.Contato;
import br.com.easyShop.persistencia.DAO.ContatoDAO;

public class ContatoService {
	
	private static Contato contato;
	
	public ContatoService(Contato contato){
		ContatoService.contato = contato;
	}

	public void inserirContato(){
		try {
			ContatoDAO.inserir(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(){
		try {
			ContatoDAO.atualizar(contato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
