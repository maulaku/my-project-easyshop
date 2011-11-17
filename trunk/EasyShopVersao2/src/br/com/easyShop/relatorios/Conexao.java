package br.com.easyShop.relatorios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Connection con;
	public static Connection getConexao() throws ExcRepositorio {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/easy";
		String login = "root";
		String senha = "root";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, login, senha);
		} catch (ClassNotFoundException e) {
			throw new ExcRepositorio("Driver n�o encontrado: " + e.getMessage());
		} catch (SQLException e) {
			throw new ExcRepositorio("Erro abrindo conex�o: " + e.getMessage());
		}
		return con;
	}
}