package br.com.easyShop.telas.cadastros;

import java.awt.EventQueue;

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

public class CadastroDeMarca extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

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

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(157, 27, 364, 26);
		contentPane.add(textField);

		JButton button = new JButton("Inserir");
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(24, 87, 160, 31);
		contentPane.add(button);

		JButton button_1 = new JButton("Limpar");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(191, 87, 160, 31);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Cancelar");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBounds(361, 87, 160, 31);
		contentPane.add(button_2);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                 Marca marca = new Marca();
                 marca.setNome(textField.getText());
                 marca.setStatus(Constantes.STATUS_ATIVO);

                 MarcaService marcaServise = new MarcaService();
                 marcaServise.inserir(marca);
                 
                 JOptionPane.showMessageDialog(null, "Marca inserida com sucesso!!");
                 textField.setText("");
			}
		});
		
		 button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		
	}

}
