package br.com.easyShop.persistencia.conexao.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import logs.Logs;
import utils.data.Data;
import br.com.easyShop.persistencia.conexao.servidores.base.BaseJDBC;

/**
 * Classe responsavel implementar um pool de conexoes
 */
public class PoolJDBC
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private BaseJDBC baseJDBC;
	private List<BaseJDBC> conexoes = new ArrayList<BaseJDBC>();
	private int poolMinimo = 1;
	private int poolMaximo = 10;
	private ColetorLixo coletorLixo;
	private Semaphore semaforo;
	private Semaphore semaforoLista = new Semaphore(1);

	
	/*-*-*-* Construtores *-*-*-*/
	/**
	 * Construtor
	 * @param baseJDBC uma instancia de conexao com o banco de dados
	 * @param poolMinimo numero minimo de conexoes que devem ser mantidas abertas com a base de dados
	 * @param poolMaximo numero maximo de conexoes que podem ser mantidas abertas com a base de dados
	 * @param tempoColetor tempo de cada iteracao do coletor (ms)
	 * @param conexaoTimeOut tempo que a conexao e considera inutil (esteja ou nao sendo utilizada) (ms)
	 * @throws exception em caso de falha de conexao com a base de dados
	 */
	public PoolJDBC(BaseJDBC baseJDBC, int poolMinimo, int poolMaximo, int tempoColetor, int conexaoTimeOut) throws Exception
	{
		this.baseJDBC = baseJDBC;
		this.baseJDBC.setPoolJDBC(this);
		this.poolMinimo = poolMinimo;
		this.poolMaximo = poolMaximo;
		this.semaforo = new Semaphore(poolMaximo);

		for(int i=0; i<this.poolMinimo; i++) { conexoes.add(baseJDBC.clonar()); }
		
		coletorLixo = new ColetorLixo(this, tempoColetor, conexaoTimeOut);
		coletorLixo.start();
	}

	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Tenta conectar todas as conexoes com a base de dados
	 */
	public void conectar() throws Exception
	{
		for(int i=0; i<conexoes.size(); i++) { conexoes.get(i).tryConexao(); }
	}
	
	/**
	 * Obtem uma conexao do banco de dados para ser utilizada
	 * @return BaseJDBC conexao nao utilizada ou com menos requisicoes no momento
	 */
	public synchronized BaseJDBC getConexao()
	{
		try
		{
			semaforo.acquire(1);
			semaforoLista.acquire(1);
			
			BaseJDBC conexao=null;
			for(int i=0; i<conexoes.size(); i++)
			{
				if(conexoes.get(i).isLivre())
				{
					conexao = conexoes.get(i);
					break; 
				}
			}

			if(conexao==null)
			{
				if(conexoes.size()<poolMaximo)
				{
					conexao = baseJDBC.clonar();
					conexoes.add(conexao);
					
					if(conexoes.size()>=poolMaximo)
					{
						Logs.addWarn("Numero maximo de conexoes no pool atingido");
					}
				}
				else
				{
					Logs.addFatal("Numero maximo de conexoes no pool atingido e nenhuma disponivel");
					return null;
				}
			}
			
			conexao.tryConexao();
			conexao.setLivre(false);
			conexao.setReserva(new Data());
			
			semaforoLista.release(1);
			
			return conexao;
		}
		catch (Exception e)
		{
			Logs.addError("Falha no metodo que obtem conexoes com o banco", e);
			return null;
		}
	}
	
	/**
	 * Libera o acesso para a obtencao de uma conexao
	 */
	public void liberarConexao()
	{
		try
		{
			semaforo.release(1);
		}
		catch (Exception e)
		{ Logs.addError("Falha liberando a conexao no pool", e); }
	}
	
	/**
	 * Metodo que desconectar todas as conexoes com o banco de dados
	 * @throws Exception
	 */
	public void desconectar() throws Exception
	{
		coletorLixo.requestStop();
		for(int i=0; i<conexoes.size(); i++)
		{
			conexoes.get(i).desconectar(true);
		}
	}
		
	/**
	 * Retorna o numero de conexoes abertas com a base de dados
	 * @return numero de conexoes abertas com a base de dados
	 */
	public int numeroConexoes()
	{
		return conexoes.size();
	}
	
	/**
	 * Retorna o numero de conexoes de um determinado status (Livre ou Ocupada)
	 * @param livre true para livre e false para nao livre (ocupada)
	 * @return numero de conexoes de um determinado status (Livre ou Ocupada) ou -1 caso no exista conexao ou algum erro ocorra
	 */
	public int numeroConexoes(boolean livre)
	{
		try
		{
			int contaStatus=0;
			semaforoLista.acquire(1);
			for(int i=0; i<conexoes.size(); i++)
			{
				if(conexoes.get(i).isLivre()==livre) { contaStatus++; }
			}
			semaforoLista.release(1);
			return contaStatus;
		} 
		catch (Exception e)
		{ 
			Logs.addError("Obtendo o numero de conexoes de livres: " + livre, e);
		}
		return -1;
	}
	
	
	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public ColetorLixo getColetorLixo() { return coletorLixo; }
	
	public List<BaseJDBC> getConexoes() { return conexoes; }
	public void setConexoes(List<BaseJDBC> conexoes) { this.conexoes = conexoes; }

	public BaseJDBC getBaseJDBC() { return baseJDBC; }
	public void setBaseJDBC(BaseJDBC baseJDBC) { this.baseJDBC = baseJDBC; }
	
	public int getPoolMinimo() { return poolMinimo; }
	public void setPoolMinimo(int poolMinimo) { this.poolMinimo = poolMinimo; }

	public int getPoolMaximo() { return poolMaximo; }
	public void setPoolMaximo(int poolMaximo) { this.poolMaximo = poolMaximo; }

	public Semaphore getSemaforoLista() { return semaforoLista; }
	public void setSemaforoLista(Semaphore semaforoLista) { this.semaforoLista = semaforoLista; }
}
