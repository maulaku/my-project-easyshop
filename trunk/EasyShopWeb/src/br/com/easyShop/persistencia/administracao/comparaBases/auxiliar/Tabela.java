package br.com.easyShop.persistencia.administracao.comparaBases.auxiliar;

import java.util.ArrayList;
import java.util.List;

import utils.Utilidades;

public class Tabela
{
	private String nome;
	
	private List<Coluna> colunas = new ArrayList<Coluna>();

	public Tabela() { }
	
	@Override
	public boolean equals(Object arg0)
	{
		return nome.equalsIgnoreCase(((Tabela)arg0).getNome());
	}

	@Override
	public String toString()
	{
		return nome;
	}
	
	public Coluna getPK()
	{
		for(int i=0; i<colunas.size(); i++)
		{
			if(colunas.get(i).getNome().startsWith("pk")) { return colunas.get(i); }
		}
		return null;
	}
	
	public String toInsert(String prefixo)
	{
		/*
		 * CREATE TABLE nome
		 * (
		 * 		nomeCampo tipo,
		 * 		...
		 * 		CONSTRAINT pk_cliente PRIMARY KEY (pknome),
		 * 		...
		 * 		CONSTRAINT fk_cliente_1 FOREIGN KEY (fknome) REFERENCES nomeOutraTabela (pkOutraTabela) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION 
		 * )
		 * WITH (OIDS=FALSE);
		 */
		StringBuffer sb = new StringBuffer();
		
		sb.append("CREATE TABLE " + nome);
		sb.append("\n" + prefixo + "(");
		
		for(int i=0; i<colunas.size(); i++)
		{
			sb.append("\n\t" + prefixo + colunas.get(i).getNome() + " " + Coluna.tipoToNome(colunas.get(i).getTipo(), colunas.get(i).getNome(), true) + (colunas.get(i).isNotNull() ? " NOT NULL" : "") + ",");
		}
		sb.append("\n" + prefixo + "\tCONSTRAINT pk_" + nome + " PRIMARY KEY (" + getPK().getNome() + ")");
		
		for(int i=0; i<colunas.size(); i++)
		{
			if(colunas.get(i).getNome().startsWith("fk"))
			{
				sb.append(",\n");
				//Exemplo para FK: CONSTRAINT fk_nomeTabela_1 FOREIGN KEY (fkNome) REFERENCES nomeOutraTabela (pkOutraTabela) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
				sb.append(prefixo + "\tCONSTRAINT fk_" + nome + "_" + (System.currentTimeMillis() + Utilidades.sorteiaNumero(0, 1000000)) + " FOREIGN KEY (" + colunas.get(i).getNome() + ") REFERENCES " + colunas.get(i).getNome().substring(2) + " (pk" + colunas.get(i).getNome().substring(2) + ") MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION");
			}
		}
		
		sb.append("\n" + prefixo + ")");
		sb.append("\n" + prefixo + "WITH (OIDS=FALSE);");
		return sb.toString();
	}
	
	public String toRemove()
	{
		return "DROP TABLE " + nome;
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public List<Coluna> getColunas() { return colunas; }
	public void setColunas(List<Coluna> colunas) { this.colunas = colunas; }
} 
