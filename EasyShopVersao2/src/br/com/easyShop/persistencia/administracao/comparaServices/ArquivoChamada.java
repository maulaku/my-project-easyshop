package br.com.easyShop.persistencia.administracao.comparaServices;

import java.util.List;

public class ArquivoChamada
{
	private String nomeArquivo;
	private List<String> chamadas;
	private String projeto;
	
	public ArquivoChamada() { }
	public ArquivoChamada(String nome, List<String> lista, String nomeProjeto)
	{
		nomeArquivo = nome;
		chamadas = lista;
		projeto = nomeProjeto;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public List<String> getChamadas() {
		return chamadas;
	}
	public void setChamadas(List<String> chamadas) {
		this.chamadas = chamadas;
	}
	public String getProjeto() {
		return projeto;
	}
	public void setProjeto(String pacote) {
		this.projeto = pacote;
	}
	
	public static String getPacoteClasseMetodo(String chamada)
	{
		String pacoteClasseMetodo = null;
		try { pacoteClasseMetodo = chamada.substring(19, chamada.indexOf("\"", 19)); }
		catch (Exception e) { }
		
		return pacoteClasseMetodo;
	}
	
	public static String getPacoteClasse(String chamada)
	{
		String pacoteClasseMetodo = getPacoteClasseMetodo(chamada);
		if(pacoteClasseMetodo==null) { return null; }
		String pacoteClasse = pacoteClasseMetodo.substring(0, pacoteClasseMetodo.lastIndexOf("."));
		
		return pacoteClasse;
	}

	public static String getMetodo(String chamada)
	{
		String pacoteClasseMetodo = getPacoteClasseMetodo(chamada);
		String metodo = pacoteClasseMetodo.substring(pacoteClasseMetodo.lastIndexOf(".")+1, pacoteClasseMetodo.length());
		
		return metodo;
	}

	public static int getNumParametros(String chamada)
	{
		String parametros = null;
		try { parametros = chamada.substring(chamada.indexOf("[")+1, chamada.indexOf("]")); }
		catch (Exception e)
		{
			return 0;
		}
		String[] parametrosVetor = parametros.split(",");
		       
		return parametrosVetor.length;
	}
}
