package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.utils.Constantes;

public class CarrinhoProdutoDAO extends BaseDAOAtta {

	public List<CarrinhoProduto> getCarrinhoProdutos(Cliente cliente, int profundidade) throws Exception  
	 {
		 QuerySQL query = new QuerySQL();
			
		 query.add("SELECT *");
		 query.add(" FROM carrinhoproduto,carrinho");
		 query.add(" WHERE carrinho.fkcliente = ?", cliente.getPkCliente());
		 query.add(" AND carrinhoproduto.fkcarrinho = carrinho.pkcarrinho");
		 query.add(" AND carrinhoproduto.status = ?", Constantes.STATUS_ATIVO);
		
		 return obtem(CarrinhoProduto.class, query, profundidade);
   }
}
