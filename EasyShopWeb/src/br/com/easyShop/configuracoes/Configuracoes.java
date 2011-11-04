package br.com.easyShop.configuracoes;

import java.io.InputStream;

import logs.Logs;
import propriedades.Propriedades;

public class Configuracoes
{
	/*-*-*-* Variaveis e Objetos Publicos *-*-*-*/
	/**
	 * Contem as configuracoes de arquivo
	 */
	public static Propriedades propriedades;
	
	public static byte[] impressaoESCP;
	public static byte[] impressaoZebra;
	
	
	/*-*-*-* Bloco Estatico *-*-*-*/
	public static void carregar(InputStream configuracoes, InputStream log4j)
	{
		try 
		{
			propriedades = new Propriedades("configuracoes.properties", configuracoes);
			
			Logs.setProperties(new Propriedades("log4j.properties", log4j).getProperties());
			
			InputStream is = Configuracoes.class.getResourceAsStream("impressao/modeloESCP.html");
			impressaoESCP = new byte[is.available()];
			is.read(impressaoESCP);			
			
			is = Configuracoes.class.getResourceAsStream("impressao/modeloZebra.html");
			impressaoZebra = new byte[is.available()];
			is.read(impressaoZebra);
		}
		catch(Exception e) 	
		{
			Logs.addFatal("Carregando configuracoes, " + configuracoes + ", " + log4j, e);
		}
	}
}
