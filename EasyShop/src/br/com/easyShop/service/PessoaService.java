package br.com.easyShop.service;

import br.com.easyShop.model.Pessoa;
import br.com.easyShop.model.PessoaFisica;
import br.com.easyShop.model.Produto;
import br.com.easyShop.persistencia.DAO.PessoaDAO;
import br.com.easyShop.persistencia.DAO.ProdutoDAO;

public class PessoaService {
	
	private static Pessoa pessoa;
	
	public PessoaService(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaService() {
	}

	public void inserirPessoa(){
		try {
			PessoaDAO.inserir(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atulizar(){
		try {
			PessoaDAO.atualizar(this.pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void atulizar(Pessoa pessoa){
		try {
			PessoaDAO.atualizar(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Pessoa getPessoa(PessoaFisica pessoaFisica){
           try {
				return (Pessoa) PessoaDAO.obtemUnico(Pessoa.class, "fkPessoaFisica = " + pessoaFisica.getPkPessoaFisica() );
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

}
