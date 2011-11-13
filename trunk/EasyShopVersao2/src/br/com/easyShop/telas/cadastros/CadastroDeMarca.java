package br.com.easyShop.telas.cadastros;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import br.com.easyShop.model.Marca;
import br.com.easyShop.service.MarcaService;
import br.com.easyShop.utils.Constantes;
import javax.swing.ImageIcon;

public class CadastroDeMarca extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMarca;
	private JButton btnLimpar = new JButton("Limpar");
	private JButton btnCancelar = new JButton("Cancelar");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeMarca frame = new CadastroDeMarca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroDeMarca() {
		
		btnCancelar.addActionListener(new Sair());
		
		setTitle("Cadastro de Marca");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 563, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDaMarca = new JLabel("Nome da Marca");
		lblNomeDaMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDaMarca.setBounds(24, 30, 152, 22);
		contentPane.add(lblNomeDaMarca);

		txtMarca = new JTextField();
		txtMarca.setColumns(10);
		txtMarca.setBounds(157, 27, 364, 26);
		contentPane.add(txtMarca);

		JButton button = new JButton("Inserir");
		button.setIcon(new ImageIcon(CadastroDeMarca.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Save.png")));
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(24, 87, 160, 31);
		contentPane.add(button);

		btnLimpar.setIcon(new ImageIcon(CadastroDeMarca.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Trash.png")));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimpar.setBounds(191, 87, 160, 31);
		contentPane.add(btnLimpar);

		btnCancelar.setIcon(new ImageIcon(CadastroDeMarca.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Close.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(361, 87, 160, 31);
		contentPane.add(btnCancelar);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtMarca.getText().equals("")){
					txtMarca.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
					JOptionPane.showMessageDialog(null, "Digite o nome da Marca!!");
				}
				else{
					JTextField jTextField = new JTextField();
					txtMarca.setBorder(jTextField.getBorder());
					
					Marca marca = new Marca();
	                 marca.setNome(txtMarca.getText());
	                 marca.setStatus(Constantes.STATUS_ATIVO);

	                 MarcaService marcaServise = new MarcaService();
	                 marcaServise.inserir(marca);
	                 
	                 JOptionPane.showMessageDialog(null, "Marca inserida com sucesso!!");
	                 txtMarca.setText("");
				}                 
			}
		});
		
		 btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMarca.setText("");
			}
		});
		
	}
	
	private class Sair implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CadastroDeMarca.this.dispose();
		}
	}

}
