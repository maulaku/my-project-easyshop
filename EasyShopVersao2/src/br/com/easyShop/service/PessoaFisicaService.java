package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
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
	
//	public ResultJava salvar(PessoaFisica pessoaFisica) {
//		try
//		{
//			return new ResultJava(new PessoaFisicaDAO().inserir(pessoaFisica));
//		} 
//		catch (Exception e) 
//		{
//			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao inserir pessoa fisica!\n" + e }));
//		}
//	}

}
