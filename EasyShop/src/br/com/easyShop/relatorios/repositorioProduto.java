package br.com.easyShop.relatorios;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class repositorioProduto{
	
	public repositorioProduto() {}
	

	public JasperPrint gerar(String arquivoJasper) throws ExcRepositorio{
		JasperPrint rel = null;
		try {
			Connection con = Conexao.getConexao();
			HashMap map = new HashMap();
			rel = JasperFillManager.fillReport(arquivoJasper, map, con);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return rel;
	}
}