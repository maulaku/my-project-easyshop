package br.com.easyShop.service;

import br.com.easyShop.model.PessoaFisica;
import br.com.easyShop.persistencia.DAO.PessoaFisicaDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class PessoaFisicaService extends BaseServiceAtta 
{

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
