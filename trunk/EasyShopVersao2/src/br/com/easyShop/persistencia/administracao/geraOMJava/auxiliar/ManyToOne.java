package br.com.easyShop.persistencia.administracao.geraOMJava.auxiliar;

import br.com.easyShop.persistencia.administracao.geraOMJava.estruturas.Relacao;
import br.com.easyShop.persistencia.administracao.geraOMJava.estruturas.Tabela;

public class ManyToOne
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private boolean renome=false;
	private Tabela tabela;
	private Tabela tabelaGeti;
	private Relacao relacao;
	
	
	/*-*-*-* Construtores *-*-*-*/
	public ManyToOne() { }
	
	/*-*-*-* Metodos Publicos *-*-*-*/
	@Override
	public boolean equals(Object obj)
	{
		return tabelaGeti.getId().equals(((ManyToOne)obj).getTabelaGeti().getId());
	}
	
	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Tabela getTabela() { return tabela; }
	public void setTabela(Tabela tabela) { this.tabela = tabela; }
	
	public Tabela getTabelaGeti() { return tabelaGeti; }
	public void setTabelaGeti(Tabela tabelaGeti) { this.tabelaGeti = tabelaGeti; }
	
	public boolean isRenome() { return renome; }
	public void setRenome(boolean renome) { this.renome = renome; }
	
	public Relacao getRelacao() { return relacao; }
	public void setRelacao(Relacao relacao) { this.relacao = relacao; }
}
