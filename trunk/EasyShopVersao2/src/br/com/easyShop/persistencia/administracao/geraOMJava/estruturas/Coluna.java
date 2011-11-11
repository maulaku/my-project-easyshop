package br.com.easyShop.persistencia.administracao.geraOMJava.estruturas;

public class Coluna
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private Integer id;
	private Integer dicionarioID;
	private String nomeFisico;
	private String nomeLogico;
	private String tipo;
	private Integer tamanho;
	private boolean pk;
	private boolean naoNulo;

	private boolean fk;
	private Relacao relacao;
	
	private boolean transiente;

	
	/*-*-*-* Construtores *-*-*-*/
	public Coluna() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public boolean isPk() { return pk; }
	public void setPk(Boolean pk) { this.pk = (pk!=null ? pk : false); }
	
	public boolean isFk() { return fk; }
	public void setFk(Boolean fk) { this.fk = (fk!=null ? fk : false); }
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getNomeLogico() { return nomeLogico; }
	public void setNomeLogico(String nomeLogico) { this.nomeLogico = nomeLogico; }

	public String getNomeFisico() { return nomeFisico; }
	public void setNomeFisico(String nomeFisico) { this.nomeFisico = nomeFisico; }

	public Integer getDicionarioID() { return dicionarioID; }
	public void setDicionarioID(Integer dicionarioID) { this.dicionarioID = dicionarioID; }

	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }

	public boolean isNaoNulo() { return naoNulo; }
	public void setNaoNulo(boolean naoNulo) { this.naoNulo = naoNulo; }
	
	public Relacao getRelacao() { return relacao; }
	public void setRelacao(Relacao relacao) { this.relacao = relacao; }
	
	public Integer getTamanho() { return tamanho; }
	public void setTamanho(Integer tamanho) { this.tamanho = tamanho; }
	
	public boolean isTransiente() { return transiente; }
	public void setTransiente(boolean transiente) { this.transiente = transiente; }
}
