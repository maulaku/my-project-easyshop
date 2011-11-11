package br.com.easyShop.persistencia.administracao.actionScript;

import java.io.File;

public class ArquivoModulo
{
	private File arquivo;
	private String projeto;
	
	
	public ArquivoModulo(File arquivo, String projeto)
	{
		this.arquivo = arquivo;
		this.projeto = projeto;
	}
	
	public File getArquivo() 				{ return arquivo; }
	public void setArquivo(File arquivo) 	{ this.arquivo = arquivo; }
	
	public String getProjeto() 				{ return projeto; }
	public void setProjeto(String projeto) 	{ this.projeto = projeto; }
}
