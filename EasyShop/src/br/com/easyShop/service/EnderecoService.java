package br.com.easyShop.service;

import br.com.easyShop.model.Endereco;
import br.com.easyShop.persistencia.DAO.EnderecoDAO;

public class EnderecoService {

	private static Endereco endereco;

	public EnderecoService(Endereco endereco){
		 EnderecoService.endereco = endereco;
	}

	public void inserir(){
		try {
			EnderecoDAO.inserir(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
