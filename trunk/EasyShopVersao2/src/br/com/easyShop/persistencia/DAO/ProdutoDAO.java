package br.com.easyShop.persistencia.DAO;

import java.util.List;

import flex.messaging.FlexContext;

import br.com.easyShop.model.Produto;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.conexao.BancoDeDados;
import br.com.easyShop.persistencia.conexao.servidores.base.BaseJDBC;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.persistencia.utils.ResultSQL;
import br.com.easyShop.utils.Constantes;

public class ProdutoDAO extends BaseDAOAtta {

	public List<Produto> getProdutos(int profundidade) throws Exception {
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM Produto");

		return obtem(Produto.class, query, profundidade);
	}

	/**
	 * Buscas os produtos atraés do nome
	 *
	 * @author Jean
	 * @param nome
	 * @param profundidade
	 * @return lista de produtos
	 * @throws Exception
	 */
	public List<Produto> getProdutosNome(String nome, int profundidade)throws Exception
	{
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM Produto");
		query.add(" WHERE UPPER(nome) like UPPER(?)", "%" + nome + "%");

		return obtem(Produto.class, query, profundidade);
	}

	public Produto getProduto(int profundidade, long id) throws Exception {

		QuerySQL query = new QuerySQL();
		query.add("SELECT * ");
		query.add("FROM produto ");
		query.add("WHERE pkProduto = ?", id);

		return obtemUnico(Produto.class, query, profundidade);
	}

	public long getQuantidadeProdutos() throws Exception {

		QuerySQL query = new QuerySQL();
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext
				.getFlexSession());
		ResultSQL resultSQL;

		query.add("SELECT COUNT (*)");
		query.add(" FROM produto");

		resultSQL = baseJDBC.executaSelect(query, false);

		return (Long) resultSQL.get(0, 0);
	}

	public List<Produto> getProdutosPromocao() throws Exception
	{
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM produto");
		query.add(" WHERE promocao = ?", true);
		query.add(" AND status = ?", Constantes.STATUS_ATIVO);
		
		return obtem(Produto.class, query, -1);
	}
}
