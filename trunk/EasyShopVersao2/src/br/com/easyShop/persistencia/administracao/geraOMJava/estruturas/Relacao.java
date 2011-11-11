package br.com.easyShop.persistencia.administracao.geraOMJava.estruturas;

public class Relacao
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private Integer id;
	private Integer source;
	private String nomeFisicoTabela;
	private String nomeLogicoTabela;
	private String attaInterface = null;
	private boolean especificoInterface = false;
	private boolean transiente = false;
	
	
	/*-*-*-* Construtores *-*-*-*/
	public Relacao() { }
	public Relacao(Integer id) 
	{ 
		this.id = id;
	}
	
	/*-*-*-* Metodos Publicos *-*-*-*/
	public boolean isNull() 
	{ 
		return id==null && source==null; 
	}
	
	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
	public Integer getSource() { return source; }
	public void setSource(Integer source) { this.source = source; }
	
	public String getNomeFisicoTabela() { return nomeFisicoTabela; }
	public void setNomeFisicoTabela(String nomeFisicoTabela) { this.nomeFisicoTabela = nomeFisicoTabela; }
 
	public String getNomeLogicoTabela() { return nomeLogicoTabela; }
	public void setNomeLogicoTabela(String nomeLogicoTabela) { this.nomeLogicoTabela = nomeLogicoTabela; }

	public String getAttaInterface() { return attaInterface; }
	public void setAttaInterface(String attaInterface) { this.attaInterface = attaInterface; }
	
	public boolean isEspecificoInterface() { return especificoInterface; }
	public void setEspecificoInterface(boolean especificoInterface) { this.especificoInterface = especificoInterface; }
	
	public boolean isTransiente() { return transiente; }
	public void setTransiente(boolean transiente) { this.transiente = transiente; }
}
