package br.com.easyShop.telas.edicao;

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
import javax.swing.border.EmptyBorder;

import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Marca;
import br.com.easyShop.model.Produto;
import br.com.easyShop.service.CategoriaService;
import br.com.easyShop.service.MarcaService;
import br.com.easyShop.service.ProdutoService;
import br.com.easyShop.utils.Constantes;

public class EditarProdutos extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel ctpCadastroProduto;
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
	private List<Produto> produtos = new ArrayList<Produto>();
	private JComboBox cboProduto = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarProdutos frame = new EditarProdutos();
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
	public EditarProdutos() {
		btnCarregarImagem.setIcon(new ImageIcon(EditarProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Picture.png")));
		btnCarregarImagem.addActionListener(new Abrir());
		btnCancelar.addActionListener(new Cancelar());
		cboProduto.addActionListener(new PreencherCampos());

		setTitle("Editar Produto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 813, 519);
		ctpCadastroProduto = new JPanel();
		ctpCadastroProduto.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ctpCadastroProduto);
		ctpCadastroProduto.setLayout(null);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategoria.setBounds(33, 93, 107, 26);
		ctpCadastroProduto.add(lblCategoria);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescrio.setBounds(33, 338, 74, 22);
		ctpCadastroProduto.add(lblDescrio);

	    cboSubcategoria = new JComboBox();
		cboSubcategoria.setBounds(124, 93, 178, 26);
		ctpCadastroProduto.add(cboSubcategoria);

		JLabel lblNomeDoProduto = new JLabel("Produto");
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeDoProduto.setBounds(33, 31, 68, 26);
		ctpCadastroProduto.add(lblNomeDoProduto);

		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMarca.setBounds(368, 93, 56, 24);
		ctpCadastroProduto.add(lblMarca);

		JLabel lblPreo = new JLabel("Pre\u00E7o Unit\u00E1rio");
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreo.setBounds(33, 152, 116, 25);
		ctpCadastroProduto.add(lblPreo);

		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(148, 152, 74, 26);
		ctpCadastroProduto.add(txtPreco);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuantidade.setBounds(232, 152, 96, 27);
		ctpCadastroProduto.add(lblQuantidade);

		txtQuantidade = new JTextField();
		txtQuantidade.setColumns(10);
		txtQuantidade.setBounds(327, 154, 68, 26);
		ctpCadastroProduto.add(txtQuantidade);

		JLabel lblGarantia = new JLabel("Garantia (M\u00EAs)");
		lblGarantia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGarantia.setBounds(405, 152, 124, 24);
		ctpCadastroProduto.add(lblGarantia);

		txtGarantia = new JTextField();
		txtGarantia.setColumns(10);
		txtGarantia.setBounds(523, 152, 74, 26);
		ctpCadastroProduto.add(txtGarantia);

		btnCancelar.setIcon(new ImageIcon(EditarProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Close.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(607, 408, 160, 41);
		ctpCadastroProduto.add(btnCancelar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(EditarProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Save.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalvar.setBounds(607, 295, 160, 41);
		ctpCadastroProduto.add(btnSalvar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setIcon(new ImageIcon(EditarProdutos.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Trash.png")));
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimpar.setBounds(607, 351, 160, 41);
		ctpCadastroProduto.add(btnLimpar);

		btnCarregarImagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCarregarImagem.setBounds(607, 200, 169, 41);
		ctpCadastroProduto.add(btnCarregarImagem);

		textArea = new TextArea();
		textArea.setBounds(111, 258, 456, 191);
		ctpCadastroProduto.add(textArea);

	    cboMarca = new JComboBox();
		cboMarca.setBounds(419, 95, 178, 26);
		ctpCadastroProduto.add(cboMarca);

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCdigo.setBounds(419, 31, 68, 26);
		ctpCadastroProduto.add(lblCdigo);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(481, 32, 116, 26);
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
				produto.setCodigo(txtCodigo.getText());
				produto.setDescricao(textArea.getText());
				produto.setGarantia(Integer.parseInt(txtGarantia.getText()));
				produto.setMarca(marca);
				//produto.setNome(txtNome.getText());
				produto.setPreco(Double.parseDouble(txtPreco.getText()));
				produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
				produto.setStatus(Constantes.STATUS_ATIVO);

				try{
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
						ImageIO.write(imagem_buffered, "jpg", new File("Imagens/ImagensProduto/produto"+produto.getPkProduto()+".jpg"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//*********************************************************************//
				}catch (Exception e1) {
				}

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

		lblImagem.setBorder(null);
		lblImagem.setBounds(607, 31, 169, 172);
		ctpCadastroProduto.add(lblImagem);

		cboProduto.setBounds(96, 31, 246, 26);
		ctpCadastroProduto.add(cboProduto);

		preencheComboCategoria();
		preencherComboMarca();
		preencheComboProdutos();
	}

	private class Abrir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(EditarProdutos.this);
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
			EditarProdutos.this.dispose();
		}
	}

	private class PreencherCampos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Produto produto = new Produto();
			produto = (Produto) cboProduto.getSelectedItem();

			txtCodigo.setText("" + produto.getCodigo());
			txtGarantia.setText("" + produto.getGarantia());
			txtPreco.setText("" + produto.getPreco());
			txtQuantidade.setText("" + produto.getQuantidade());
			textArea.setText(produto.getDescricao());
			cboMarca.setSelectedIndex((int) (((Long) produto.getMarca().getPkMarca())-1));
//			cboCategoria.setSelectedItem(produto.getCategoria().getPkCategoria()-1);

			int qtd = cboSubcategoria.getItemCount(), i;
			Categoria categoria = new Categoria();
			for(i=0; i<qtd; i++){
				categoria = (Categoria) cboSubcategoria.getItemAt(i);
				if(produto.getCategoria().getPkCategoria() == categoria.getPkCategoria()){
					cboSubcategoria.setSelectedIndex(i);
					break;
				}
			}

			try {
				File imagem_file = new File("Imagens/ImagensProduto/produto"+ produto.getPkProduto() + ".jpg");
				imagem_buffered = null;

				try {
					imagem_buffered = ImageIO.read(imagem_file );
				} catch (IOException e2) {
				}

				 BufferedImage aux = new BufferedImage(lblImagem.getSize().width, lblImagem.getSize().height, imagem_buffered.getType());//cria um buffer auxiliar com o tamanho desejado
				 Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao
				 AffineTransform at = AffineTransform.getScaleInstance((double) lblImagem.getSize().width / imagem_buffered.getWidth(), (double) lblImagem.getSize().height / imagem_buffered.getHeight());//cria a transformacao
				 g.drawRenderedImage(imagem_buffered, at);//pinta e transforma a imagem real no auxiliar

				 lblImagem.setIcon(new ImageIcon(aux));

		    } catch (Exception e1) {
				// TODO: handle exception
		    	lblImagem.setIcon(new ImageIcon("Imagens/Padrao/padraoProduto.png"));
			}

		}
	}

	private void preencheComboCategoria(){
		 categoriaService = new CategoriaService();
		 categorias = categoriaService.getCategorias();

		 for(Categoria categoria : categorias){
			 cboSubcategoria.addItem(categoria);
       }
	 }

	 private void preencheComboProdutos(){
		 ProdutoService produtoService = new ProdutoService();

		 produtos = produtoService.getProdutos();
		 for(Produto produto : produtos){
			 cboProduto.addItem(produto);
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
		 txtPreco.setText("");
		 txtQuantidade.setText("");
		 textArea.setText("");
	 }

	 private String nextCodigo(){
		long count;
		ProdutoService produtoService = new ProdutoService();
		count = produtoService.getCountProdutos();
		count++;
		return Long.toString(count);
	 }
}
