package br.com.easyShop.service;


import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Desejo;
import br.com.easyShop.persistencia.DAO.DesejoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class DesejoService extends BaseServiceAtta{
	
	public void inserir(Desejo desejo){
		
		try {
			new DesejoDAO().salvar(desejo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Desejo getDesejo(Cliente cliente){
		
		DesejoDAO desejoDao = new DesejoDAO();
		try {
			return desejoDao.getDesejo(0, cliente);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}