package br.com.easyShop.persistencia.DAO;

import java.util.List;

import utils.IntegerUtil;
import br.com.easyShop.model.Categoria;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;
import br.com.easyShop.utils.Constantes;

public class CategoriaDAO extends BaseDAO{


	public List<Categoria> getAllCategorias(){
		List<Categoria> categorias = null;
		try {
			categorias = obtemLista(Categoria.class, "Select * from categoria");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categorias;
	}

	public List<Categoria> getCategoriasSub(Categoria categoria){
		List<Categoria> subCategorias = null;
		try {
			subCategorias = obtemLista(Categoria.class, "Select * from Categoria where fkcategoria = " + categoria.getPkCategoria());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return subCategorias;
	}

	public List<Categoria> getCategorias(){
		List<Categoria> subCategorias = null;
		try {
			subCategorias = obtemLista(Categoria.class, "Select * from Categoria where fkcategoria is not null " );
		} catch (Exception e) {
			e.printStackTrace();
		}

		return subCategorias;
	}
	
	public List<Categoria> getTodasCategoriasPai() throws Exception
	{
		String query;
		
		query = "SELECT * FROM categoria WHERE TIPO = "+IntegerUtil.toString(Constantes.CATEGORIA_PAI);
		
		return obtemLista(Categoria.class, query);
	}
}
