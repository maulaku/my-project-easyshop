package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Estado;
import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.EstadoDAO;

public class EstadoService {
	
	public List<Estado> getEstados(Pais pais)
	{
		return new EstadoDAO().getEstados(pais);
	}
	
	public static Estado getEstado(String nome)
	{
		return new EstadoDAO().getEstado(nome);
	}

}
