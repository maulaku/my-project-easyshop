package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Tela;
import br.com.easyShop.persistencia.DAO.TelaDAO;

public class TelaService {

	public List<Tela> getTelas(){
		TelaDAO telaDAO = new TelaDAO();
		return telaDAO.getTelas();
	}
}