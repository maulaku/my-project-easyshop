package br.com.easyShop.aplicacao;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.easyShop.comunicacao.FlexProxy;
import br.com.easyShop.comunicacao.JavaFlexRO;

import flex.messaging.io.PropertyProxyRegistry;

import logs.Logs;


public class MainEasyShop implements Servlet
{
	@Override
	public void init(ServletConfig arg0) throws ServletException
	{
		try
		{
			PropertyProxyRegistry.getRegistry().register(Object.class, new FlexProxy());
			
			JavaFlexRO.prePackages.add("br.com.easyShop.service");
			
			System.out.println("SISTEMA EASYSHOP INICIALIZADO");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			return;
		}
		
	}

	@Override
	public void destroy() 
	{ 
		try
		{
			
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
