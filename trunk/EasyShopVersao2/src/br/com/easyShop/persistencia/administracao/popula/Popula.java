package br.com.easyShop.persistencia.administracao.popula;

import br.com.easyShop.persistencia.conexao.BancoDeDados;


public class Popula implements PopulaBanco
{
	/**
	 * Popula o banco com as informacoes iniciais ou para teste
	 */
	public void popular(boolean versaoFinal)
	{ 
		try
		{
			BancoDeDados.conectar();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
