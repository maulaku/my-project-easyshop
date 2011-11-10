package br.com.easyShop.persistencia.conexao.pool;

import utils.data.Data;

public class PoolJDBCItem
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private PoolJDBC poolJDBC=null;
	private boolean livre = true;
	private Data reserva = new Data();

	
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Define a conexao como livre e avisa o pool de conexoes para liberar um acesso a conexoes
	 * @param livre
	 */
	public void setLivre(boolean livre) 
	{ 
		if(this.livre==false && livre && poolJDBC!=null) { poolJDBC.liberarConexao(); }
		this.livre = livre; 
	}
	
	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public boolean isLivre() { return livre; }
	
	public Data getReserva() { return reserva; }
	public void setReserva(Data reserva) { this.reserva = reserva; }
	
	public PoolJDBC getPoolJDBC() { return poolJDBC; }
	public void setPoolJDBC(PoolJDBC poolJDBC) { this.poolJDBC = poolJDBC; }
}
