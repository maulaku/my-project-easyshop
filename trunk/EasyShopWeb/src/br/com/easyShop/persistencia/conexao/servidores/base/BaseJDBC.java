package br.com.easyShop.persistencia.conexao.servidores.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import logs.Logs;
import utils.data.Data;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOSQL;
import br.com.easyShop.persistencia.conexao.pool.PoolJDBC;
import br.com.easyShop.persistencia.conexao.pool.PoolJDBCItem;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.persistencia.utils.ResultSQL;

/**
 * Classe responsavel conter os metodos a serem implementados por classes de acesso a banco de dados via JDBC
 */
public abstract class BaseJDBC extends PoolJDBCItem
{
	/*-*-*-* Variaveis e Objetos Protegidos *-*-*-*/
	protected Connection connection = null;
	protected String driver = "";
	protected String url = "";
	protected String endereco = new String();
	protected String base = new String();
	protected int porta = 0;
	protected String login = new String();
	protected String senha = new String();
	
	
	/*-*-*-* Construtores *-*-*-*/
	/**
	 * COnstrutor Vazio
	 */
	public BaseJDBC() { }
	
	/**
	 * Construtor que recebe as informacoes da conexao com o banco de dados e inicializa as variaveis de Driver e Prefixo da URL do banco de dados
	 * @param driver a ser utilizado para a conexao
	 * @param url prefixo da url de conexao, sera concatenada com o endereco
	 * @param endereco do banco de dados
	 * @param base a ser acessada
	 * @param porta que recebe as conexoes da base
	 * @param login do banco de dados
	 * @param senha do banco de dados
	 */
	public BaseJDBC(String driver, String url, String endereco, String base, int porta, String login, String senha, PoolJDBC poolJDBC)
	{
		this.driver = driver;
		this.url = url;
		this.endereco = endereco;
		this.base = base;
		this.porta = porta;
		this.login = login;
		this.senha = senha;
		setPoolJDBC(poolJDBC);
	}
	
	
	/*-*-*-* Metodos Publicos *-*-*-*/	
	/**
	 * Metodo que tenta conectar no banco de dados caso ele nao esteja conectado
	 * @return true caso a conexao seja estabelecida ou ja exista e false caso nao seja possivel estabelecer uma conexao
	 * @exception disparada em caso de erro o estabelecimento da conexao
	 */
	public boolean tryConexao()
	{
		try
		{
			if(connection==null || connection.isClosed())
			{
				setReserva(new Data());
				Class.forName(driver); //Verifica se a classe do driver existe, caso nao exista lanca exception
				connection = DriverManager.getConnection(url + getEndereco() + ":" + getPorta() + "/" + getBase(), getLogin(), getSenha());
				connection.setAutoCommit(false);
				setLivre(true);
			}
			return true;
		}
		catch(Exception e)
		{
			Logs.addError("Falha tentando conectar com a base de dados", e);
			return false;
		}
	}
	
	/**
	 * Metodo que reconecta no banco de dados (tenta estabeler uma nova conexao sobreescrevendo a conexao existente (Evite utilizar este metodo)
	 * @return true caso a conexao seja estabelecida e false caso nao seja possivel estabelecer uma conexao
	 * @exception disparada em caso de erro o estabelecimento da conexao
	 */
	public boolean reconectar() throws Exception
	{
		try
		{
			setReserva(new Data());
			Class.forName(driver); //Verifica se a classe do driver existe, caso nao exista lanca exception
			connection = DriverManager.getConnection(url + getEndereco() + ":" + getPorta() + "/" + getBase(), getLogin(), getSenha());
			connection.setAutoCommit(false);
			setLivre(true);
			return true;
		}
		catch(Exception e)
		{
			Logs.addError("Falha tentando conectar com a base de dados", e);
			return false;
		}
	}
	
	/**
	 * Metodo que desconecta do banco de dados liberando, fechando e definindo como null a conexao
	 * @param livre apos desconectar define a conexao para o pool como livre ou ocupada
	 * @exception disparada em caso de erro tentando fechar a conexao
	 */
	public void desconectar(boolean livre) throws Exception
	{
		if(connection!=null)
		{
			setLivre(livre);
			connection.close();
			connection = null;
		}
	}
	
	/**
	 * Executa a operacao de RollBack na conexao
	 * @throws Exception
	 */
	public void rollBack() throws Exception
	{
		setReserva(new Data());
		if(connection!=null) { connection.rollback(); }
	}
	
	/**
	 * Fecha um Statment, caso o parametro seja nullo nada sera executado
	 * @param statement a ser fechado
	 * @throws Exception
	 */
	public void closeStatement(Statement statement) throws Exception
	{
		if(statement!=null) { statement.close(); }
	}
	
	/**
	 * Executa a operacao de Commit na conexao
	 * @throws Exception
	 */
	public void commit() throws Exception
	{
		setReserva(new Data());
		if(connection!=null) { connection.commit(); }
	}
	
	/**
	 * Libera a conexao comitando ou nao os valores ou executando um rollBack, a liberacao da conexao e utilizada pelo pool de conexoes
	 * @param parametros
	 * <BR>Nenhum: A conexao sera comitada e depois definida como livre
	 * <BR>Boolean: true para comitar os dados e definir como livre (equivamente a nenhum parametro)
	 * <BR>			false executa um rollBack libera a conexao (Nao comita os dados)
	 * @throws Exception
	 */
	public void liberarConexao(Object... parametros)
	{
		try
		{
			if(parametros==null || ((parametros.length>0) && (parametros[0] instanceof Boolean) && ((Boolean)parametros[0]))) 	{ commit(); }
			else if((parametros.length>0) && (parametros[0] instanceof Boolean) && ((Boolean)parametros[0])==false) 			{ rollBack(); }
			setLivre(true);
		}
		catch(Exception e)
		{
			Logs.addFatal("Liberando conexao: " + parametros, e);
		}
	}
	
	/**
	 * Metodo que executa uma query de pesquisa no banco de dados
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @param parametros
	 * <BR><B>Nenhum Parametro:</B> ()
	 * <BR>   A conexao sera liberada
	 * <BR><B>Um Parametro:</B> (Boolean)
	 * <BR>   1: TRUE para liberar a conexao e FALSE para o contrario
	 * @return ResultSQL com os registros da consulta
	 * @exception Exception
	 */
	public ResultSQL executaSelect(Object query, Object... parametros) throws Exception
	{
		setReserva(new Data());
		tryConexao(); //Caso nao esteja conectado tenta conectar no banco
		Statement statement = connection.createStatement();
		
		try
		{
			String sql;
			if(query instanceof QuerySQL)	{ sql = ((QuerySQL)query).toString(this); }
			else							{ sql = (String)query; }
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			ResultSQL resultSQL = BaseDAOSQL.resultSetToResultSQL(resultSet, this);
			if(parametros==null || ((parametros.length>0) && (parametros[0] instanceof Boolean) && ((Boolean)parametros[0]))) { setLivre(true); }
			closeStatement(statement);
			return resultSQL;
		}
		catch(Exception e)
		{
			rollBack();
			closeStatement(statement);
			throw new Exception("Executando query [" + query + "]\n" + e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que executa uma query de insert simples onde a PK do registro nao sera retornada no objeto
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @param parametros
	 * <BR><B>Nenhum Parametro:</B> ()
	 * <BR>   A conexao sera comitada e liberada
	 * <BR><B>Um Parametro:</B> (Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR><B>Dois Parametros:</B> (Boolean, Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR>   2: TRUE para liberar a conexao e FALSE para o contrario
	 * @return numero de linhas afetadas
	 * @exception Exception
	 */
	public int executaInsertSimples(Object query, Object... parametros) throws Exception
	{
		setReserva(new Data());
		tryConexao(); //Caso nao esteja conectado tenta conectar no banco
		Statement statement = connection.createStatement();
		
		try
		{
			String sql;
			if(query instanceof QuerySQL)	{ sql = ((QuerySQL)query).toString(this); }
			else							{ sql = (String)query; }
			
			int linhasAfetadas = statement.executeUpdate(sql);

			if(parametros==null || ((parametros.length>0) && (parametros[0] instanceof Boolean) && ((Boolean)parametros[0]))) { connection.commit(); }
			if(parametros==null || ((parametros.length>1) && (parametros[1] instanceof Boolean) && ((Boolean)parametros[1]))) { setLivre(true); }
			closeStatement(statement);
			return linhasAfetadas;
		}
		catch(Exception e)
		{
			rollBack();
			closeStatement(statement);
			throw new Exception("Executando query [" + query + "]\nROLLBACK efetuado\n" + e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que executa uma query de insert no banco de dados e retorna o registro inserido com sua PrimayKey, este metodo nao e suportado por algumas bases de dados
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @param parametros
	 * <BR><B>Nenhum Parametro:</B> ()
	 * <BR>   A conexao sera comitada e liberada
	 * <BR><B>Um Parametro:</B> (Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR><B>Dois Parametros:</B> (Boolean, Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR>   2: TRUE para liberar a conexao e FALSE para o contrario
	 * @return ResultSQL com o registro da insersao e com sua primarykey preenchida
	 * @exception Exception
	 */
	public ResultSQL executaInsert(Object query, Object... parametros) throws Exception
	{
		setReserva(new Data());
		tryConexao(); //Caso nao esteja conectado tenta conectar no banco
		Statement statement = connection.createStatement();
		
		try
		{
			String sql;
			if(query instanceof QuerySQL)	{ sql = ((QuerySQL)query).toString(this); }
			else							{ sql = (String)query; }
			
			statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			
			ResultSQL resultSQL = BaseDAOSQL.resultSetToResultSQL(statement.getGeneratedKeys(), this);
			if(parametros==null || ((parametros.length>0) && (parametros[0] instanceof Boolean) && ((Boolean)parametros[0]))) { connection.commit(); }
			if(parametros==null || ((parametros.length>1) && (parametros[1] instanceof Boolean) && ((Boolean)parametros[1]))) { setLivre(true); }
			closeStatement(statement);
			return resultSQL;
		}
		catch(Exception e)
		{
			rollBack();
			closeStatement(statement);
			throw new Exception("Executando query [" + query + "]\nROLLBACK efetuado\n" + e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que executa uma query de delete no banco de dados
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @param parametros
	 * <BR><B>Nenhum Parametro:</B> ()
	 * <BR>   A conexao sera comitada e liberada
	 * <BR><B>Um Parametro:</B> (Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR><B>Dois Parametros:</B> (Boolean, Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR>   2: TRUE para liberar a conexao e FALSE para o contrario
	 * @return numero de linhas afetadas
	 * @exception Exception
	 */
	public int executaDelete(Object query, Object... parametros) throws Exception
	{
		setReserva(new Data());
		tryConexao(); //Caso nao esteja conectado tenta conectar no banco
		Statement statement = connection.createStatement();

		try
		{
			String sql;
			if(query instanceof QuerySQL)	{ sql = ((QuerySQL)query).toString(this); }
			else							{ sql = (String)query; }
			
			int linhasAfetadas = statement.executeUpdate(sql);
			
			if(parametros==null || ((parametros.length>0) && (parametros[0] instanceof Boolean) && ((Boolean)parametros[0]))) { connection.commit(); }
			if(parametros==null || ((parametros.length>1) && (parametros[1] instanceof Boolean) && ((Boolean)parametros[1]))) { setLivre(true); }
			closeStatement(statement);
			return linhasAfetadas;
		}
		catch(Exception e)
		{
			rollBack();
			closeStatement(statement);
			throw new Exception("Executando query [" + query + "]\nROLLBACK efetuado\n" + e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo que executa uma query de update no banco de dados
	 * @param query
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * @param parametros
	 * <BR><B>Nenhum Parametro:</B> ()
	 * <BR>   A conexao sera comitada e liberada
	 * <BR><B>Um Parametro:</B> (Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR><B>Dois Parametros:</B> (Boolean, Boolean)
	 * <BR>   1: TRUE para commitar a conexao e FALSE para o contrario
	 * <BR>   2: TRUE para liberar a conexao e FALSE para o contrario
	 * @return numero de linhas afetadas
	 * @exception Exception
	 */
	public int executaUpdate(Object query, Object... parametros) throws Exception
	{
		setReserva(new Data());
		tryConexao(); //Caso nao esteja conectado tenta conectar no banco
		Statement statement = connection.createStatement();

		try
		{
			String sql;
			if(query instanceof QuerySQL)	{ sql = ((QuerySQL)query).toString(this); }
			else							{ sql = (String)query; }
			
			int linhasAfetadas = statement.executeUpdate(sql);
			
			if(parametros==null || ((parametros.length>0) && (parametros[0] instanceof Boolean) && ((Boolean)parametros[0]))) { connection.commit(); }
			if(parametros==null || ((parametros.length>1) && (parametros[1] instanceof Boolean) && ((Boolean)parametros[1]))) { setLivre(true); }
			closeStatement(statement);
			return linhasAfetadas;
		}
		catch(Exception e)
		{
			rollBack();
			closeStatement(statement);
			throw new Exception("Executando query [" + query + "]\nROLLBACK efetuado\n" + e.getMessage(), e);
		}
	}
	
	
	/*-*-*-* Metodos Publicos Abstratos *-*-*-*/
	/**
	 * Metodo que converte um parametro de um ResultSet em seu respectivo Objeto no Java
	 * @param resultSet que contem o elemento a ser convertido
	 * @param resultSetMetaData que contem o tipo do elemento no banco de dados
	 * @param index do elemento no ResultSet
	 * @return Objeto java equivalente ao tipo do elemento no banco de dados, caso o tipo do parametro nao esteja mapeado sera retornando ele em formato de String
	 */
	public abstract Object SQLToObject(ResultSet resultSet, ResultSetMetaData resultSetMetaData, int index) throws Exception;
	
	/**
	 * Metodo que converte um objeto qualquer em seu formato SQL para ser utilizado em Querys inserindo caracteres de escape
	 * @param object a ser convertido
	 * @return string do valor do objeto em formato SQL, em caso de erros retorna null
	 * <BR>Alguns resultados:
	 * <BR>null ou Double.NaN -> null
	 * <BR>String 	-> 'string'
	 * <BR>Char		-> 'c'
	 * <BR>Data 	-> 'yyyy-MM-dd HH:mm:ss'
	 * <BR>List 	-> this(lista.get(0)), this(lista.get(1)), ...
	 * <BR>vetor[] 	-> this(vetor[0]), this(vetor[1]), ...
	 * <BR>Caso o parametro nao seja identificado sera retornado: parametro.toString()
	 */
	public abstract String objectToSQL(Object object);
	
	/**
	 * Metodo que clona a classe de conexao com o banco de dados, utilizado pelo pool de conexoes
	 * este metodo nao conecta na base
	 * @param BaseJDBC clonado
	 * @throws Exception
	 */
	public abstract BaseJDBC clonar() throws Exception;
	

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public String getEndereco() { return endereco; }
	public void setEndereco(String endereco) { this.endereco = endereco; }
	
	public String getBase() { return base; }
	public void setBase(String base) { this.base = base; }
	
	public int getPorta() { return porta; }
	public void setPorta(int porta) { this.porta = porta; }
	
	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }
	
	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }

	public String getDriver() { return driver; }
	public void setDriver(String driver) { this.driver = driver; }

	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	
	public Connection getConnection() { return connection; }
	public void setConnection(Connection connection) { this.connection = connection; }
}
