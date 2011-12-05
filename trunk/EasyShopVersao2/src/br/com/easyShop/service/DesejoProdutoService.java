package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Desejo;
import br.com.easyShop.model.DesejoProduto;
import br.com.easyShop.persistencia.DAO.DesejoDAO;
import br.com.easyShop.persistencia.DAO.DesejoProdutoDAO;
import br.com.easyShop.utils.Constantes;

public class DesejoProdutoService {
	
	public ResultJava getDesejoProduto(Cliente cliente) {
		try
		{
			return new ResultJava( new DesejoProdutoDAO().getMeusDesejosProdutos(cliente, 3));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar desejoProduto\n" + e }));
		}
		
	}
	
	public ResultJava removerDesejo(DesejoProduto desejoProduto) {
		try
		{
			desejoProduto.setStatus(Constantes.STATUS_REMOVIDO);  
			return new ResultJava(new DesejoProdutoDAO().alterar(desejoProduto));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao atualizar carrinho produtos!\n" + e }));
		}
	}
	
	public ResultJava inserirDesejo(DesejoProduto desejoProduto) {
		try
		{
			DesejoService desejoService = new DesejoService();
			desejoProduto.getDesejo().setStatus(Constantes.STATUS_ATIVO);
			desejoService.inserir(desejoProduto.getDesejo());
			
			desejoProduto.setStatus(Constantes.STATUS_ATIVO);
			
			return new ResultJava(new DesejoProdutoDAO().salvar(desejoProduto));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao enviar desejos!\n" + e }));
		}
	}
	
	public ResultJava getMeusDesejosProduto(Cliente cliente) {
		try
		{
			Desejo desejo = new Desejo();
			desejo = new DesejoDAO().getDesejo(-1, cliente);
			
			return new ResultJava(new DesejoProdutoDAO().getMeusDesejos(desejo, -1));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar meus desejos!\n" + e }));
		}
	}

}
