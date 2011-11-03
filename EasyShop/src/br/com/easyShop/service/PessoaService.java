package br.com.easyShop.service;

import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.PessoaDAO;

public class PessoaService {
	
	private static Pessoa pessoa;
	
	public PessoaService(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void inserirPessoa(){
		try {
			PessoaDAO.inserir(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atulizar(){
		try {
			PessoaDAO.atualizar(this.pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
