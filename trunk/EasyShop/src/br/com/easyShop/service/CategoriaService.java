package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Categoria;
import br.com.easyShop.persistencia.DAO.CategoriaDAO;

public class CategoriaService {

	public List<Categoria> getAllCategorias(){
		CategoriaDAO categoriaDao = new CategoriaDAO();
		return categoriaDao.getAllCategorias();
	}

	public void inserirCategoria(Categoria categoria){
		try {
			CategoriaDAO.inserir(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Categoria> getSubCategorias(Categoria categoria){
		CategoriaDAO categoriaDao = new CategoriaDAO();
		return categoriaDao.getCategoriasSub(categoria);
	}

	public List<Categoria> getCategorias(){
		CategoriaDAO categoriaDao = new CategoriaDAO();
		return categoriaDao.getCategorias();
	}

}
