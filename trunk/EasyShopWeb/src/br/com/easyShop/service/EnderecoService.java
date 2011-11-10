package br.com.easyShop.service;

import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.EnderecoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class EnderecoService extends BaseServiceAtta
{

	private static Endereco endereco;
	
	public EnderecoService(){ }

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

	public void atualizar(){
		try {
			EnderecoDAO.atualizar(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(Endereco endereco){
		try {
			EnderecoDAO.atualizar(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
