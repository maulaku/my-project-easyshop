package br.com.easyShop.persistencia.administracao.geraOMJava.estruturas;

public class Dicionario
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private Integer id;
	private String nomeFisico;
	private String nomeLogico;
	private Integer tamanho;
	
	
	/*-*-*-* Construtores *-*-*-*/
	public Dicionario() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getNomeLogico() { return nomeLogico; }
	public void setNomeLogico(String nomeLogico) { this.nomeLogico = nomeLogico; }

	public String getNomeFisico() { return nomeFisico; }
	public void setNomeFisico(String nomeFisico) { this.nomeFisico = nomeFisico; }
	
	public Integer getTamanho() { return tamanho; }
	public void setTamanho(Integer tamanho) { this.tamanho = tamanho; }
}
