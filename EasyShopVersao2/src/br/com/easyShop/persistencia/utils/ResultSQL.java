package br.com.easyShop.persistencia.utils;

import java.sql.ResultSet;
import java.util.Vector;

/**
 * Classe utilizada para receber o resultado de uma consulta JDBC em um banco de dados
 * Possui metodos para trabalhar com o conteudo do conjunto de resultados
 */
public class ResultSQL
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private ResultSet resultSet;
	private Vector<String> cabecalho = new Vector<String>();
	private Vector<Vector<Object>> registros = new Vector<Vector<Object>>();

	
	/*-*-*-* Construtores *-*-*-*/
	/**
	 * Construtor vazio
	 */
	public ResultSQL() { }
	
	/**
	 * Construtor
	 * @param cabecalho vetor com as colunas do conjunto de resultados
	 * @param registros vetor de vetor com os registros do conjunto de resultados
	 */
	public ResultSQL(Vector<String> cabecalho, Vector<Vector<Object>> registros, ResultSet resultSet)
	{
		this.resultSet = resultSet;
		this.cabecalho = cabecalho;
		this.registros = registros;
	}

	
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Reimplementacao do metodo
	 * @return objeto em formato de string
	 */
	@Override
	public String toString()
	{
		return cabecalho.toString() + "\n" + registros.toString();
	}

	/**
	 * Retorna o valor de uma determinada coluna de um determinado registros
	 * @param index do registro
	 * @param coluna nome da coluna
	 * @return valor da coluna ou null caso no encontre valor
	 */
	public Object get(int index, String coluna)
	{
		int indexColuna = getCabecalhoIndex(coluna);
		
		if(index>=0 && index<registros.size() && indexColuna>-1)
		{
			return registros.get(index).get(indexColuna);
		}
		return null;
	}
	
	/**
	 * Retorna o valor de uma determinada coluna de um determinado registros
	 * @param index do registro
	 * @param coluna index da coluna
	 * @return valor da coluna ou null caso n�o encontre valor
	 */
	public Object get(int index, int coluna)
	{
		if(index>=0 && index<registros.size() && coluna>=0 && coluna<cabecalho.size())
		{
			return registros.get(index).get(coluna);
		}
		return null;
	}
	
	/**
	 * Retorna um registro completo de acordo com seu index
	 * @param index do registro a ser obtido
	 * @return vetor com os objetos que compoem o registro ou null caso n�o encontre o registro
	 */
	public Vector<Object> get(int index)
	{
		if(index>=0 && index<registros.size())
		{
			return registros.get(index);
		}
		return null;
	}
	
	/**
	 * Retorna a quantidade de registros do conjunto de resultados
	 * @return quantidade de registros
	 */
	public int size()
	{
		return registros.size();
	}
	
	/**
	 * Retorna o nome do cabecalho de uma determinada coluna de acordo com seu index
	 * @param index da coluna
	 * @return nome do cabecalho da coluna
	 */
	public String getCabecalho(int index)
	{
		if(index>=0 && index<cabecalho.size())
		{
			return cabecalho.get(index);
		}
		return null;
	}
	
	/**
	 * Retorna a quantidade de colunas que compoem o cabecalho
	 * @return quantidade de colunas que compoem o cabecalho
	 */
	public int sizeCabecalho()
	{
		return cabecalho.size();
	}
	
	/**
	 * Retorna o index de uma determinada coluna de acordo com seu nome
	 * @param coluna procurada
	 * @return index de uma determinada coluna de acordo com seu nome ou -1 caso nao encontre a coluna
	 */
	public int getCabecalhoIndex(String coluna)
	{
		for(int i=0; i<cabecalho.size(); i++)
		{
			if(cabecalho.get(i).equalsIgnoreCase(coluna))
			{
				return i;
			}
		}
		return -1;
	}
	
	
	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Vector<String> getCabecalho() { return cabecalho; }
	public void setCabecalho(Vector<String> cabecalho) { this.cabecalho = cabecalho; }
	
	public Vector<Vector<Object>> getRegistros() { return registros; }
	public void setRegistros(Vector<Vector<Object>> registros) { this.registros = registros; }

	public ResultSet getResultSet() { return resultSet; }
	public void setResultSet(ResultSet resultSet) { this.resultSet = resultSet; }
}
