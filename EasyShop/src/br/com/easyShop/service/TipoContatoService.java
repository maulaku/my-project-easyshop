package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.TipoContato;
import br.com.easyShop.persistencia.DAO.TipoContatoDAO;

public class TipoContatoService {
	
	public List<TipoContato> getlistaTipoContatos(){
		TipoContatoDAO tipoDAO = new TipoContatoDAO();
		return tipoDAO.getTiposContatos();
	}

}
