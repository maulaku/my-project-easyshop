package br.com.easyShop.persistencia.DAO;

import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class EnderecoDAO extends BaseDAOAtta {
	
	public Endereco getEnderecoPessoa(Pessoa pessoa, int profundidade) throws Exception
	{
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM endereco");
		query.add(" WHERE fkPessoa = ?", pessoa.getPkPessoa());
		
		return obtemUnico(Endereco.class, query, profundidade);
	}

}
