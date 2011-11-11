package br.com.easyShop.persistencia.utils;

import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import logs.Logs;
import utils.LongUtil;

/**
 * Classe utilizada para a geracao de codigos nao utilizados
 */
public class GeraCodigoAtta
{
	/**
	 * {@link GeraCodigoAtta#get(String, String, String, Class)}
	 */
	public static synchronized String getString(String sequence, String tabela, String coluna)
	{
		return (String)get(sequence, tabela, coluna, String.class);
	}
	
	/**
	 * {@link GeraCodigoAtta#get(String, String, String, Class)}
	 */
	public static synchronized Long getLong(String sequence, String tabela, String coluna)
	{
		return (Long)get(sequence, tabela, coluna, Long.class);
	}
	
	/**
	 * Metodo que obtem um valor de codigo nao utilizado.
	 * Obtem o proximo valor da sequence e verifica se ele esta sendo utilizado em uma tabela/coluna, em caso possitivo obtem o proximo valor da sequence...
	 * ATENCAO: caso o valor retornado seja descartado ele nao sera sugerido novamente
	 * @param sequence que o codigo deve ser obtido
	 * @param tabela em que sera feita uma busca para verificar se o codigo escolhido existe
	 * @param coluna em que sera feita uma busca para verificar se o codigo escolhido existe 
	 * @param classeColuna tipo da coluna para comparacoes, ex: String.class, Long.class
	 * @return valor nao utilizado de codigo, em caso de erros sera retornado null
	 */
	public static synchronized Object get(String sequence, String tabela, String coluna, Class<?> classeColuna)
	{
		QuerySQL query;
		ResultSQL resultSQL;
		
		try
		{
			query = new QuerySQL();
			query.add("SELECT NEXTVAL(?)", sequence);
			resultSQL = new BaseDAOAtta().obtemSQL(query);

			return obtemValor(sequence, tabela, coluna, classeColuna, (Long)resultSQL.get(0, 0));
		}
		catch(Exception e)
		{
			Logs.addWarn("Sequence " + sequence + " nao existente, o sistema ira criar uma sequencia");

			try
			{
				new BaseDAOAtta().alterarSQL("CREATE SEQUENCE " + sequence + " INCREMENT 1 START 1");
				
				try
				{
					query = new QuerySQL();
					query.add("SELECT NEXTVAL(?)", sequence);
					resultSQL = new BaseDAOAtta().obtemSQL(query);
					
					return obtemValor(sequence, tabela, coluna, classeColuna, (Long)resultSQL.get(0, 0));
				}
				catch(Exception e1)
				{
					Logs.addError("Nao foi possivel obter um valor da sequence [" + sequence + "]", e1);
				}
			}
			catch(Exception e2)
			{
				Logs.addFatal("Nao foi possivel criar a sequence [" + sequence + "]", e2);
			}
		}
		
		return null;
	}
	
	/**
	 * Metodo que verifica se um valor de codigo esta sendo utilizado, em caso positivo o metodo obtem o proximo codigo e recursivamente refaz a verificacao de utilizacao do codigo
	 * ATENCAO: caso o valor retornado seja descartado ele nao sera sugerido novamente
	 * @param sequence que o codigo deve ser obtido
	 * @param tabela em que sera feita uma busca para verificar se o codigo escolhido existe
	 * @param coluna em que sera feita uma busca para verificar se o codigo escolhido existe 
	 * @param classeColuna tipo da coluna para comparacoes, ex: String.class, Long.class
	 * @param valorAtual o valor atual do codigo da sequence para ser testado
	 * @return valor nao utilizado de codigo, em caso de erros sera retornado null
	 */
	private static synchronized Object obtemValor(String sequence, String tabela, String coluna, Class<?> classeColuna, long valorAtual)
	{
		QuerySQL query;
		ResultSQL resultSQL;
		
		try
		{
			Object valorColuna;
			if(classeColuna.equals(String.class))
			{
				valorColuna = LongUtil.toString(valorAtual);
			}
			else //if(classeColuna.equals(Long.class) || classeColuna.equals(long.class) || classeColuna.equals(Integer.class) || classeColuna.equals(int.class))
			{
				valorColuna = valorAtual;
			}
			
			query = new QuerySQL();
			query.add("SELECT " + coluna + " FROM " + tabela);
			query.add(" WHERE " + coluna + "=?", valorColuna);
			resultSQL = new BaseDAOAtta().obtemSQL(query);
			
			if(resultSQL.size()>0)
			{
				query = new QuerySQL();
				query.add("SELECT NEXTVAL(?)", sequence);
				resultSQL = new BaseDAOAtta().obtemSQL(query);
				
				return obtemValor(sequence, tabela, coluna, classeColuna, (Long)resultSQL.get(0, 0));
			}
			else
			{
				return valorColuna;
			}
		}
		catch(Exception e)
		{
			Logs.addFatal("Falha obtendo um codigo liberado para [" + sequence + " " + tabela + " " + coluna + "]", e);
		}
		return null;
	}
}
