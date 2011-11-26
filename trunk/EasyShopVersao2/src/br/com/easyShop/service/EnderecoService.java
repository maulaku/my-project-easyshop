package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.EnderecoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class EnderecoService extends BaseServiceAtta {
	public void salvar(Endereco endereco) {
		try {
			new EnderecoDAO().salvar(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Endereco getEnderecoPessoa(Pessoa pessoa) {
		try {
			return new EnderecoDAO().getEnderecoPessoa(pessoa, 3);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void atualizar(Endereco endereco) {
		salvar(endereco);
	}
	
	public ResultJava getEnderecosCliente(Pessoa pessoa) {
		try
		{
			return new ResultJava(new EnderecoDAO().getEnderecosPessoa(pessoa, 3));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar enderecos!\n" + e }));
		}
	}
}
