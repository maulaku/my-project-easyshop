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
import javax.swing.JTabbedPane;

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
	private List<Categoria> categoriasPaisSolteiros = new ArrayList<Categoria>();
	private List<Marca> marcas = new ArrayList<Marca>();
	private CategoriaService categoriaService;
	private JComboBox cboSubcategoria;
	private JComboBox cboMarca;
	private Categoria subCategoria;
	private JTextField txtCodigo;
	private Marca marca;
	private TextArea txtAreaDescricao;
	private BufferedImage imagem_buffered;
	private JLabel lblImagem = new JLabel("");
	private String caminhoImagem;
	private JButton btnCancelar = new JButton("Cancelar");
	private TextArea txtAreaCaracteristica = new TextArea();
	private TextArea txtAreaEspecificacaoTecnica = new TextArea();


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
		btnCarregarImagem.setBounds(595, 200, 169, 41);
		btnCarregarImagem.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Picture.png")));
		btnCarregarImagem.addActionListener(new Abrir());
		btnCancelar.setBounds(604, 365, 160, 41);
		btnCancelar.addActionListener(new Cancelar());

		setTitle("Cadastro de Produto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 805, 470);
		ctpCadastroProduto = new JPanel();
		ctpCadastroProduto.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ctpCadastroProduto);
		ctpCadastroProduto.setLayout(null);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(47, 84, 107, 26);
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(lblCategoria);

	    cboSubcategoria = new JComboBox();
	    cboSubcategoria.setBounds(130, 84, 186, 26);
		ctpCadastroProduto.add(cboSubcategoria);

		JLabel lblNomeDoProduto = new JLabel("Produto");
		lblNomeDoProduto.setBounds(47, 28, 68, 26);
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(lblNomeDoProduto);

		txtNome = new JTextField();
		txtNome.setBounds(110, 31, 269, 26);
		txtNome.setColumns(10);
		ctpCadastroProduto.add(txtNome);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(338, 82, 56, 24);
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(lblMarca);

		JLabel lblPreo = new JLabel("Pre\u00E7o Unit\u00E1rio");
		lblPreo.setBounds(47, 141, 116, 25);
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(lblPreo);

		txtPreco = new JTextField();
		txtPreco.setBounds(162, 141, 56, 26);
		txtPreco.setColumns(10);
		ctpCadastroProduto.add(txtPreco);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(228, 138, 96, 27);
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(lblQuantidade);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(320, 143, 56, 26);
		txtQuantidade.setColumns(10);
		ctpCadastroProduto.add(txtQuantidade);

		JLabel lblGarantia = new JLabel("Garantia (M\u00EAs)");
		lblGarantia.setBounds(386, 141, 124, 24);
		lblGarantia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(lblGarantia);

		txtGarantia = new JTextField();
		txtGarantia.setBounds(504, 141, 63, 26);
		txtGarantia.setColumns(10);
		ctpCadastroProduto.add(txtGarantia);

		btnCancelar.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Close.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(604, 252, 160, 41);
		btnSalvar.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Save.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(604, 308, 160, 41);
		btnLimpar.setIcon(new ImageIcon(CadastroDeProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Trash.png")));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(btnLimpar);

		btnCarregarImagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ctpCadastroProduto.add(btnCarregarImagem);

	    cboMarca = new JComboBox();
	    cboMarca.setBounds(389, 84, 178, 26);
		ctpCadastroProduto.add(cboMarca);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(389, 30, 68, 26);
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ctpCadastroProduto.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(451, 31, 116, 26);
		txtCodigo.setColumns(10);
		txtCodigo.setEditable(false);
		ctpCadastroProduto.add(txtCodigo);

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				marca = new Marca();
				marca = (Marca) cboMarca.getSelectedItem();

				subCategoria = new Categoria();
				subCategoria = (Categoria) cboSubcategoria.getSelectedItem();

				Produto produto = new Produto();
				produto.setCategoria(subCategoria);
				produto.setCodigo(txtCodigo.getText());
				produto.setDescricao(txtAreaDescricao.getText());
				produto.setCaracteristicas(txtAreaCaracteristica.getText());
				produto.setEspecificacoesTecnicas(txtAreaEspecificacaoTecnica.getText());
				produto.setGarantia(Integer.parseInt(txtGarantia.getText()));
				produto.setMarca(marca);
				produto.setNome(txtNome.getText());
				produto.setPreco(Double.parseDouble(txtPreco.getText()));
				produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
				produto.setStatus(Constantes.STATUS_ATIVO);

				//*********************************************************************//
				//Salvar imagem na pasta
				try {
					File imagem_file = new File(caminhoImagem);
					BufferedImage imagem_buffered = null;				
					imagem_buffered = ImageIO.read( imagem_file );					
					ImageIO.write(imagem_buffered, "jpg", new File("Imagens/ImagensProduto/produto"+nextCodigo()+".jpg"));
				}
				catch (Exception e2) {
					
				}
				//*********************************************************************//

				ProdutoService produtoService = new ProdutoService();
			    produtoService.inserirProduto(produto);

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
		lblImagem.setBounds(595, 31, 160, 172);

		lblImagem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		ctpCadastroProduto.add(lblImagem);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(47, 189, 520, 217);
		ctpCadastroProduto.add(tabbedPane);
		
				txtAreaDescricao = new TextArea();
				tabbedPane.addTab("Descri\u00E7\u00E3o", null, txtAreaDescricao, null);
				
				tabbedPane.addTab("Caracter\u00EDstica", null, txtAreaCaracteristica, null);
				
				tabbedPane.addTab("Especifica\u00E7\u00E3o T\u00E9cnica", null, txtAreaEspecificacaoTecnica, null);

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
					ImageIO.write(imagem_buffered, "jpg", new File("Imagens/ImagensProduto/CadastroDeProduto.jpg"));
					lblImagem.setIcon(new ImageIcon("Imagens/ImagensProduto/CadastroDeProduto.jpg"));
					caminhoImagem = fc.getSelectedFile().toString();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Arquivo selecionado n�o � uma imagem!");
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
		 categoriasPaisSolteiros = categoriaService.getPaisSolteiros();

		 for(Categoria categoria : categoriasPaisSolteiros){
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
		 txtAreaDescricao.setText("");
	 }

	 private String nextCodigo(){
		long count;
		ProdutoService produtoService = new ProdutoService();
		count = produtoService.getCountProdutos();
		count++;
		return Long.toString(count);
	 }
}
