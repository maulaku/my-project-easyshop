package br.com.easyShop.service;

import br.com.easyShop.model.PessoaJuridica;
import br.com.easyShop.persistencia.DAO.PessoaJuridicaDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class PessoaJuridicaService extends BaseServiceAtta
{
	
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
	
	public void atualizar(){
		try {
			PessoaJuridicaDAO.atualizar(pessoaJuridica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
