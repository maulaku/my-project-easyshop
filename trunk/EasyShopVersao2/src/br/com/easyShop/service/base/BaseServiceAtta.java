package br.com.easyShop.service.base;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.utils.Constantes;

import logs.Logs;
import logs.LogsUtil;

public class BaseServiceAtta
{
	 /**
     * Obtem todas os registros de uma tabela
     * @author Flacker
	 * @param objetoClasse instancia do objeto/tabela que se deseja obter os registros
	 * @param profundidade da obtencao dos dados, caso seja nullo sera utilizada a profundidade -1
	 * @param status caso a tabela possua o campo status se valor pode ser passado por parametro, caso seja nullo ele sera ignorado
	 * @param orderBy passe os campos que deverao estar no order by da query, exemplo: "nome ASC, idade DESC", ou null para sem ordenacao
     * @return todas os tipos de normas
     */
	public ResultJava getTodos(Object objetoClasse, Integer profundidade, Integer status, String orderBy)
	{
		try
		{
			return new ResultJava(new BaseDAOAtta().getTodos(objetoClasse.getClass(), (profundidade!=null ? profundidade : -1), status, orderBy));
		} 			
		catch(Exception e)
		{
			Logs.addError("Falha obtendo registros", e);
			return new ResultJava();
		}
	}
	
	/**
	 * Salva um registro no banco de dados, caso a pk seja 0 o registro sera inserido, caso contrario sera feito um update de seus campos 
	 * @author Flacker
	 * @param registro, objeto a ser salvo no banco de dados.
	 * @return ResultJava, true em caso de sucesso e false em caso de erros, a lista para o caso de false voltara com uma string na primeira posicao indicando o erro
	 */
	public ResultJava salvar(Object registro)
	{		
		try
		{	
			new BaseDAOAtta().salvar(registro);
			return new ResultJava(true);
		} 
		catch(Exception e)
		{
			Logs.addError("Falha salvando o registro: " + LogsUtil.classeToString(registro), e);
			return new ResultJava(false, Arrays.asList(new String[]{"Não foi possivel salvar o registro"}));
		}
	}
	
	/**
	 * Remove do banco de dados um registro definindo seu status como Removido (Nao aparaga efetivamente o registro do banco)
	 * @author Flacker
	 * @param registro, objeto/registro a ser removido do banco de dados
	 * @return ResultJava, true em caso de sucesso e false em caso de erros, a lista para o caso de false voltara com uma string na primeira posicao indicando o erro
	 */
	public ResultJava removerStatus(Object registro)
	{
		try
		{
			 if((new BaseDAOAtta().setStatus(registro, Constantes.STATUS_REMOVIDO)) > 0)
			 { return new ResultJava(true);}
			 else 
			 { return new ResultJava(false);}
		} 
		catch(Exception e)
		{
			Logs.addError("Falha removendo status do registro: " + LogsUtil.classeToString(registro), e);
			return new ResultJava(false);
		}
		
	}
	
	/**
	 * Remove do banco de dados um registro
	 * @author Flacker
	 * @param registro, objeto/registro a ser removido do banco de dados
	 * @return ResultJava, true em caso de sucesso e false em caso de erros, a lista para o caso de false voltara com uma string na primeira posicao indicando o erro
	 */
	public ResultJava remover(Object registro)
	{
		try
		{
			return new ResultJava(new BaseDAOAtta().remover(registro)>0);
		} 
		catch(Exception e)
		{
			Logs.addError("Falha removendo registro: " + LogsUtil.classeToString(registro), e);
			return new ResultJava(false);
		}
	}
	
	/**
	 * Obtem um registro do banco de dados a partir de uma coluna e um valor
	 * @param objetoClasse instancia do objeto/tabela que se deseja obter os registros
	 * @param campo nome da coluna
	 * @param valor valor procurado na coluna
	 * @param profundidade
	 * @return
	 */
	public ResultJava obtemRegistroPeloCampo(Object objetoClasse, String campo, String valor, int profundidade)
	{		
		try
		{
			QuerySQL query = new QuerySQL();
			query.add("SELECT *");
			query.add(" FROM " + objetoClasse.getClass().getSimpleName());
			query.add(" WHERE " + campo + "=?", valor);
			return new ResultJava(new BaseDAOAtta().obtemUnico(objetoClasse.getClass(), query, profundidade));
		}
		catch(Exception e)
		{
			Logs.addError("Obtendo registro pelo campo: " + campo + ", " + valor, e);
			return new ResultJava(false, Arrays.asList(new String[]{"Não foi possivel obter o registro"}));
		}
	}
	/**
	 * Obtem varios registros do banco de dados a partir de uma coluna e um valor
	 * @param objetoClasse instancia do objeto/tabela que se deseja obter os registros
	 * @param campo nome da coluna
	 * @param valor valor procurado na coluna
	 * @param profundidade
	 * @param status
	 * @param orderBy
	 * @return Lista com os registros obtidos
	 */
	public ResultJava obtemNRegistrosPeloCampo(Object objetoClasse, String campo, Object valor, int profundidade, Integer status, String orderBy)
	{		
		try
		{
			QuerySQL query = new QuerySQL();
			query.add("SELECT *");
			query.add(" FROM " + objetoClasse.getClass().getSimpleName());
			query.add(" WHERE " + campo + "=?", valor);
			if(status!=null) 	{ query.add(" AND status=?", status); }
			if(orderBy!=null) 	{ query.add(" ORDER BY " + orderBy); }
			return new ResultJava(new BaseDAOAtta().obtem(objetoClasse.getClass(), query, profundidade));
		}
		catch(Exception e)
		{
			Logs.addError("Obtendo registros pelo campo: " + campo + ", " + valor, e);
			return new ResultJava(false, Arrays.asList(new String[]{"Não foi possivel obter os registros"}));
		}
	}
	
	
}
