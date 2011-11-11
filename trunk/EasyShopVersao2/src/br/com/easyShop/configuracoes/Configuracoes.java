package br.com.easyShop.configuracoes;

import java.io.InputStream;
import java.util.Properties;

import logs.Logs;
import propriedades.Propriedades;

public class Configuracoes
{
	/*-*-*-* Variaveis e Objetos Publicos *-*-*-*/
	/**
	 * Contem as configuracoes de arquivo
	 */
	public static Propriedades propriedades;
	
	
	/*-*-*-* Bloco Estatico *-*-*-*/
	public static void carregar(InputStream configuracoes, InputStream log4j)
	{
		try 
		{
			propriedades = new Propriedades("configuracoes.properties", configuracoes);
			
			Properties propLog = new Properties();
			propLog.load(log4j);
			Logs.setProperties(propLog);
		}
		catch(Exception e) 	
		{
			Logs.addFatal("Carregando configuracoes, " + configuracoes + ", " + log4j, e);
		}
	}
}
