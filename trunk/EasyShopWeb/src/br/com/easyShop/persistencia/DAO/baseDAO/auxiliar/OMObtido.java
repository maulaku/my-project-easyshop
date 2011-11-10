package br.com.easyShop.persistencia.DAO.baseDAO.auxiliar;

public class OMObtido
{
	private Object om;
	private int profundidade=-2;
	

	public OMObtido(Object om, int profundidade)
	{
		this.om = om;
		this.profundidade = profundidade;
	}
	
	public Object getOm() { return om; }
	public void setOm(Object om) { this.om = om; }
	
	public int getProfundidade() { return profundidade; }
	public void setProfundidade(int profundidade) { this.profundidade = profundidade; }
}
