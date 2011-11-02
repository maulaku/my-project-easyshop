package br.com.easyShop.telas.cadastros;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Marca;
import br.com.easyShop.model.Produto;
import br.com.easyShop.relatorios.ExcRepositorio;
import br.com.easyShop.relatorios.repositorioProduto;
import br.com.easyShop.service.CategoriaService;
import br.com.easyShop.service.MarcaService;
import br.com.easyShop.service.ProdutoService;
import br.com.easyShop.utils.Constantes;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

public class CadastroDeProdutos extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel ctpCadastroProduto;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTextField txtQuantidade;
	private JTextField txtGarantia;
	private static JButton btnCarregarImagem = new JButton("Carregar Imagem");
	private List<Categoria> categorias = new ArrayList<Categoria>();
	private List<Marca> marcas = new ArrayList<Marca>();
	private CategoriaService categoriaService;
	private JComboBox cboCategoria;
	private JComboBox cboSubcategoria;
	private JComboBox cboMarca;
	private Categoria categoria;
	private Categoria subCategoria;
	private JTextField txtCodigo;
	private Marca marca;
	private TextArea textArea;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroDeProdutos frame = new CadastroDeProdutos();
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
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
	public CadastroDeProdutos() {
		btnCarregarImagem.addActionListener(new Abrir());

		setTitle("Cadastro de Produto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 805, 519);
		ctpCadastroProduto = new JPanel();
		ctpCadastroProduto.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ctpCadastroProduto);
		ctpCadastroProduto.setLayout(null);

		JLabel label_1 = new JLabel("Categoria");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(33, 84, 152, 22);
		ctpCadastroProduto.add(label_1);

		JLabel label_2 = new JLabel("Subcategoria");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(298, 84, 152, 22);
		ctpCadastroProduto.add(label_2);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescrio.setBounds(33, 258, 74, 22);
		ctpCadastroProduto.add(lblDescrio);

	    cboCategoria = new JComboBox();
		cboCategoria.setBounds(119, 87, 169, 23);
		ctpCadastroProduto.add(cboCategoria);

	    cboSubcategoria = new JComboBox();
		cboSubcategoria.setBounds(404, 87, 163, 23);
		ctpCadastroProduto.add(cboSubcategoria);

		JLabel lblNomeDoProduto = new JLabel("Produto");
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDoProduto.setBounds(33, 34, 68, 14);
		ctpCadastroProduto.add(lblNomeDoProduto);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(111, 31, 256, 26);
		ctpCadastroProduto.add(txtNome);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMarca.setBounds(33, 146, 56, 14);
		ctpCadastroProduto.add(lblMarca);

		JLabel lblPreo = new JLabel("Pre\u00E7o Unit\u00E1rio");
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreo.setBounds(273, 200, 152, 23);
		ctpCadastroProduto.add(lblPreo);

		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(389, 201, 178, 26);
		ctpCadastroProduto.add(txtPreco);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantidade.setBounds(34, 200, 152, 23);
		ctpCadastroProduto.add(lblQuantidade);

		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(128, 201, 135, 26);
		ctpCadastroProduto.add(txtQuantidade);

		JLabel lblGarantia = new JLabel("Garantia (M\u00EAs)");
		lblGarantia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGarantia.setBounds(328, 142, 152, 23);
		ctpCadastroProduto.add(lblGarantia);

		txtGarantia = new JTextField();
		txtGarantia.setColumns(10);
		txtGarantia.setBounds(448, 143, 116, 26);
		ctpCadastroProduto.add(txtGarantia);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(595, 418, 160, 31);
		ctpCadastroProduto.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalvar.setBounds(595, 293, 160, 31);
		ctpCadastroProduto.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimpar.setBounds(595, 358, 160, 31);
		ctpCadastroProduto.add(btnLimpar);

		btnCarregarImagem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCarregarImagem.setBounds(595, 47, 160, 168);
		ctpCadastroProduto.add(btnCarregarImagem);

		textArea = new TextArea();
		textArea.setBounds(111, 258, 456, 191);
		ctpCadastroProduto.add(textArea);

	    cboMarca = new JComboBox();
		cboMarca.setBounds(94, 142, 169, 23);
		ctpCadastroProduto.add(cboMarca);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCdigo.setBounds(389, 34, 68, 20);
		ctpCadastroProduto.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(451, 31, 116, 26);
		txtCodigo.setEditable(false);
		ctpCadastroProduto.add(txtCodigo);

		cboCategoria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				categoria = (Categoria) cboCategoria.getSelectedItem();

				CategoriaService categoriaService = new CategoriaService();
				//List<Categoria> subCategorias = categoriaService.getSubCategorias(categoria);
				List<Categoria> subCategorias = categoriaService.getCategorias();

				cboSubcategoria.removeAllItems();

                for(Categoria subCategoria : subCategorias){
                	cboSubcategoria.addItem(subCategoria);
                }
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				marca = new Marca();
				marca = (Marca) cboMarca.getSelectedItem();

				subCategoria = new Categoria();
				subCategoria = (Categoria) cboSubcategoria.getSelectedItem();

				if(subCategoria == null){
					subCategoria = (Categoria) cboCategoria.getSelectedItem();
				}

				Produto produto = new Produto();
				produto.setCategoria(subCategoria);
				produto.setCodigo(Integer.parseInt(txtCodigo.getText()));
				produto.setDescricao(textArea.getText());
				produto.setGarantia(Integer.parseInt(txtGarantia.getText()));
				produto.setMarca(marca);
				produto.setNome(txtNome.getText());
				produto.setPreco(Double.parseDouble(txtPreco.getText()));
				produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
				produto.setStatus(Constantes.STATUS_ATIVO);

				JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!!");
				
				clean();
			}
		});

		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clean();
			}
		});

		txtCodigo.setText(nextCodigo());
		preencheComboCategoria();
		preencherComboMarca();
	}

	private class Abrir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(CadastroDeProdutos.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.print(fc.getSelectedFile());
			}
		}
	}

	 private void preencheComboCategoria(){
		 categoriaService = new CategoriaService();
		 categorias = categoriaService.getAllCategorias();

		 for(Categoria categoria : categorias){
			 cboCategoria.addItem(categoria);
         }
	 }

	 private void preencherComboMarca(){
		 MarcaService marcaService = new MarcaService();
		 marcas = marcaService.getMarcas();

		 for(Marca marca : marcas){
			 cboMarca.addItem(marca);
		 }
	 }

	 private void clean(){
		 txtCodigo.setText("");
		 txtGarantia.setText("");
		 txtNome.setText("");
		 txtPreco.setText("");
		 txtQuantidade.setText("");
		 textArea.setText("");
	 }

	 private String nextCodigo(){
		int count;
		ProdutoService produtoService = new ProdutoService();
		count = produtoService.getCountProdutos();
		count++;
		return Integer.toString(count);
	 }
}
