package br.com.easyShop.service;

import br.com.easyShop.model.PessoaJuridica;
import br.com.easyShop.persistencia.DAO.PessoaJuridicaDAO;

public class PessoaJuridicaService {
	
private static PessoaJuridica pessoaJuridica;
	
    public PessoaJuridicaService(PessoaJuridica pessoaJuridica) {
		PessoaJuridicaService.pessoaJuridica = pessoaJuridica;
	}

	public void inserirPessoaJuridica(){
		try {
			PessoaJuridicaDAO.inserir(pessoaJuridica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
