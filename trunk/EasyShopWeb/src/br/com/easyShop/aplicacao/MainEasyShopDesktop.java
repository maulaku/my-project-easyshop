package br.com.easyShop.aplicacao;

import java.awt.EventQueue;

import javax.swing.UIManager;

import br.com.easyShop.telas.Login;

public class MainEasyShopDesktop {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {						
						Login login = new Login(); 
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
						login.setSize(800,600); 	
//						Dimension d = Toolkit.getDefaultToolkit().getScreenSize();				
//						principal.setSize(d);  
//						principal.setResizable(false);  
//						principal.setAlwaysOnTop(true);  
//						principal.setVisible(true);
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
