package br.com.easyShop.service;

import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.persistencia.DAO.CarrinhoProdutoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;
import br.com.easyShop.utils.Constantes;

public class CarrinhoProdutoService extends BaseServiceAtta
{
	public void inserir(CarrinhoProduto carrinhoProduto)
	{
		try 
		{
			new CarrinhoProdutoDAO().salvar(carrinhoProduto);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public ResultJava inserirCarrinho(CarrinhoProduto carrinhoProduto) {
		try
		{
			CarrinhoService carrinhoService = new CarrinhoService();
			carrinhoService.inserir(carrinhoProduto.getCarrinho());
			
			return new ResultJava(new CarrinhoProdutoDAO().salvar(carrinhoProduto));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao enviar carrinho produtos!\n" + e }));
		}
	}
	
	public ResultJava getCarrinhoProdutos(Cliente cliente) {
		try
		{
			return new ResultJava(new CarrinhoProdutoDAO().getCarrinhoProdutos(cliente, 3));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar carrinho produtos!\n" + e }));
		}
	}
	
	public ResultJava atualizaCarrinhoProdutos(Cliente cliente) {
		try
		{
			List<CarrinhoProduto> carrinhoProdutos = (new CarrinhoProdutoDAO().getCarrinhoProdutos(cliente, 3));
			int i;
			
			for(i=0;i<carrinhoProdutos.size();i++){
				carrinhoProdutos.get(i).setStatus(Constantes.STATUS_REMOVIDO);
				new CarrinhoProdutoDAO().alterar(carrinhoProdutos.get(i));
			}
			
			return null;
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar carrinho produtos!\n" + e }));
		}
	}
}
