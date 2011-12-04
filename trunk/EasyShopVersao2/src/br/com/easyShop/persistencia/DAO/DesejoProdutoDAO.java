package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Desejo;
import br.com.easyShop.model.DesejoProduto;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class DesejoProdutoDAO extends BaseDAOAtta{
	

	public List<DesejoProduto> getMeusDesejos(Desejo desejo, int profundidade) throws Exception{
		
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM DesejoProduto");
		query.add(" WHERE fkDesejo = ?", desejo.getPkDesejo());

		return obtem(DesejoProduto.class, query, profundidade);
	}
	
	public List<DesejoProduto> getMeusDesejosProdutos(Cliente cliente, int profundidade) throws Exception {
		QuerySQL query = new QuerySQL();

		 query.add("SELECT *");
		 query.add(" FROM desejoproduto,desejo");
		 query.add(" WHERE desejo.fkcliente = ?", cliente.getPkCliente());
		 query.add(" AND desejoproduto.fkdesejo = desejo.pkdesejo");

		 return obtem(DesejoProduto.class, query, profundidade);
	}


}
