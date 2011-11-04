package br.com.easyShop.comunicacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe utilizada para retornar conteudos para o flex
 */
public class ResultJava
{
	/*-*-*-* Variaveis e Objetos Publicos *-*-*-*/
	private Object item;
	private List<?> lista = new ArrayList<Object>();
	private boolean cache = false;
	
	
	/*-*-*-* Construtores *-*-*-*/
	/**
	 * Contrutor Vazio
	 */
	public ResultJava() { }
	
	/**
	 * Contrutor
	 * @param item generico para resposta
	 * @param lista de objetos genericos para resposta
	 */
	public ResultJava(Object item, List<?> lista)
	{
		this.item = item;
		this.lista = lista;
	}
	
	/**
	 * Contrutor
	 * @param item generico para resposta
	 * @param lista de objetos genericos para resposta
	 */
	public ResultJava(Object item, String... lista)
	{
		this.item = item;
		this.lista = Arrays.asList(lista);
	}
	
	/**
	 * Contrutor
	 * @param objeto generico para resposta
	 */
	public ResultJava(Object item)
	{
		this.item = item;
	}
	
	/**
	 * Contrutor
	 * @param lista de objetos genericos para resposta
	 */
	public ResultJava(List<?> lista)
	{
		this.lista = lista;
	}
	
	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Object getItem() { return item; }
	public void setItem(Object item) { this.item = item; }
	
	public List<?> getLista() { return lista; }
	public void setLista(List<?> lista) { this.lista = lista; }
	
	public boolean isCache() { return cache; }
	public void setCache(boolean cache) { this.cache = cache; }
}
