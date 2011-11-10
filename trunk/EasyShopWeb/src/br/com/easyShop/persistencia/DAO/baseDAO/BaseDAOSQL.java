package br.com.easyShop.persistencia.DAO.baseDAO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import logs.Logs;
import br.com.easyShop.persistencia.conexao.servidores.base.BaseJDBC;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.persistencia.utils.ResultSQL;

/**
 * Classe com metodos para auxiliar em operacoes SQL com conexoes JDBC 
 */
public class BaseDAOSQL
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	protected static HashMap<Class<?>, Field[]> fieldsMaps = new HashMap<Class<?>, Field[]>();
	protected static HashMap<Field, Annotation[]> annotationsMaps = new HashMap<Field, Annotation[]>();
	
	
	/**
	 * Metodo que adapta um ResultSQL para uma lista de objetos, utilizando reflection
	 * <BR>Busca primeiro pelo nome do campo (nao Case Sensitive), caso nao encontre busca pela Annotation (Column(name))
	 * @param classe onde o resultSQL deve ser adaptado
	 * @param resultSQL com o resultado de uma pesquisa
	 * @return Lista da classe do parametro adaptada
	 * @throws Exception
	 */
	public static <T> List<T> resultSQLToList(Class<T> classe, ResultSQL resultSQL) throws Exception
	{
		List<T> lista = new ArrayList<T>();

		T classeInstance;
		Object valor;
		Field field;
		Field[] fields = classe.getDeclaredFields();
		
		for(int i=0; i<resultSQL.size(); i++)
		{
			classeInstance = classe.newInstance();
			for(int j=0; j<resultSQL.sizeCabecalho(); j++)
			{
				try
				{
					valor = resultSQL.get(i, j);
					field = getField(resultSQL.getCabecalho(j), fields);
					if(field!=null)
					{
						field.setAccessible(true);
						field.set(classeInstance, valor);
					}
				}
				catch(Exception e)
				{ Logs.addError("Falha trasformando o resultSQL em lista de objetos", e); }
			}
			lista.add(classeInstance);
		}

		return lista;
	}
	
	/**
	 * Metodo que adapta um ResultSQL para um objeto, utilizando reflection, ele utiliza o primeiro registro o resultset
	 * <BR>Busca primeiro pelo nome do campo (nao Case Sensitive), caso nao encontre busca pela Annotation (Column(name))
	 * @param classe onde o resultSQL deve ser adaptado
	 * @param resultSQL com o resultado de uma pesquisa
	 * @return Object da classe do parametro adaptada
	 * @throws Exception
	 */
	public static <T> T resultSQLToUniqueResult(Class<T> classe, ResultSQL resultSQL) throws Exception
	{
		T classeInstance;
		Object valor;
		Field field;
		Field[] fields = classe.getDeclaredFields();
		
		if(resultSQL.size()>0)
		{
			classeInstance = classe.newInstance();
			for(int j=0; j<resultSQL.sizeCabecalho(); j++)
			{
				try
				{
					valor = resultSQL.get(0, j);
					field = getField(resultSQL.getCabecalho(j), fields);
					if(field!=null)
					{
						field.setAccessible(true);
						field.set(classeInstance, valor);
					}
				}
				catch(Exception e)
				{ Logs.addError("Falha trasformando o resultSQL em um unico resultado", e); }
			}
			
			return classeInstance;
		}
		
		return null;
	}
	
	
	/**
	 * Metodo que gera uma SQL de insert a partir de um objeto
	 * @param objeto a ser inserido no banco de dados
	 * @param baseJDBC utilizada para a conversao dos parametros para a query
	 * @return QuerySQL com a query de insersao
	 * @throws Exception
	 */
	public QuerySQL objectToInsertSQL(Object objeto, BaseJDBC baseJDBC) throws Exception
	{
		QuerySQL query = new QuerySQL(baseJDBC);

		String nome = objeto.getClass().getSimpleName();
		Annotation[] annotations = objeto.getClass().getAnnotations();
		for(int i=0; i<annotations.length; i++)
		{
			if(annotations[i] instanceof Entity && ((Entity)annotations[i]).name()!=null && ((Entity)annotations[i]).name().length()>0)
			{
				nome = ((Entity)annotations[i]).name();
			}
		}
		query.add("INSERT INTO " + nome + " (");
				
		Field[] fields = objeto.getClass().getDeclaredFields();
		camposNome: for(int i=0; i<fields.length; i++)
		{
			annotations = fields[i].getAnnotations();
			nome = fields[i].getName();

			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Transient || annotations[j] instanceof OneToMany)
				{
					continue camposNome;
				}
				else if(annotations[j] instanceof Id)
				{
					fields[j].setAccessible(true);
					if(getIdValue(objeto)==null || (Long)getIdValue(objeto)<=0)
					{
						continue camposNome;
					}
				}
				else if(annotations[j] instanceof Column && ((Column)annotations[j]).name()!=null && ((Column)annotations[j]).name().length()>0)
				{
					nome = ((Column)annotations[j]).name();
				}
				else if(annotations[j] instanceof JoinColumn && ((JoinColumn)annotations[j]).name()!=null && ((JoinColumn)annotations[j]).name().length()>0)
				{
					nome = ((JoinColumn)annotations[j]).name();
				}
			}
			
			query.add(query.getUltimoChar()!='(' ? ", " : "");
			query.add(nome);
		}
		query.add(") VALUES (");

		camposValor: for(int i=0; i<fields.length; i++)
		{
			annotations = fields[i].getAnnotations();
			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Transient || annotations[j] instanceof OneToMany) 
				{ 
					continue camposValor; 
				}
				else if(annotations[j] instanceof Id)
				{
					fields[j].setAccessible(true);
					if(getIdValue(objeto)==null || (Long)getIdValue(objeto)<=0)
					{
						continue camposValor;
					}
				}
				else if(annotations[j] instanceof ManyToOne)
				{
					query.add(query.getUltimoChar()!='(' ? ", " : "");
					fields[i].setAccessible(true);
					query.add("?", getIdValue(fields[i].get(objeto)));
					continue camposValor;
				}
			}
			
			query.add(query.getUltimoChar()!='(' ? ", " : "");
			fields[i].setAccessible(true);
			query.add("?", fields[i].get(objeto));
		}
		query.add(")");
		
		return query;
	}
	
	/**
	 * Metodo que gera uma SQL de update a partir de um objeto
	 * @param objeto a ser alterado no banco de dados
	 * @param baseJDBC utilizada para a conversao dos parametros para a query
	 * @param classes vetor de classes que devem ser incluidas ou excluidas do update (depende do parametro classesVerifica, verifique o javaDOC)
	 * @param classesVerifica pode assumir as constantes: BaseDAO.CONSIDERAR_CLASSES ou BaseDAO.IGNORAR_CLASSES
	 * <BR>CONSIDERAR_CLASSES: Ao construir a query serao considerados apenas os relacionamentos ManyToOne contidos no vetor de classes, caso o vetor de classes seja nulo todas os relacionamentos serao considerados
	 * <BR>IGNORAR_CLASSES: Ao construir a query serao ignorados apenas os relacionamentos ManyToOne contidos no vetor de classes, caso o vetor de classes seja nulo todas os relacionamentos serao ignorados
	 * @return QuerySQL com a query de alteracao
	 * @throws Exception
	 */
	public QuerySQL objectToUpdateSQL(Object objeto, BaseJDBC baseJDBC, Class<?>[] classes, int classesVerifica) throws Exception
	{
		QuerySQL query = new QuerySQL(baseJDBC);
		boolean consideraIgnora = classesVerifica==BaseDAO.CONSIDERAR_CLASSES;
		
		String nome = objeto.getClass().getSimpleName();
		Annotation[] annotations = objeto.getClass().getAnnotations();
		for(int i=0; i<annotations.length; i++)
		{
			if(annotations[i] instanceof Entity && ((Entity)annotations[i]).name()!=null && ((Entity)annotations[i]).name().length()>0)
			{
				nome = ((Entity)annotations[i]).name();
			}
		}
		query.add("UPDATE " + nome + " SET ");
				
		Field[] fields = objeto.getClass().getDeclaredFields();
		String idField = new String();
		Object idValue = null;
		boolean manyToOne = false;
		camposNome: for(int i=0; i<fields.length; i++)
		{
			manyToOne = false;
			annotations = fields[i].getAnnotations();
			nome = fields[i].getName();

			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Transient || annotations[j] instanceof OneToMany)
				{
					continue camposNome;
				}
				else if(annotations[j] instanceof Id)
				{
					fields[i].setAccessible(true);
					idValue = fields[i].get(objeto);
					idField = nome + "=?";
					continue camposNome;
				}
				else if(annotations[j] instanceof Column && ((Column)annotations[j]).name()!=null && ((Column)annotations[j]).name().length()>0)
				{
					nome = ((Column)annotations[j]).name();
				}
				else if(annotations[j] instanceof JoinColumn && ((JoinColumn)annotations[j]).name()!=null && ((JoinColumn)annotations[j]).name().length()>0)
				{
					nome = ((JoinColumn)annotations[j]).name();
				}
				else if(annotations[j] instanceof ManyToOne)
				{
					if((classes==null && classesVerifica==BaseDAO.CONSIDERAR_CLASSES) || (classes!=null && BaseDAO.contem(fields[i].getType(), classes)==consideraIgnora))
					{ 
						manyToOne = true; 
					}
					else 
					{ 
						continue camposNome;
					}
				}
			}
			
			query.add(query.getUltimoChar()!=' ' ? ", " : "");
			query.add(nome + "=");
			fields[i].setAccessible(true);
			query.add("?", manyToOne ? getIdValue(fields[i].get(objeto)) : fields[i].get(objeto));
		}
		query.add(" WHERE " + idField, idValue);
		
		return query;
	}
	
	/**
	 * Metodo que gera uma SQL de update a partir de um objeto
	 * @param objeto a ser inserido no banco de dados
	 * @param baseJDBC utilizada para a conversao dos parametros para a query
	 * @return QuerySQL com a query de remocao
	 * @throws Exception
	 */
	public static QuerySQL objectToDeleteSQL(Object objeto, BaseJDBC baseJDBC) throws Exception
	{
		QuerySQL query = new QuerySQL(baseJDBC);

		String nome = objeto.getClass().getSimpleName();
		Annotation[] annotations = objeto.getClass().getAnnotations();
		for(int i=0; i<annotations.length; i++)
		{
			if(annotations[i] instanceof Entity && ((Entity)annotations[i]).name()!=null && ((Entity)annotations[i]).name().length()>0)
			{
				nome = ((Entity)annotations[i]).name();
			}
		}
		query.add("DELETE FROM " + nome + " WHERE ");
				
		Field[] fields = objeto.getClass().getDeclaredFields();
		camposNome: for(int i=0; i<fields.length; i++)
		{
			annotations = fields[i].getAnnotations();
			nome = fields[i].getName();

			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Id)
				{
					fields[i].setAccessible(true);
					query.add(nome + "=?", fields[i].get(objeto));
					break camposNome;
				}
			}
		}

		return query;
	}
	
	/**
	 * Metodo que gera uma SQL para selecionar todos os registros a partir de uma classe
	 * @param classe referente a tabela
	 * @param baseJDBC utilizada para a conversao dos parametros para a query
	 * @return QuerySQL com a query de select para todos os registros
	 * @throws Exception
	 */
	public static QuerySQL classeToSelectAllSQL(Class<?> classe, BaseJDBC baseJDBC) throws Exception
	{
		QuerySQL query = new QuerySQL(baseJDBC);

		String nome = classe.getSimpleName();
		Annotation[] annotations = classe.getAnnotations();
		for(int i=0; i<annotations.length; i++)
		{
			if(annotations[i] instanceof Entity && ((Entity)annotations[i]).name()!=null && ((Entity)annotations[i]).name().length()>0)
			{
				nome = ((Entity)annotations[i]).name();
			}
		}
		query.add("SELECT * FROM " + nome);
		
		return query;
	}
	
	/**
	 * Metodo que gera uma SQL para selecionar um registro a partir de uma classe e sua pk
	 * @param objeto a ser inserido no banco de dados
	 * @param baseJDBC utilizada para a conversao dos parametros para a query
	 * @return query de select um determinado registro
	 * @throws Exception
	 */
	public static QuerySQL classeToSelectRegistroSQL(Class<?> classe, Object pk, BaseJDBC baseJDBC) throws Exception
	{
		QuerySQL query = new QuerySQL(baseJDBC);
		
		String nome = classe.getSimpleName();
		Annotation[] annotations = classe.getAnnotations();
		for(int i=0; i<annotations.length; i++)
		{
			if(annotations[i] instanceof Entity && ((Entity)annotations[i]).name()!=null && ((Entity)annotations[i]).name().length()>0)
			{
				nome = ((Entity)annotations[i]).name();
			}
		}
		query.add("SELECT * FROM " + nome + " WHERE ");
		
		Field[] fields = classe.getDeclaredFields();
		camposNome: for(int i=0; i<fields.length; i++)
		{
			annotations = fields[i].getAnnotations();
			nome = fields[i].getName();

			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Id)
				{
					fields[i].setAccessible(true);
					query.add(nome + "=?", pk);
					break camposNome;
				}
			}
		}
		
		return query;
	}
	
	/**
	 * Metodo que retorna o nome da tabela do banco de dados considerando as annotatios da classe
	 * @param objeto a ser verificado o nome da tabela
	 * @return nome da tabela
	 * @throws Exception
	 */
	public static String getNomeTabela(Object objeto) throws Exception
	{
		String nome = objeto.getClass().getSimpleName();
		Annotation[] annotations = objeto.getClass().getAnnotations();
		for(int i=0; i<annotations.length; i++)
		{
			if(annotations[i] instanceof Entity && ((Entity)annotations[i]).name()!=null && ((Entity)annotations[i]).name().length()>0)
			{
				nome = ((Entity)annotations[i]).name();
			}
		}
		return nome;
	}
	
	/**
	 * Metodo que obtem o nome do campo com a Annotation ID do objeto
	 * @param objeto
	 * @return nome do campo do ID do objeto
	 * @throws Exception
	 */
	public static String getIdName(Object objeto) throws Exception
	{
		if(objeto==null) { return null; }
		
		Annotation[] annotations;
		Field[] fields = objeto.getClass().getDeclaredFields();
		for(int i=0; i<fields.length; i++)
		{
			annotations = fields[i].getAnnotations();
			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Id)
				{
					return fields[i].getName();
				}
			}
		}
		return null;
	}
	
	/**
	 * Metodo que obtem o valor do campo com a Annotation ID do objeto
	 * @param objeto
	 * @return valor do ID do objeto
	 * @throws Exception
	 */
	public Object getIdValue(Object objeto) throws Exception
	{
		if(objeto==null) { return null; }
		
		Annotation[] annotations;
		Field field;
		Field[] fields = getFields(objeto.getClass());
		for(int i=0; i<fields.length; i++)
		{
			field = fields[i];
			annotations = getAnnotations(field);
			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Id)
				{
					field.setAccessible(true);
					return field.get(objeto);
				}
			}
		}

		return null;
	}
	
	/**
	 * Metodo que obtem o Field da classe correspondende buscando primeiro pelo nome e segundo por sua Annotation (Column(name))
	 * @param nome do objeto ou variavel da classe
	 * @param fields da classe
	 * @return Field
	 * @throws Exception
	 */
	private static Field getField(String nome, Field[] fields) throws Exception
	{
		Annotation[] annotations;
		
		for(int i=0; i<fields.length; i++)
		{
			if(fields[i].getName().compareToIgnoreCase(nome)==0) { return fields[i]; }
		}
		
		for(int i=0; i<fields.length; i++)
		{
			annotations = fields[i].getAnnotations();
			for(int j=0; j<annotations.length; j++)
			{
				if(annotations[j] instanceof Column && ((Column)annotations[j]).name().compareToIgnoreCase(nome)==0)
				{
					return fields[i];
				}
			}
		}
		return null;
	}

	/**
	 * Metodo que retorna os fields de uma classe verificando se ele existe no hashmap em caso positivo retorna o valor do hashmap
	 * Caso no exista cria o verto de fields adiciona no hashmap e retorna o vetor
	 * @param classe que se quer obter os fields
	 * @return vetor com os fields da classe
	 */
	public Field[] getFields(Class<?> classe)
	{
		Field[] fields = fieldsMaps.get(classe);
		if(fields==null)
		{
			fields = classe.getDeclaredFields();
			fieldsMaps.put(classe, fields);
		}
		return fields;
	}
	
	/**
	 * Metodo que retorna as annotations de um field verificando se ela existe no hashmap em caso positivo retorna o valor do hashmap
	 * Caso no exista cria o vetor de annotations adiciona no hashmap e retorna o vetor
	 * @param field que se quer obter as annotations
	 * @return vetor com as annotations do campo
	 */
	public Annotation[] getAnnotations(Field field)
	{
		Annotation[] annotations = annotationsMaps.get(field);
		if(annotations==null)
		{
			annotations = field.getAnnotations();
			annotationsMaps.put(field, annotations);
		}
		return annotations;
	}
	
	/**
	 * Metodo que transforma o ResultSet em ResultSQL
	 * @param resultSet a ser transformado
	 * @return ResultSQL equivalente ao resultSet passado por parametro
	 * @throws Exception em caso de algum erro de conversao
	 */
	public static ResultSQL resultSetToResultSQL(ResultSet resultSet, BaseJDBC baseJDBC) throws Exception
	{
		Vector<String> cabecalhos = new Vector<String>();
		Vector<Vector<Object>> linhas = new Vector<Vector<Object>>();
		if(resultSet!=null && resultSet.next()) 
		{	
			ResultSetMetaData rsmd = resultSet.getMetaData();
			for(int i=1; i<=rsmd.getColumnCount(); i++)
			{
				cabecalhos.addElement(rsmd.getColumnName(i));
			}
			
			linhas.addElement(obtemProximaLinha(resultSet, rsmd, baseJDBC));
			while(resultSet.next())
			{
				linhas.addElement(obtemProximaLinha(resultSet, rsmd, baseJDBC));
			}
		}
		
		return new ResultSQL(cabecalhos, linhas, resultSet);
	}
	
	/**
	 * Metodo auxilizar para a execucao do metodo resultSetToResultSQL, responsavel por percorer as linhas do resultset
	 * @param resultSet da consulta
	 * @param resultSetMetaData da consulta
	 * @return um registro da consulta
	 */
	private static Vector<Object> obtemProximaLinha(ResultSet resultSet, ResultSetMetaData resultSetMetaData, BaseJDBC baseJDBC) throws Exception
	{
		Vector<Object> linhaCorrente = new Vector<Object>();
		for(int i=1; i<=resultSetMetaData.getColumnCount(); i++)
		{
			linhaCorrente.addElement(baseJDBC.SQLToObject(resultSet, resultSetMetaData, i));
		}
		return linhaCorrente;
	}
}
