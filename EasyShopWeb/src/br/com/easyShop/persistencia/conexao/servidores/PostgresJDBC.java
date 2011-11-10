package br.com.easyShop.persistencia.conexao.servidores;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.util.List;

import br.com.easyShop.persistencia.conexao.pool.PoolJDBC;
import br.com.easyShop.persistencia.conexao.servidores.base.BaseJDBC;

import utils.data.Data;

/**
 * Classe que extende a BaseJDBC e implementa os metodos de acesso a base de dados Postgres
 */
public class PostgresJDBC extends BaseJDBC
{
	/*-*-*-* Construtores *-*-*-*/
	/**
	 * Construtor
	 */
	public PostgresJDBC() { }
	
	/**
	 * Construtor que recebe as informacoes da conexao com o banco de dados e inicializa as variaveis de Driver e Prefixo da URL do banco de dados
	 * @param endereco do banco de dados
	 * @param base a ser acessada
	 * @param porta que recebe as conexoes da base
	 * @param login do banco de dados
	 * @param senha do banco de dados
	 */
	public PostgresJDBC(String endereco, String base, int porta, String login, String senha, PoolJDBC poolJDBC)
	{
		super("org.postgresql.Driver", "jdbc:postgresql://", endereco, base, porta, login, senha, poolJDBC);
	}
	
	
	/*-*-*-* Metodos Abstratos Implementados *-*-*-*/
	/**
	 * {@link BaseJDBC#clonar()}
	 */
	public BaseJDBC clonar() throws Exception
	{
		BaseJDBC baseJDBC = this.getClass().newInstance();
		baseJDBC.setEndereco(getEndereco());
		baseJDBC.setBase(getBase());
		baseJDBC.setPorta(getPorta());
		baseJDBC.setLogin(getLogin());
		baseJDBC.setSenha(getSenha());
		baseJDBC.setDriver(getDriver());
		baseJDBC.setUrl(getUrl());
		baseJDBC.setPoolJDBC(getPoolJDBC());
		return baseJDBC;
	}
	
	/**
	 * {@link BaseJDBC#SQLToObject(ResultSet, ResultSetMetaData, int)}
	 */
	public Object SQLToObject(ResultSet resultSet, ResultSetMetaData resultSetMetaData, int index) throws Exception
	{
		if(resultSet.getObject(index)==null) { return null; }
		
		switch(resultSetMetaData.getColumnType(index))
		{
			case Types.VARCHAR: 	{ return resultSet.getString(index); }
			case Types.CHAR: 		{ return resultSet.getString(index).length()>0 ? resultSet.getString(index).charAt(0) : ""; }
			case Types.BINARY: 		{ return resultSet.getBytes(index); }
			case Types.INTEGER: 	{ return resultSet.getInt(index); }
			case Types.BIGINT: 		{ return resultSet.getLong(index); }
			case Types.DOUBLE: 		{ return resultSet.getDouble(index); }
			case Types.DATE: 		{ return new Data(resultSet.getDate(index).getTime()); }
			case Types.TIMESTAMP:	{ return new Data(resultSet.getTimestamp(index).getTime()); }
			case Types.BOOLEAN: 	{ return resultSet.getBoolean(index); }
			case Types.BIT: 		{ return resultSet.getBoolean(index); }
			default:				{ return resultSet.getString(index); }
		}
	}
	
	/**
	 * {@link BaseJDBC#objectToSQL(Object)}
	 */
	public String objectToSQL(Object object)
	{
		if(object==null || object.equals(Double.NaN))
		{ return "null";  }
		else if(object instanceof List<?>)
		{
			List<?> lista = (List<?>)object;
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<lista.size(); i++)
			{
				sb.append(objectToSQL(lista.get(i)));
				if(i+1<lista.size()) { sb.append(", "); }
			}
			return sb.toString();
		}
		else if(object.getClass().isArray())
		{
			Object[] vetor;

			if(object instanceof int[])
			{
				vetor = new Object[((int[])object).length];
				for(int i=0; i<vetor.length; i++) { vetor[i] = ((int[])object)[i]; }
			}
			else if(object instanceof long[])
			{
				vetor = new Object[((long[])object).length];
				for(int i=0; i<vetor.length; i++) { vetor[i] = ((long[])object)[i]; }
			}
			else if(object instanceof double[])
			{
				vetor = new Object[((double[])object).length];
				for(int i=0; i<vetor.length; i++) { vetor[i] = ((double[])object)[i]; }
			}
			else if(object instanceof char[])
			{
				vetor = new Object[((char[])object).length];
				for(int i=0; i<vetor.length; i++) { vetor[i] = ((char[])object)[i]; }
			}
			else
			{
				vetor = (Object[])object;
			}
			
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<vetor.length; i++)
			{
				sb.append(objectToSQL(vetor[i]));
				if(i+1<vetor.length) { sb.append(", "); }
			}
			return sb.toString();
		}		
		else if(object instanceof String)
		{
			String string = (String)object;
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<string.length(); i++)
			{
				if(string.charAt(i)=='\'')
				{
					sb.append("\\'");
				}
				else if(string.charAt(i)=='\\')
				{
					sb.append("\\\\");
				}
				else
				{
					sb.append(string.charAt(i));
				}
			}
			return "E'" + sb.toString() + "'"; 
		}
		else if(object instanceof Character)
		{ 
			return (Character)object=='\'' ? "E'\\''" : "E'" + object + "'";
		}
		else if(object instanceof Data)
		{ return "'" + ((Data)object).toString("yyyy-MM-dd HH:mm:ss") + "'"; }
		else
		{ return object.toString(); }
	}
}
