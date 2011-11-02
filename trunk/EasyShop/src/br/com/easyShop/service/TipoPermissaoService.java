package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.TipoPermissao;
import br.com.easyShop.persistencia.DAO.TipoPermissaoDAO;

public class TipoPermissaoService {
	public List<TipoPermissao> getTipoPermissao(){
		TipoPermissaoDAO tipoPermissaoDAO = new TipoPermissaoDAO();
		return tipoPermissaoDAO.geTipoPermissao();
	}
}
