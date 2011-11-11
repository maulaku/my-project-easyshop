package br.com.easyShop.aplicacao;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import logs.Logs;
import br.com.easyShop.comunicacao.FlexProxy;
import br.com.easyShop.comunicacao.JavaFlexRO;
import br.com.easyShop.configuracoes.Configuracoes;
import br.com.easyShop.persistencia.conexao.BancoDeDados;
import flex.messaging.io.PropertyProxyRegistry;


public class MainEasyShop implements Servlet
{
	@Override
	public void init(ServletConfig arg0) throws ServletException
	{
		try
		{
			PropertyProxyRegistry.getRegistry().register(Object.class, new FlexProxy());

			JavaFlexRO.prePackages.add("br.com.easyShop.service");

			Configuracoes.carregar(Configuracoes.class.getResourceAsStream("configuracoes.properties"), Configuracoes.class.getResourceAsStream("log4j.properties"));

			System.out.println("Iniciando DB...");
			BancoDeDados.conectar();
			System.out.println("SISTEMA EASYSHOP INICIALIZADO COM SUCESSO");
		}
		catch(Exception e)
		{
			System.out.println("SISTEMA EASYSHOP NÃO INICIALIZADO");
			e.printStackTrace();

			return;
		}

	}

	@Override
	public void destroy()
	{
		try
		{
			BancoDeDados.desconectar();
		}
		catch(Exception e)
		{
			Logs.addFatal("Falha desativando o sistema", e);
		}
	}

	@Override
	public ServletConfig getServletConfig() { return null; }

	@Override
	public String getServletInfo() { return null; }

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException { }
}
