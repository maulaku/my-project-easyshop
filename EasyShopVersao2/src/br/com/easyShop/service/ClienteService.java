package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.persistencia.DAO.ClienteDAO;
import br.com.easyShop.persistencia.DAO.PessoaFisicaDAO;
import br.com.easyShop.persistencia.DAO.PessoaJuridicaDAO;

public class ClienteService {
	
	public void inserirCliente(Cliente cliente) {
		try {
			new ClienteDAO().inserir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultJava getCliente(String nome, String senha) {
		try
		{
			return new ResultJava(new ClienteDAO().getClienteNome(nome, senha, 3));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar clientes!\n" + e }));
		}
	}
	
	public ResultJava salvarCliente(Cliente cliente) {
		try
		{
			int i;
			
			if(cliente.getPessoa().getPessoaFisica()!=null){
				PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
				pessoaFisicaDAO.salvar(cliente.getPessoa().getPessoaFisica());	
			}else{
				PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
				pessoaJuridicaDAO.salvar(cliente.getPessoa().getPessoaJuridica());					
			}
			
			PessoaService pessoaService = new PessoaService();
			pessoaService.inserirPessoa(cliente.getPessoa());

			EnderecoService enderecoService = new EnderecoService();
			for(i=0;i<cliente.getPessoa().getEnderecos().size();i++){
				enderecoService.salvar(cliente.getPessoa().getEnderecos().get(i));
			}
			
			ContatoService contatoService = new ContatoService();
			for(i=0;i<cliente.getPessoa().getContatos().size();i++){
				contatoService.salvarContato(cliente.getPessoa().getContatos().get(i));
			}
			
			UsuarioService usuarioService = new UsuarioService();
			for(i=0;i<cliente.getPessoa().getUsuarios().size();i++){
				usuarioService.salvar(cliente.getPessoa().getUsuarios().get(i));
			}
			
			return new ResultJava(new ClienteDAO().salvar(cliente));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao cadastrar clientes!\n" + e }));
		}
	}
}
