package br.com.easyShop.telas.cadastros;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Marca;
import br.com.easyShop.model.Produto;
import br.com.easyShop.service.CategoriaService;
import br.com.easyShop.service.MarcaService;
import br.com.easyShop.service.ProdutoService;
import br.com.easyShop.utils.Constantes;

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
	private JComboBox cboSubcategoria;
	private JComboBox cboMarca;
	private Categoria categoria;
	private Categoria subCategoria;
	private JTextField txtCodigo;
	private Marca marca;
	private TextArea textArea;
	private BufferedImage imagem_buffered;
	private JLabel lblImagem = new JLabel("");
	private String caminhoImagem;
	private JButton btnCancelar = new JButton("Cancelar");

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
		btnCarregarImagem.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/Picture.png")));
		btnCarregarImagem.addActionListener(new Abrir());
		btnCancelar.addActionListener(new Cancelar());

		setTitle("Cadastro de Produto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 805, 470);
		ctpCadastroProduto = new JPanel();
		ctpCadastroProduto.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ctpCadastroProduto);
		ctpCadastroProduto.setLayout(null);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategoria.setBounds(47, 84, 107, 26);
		ctpCadastroProduto.add(lblCategoria);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescrio.setBounds(35, 285, 74, 22);
		ctpCadastroProduto.add(lblDescrio);

	    cboSubcategoria = new JComboBox();
		cboSubcategoria.setBounds(130, 84, 186, 26);
		ctpCadastroProduto.add(cboSubcategoria);

		JLabel lblNomeDoProduto = new JLabel("Produto");
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDoProduto.setBounds(47, 28, 68, 26);
		ctpCadastroProduto.add(lblNomeDoProduto);

		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(110, 31, 269, 26);
		ctpCadastroProduto.add(txtNome);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMarca.setBounds(338, 82, 56, 24);
		ctpCadastroProduto.add(lblMarca);

		JLabel lblPreo = new JLabel("Pre\u00E7o Unit\u00E1rio");
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreo.setBounds(47, 141, 116, 25);
		ctpCadastroProduto.add(lblPreo);

		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(162, 141, 56, 26);
		ctpCadastroProduto.add(txtPreco);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantidade.setBounds(228, 138, 96, 27);
		ctpCadastroProduto.add(lblQuantidade);

		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(320, 143, 56, 26);
		ctpCadastroProduto.add(txtQuantidade);

		JLabel lblGarantia = new JLabel("Garantia (M\u00EAs)");
		lblGarantia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGarantia.setBounds(386, 141, 124, 24);
		ctpCadastroProduto.add(lblGarantia);

		txtGarantia = new JTextField();
		txtGarantia.setColumns(10);
		txtGarantia.setBounds(504, 141, 63, 26);
		ctpCadastroProduto.add(txtGarantia);

		btnCancelar.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/Close.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(604, 365, 160, 41);
		ctpCadastroProduto.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/Save.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalvar.setBounds(604, 252, 160, 41);
		ctpCadastroProduto.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/Trash.png")));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimpar.setBounds(604, 308, 160, 41);
		ctpCadastroProduto.add(btnLimpar);

		btnCarregarImagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCarregarImagem.setBounds(595, 200, 169, 41);
		ctpCadastroProduto.add(btnCarregarImagem);

		textArea = new TextArea();
		textArea.setBounds(113, 200, 456, 206);
		ctpCadastroProduto.add(textArea);

	    cboMarca = new JComboBox();
		cboMarca.setBounds(389, 84, 178, 26);
		ctpCadastroProduto.add(cboMarca);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCdigo.setBounds(389, 30, 68, 26);
		ctpCadastroProduto.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(451, 31, 116, 26);
		txtCodigo.setEditable(false);
		ctpCadastroProduto.add(txtCodigo);

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				marca = new Marca();
				marca = (Marca) cboMarca.getSelectedItem();

				subCategoria = new Categoria();
				subCategoria = (Categoria) cboSubcategoria.getSelectedItem();

//				if(subCategoria == null){
//					subCategoria = (Categoria) cboCategoria.getSelectedItem();
//				}

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

				//*********************************************************************//
				//Salvar imagem na pasta
				File imagem_file = new File(caminhoImagem);
				BufferedImage imagem_buffered = null;
				try {
					imagem_buffered = ImageIO.read( imagem_file );
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				try {
					ImageIO.write(imagem_buffered, "jpg", new File("bin/br/com/easyShop/telas/imagens/produto"+nextCodigo()+".jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				//*********************************************************************//
				
				ProdutoService produtoService = new ProdutoService(produto);
			    produtoService.inserirProduto();
			    
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
		
		lblImagem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblImagem.setBounds(595, 31, 160, 172);
		ctpCadastroProduto.add(lblImagem);
		
		preencheComboCategoria();
		preencherComboMarca();
	}

	private class Abrir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(CadastroDeProdutos.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				fc.getSelectedFile().toString();
				
				//*********************************************************************//
				//Salvar imagem na pasta
				
				try {
				    imagem_buffered = null;
					File imagem_file = new File(fc.getSelectedFile().toString());
					imagem_buffered = ImageIO.read( imagem_file );
					ImageIO.write(imagem_buffered, "jpg", new File("bin/br/com/easyShop/telas/imagens/CadastroDeProduto.jpg"));
					lblImagem.setIcon(new ImageIcon(getClass().getResource("/br/com/easyShop/telas/imagens/CadastroDeProduto.jpg")));
					caminhoImagem = fc.getSelectedFile().toString();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Arquivo selecionado não é uma imagem!");
				}
				//*********************************************************************//
				
				 BufferedImage aux = new BufferedImage(lblImagem.getSize().width, lblImagem.getSize().height, imagem_buffered.getType());//cria um buffer auxiliar com o tamanho desejado
				 Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao
				 AffineTransform at = AffineTransform.getScaleInstance((double) lblImagem.getSize().width / imagem_buffered.getWidth(), (double) lblImagem.getSize().height / imagem_buffered.getHeight());//cria a transformacao
				 g.drawRenderedImage(imagem_buffered, at);//pinta e transforma a imagem real no auxiliar
				
				 lblImagem.setIcon(new ImageIcon(aux));
		}
	}
	}
	
	private class Cancelar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CadastroDeProdutos.this.dispose();
		}
	}

	 private void preencheComboCategoria(){
		 categoriaService = new CategoriaService();
		 categorias = categoriaService.getCategorias();

		 for(Categoria categoria : categorias){
			 cboSubcategoria.addItem(categoria);
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
