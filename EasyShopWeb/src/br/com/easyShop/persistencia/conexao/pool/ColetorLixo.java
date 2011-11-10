package br.com.easyShop.persistencia.conexao.pool;

import utils.data.Data;
import logs.Logs;

public class ColetorLixo extends Thread
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private volatile boolean execucao = true;
	private PoolJDBC poolJDBC=null;
	private int tempoColetor = 10000;
	private int conexaoTimeOut = 10000;
	
	
	/*-*-*-* Construtores *-*-*-*/
	/**
	 * Construtor da classe
	 * @param poolJDBC de conexoes onde o coletor de lixo ira atuar
	 * @param tempoColetor tempo de cada iteracao do coletor (ms)
	 * @param conexaoTimeOut tempo que a conexao e considera inutil (esteja ou nao sendo utilizada) (ms)
	 */
	public ColetorLixo(PoolJDBC poolJDBC, int tempoColetor, int conexaoTimeOut)
	{
		this.poolJDBC = poolJDBC;
		this.tempoColetor = tempoColetor;
		this.conexaoTimeOut = conexaoTimeOut;
	}

	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Metodo paralelo da thread, de implementacao obrigatoria para implementar o Runnable
	 * Este e chamado para ser executado quando a thread tem sua execução disparado pelo metodo "start()"
	 */
	public void run()
	{
		while(execucao)
		{
			try 
			{ 
				poolJDBC.getSemaforoLista().acquire(1);
				
				Data data = new Data();
				boolean restart = true;
				while(restart)
				{
					restart = false;
					for(int i=0; i<poolJDBC.getConexoes().size(); i++)
					{
						if((data.getTimeInMillis()-poolJDBC.getConexoes().get(i).getReserva().getTimeInMillis())>=conexaoTimeOut)
						{
							poolJDBC.getConexoes().get(i).desconectar(true);
							if(poolJDBC.getConexoes().size()>poolJDBC.getPoolMinimo())
							{
								poolJDBC.getConexoes().remove(poolJDBC.getConexoes().get(i));
								restart = true;
								break;
							}
						}
					}
				}
				
				poolJDBC.getSemaforoLista().release(1);
				synchronized(this) { wait(tempoColetor); }
			} 
			catch (Exception e) 
			{
				poolJDBC.getSemaforoLista().release(1);
				Logs.addError("Durante o coletor de lixo de conexoes", e);
			}
		}
	}
	
	/**
	 * Metodo usado para requisitar que a thread seja executada, as requisições de execução são acumulativas,
	 * ou seja, se este método for chamado 2 vezes a thread irá iterar 2 vezes
	 */
	public void requestExecute()
	{
		synchronized(this) { notify(); }
	}

	/**
	 * Metodo usado para parar a execucao da thread, mas ela irá parar apenas na proxima iteração, saindo do loop de iterações
	 */
	public void requestStop()
	{
		execucao = false;
		requestExecute();
	}
}
