package br.com.easyShop.telas.consultas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PesquisarProduto extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisarProduto frame = new PesquisarProduto();
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
	public PesquisarProduto() {
		setTitle("Pesquisar Produto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 712, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_3 = new JLabel("Nome do Produto");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(35, 27, 152, 26);
		contentPane.add(label_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(35, 80, 624, 567);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Categoria");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(24, 31, 152, 22);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Subcategoria");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(24, 88, 152, 22);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Descri\u00E7\u00E3o");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(24, 328, 152, 22);
		panel.add(label_2);
		
		JLabel label_4 = new JLabel("Marca");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(24, 149, 152, 14);
		panel.add(label_4);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(81, 146, 334, 26);
		panel.add(textField);
		
		JLabel label_5 = new JLabel("Pre\u00E7o Unit\u00E1rio");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(263, 265, 152, 23);
		panel.add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(379, 266, 178, 26);
		panel.add(textField_1);
		
		JLabel label_6 = new JLabel("Quantidade");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(24, 265, 152, 23);
		panel.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(118, 266, 135, 26);
		panel.add(textField_2);
		
		JLabel label_7 = new JLabel("Garantia (M\u00EAs)");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_7.setBounds(24, 207, 152, 23);
		panel.add(label_7);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(144, 208, 122, 26);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(119, 31, 473, 26);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(141, 88, 274, 26);
		panel.add(textField_5);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(108, 328, 446, 210);
		panel.add(textPane);
		textPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textPane.setBackground(Color.WHITE);
		
		JLabel lblNewLabel = new JLabel("Foto");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setBounds(432, 73, 160, 172);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(180, 27, 231, 26);
		contentPane.add(comboBox);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFiltrar.setBounds(421, 25, 114, 31);
		contentPane.add(btnFiltrar);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(545, 25, 114, 31);
		contentPane.add(button_1);
	}
}
