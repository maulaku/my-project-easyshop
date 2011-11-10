package br.com.easyShop.persistencia.DAO.baseDAO;

import java.util.ArrayList;
import java.util.List;

import br.com.easyShop.persistencia.conexao.BancoDeDados;
import br.com.easyShop.persistencia.conexao.servidores.base.BaseJDBC;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.persistencia.utils.ResultSQL;
import br.com.easyShop.utils.Constantes;
import flex.messaging.FlexContext;

/**
 * Classe que extende BaseDAO, e responsavel conter os metodos basicos de acesso ao banco de dados dos projetos Atta
 */
public class BaseDAOAtta extends BaseDAO
{
	/*-*-*-* Metodos Projeto Atta *-*-*-*/
	/**
	 * Obtem todos os registros de um determinado tipo/tabela
	 * @author Flacker
	 * @param classe indique o tipo da classe/Tabela do banco de dados que os registros devem ser obtidos
	 * @param status do resgitros, ou null caso esse parametro nao deva ser levado em consideracao ou nao exista
	 * @param profundidade
	 * @param orderBy passe os campos que deverao estar no order by da query, exemplo: "nome ASC, idade DESC", ou null para sem ordenacao
	 * @return lista de registros
	 * @throws Exception
	 */
	public <T> List<T> getTodos(Class<T> classe, int profundidade, Integer status, String orderBy) throws Exception
	{
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM " + classe.getSimpleName());
		if(status!=null) 	{ query.add(" WHERE status = ?", status); }
		if(orderBy!=null) 	{ query.add(" ORDER BY " + orderBy); }
		return (ArrayList<T>)obtem(classe, query, profundidade);
	}
	
	/**
	 * Obtem todos os registros de um determinado tipo/tabela
	 * @author Flacker
	 * @param classe indique o tipo da classe/Tabela do banco de dados que os registros devem ser obtidos
	 * @param status do resgitros, ou null caso esse parametro nao deva ser levado em consideracao ou nao exista
	 * @param profundidade
	 * @param orderBy passe os campos que deverao estar no order by da query, exemplo: "nome ASC, idade DESC", ou null para sem ordenacao
	 * @param classes vetor de classes que devem ser incluidas ou excluidas do preenchimento (depende do parametro classesVerifica, verifique o javaDOC)
	 * @param classesVerifica pode assumir as constantes: CONSIDERAR_CLASSES ou IGNORAR_CLASSES
	 * <BR>CONSIDERAR_CLASSES: Ao realizar uma busca de profundidade onde os relacionamentos ManyToOne sao retornados sera verificado se eles estao no vetor de classes e assim serao obtidos, caso o vetor de classes seja nulo todas os relacionamentos serao considerados
	 * <BR>IGNORAR_CLASSES: Ao realizar uma busca de profundidade onde os relacionamentos ManyToOne sao retornados sera verificado se eles estao no vetor de classes e assim serao ignorados, caso o vetor de classes seja nulo todas os relacionamentos serao ignorados
	 * @return lista de registros
	 * @throws Exception
	 */
	public <T> List<T> getTodos(Class<T> classe, int profundidade, Integer status, String orderBy, Class<?>[] classes, int classesVerifica) throws Exception
	{
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM " + classe.getSimpleName());
		if(status!=null) 	{ query.add(" WHERE status = ?", status); }
		if(orderBy!=null) 	{ query.add(" ORDER BY " + orderBy); }
		return (ArrayList<T>)obtem(classe, query, profundidade, classes, classesVerifica);
	}
	
	/**
	 * Obtem todos os registros de um determinado tipo/tabela referentes a um determinado campo
	 * @author Flacker
	 * @param classe indique o tipo da classe/Tabela do banco de dados que os registros devem ser obtidos
	 * @param status do resgitros, ou null caso esse parametro nao deva ser levado em consideracao ou nao exista
	 * @param profundidade
	 * @param campo coluna do banco de dados a ser considerada, caso seja nullo sera ignorado
	 * @param valor do campo a ser considerado
	 * @param orderBy passe os campos que deverao estar no order by da query, exemplo: "nome ASC, idade DESC", ou null para sem ordenacao
	 * @param classes vetor de classes que devem ser incluidas ou excluidas do preenchimento (depende do parametro classesVerifica, verifique o javaDOC)
	 * @param classesVerifica pode assumir as constantes: CONSIDERAR_CLASSES ou IGNORAR_CLASSES
	 * <BR>CONSIDERAR_CLASSES: Ao realizar uma busca de profundidade onde os relacionamentos ManyToOne sao retornados sera verificado se eles estao no vetor de classes e assim serao obtidos, caso o vetor de classes seja nulo todas os relacionamentos serao considerados
	 * <BR>IGNORAR_CLASSES: Ao realizar uma busca de profundidade onde os relacionamentos ManyToOne sao retornados sera verificado se eles estao no vetor de classes e assim serao ignorados, caso o vetor de classes seja nulo todas os relacionamentos serao ignorados
	 * @return lista de registros
	 * @throws Exception
	 */
	public <T> List<T> getTodos(Class<T> classe, int profundidade, String campo, Object valor, Integer status, String orderBy, Class<?>[] classes, int classesVerifica) throws Exception
	{
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM " + classe.getSimpleName());
		if(status!=null) 	{ query.add(" WHERE status = ?", status); }
		if(campo!=null && valor!=null) 
		{ 
			if(status == null)
			{ query.add(" WHERE " + campo + "=?", valor); }
			else
			{ query.add(" AND " + campo + "=?", valor); }
		}
		if(orderBy!=null) 	{ query.add(" ORDER BY " + orderBy); }
		return (ArrayList<T>)obtem(classe, query, profundidade, classes, classesVerifica);
	}
	
	/**
	 * <B>Controla a conexao sendo responsavel iniciar uma conexao e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAO#obtem(BaseJDBC, Class, Object, int, Class[], int)}
	 * @throws Exception
	 */
	public <T> List<T> obtem(Class<T> classe, Object condicao, int profundidade, Class<?>[] classes, int classesVerifica) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			List<T> lista = obtem(baseJDBC, classe, condicao, profundidade, classes, classesVerifica);
			baseJDBC.liberarConexao(false);
			return lista;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * {@link BaseDAOAtta#obtem(Class, Object, int, Class[], int)}
	 */
	public <T> List<T> obtem(Class<T> classe, Object condicao, int profundidade) throws Exception
	{
		return obtem(classe, condicao, profundidade, null, CONSIDERAR_CLASSES);
	}
	
	
	/**
	 * Obtem um objeto a partir de um resultado de pesquisa no banco de dados, explicacao de funcionamento:
	 * <BR>O primeiro elemento da lista sera retornado ou null caso a pesquisa nao traga resultados (execute querys de apenas uma resposta para maior eficiencia)
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAO#obtem(BaseJDBC, Class, Object, int, Class[], int)}
	 * @throws Exception
	 */
	public <T> T obtemUnico(Class<T> classe, Object condicao, int profundidade, Class<?>[] classes, int classesVerifica) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			T objeto = obtemUnico(baseJDBC, classe, condicao, profundidade, classes, classesVerifica);
			baseJDBC.liberarConexao(false);
			return objeto;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * {@link BaseDAOAtta#obtemUnico(Class, Object, int, Class[], int)}
	 */
	public <T> T obtemUnico(Class<T> classe, Object condicao, int profundidade) throws Exception
	{
		return obtemUnico(classe, condicao, profundidade, null, CONSIDERAR_CLASSES);
	}

	
	/**
	 * Metodo que executa uma query de Select na base de dados
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @return ResultSQL com o registro inserido e sua PK
	 * @throws Exception
	 */
	public ResultSQL obtemSQL(Object query) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			return baseJDBC.executaSelect(query, true);
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que executa uma query de Select na base de dados
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @return ResultSQL com o registro inserido e sua PK
	 * @throws Exception
	 */
	public ResultSQL obtemSQL(BaseJDBC baseJDBC, Object query) throws Exception
	{
		return baseJDBC.executaSelect(query, false, false);
	}
	
	/**
	 * Metodo que Insere um registro no banco de dados, a pk do registro e preenchida no campo com a annotation Id
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * @param objeto a ser persistido
	 * @return resultado SQL do insert
	 * @throws Exception
	 */
	public ResultSQL inserir(Object objeto) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			ResultSQL resultSQL = inserir(baseJDBC, objeto);
			baseJDBC.liberarConexao(true);
			return resultSQL;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que executa uma query de Insert na base de dados
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @return ResultSQL com o registro inserido e sua PK
	 * @throws Exception
	 */
	public ResultSQL inserirSQL(Object query) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			return baseJDBC.executaInsert(query, true, true);
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que altera um registro no banco de dados utilizando de referencia seu campo com a annotations Id, todos os campos e relacionamentos ManyToOne do objeto serao utilizados no update
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAOSQL#objectToUpdateSQL(Object, BaseJDBC, Class[], int)}
	 * @param objeto a ser alterado
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int alterar(Object objeto) throws Exception
	{
		return alterar(objeto, null, BaseDAO.CONSIDERAR_CLASSES);
	}
	
	/**
	 * Metodo que altera um registro no banco de dados utilizando de referencia seu campo com a annotations Id
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAOSQL#objectToUpdateSQL(Object, BaseJDBC, Class[], int)}
	 * @throws Exception
	 */
	public int alterar(Object objeto, int classesVerifica) throws Exception
	{
		return alterar(objeto, null, classesVerifica);
	}
	
	/**
	 * Metodo que altera um registro no banco de dados utilizando de referencia seu campo com a annotations Id, todos os campos do objeto serao utilizados no update
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * <BR>{@link BaseDAOSQL#objectToUpdateSQL(Object, BaseJDBC, Class[], int)}
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int alterar(BaseJDBC baseJDBC, Object objeto) throws Exception
	{
		return alterar(baseJDBC, objeto, null, CONSIDERAR_CLASSES);
	}
	
	/**
	 * Metodo que altera um registro no banco de dados utilizando de referencia seu campo com a annotations Id
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAOSQL#objectToUpdateSQL(Object, BaseJDBC, Class[], int)}
	 * @throws Exception
	 */
	public int alterar(Object objeto, Class<?>[] classes, int classesVerifica) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			int linhasAfetadas = alterar(baseJDBC, objeto, classes, classesVerifica);
			baseJDBC.liberarConexao(true);
			return linhasAfetadas;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que executa uma query de Update na base de dados
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int alterarSQL(Object query) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			return baseJDBC.executaUpdate(query, true, true);
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}

	/**
	 * Metodo que executa uma query de Update na base de dados
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * @param baseJDBC conexao a ser utilizada para realizar a operacao
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int alterarSQL(BaseJDBC baseJDBC, Object objeto) throws Exception
	{
		return baseJDBC.executaUpdate(objeto);
	}
	
	/**
	 * Metodo que remove um registro no banco de dados utilizando de referencia seu campo com a annotations Id
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * @param objeto a ser removido
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int remover(Object objeto) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			int linhasAfetadas = remover(baseJDBC, objeto);
			baseJDBC.liberarConexao(true);
			return linhasAfetadas;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que executa uma query de Delete na base de dados
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int removerSQL(Object query) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			return baseJDBC.executaDelete(query, true, true);
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que executa uma query de Delete na base de dados
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * @param baseJDBC conexao a ser utilizada para realizar a operacao
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int removerSQL(BaseJDBC baseJDBC, Object objeto) throws Exception
	{
		return baseJDBC.executaDelete(objeto);
	}
	
	/**
	 * Metodo que salva um registro no banco de dados utilizando de referencia seu campo com a annotations Id, todos os campos e relacionamentos ManyToOne do objeto serao utilizados no update
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAO#salvar(BaseJDBC, Object)}
	 * @param objeto a ser inserido ou alterado
	 * @return numero de linhas afetadas ou
	 * @throws Exception
	 */
	public Object salvar(Object objeto) throws Exception
	{
		return salvar(objeto, null, BaseDAO.CONSIDERAR_CLASSES);
	}
	
	/**
	 * Metodo que altera um registro no banco de dados utilizando de referencia seu campo com a annotations Id
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAO#salvar(BaseJDBC, Object)}
	 * @throws Exception
	 */
	public Object salvar(Object objeto, int classesVerifica) throws Exception
	{
		return salvar(objeto, null, classesVerifica);
	}
	
	/**
	 * Metodo que salva (insere ou altera) um registro no banco de dados utilizando de referencia seu campo com a annotations Id, todos os campos do objeto serao utilizados no update
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * <BR>{@link BaseDAO#salvar(BaseJDBC, Object)}
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public Object salvar(BaseJDBC baseJDBC, Object objeto) throws Exception
	{
		return salvar(baseJDBC, objeto, null, CONSIDERAR_CLASSES);
	}
	
	/**
	 * Metodo que salva (insere ou altera) um registro no banco de dados utilizando de referencia seu campo com a annotations Id
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * <BR>{@link BaseDAO#salvar(BaseJDBC, Object)}
	 * @throws Exception
	 */
	public Object salvar(Object objeto, Class<?>[] classes, int classesVerifica) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			Object resultado = salvar(baseJDBC, objeto, classes, classesVerifica);
			baseJDBC.liberarConexao(true);
			return resultado;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que altera o status de um objeto no banco de dados
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao
	 * @param objeto a ser alterado
	 * @param status do registro
	 * @return int com numero de linhas alteradas
	 * @throws Exception
	 */
	public int setStatus(Object objeto, int status) throws Exception
	{
		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			int linhasAfetadas = setStatus(baseJDBC, objeto, status);
			baseJDBC.liberarConexao(true);
			return linhasAfetadas;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Metodo que altera o status de um objeto no banco de dados
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * @param baseJDBC conexao a ser utilizada para realizar a operacao
	 * @param objeto a ser alterado
	 * @param status do registro
	 * @return int com numero de linhas alteradas
	 * @throws Exception
	 */
	public int setStatus(BaseJDBC baseJDBC, Object objeto, int status) throws Exception
	{
		QuerySQL query = new QuerySQL();
		query.add("UPDATE " + BaseDAOSQL.getNomeTabela(objeto));
		query.add(" SET status=?", status);
		query.add(" WHERE " + BaseDAOSQL.getIdName(objeto) + "=?", getIdValue(objeto));
		return baseJDBC.executaUpdate(query.toString(baseJDBC), false, false);
	}
	
	/**
	 * Verifica se existe um registro nao removido que esta utilizando o codigo
	 * <BR>Caso o codigo seja nulo ou vazio ele retorna que o codigo esta livre pois considera que este nao e um campo obrigatorio
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao e liberar a conexao apos sua execucao</B>
	 * @param objeto
	 * @param codigo
	 * @return true caso o codigo nao esteja sendo utilizado e false caso ele esteja sendo utilizado ou esteja incorreto com tamanho 0 ou null
	 * @throws Exception
	 */
	public boolean isCodigoLivre(Object objeto, String codigo) throws Exception
	{
		if(codigo==null || codigo.length()==0) { return false; }

		QuerySQL query = new QuerySQL();
		query.add("SELECT codigo");
		query.add(" FROM " + BaseDAOSQL.getNomeTabela(objeto));
		query.add(" WHERE " + BaseDAOSQL.getIdName(objeto) + "<>?", getIdValue(objeto));
		query.add(" AND codigo=?", codigo);
		query.add(" AND status=?", Constantes.STATUS_ATIVO);

		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			ResultSQL resultSQL = baseJDBC.executaSelect(query.toString(baseJDBC), true);
			return resultSQL.size()<=0;
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
	
	/**
	 * Define o campo codigo como vazio dos registros removidos que possuem um determinado codigo
	 * <BR><B>Controla a conexao sendo responsavel iniciar uma conexao, comitar e liberar a conexao apos sua execucao</B>
	 * @param objeto que NAO tera seu codigo removido
	 * @param codigo
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int setCodigoLivre(Object objeto, String codigo) throws Exception
	{
		QuerySQL query = new QuerySQL();
		query.add("UPDATE " + BaseDAOSQL.getNomeTabela(objeto));
		query.add(" SET codigo=''");
		query.add(" WHERE " + BaseDAOSQL.getIdName(objeto) + "<>?", getIdValue(objeto));
		query.add(" AND codigo=?", codigo);
		query.add(" AND status=?", Constantes.STATUS_REMOVIDO);

		BaseJDBC baseJDBC = BancoDeDados.getConexao(FlexContext.getFlexSession());
		try
		{
			return baseJDBC.executaUpdate(query.toString(baseJDBC), true, true);
		}
		catch(Exception e)
		{
			baseJDBC.liberarConexao(false);
			throw e;
		}
	}
}
