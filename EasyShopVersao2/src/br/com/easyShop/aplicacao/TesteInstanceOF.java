package br.com.easyShop.aplicacao;

public class TesteInstanceOF
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int inteiro = 22;
		long testeLong = inteiro;
		
		if ((Long)testeLong instanceof Long) 
		{
			System.out.println("true");
		}
		else 
		{
			System.out.println("false");
		}
	}

}
