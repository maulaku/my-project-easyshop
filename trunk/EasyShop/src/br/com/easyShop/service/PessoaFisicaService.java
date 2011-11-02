package br.com.easyShop.service;

import br.com.easyShop.model.PessoaFisica;
import br.com.easyShop.persistencia.DAO.PessoaFisicaDAO;

public class PessoaFisicaService {

private static PessoaFisica pessoaFisica;
	
	public PessoaFisicaService(PessoaFisica pessoaFisica) {
		PessoaFisicaService.pessoaFisica = pessoaFisica;
	}

	public void inserirPessoaFisica(){
		try {
			PessoaFisicaDAO.inserir(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(){
		try {
			PessoaFisicaDAO.atualizar(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
