package br.com.easyShop.persistencia.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.easyShop.persistencia.conexao.servidores.base.BaseJDBC;

/**
 * Classe utilizada para a criacao de querys para execucao de operacoes no banco de dados
 */
public class QuerySQL
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private BaseJDBC baseJDBC;
	private List<String> querys = new ArrayList<String>();
	private List<Object> parametros = new ArrayList<Object>();
	
	
	/*-*-*-* Construtores *-*-*-*/
	/**
	 * Construtor vazio, nao especifica a base de conversao
	 * <BR>Caso utilize este contrutor para obter a query utilize: toString(baseJDBC)
	 */
	public QuerySQL() { }

	/**
	 * Construtor que especifica qual a base de conversao que deve ser utilizada
	 * @param baseJDBC a ser utilizada para a conversao dos parametros para a query
	 * <BR>Caso utilize este contrutor para obter a query utilize: toString()
	 */
	public QuerySQL(BaseJDBC baseJDBC)
	{
		this.baseJDBC = baseJDBC;
	}	
	
	
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Adiciona o trecho na query
	 * @param query a ser adicionada, para definir os parametros utilize o caractere '?'
	 * @param parametros lista de parametros a serem adicionados no trecho, os parametros devem estar na mesma ordem em que os '?' estao na query
	 * @throws Exception
	 */
	public void add(String query, Object... parametros)
	{
		this.querys.add(query);
		this.parametros.add(parametros);
	}
	
	/**
	 * Metodo que monta e retorna a query em formato String utlizando a base de conversao global
	 * @return query
	 */
	public String toString()
	{
		return toString(baseJDBC);
	}
	
	/**
	 * Metodo que monta e retorna a query em formato String utlizando a base de conversao passada por parametro
	 * @param baseJDBC utilizada para a conversao dos objects em strings para query
	 * @return query
	 */
	public String toString(BaseJDBC baseJDBC)
	{
		StringBuffer querySQL = new StringBuffer();
		String query;
		Object[] parametros;
		
		for(int i=0; i<querys.size(); i++)
		{
			query = querys.get(i);
			parametros = (Object[])this.parametros.get(i);
			
			if(query!=null)
			{		
				int indexParametro=0;
				char caractere;
				for(int j=0; j<query.length(); j++)
				{
					caractere = query.charAt(j);
					if(caractere=='?')
					{
						if(baseJDBC!=null)
						{
							querySQL.append(baseJDBC.objectToSQL(parametros[indexParametro]));
						}
						indexParametro++;
					}
					else
					{
						querySQL.append(caractere);
					}
				}
			}
		}

		return querySQL.toString();
	}
	
	/**
	 * Este metodo e utilizado para a concatenacao de querys
	 * @return o ultimo caractere da ultima query inserida
	 */
	public Character getUltimoChar()
	{
		if(querys.size()>0)
		{
			String query = querys.get(querys.size()-1);
			if(query!=null && query.length()>0)
			{
				return query.charAt(query.length()-1);
			}
		}
		return null;
	}
	
	/**
	 * Metodo que clona a classe e todas suas variaveis
	 * Cria instancias novas das querys, mas utiliza os mesmo parametros, Atenção quando efetuar alterações nos valores de parametros que são passados por referencia
	 * @return querySQL
	 */
	public QuerySQL clone()
	{
		QuerySQL clone = new QuerySQL();
		clone.baseJDBC = baseJDBC;
		for(int i=0; i<querys.size(); i++)
		{
			clone.add(new String(querys.get(i)), parametros.get(i));
		}
		return clone;
	}
}
