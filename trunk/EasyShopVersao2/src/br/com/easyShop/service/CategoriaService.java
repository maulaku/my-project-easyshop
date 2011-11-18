package br.com.easyShop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Categoria;
import br.com.easyShop.persistencia.DAO.CategoriaDAO;
import br.com.easyShop.service.base.BaseServiceAtta;
import br.com.easyShop.utils.Constantes;

public class CategoriaService extends BaseServiceAtta
{

	private List<Categoria> categoriasPais = new ArrayList<Categoria>();

	public List<Categoria> getAllCategorias() 
	{
		try
		{
			return new CategoriaDAO().getAllCategorias(-1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void inserirCategoria(Categoria categoria) {
		try 
		{
			new CategoriaDAO().salvar(categoria);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public List<Categoria> getSubCategorias(Categoria categoria) 
	{
		try
		{
			return new CategoriaDAO().getCategoriasSub(categoria, -1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public List<Categoria> getCategorias() 
	{
		try
		{
			return new CategoriaDAO().getCategorias(-1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	private void addSubCategoria(Categoria categoria) {
		categoriasPais.add(categoria);
	}

	public List<Categoria> getPaisSolteiros() {

		List<Categoria> categorias = new ArrayList<Categoria>();
		
		try
		{
			categorias = new CategoriaDAO().getAllCategorias(-1);

			for (Categoria categoria : categorias)
			{
	
				List<Categoria> subCategoria = new ArrayList<Categoria>();
				subCategoria = new CategoriaDAO().getCategoriasSub(categoria, -1);
	
				if (subCategoria.size() == 0) {
					addSubCategoria(categoria);
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return categoriasPais;
	}

	/**
	 * Busca todas categorias 
	 * @author Jean
	 * @Uso MainEasyShop
	 * @return lista de categorias
	 */
	public ResultJava getTodasCategoriasPai() {
		try
		{
			return new ResultJava(true, new CategoriaDAO().getTodasCategoriasTipo(Constantes.CATEGORIA_PAI, -1));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar categorias\n" + e }));
		}
	}
	
	public ResultJava getTodasCategoriasSub(Categoria categoria) {
		try
		{
			return new ResultJava(true, new CategoriaDAO().getCategoriasSub(categoria,3));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar sub categorias\n" + e }));
		}
	}
}
