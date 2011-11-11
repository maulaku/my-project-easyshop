package br.com.easyShop.persistencia.administracao.geraOMJava.estruturas;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Tabela
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private Integer id;
	private String nomeFisico;
	private String nomeLogico;
	private String attaInterface = null;
	private boolean especificoInterface = false;
	
	private List<Coluna> colunas = new ArrayList<Coluna>();
	private List<Relacao> relacoes = new ArrayList<Relacao>();
	private Properties descricao = new Properties();

	
	/*-*-*-* Construtores *-*-*-*/
	public Tabela() { }
	
	/*-*-*-* Metodos Publicos *-*-*-*/
	public Coluna getColunaRelacao(Relacao relacao)
	{
		for(int i=0; i<colunas.size(); i++)
		{
			if(colunas.get(i).getRelacao()!=null && colunas.get(i).getRelacao().getId().equals(relacao.getId()))
			{
				return colunas.get(i);
			}
		}
		return null;
	}
	
	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getNomeLogico() { return nomeLogico; }
	public void setNomeLogico(String nomeLogico) { this.nomeLogico = nomeLogico; }

	public String getNomeFisico() { return nomeFisico; }
	public void setNomeFisico(String nomeFisico) { this.nomeFisico = nomeFisico; }
	
	public List<Coluna> getColunas() { return colunas; }
	public void setColunas(List<Coluna> colunas) { this.colunas = colunas; }
	
	public List<Relacao> getRelacoes() { return relacoes; }
	public void setRelacoes(List<Relacao> relacoes) { this.relacoes = relacoes; }
	
	public Properties getDescricao() { return descricao; }
	public void setDescricao(Properties descricao) { this.descricao = descricao; }
	
	public String getAttaInterface() { return attaInterface; }
	public void setAttaInterface(String attaInterface) { this.attaInterface = attaInterface; }
	
	public boolean isEspecificoInterface() { return especificoInterface; }
	public void setEspecificoInterface(boolean especificoInterface) { this.especificoInterface = especificoInterface; }
}
