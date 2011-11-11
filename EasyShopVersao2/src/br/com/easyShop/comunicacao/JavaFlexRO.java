package br.com.easyShop.comunicacao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logs.Logs;
import utils.data.Data;
import flex.messaging.io.ArrayCollection;

/**
 * Classe responsavel por conter o metodo de acesso ao java utilizado pelo flex
 */
public class JavaFlexRO
{
	/**
	 * Enderecos iniciais de pacotes utilizado por todas as classes de acesso ao java
	 * Deve ser previamente inicializado, ex: prePackages.add("br.com.mresolucoes.services")
	 */
	public static List<String> prePackages = new ArrayList<String>();
		
	/**
	 * Utilizado para chamar metodos dentro do java utilizando reflection
	 * @param pacoteClasseMetodo endereco da classe a partir do pacote aplicacao, ex: modulos.vendas.CadastroCliente
	 * @return JavaResult com os resultados da consulta
	 */
	public ResultJava getJava(String pacoteClasseMetodo, Object usuario)
	{
		return getJava(pacoteClasseMetodo, new Object[0], usuario);
	}
	
	/**
	 * Utilizado para chamar metodos dentro do java utilizando reflection
	 * @param pacoteClasseMetodo endereco da classe a partir do pacote aplicacao, ex: modulos.vendas.CadastroCliente
	 * @param parametros lista de parametros a serem passados para o metodos
	 * @return JavaResult com os resultados da consulta
	 */
	public ResultJava getJava(String pacoteClasseMetodo, Object[] parametros)
	{
		return getJava(pacoteClasseMetodo, parametros, null);
	}
	
	/**
	 * Utilizado para chamar metodos dentro do java utilizando reflection
	 * @param pacoteClasseMetodo endereco da classe a partir do pacote aplicacao, ex: modulos.vendas.CadastroCliente
	 * @param parametros lista de parametros a serem passados para o metodos
	 * @param usuario com a informacao do usuario que requisitou o service
	 * @return JavaResult com os resultados da consulta
	 */
	public ResultJava getJava(String pacoteClasseMetodo, Object[] parametros, Object usuario)
	{
		try
		{
			String pacoteClasse = pacoteClasseMetodo.substring(0, pacoteClasseMetodo.lastIndexOf("."));
			String metodo = pacoteClasseMetodo.substring(pacoteClasseMetodo.lastIndexOf(".")+1, pacoteClasseMetodo.length());

			Object[] metodoJava = getMetodoJava(pacoteClasse, metodo, parametros);
			if(metodoJava!=null && metodoJava.length>=2 && metodoJava[0]!=null && metodoJava[1]!=null)
			{
				return (ResultJava)((Method)metodoJava[1]).invoke((metodoJava[0]), parametros);
			}
			else
			{
				Logs.addFatal("Falha ao verificar os parametros para chamada de metodo: " + pacoteClasseMetodo);
			}
		}
		catch(Exception e) 
		{ 
			Logs.addFatal("Falha invocando metodo: " + pacoteClasseMetodo, e);
		}
		
		return null;
	}
	
	
	/**
	 * Obtem uma instancia do objeto (object) e o metodo (Method) do java
	 * @return [0]object, [1]Method
	 */
	private Object[] getMetodoJava(String pacoteClasse, String metodo, Object[] parametros)
	{
		Object instancia = null;
		
		for(int i=0; i<prePackages.size(); i++)
		{
			try
			{
				instancia = Class.forName(prePackages.get(i) + "." + pacoteClasse).newInstance();
				break;
			}
			catch(Exception e) 
			{ instancia=null; }
		}
		
		try 
		{
			if(instancia!=null)
			{
				Class<?>[] fParametros;
				procurandoMetodos: for(int i=0; i<instancia.getClass().getMethods().length; i++)
				{
					if(instancia.getClass().getMethods()[i].getName().equals(metodo))
					{
						fParametros = instancia.getClass().getMethods()[i].getParameterTypes();
						if(parametros==null && (fParametros==null || fParametros.length<=0))
						{
							return new Object[]{instancia, instancia.getClass().getMethods()[i]};
						}
						else if(parametros!=null && (parametros.length == fParametros.length))
						{
							for(int j=0; j<fParametros.length; j++)
							{
								if(parametros[j]!=null && parametros[j].getClass()!=fParametros[j])
								{
									if(parametros[j] instanceof Integer && (fParametros[j]==Double.class || fParametros[j]==double.class))
									{
										parametros[j] = new Double((Integer)parametros[j]);
									}
									else if(parametros[j] instanceof Integer && (fParametros[j]==Long.class || fParametros[j]==long.class))
									{
										parametros[j] = new Long((Integer)parametros[j]);
									}
									else if(parametros[j] instanceof Boolean && (fParametros[j]==Boolean.class || fParametros[j]==boolean.class))
									{
										//Nao precisa mexer
									}
									else if(parametros[j] instanceof Integer && (fParametros[j]==Integer.class || fParametros[j]==int.class))
									{
										//Nao precisa mexer
									}
									else if(parametros[j] instanceof ArrayCollection && (fParametros[j]==List.class || fParametros[j]==ArrayList.class))
									{
										//Nao precisa mexer
									}
									else if(parametros[j] instanceof Date && fParametros[j]==Data.class)
									{
										parametros[j] = new Data((Date)parametros[j]);
									}
									else if(fParametros[j]==Object.class)
									{
										//Nao precisa mexer
									}
									else
									{
										continue procurandoMetodos;
									}
								}
							}
							return new Object[]{instancia, instancia.getClass().getMethods()[i]};
						}
					}
				}
			}
			else
			{
				Logs.addFatal("Metodo java nao encontrado: " + pacoteClasse + "." + metodo);
			}
		} 
		catch(Exception e) 
		{
			Logs.addFatal("Falha obtendo metodo java: " + pacoteClasse + "." + metodo, e);
		}

		return null;
	}
}
