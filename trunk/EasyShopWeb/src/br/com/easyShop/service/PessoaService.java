package br.com.easyShop.service;

import br.com.easyShop.model.Pessoa;
import br.com.easyShop.model.PessoaFisica;
import br.com.easyShop.persistencia.DAO.PessoaDAO;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.service.base.BaseServiceAtta;

public class PessoaService extends BaseServiceAtta {

	public void inserirPessoa(Pessoa pessoa) {
		try {
			new PessoaDAO().salvar(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atulizar(Pessoa pessoa) {
		try {
			inserirPessoa(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pessoa getPessoa(PessoaFisica pessoaFisica, int profundidade) {

		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM pessoa");
		query.add(" WHERE fkPessoaFisica = ?", pessoaFisica.getPkPessoaFisica());

		try {
			return new PessoaDAO()
					.obtemUnico(Pessoa.class, query, profundidade);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
