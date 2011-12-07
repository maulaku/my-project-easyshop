package br.com.easyShop.relatorios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class gConexao {
	private static Connection con;
	public static Connection getConexao() throws ExcRepositorio {
		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/easy";
		String login = "postgres";
		String senha = "postgres";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, login, senha);
		} catch (ClassNotFoundException e) {
			throw new ExcRepositorio("Driver não encontrado: " + e.getMessage());
		} catch (SQLException e) {
			throw new ExcRepositorio("Erro abrindo conexão: " + e.getMessage());
		}
		return con;
	}
}