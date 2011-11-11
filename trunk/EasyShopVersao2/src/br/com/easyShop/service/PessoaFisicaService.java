package br.com.easyShop.service;

import br.com.easyShop.model.PessoaFisica;
import br.com.easyShop.persistencia.DAO.PessoaFisicaDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class PessoaFisicaService extends BaseServiceAtta {
	public void inserirPessoaFisica(PessoaFisica pessoaFisica) {
		try {
			new PessoaFisicaDAO().salvar(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(PessoaFisica pessoaFisica) {
		try {
			inserirPessoaFisica(pessoaFisica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
