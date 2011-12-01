package br.com.easyShop.aplicacao;

import java.awt.EventQueue;

import javax.swing.UIManager;

import flex.messaging.io.PropertyProxyRegistry;

import br.com.easyShop.comunicacao.FlexProxy;
import br.com.easyShop.comunicacao.JavaFlexRO;
import br.com.easyShop.configuracoes.Configuracoes;
import br.com.easyShop.persistencia.conexao.BancoDeDados;
import br.com.easyShop.telas.Login;

public class MainEasyShopDesktop {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		PropertyProxyRegistry.getRegistry().register(Object.class, new FlexProxy());

		JavaFlexRO.prePackages.add("br.com.easyShop.service");

		Configuracoes.carregar(Configuracoes.class.getResourceAsStream("configuracoes.properties"), Configuracoes.class.getResourceAsStream("log4j.properties"));

		System.out.println("Iniciando DB...");
		BancoDeDados.conectar();
		System.out.println("SISTEMA EASYSHOP INICIALIZADO COM SUCESSO");
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {			
						Login login = new Login(); 
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
						login.setSize(800,600); 		
						login.setLocationRelativeTo(null);  
						login.setVisible(true);
						login.setLocationRelativeTo(null);	//Coloca o Frame no meio da tela				
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
}
