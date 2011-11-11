package br.com.easyShop.persistencia.administracao.comparaBases.auxiliar;

import utils.Utilidades;


public class Coluna
{
	private String nome;
	private long tipo;
	private boolean notNull;
	
	
	public Coluna() { }
	
	@Override
	public String toString()
	{
		return tipoToNome(tipo, nome, false) + " " + nome + (notNull ? " NotNull" : "");
	}
	
	@Override
	public boolean equals(Object arg0)
	{
		return nome.equalsIgnoreCase(((Coluna)arg0).getNome());
	}
	
	public String toNotNull(Tabela tabela)
	{
		return "ALTER TABLE " + tabela.getNome() + " ALTER COLUMN " + nome + " SET NOT NULL";
	}
	
	public String toNull(Tabela tabela)
	{
		return "ALTER TABLE " + tabela.getNome() + " ALTER COLUMN " + nome + " DROP NOT NULL";
	}
	
	public String toTipo(Tabela tabela, long tipo)
	{
		return "ALTER TABLE " + tabela.getNome() + " ALTER COLUMN " + nome + " TYPE " + tipoToNome(tipo, nome, true) + " USING " + nome + "::" + tipoToNome(tipo, nome, true);
	}
	
	public String toInsert(Tabela tabela, String prefixo)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE " + tabela.getNome() + " ADD COLUMN " + nome + " " + tipoToNome(tipo, nome, true) + (notNull ? " NOT NULL" : ""));
		if(nome!=null && nome.startsWith("fk"))
		{
			sb.append(";\n" + prefixo + "ALTER TABLE " + tabela.getNome() + " ADD CONSTRAINT fk_" + tabela.getNome() + "_" + (System.currentTimeMillis() + Utilidades.sorteiaNumero(0, 1000000)) + " FOREIGN KEY (" + nome + ") REFERENCES " + nome.substring(2) + " (pk" + nome.substring(2) + ") MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION");
		}
		return sb.toString();
	}
	
	public String toRemove(Tabela tabela)
	{
		return "ALTER TABLE " + tabela.getNome() + " DROP COLUMN " + nome;
	}
	
	/**
	 * Converte um tipo numerico para seu nome
	 * @param tipo
	 * @return nome do tipo
	 */
	public static String tipoToNome(long tipo, String nome, boolean sql)
	{
		if(tipo==20) 			{ return sql ? (nome!=null && nome.startsWith("pk") ? "bigserial" : "bigint") : (nome!=null && nome.startsWith("pk") ? "Bigserial" : "Bigint"); }
		else if(tipo==23) 		{ return sql ? "integer" : "Integer"; }
		else if(tipo==25) 		{ return sql ? "text" : "Text"; }
		else if(tipo==1114) 	{ return sql ? "timestamp" : "Timestamp"; }
		else if(tipo==1043) 	{ return sql ? "character varying" : "Varchar"; }
		else if(tipo==16) 		{ return sql ? "boolean" : "Boolean"; }
		else if(tipo==701) 		{ return sql ? "double precision" : "Double"; }
		else 					{ return "Desconhecido"; }
	}

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public long getTipo() { return tipo; }
	public void setTipo(long tipo) { this.tipo = tipo; }

	public boolean isNotNull() { return notNull; }
	public void setNotNull(boolean notNull) { this.notNull = notNull; }
}
