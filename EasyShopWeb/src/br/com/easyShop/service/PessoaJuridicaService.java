package br.com.easyShop.service;

import br.com.easyShop.model.PessoaJuridica;
import br.com.easyShop.persistencia.DAO.PessoaJuridicaDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class PessoaJuridicaService extends BaseServiceAtta {
	public void inserirPessoaJuridica(PessoaJuridica pessoaJuridica) {
		try {
			new PessoaJuridicaDAO().salvar(pessoaJuridica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(PessoaJuridica pessoaJuridica) {
		try {
			inserirPessoaJuridica(pessoaJuridica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
