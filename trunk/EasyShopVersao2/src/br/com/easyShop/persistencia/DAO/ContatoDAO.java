package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Contato;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class ContatoDAO extends BaseDAOAtta
{
	public List<Contato> getContatosPessoa(Pessoa pessoa, int profundidade) throws Exception
	{
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM Contato");
		query.add(" WHERE fkPessoa = ?", pessoa.getPkPessoa());
		
		return obtem(Contato.class, query, profundidade);		
    }
}
