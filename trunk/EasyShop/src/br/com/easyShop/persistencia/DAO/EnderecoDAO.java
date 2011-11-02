package br.com.easyShop.persistencia.DAO;

import javax.swing.JOptionPane;

import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAO;

public class EnderecoDAO extends BaseDAO {
	
	public Endereco getEndereco(Pessoa pessoa){
		Endereco endereco = new Endereco();
		try {
			endereco = (Endereco) obtemUnico(Endereco.class,"fkPessoa = " + pessoa.getPkPessoa());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta!");
		}
			return endereco;
	}

}
