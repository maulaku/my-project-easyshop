package br.com.easyShop.aplicacao;

public class TesteInstanceOF
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		long testeLong = 22;
		
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
