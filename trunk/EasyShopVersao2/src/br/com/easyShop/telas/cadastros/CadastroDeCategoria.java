package br.com.easyShop.telas.cadastros;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.easyShop.model.Categoria;
import br.com.easyShop.model.Usuario;
import br.com.easyShop.model.UsuarioTela;
import br.com.easyShop.service.CategoriaService;
import br.com.easyShop.service.UsuarioTelaService;
import br.com.easyShop.utils.Constantes;

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
	private JButton btnCarregar = new JButton("Carregar Imagem");
	private JLabel lblImagem = new JLabel("");
	private BufferedImage imagem_buffered;
	private String caminhoImagem;
	private JButton btnCancelar = new JButton("Cancelar");
	private JButton btnLimpar = new JButton("Limpar");
	private Usuario usuarioPrincipal;
	
	public CadastroDeCategoria(Usuario usuario) {
		usuarioPrincipal = usuario;
		
		btnCarregar.addActionListener(new Abrir());
		btnCancelar.addActionListener(new Cancelar());
		btnLimpar.addActionListener(new Apagar());
		
		setTitle("Cadastro de Categoria");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 770, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDaCategoria = new JLabel("Categoria:");
		lblNomeDaCategoria.setBounds(36, 46, 85, 27);
		lblNomeDaCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNomeDaCategoria);

		txtNome = new JTextField();
		txtNome.setBounds(120, 49, 350, 26);
		txtNome.setColumns(10);
		contentPane.add(txtNome);

		JButton btnInserir = new JButton("Inserir");
		btnInserir.setIcon(new ImageIcon(CadastroDeCategoria.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Save.png")));
		btnInserir.setBounds(10, 264, 160, 31);
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnInserir);

		btnLimpar.setIcon(new ImageIcon(CadastroDeCategoria.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Trash.png")));
		btnLimpar.setBounds(181, 264, 160, 31);
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnLimpar);

		btnCancelar.setIcon(new ImageIcon(CadastroDeCategoria.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Close.png")));
		btnCancelar.setBounds(351, 264, 160, 31);
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnCancelar);

	    lblCategoriaPai = new JLabel("Categoria Pai");
	    lblCategoriaPai.setBounds(190, 157, 109, 22);
		lblCategoriaPai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblCategoriaPai);

	    cboCategoriaPai = new JComboBox();
	    cboCategoriaPai.setBounds(141, 178, 228, 26);
		contentPane.add(cboCategoriaPai);
		rdPai.setBounds(282, 119, 129, 31);
		rdPai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(rdPai);
		rdSub.setBounds(95, 123, 139, 23);

		rdSub.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(rdSub);

		btnInserir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Long fkTela = Long.parseLong("3");
				Long fkTipoPermissao = Long.parseLong("2");
				UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuarioPrincipal,fkTela ,fkTipoPermissao);
				
				if(usuarioTela!=null || abrirLancamentoDePermissaoADM()==false){
					if(txtNome.getText().equals("")){
						txtNome.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
						JOptionPane.showMessageDialog(null, "Digite o nome da Categoria!!");
					}
					else{
						JTextField jTextField = new JTextField();
						txtNome.setBorder(jTextField.getBorder());
						
						CategoriaService categoriaService = new CategoriaService();
						
						Categoria categoria = new Categoria();
						categoria.setNome(txtNome.getText());
						
						
						if(rdSub.isSelected() == true){
							categoria.setSubCategoria((Categoria) cboCategoriaPai.getSelectedItem());
							
						}
						else{
							categoria.setTipo(Constantes.CATEGORIA_PAI);
						}
		
		                
		                categoriaService.inserirCategoria(categoria);
		                
		                
		                //*********************************************************************//
						//Salvar imagem na pasta
						try{
							File imagem_file = new File(caminhoImagem);
							BufferedImage imagem_buffered = null;
							imagem_buffered = ImageIO.read( imagem_file );
							ImageIO.write(imagem_buffered, "jpg", new File(Constantes.ENDERECO_CATEGORIA+categoria.getPkCategoria()+".jpg"));
						}catch (Exception e1) {
							
						}
						//*********************************************************************//
		                
		                JOptionPane.showMessageDialog(null, "Categoria inserido com sucesso!!");
		                clean();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Escrita");
				}		
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
		
		btnCarregar.setIcon(new ImageIcon(CadastroDeCategoria.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Picture.png")));
		btnCarregar.setBounds(540, 223, 177, 41);
		btnCarregar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnCarregar);
		
		lblImagem.setBounds(547, 47, 160, 180);
		contentPane.add(lblImagem);
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
	 
	 private class Abrir implements ActionListener {
			public void actionPerformed(ActionEvent e) {		
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(CadastroDeCategoria.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fc.getSelectedFile().toString();
					
					//*********************************************************************//
					//Salvar imagem na pasta
					
					try {
					    imagem_buffered = null;
						File imagem_file = new File(fc.getSelectedFile().toString());
						imagem_buffered = ImageIO.read( imagem_file );
						ImageIO.write(imagem_buffered, "jpg", new File("Imagens/ImagensCategoria/CadastroDeCategoria.jpg"));
						lblImagem.setIcon(new ImageIcon(("Imagens/ImagensCategoria/CadastroDeCategoria.jpg")));
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
				CadastroDeCategoria.this.dispose();
			}
		}
	 
	 private class Apagar implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				cboCategoriaPai.removeAllItems();
				 preencheCombo();
				 txtNome.setText("");
			}
		}
	 
	 private boolean abrirLancamentoDePermissaoADM(){
			if(usuarioPrincipal.getLogin().equals("adm")){			
				return false;
			}
			return true;
		}
}
