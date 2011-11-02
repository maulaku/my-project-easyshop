package br.com.easyShop.telas.cadastros;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import br.com.easyShop.model.Categoria;
import br.com.easyShop.service.CategoriaService;

import javax.swing.JRadioButton;

public class CadastroDeCategoria extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private List<Categoria> categorias = new ArrayList<Categoria>();
	private CategoriaService categoriaService;
	private final JComboBox cboCategoriaPai;
	private final JRadioButton rdPai = new JRadioButton("Categoria pai");
	private final JRadioButton rdSub = new JRadioButton("SubCategoria");
	private JLabel lblCategoriaPai;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeCategoria frame = new CadastroDeCategoria();
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
	public CadastroDeCategoria() {
		setTitle("Cadastro de Categoria");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 534, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDaCategoria = new JLabel("Categoria:");
		lblNomeDaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDaCategoria.setBounds(40, 14, 85, 22);
		contentPane.add(lblNomeDaCategoria);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(135, 11, 339, 26);
		contentPane.add(txtNome);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnInserir.setBounds(10, 166, 160, 31);
		contentPane.add(btnInserir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimpar.setBounds(177, 166, 160, 31);
		contentPane.add(btnLimpar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(347, 166, 160, 31);
		contentPane.add(btnCancelar);

	    lblCategoriaPai = new JLabel("Categoria Pai");
		lblCategoriaPai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategoriaPai.setBounds(157, 94, 109, 22);
		contentPane.add(lblCategoriaPai);

	    cboCategoriaPai = new JComboBox();
		cboCategoriaPai.setBounds(157, 115, 228, 26);
		contentPane.add(cboCategoriaPai);
		rdPai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdPai.setBounds(322, 49, 129, 31);
		contentPane.add(rdPai);

		rdSub.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdSub.setBounds(135, 53, 139, 23);
		contentPane.add(rdSub);

		btnInserir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Categoria categoriaPai = new Categoria();
				CategoriaService categoriaService = new CategoriaService();
				
				if(rdSub.isSelected() == true){
					 categoriaPai = (Categoria) cboCategoriaPai.getSelectedItem();
				}
				else{
					categoriaPai = null;
				}

                Categoria categoria = new Categoria();
                categoria.setNome(txtNome.getText());
                categoria.setSubCategoria(categoriaPai);
                
                categoriaService.inserirCategoria(categoria);
                JOptionPane.showMessageDialog(null, "Categoria inserido com sucesso!!");
                clean();
			}
		});
		
		rdSub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				trueVisibleSubCategoria();
				rdPai.setSelected(false);
			}
		});
		
		rdPai.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				falseVisibleSubCategoria();
				rdSub.setSelected(false);
			}
		});
		
		rdPai.setSelected(true);
		rdSub.setSelected(false);
		falseVisibleSubCategoria();
	    preencheCombo();
	}
	
	 private void preencheCombo(){
		 categoriaService = new CategoriaService();
		 categorias = categoriaService.getAllCategorias();

		 for(Categoria categoria : categorias){
			 cboCategoriaPai.addItem(categoria);
         }
	 }
	 
	 private void clean(){
		 cboCategoriaPai.removeAllItems();
		 preencheCombo();
		 txtNome.setText("");
	 }
	 
	 private void falseVisibleSubCategoria(){
		 cboCategoriaPai.setVisible(false);
		 lblCategoriaPai.setVisible(false);
	 }
	 
	 private void trueVisibleSubCategoria(){
		 cboCategoriaPai.setVisible(true);
		 lblCategoriaPai.setVisible(true);
	 }
}
