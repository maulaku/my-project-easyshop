package br.com.easyShop.persistencia.administracao.actionScript;

public class PathASBase
{
	private String path;
	private String projetoImport;
	
	public PathASBase(String path, String projetoImport)
	{
		this.path = path;
		this.projetoImport = projetoImport;
	}
	
	public String getPath() 			{ return path; }
	public void setPath(String path) 	{ this.path = path; }
	
	public String getProjetoImport() 			{ return projetoImport; }
	public void setProjetoImport(String projetoImport) 	{ this.projetoImport = projetoImport; }
}
