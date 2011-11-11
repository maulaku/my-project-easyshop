package br.com.easyShop.relatorios;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class principal {
	public static void main(String[] args) throws JRException {
		repositorioProduto rep = new repositorioProduto();
		JasperPrint relat;

		try {
			//rep.inserir(prod);
			relat = rep.gerar("easyShopRelatorioDeProduto.jasper");
			JasperViewer.viewReport(relat, false);
		} catch (ExcRepositorio e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
		}	
	}
}