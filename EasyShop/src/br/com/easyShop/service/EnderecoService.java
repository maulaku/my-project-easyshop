package br.com.easyShop.service;

import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.EnderecoDAO;

public class EnderecoService {

	private static Endereco endereco;
	
	public EnderecoService(){
		
	}

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
	
	public Endereco getEndereco(Pessoa pessoa){
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		return enderecoDAO.getEndereco(pessoa);
	}

}
