package br.com.easyShop.telas;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.SwingConstants;

import br.com.easyShop.aplicacao.MainEasyShopDesktop;
import br.com.easyShop.model.Usuario;
import br.com.easyShop.relatorios.ExcRepositorio;
import br.com.easyShop.relatorios.repositorioProduto;
import br.com.easyShop.telas.cadastros.CadastroDeCategoria;
import br.com.easyShop.telas.cadastros.CadastroDeMarca;
import br.com.easyShop.telas.cadastros.CadastroDeProdutos;
import br.com.easyShop.telas.cadastros.CadastroDeUsuario;
import br.com.easyShop.telas.cadastros.PesquisarProduto;
import br.com.easyShop.telas.consultas.MeusDados;
import br.com.easyShop.telas.consultas.MeusDados;
import br.com.easyShop.telas.edicao.EditarProdutos;
import br.com.easyShop.telas.lancamentos.LancamentoDePermissao;

import java.awt.Color;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class Janela extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCadastroProduto = new JButton("");
	private JButton btnCadastroUsuario = new JButton("");
	private JButton btnPesquisarProduto = new JButton("");
	private JButton btnCadastroDeCategoria = new JButton("");
	private JButton btnCadastroDeMarca = new JButton("");
	private JButton btnLancamentoDePermissao = new JButton("");
	private JButton btnRelatorioProduto = new JButton("");
	private JButton btnRelatorioDeMarca = new JButton("");
	private JButton btnSair = new JButton("   Sair");
	private JButton btnLogoff = new JButton("Logoff");
	private JButton btnMeusDados = new JButton("Meus Dados");
	private JButton btnEditarMeusDados = new JButton("Editar Meus Dados");
	private JButton btnRelatorioDeCategoria = new JButton("");
	private List<Frame> fecharJanelasAbertas;
	private repositorioProduto rep = new repositorioProduto();
	private JasperPrint relat;
	private JLabel lblRelogio = new JLabel("Rel\u00F3gio");
	private javax.swing.Timer timer;  
	private Usuario usuario = new Usuario();
	private BufferedImage imagem_buffered;
	private JLabel lblEditarProduto = new JLabel("Editar Produto");
	private JButton btnEditarProduto = new JButton("");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Janela(Usuario usuario) {
		this.usuario = usuario;
		
		setTitle("EasyShop");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 855, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnCadastroProduto.addActionListener(new CadastroDeProduto());
		btnCadastroUsuario.addActionListener(new CadastroDeUsuarios());
		btnPesquisarProduto.addActionListener(new PesquisaDeProduto());
		btnCadastroDeCategoria.addActionListener(new PesquisaDeCategoria());
		btnCadastroDeMarca.addActionListener(new PesquisaDeMarca());
		btnLancamentoDePermissao.addActionListener(new AbrirLancamentoDePermissao());
		btnRelatorioProduto.addActionListener(new RelatorioDeProduto());
		btnRelatorioDeMarca.addActionListener(new RelatorioDeMarca());
		btnRelatorioDeCategoria.addActionListener(new RelatorioDeCategoria());
		btnSair.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/Turn off.png")));
		btnSair.addActionListener(new Sair());
		btnLogoff.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/1320376266_login.png")));
		btnLogoff.addActionListener(new Logoff());
		//btnEditarMeusDados.addActionListener(new EditarMeusDados());
		btnMeusDados.addActionListener(new MeusDado());
		btnEditarProduto.addActionListener(new EditarProduto());

		lblRelogio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelogio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelogio.setBounds(713, 531, 147, 20);
		contentPane.add(lblRelogio);
		disparaRelogio();  
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 521, 839, 41);
		contentPane.add(menuBar);
		
		JMenu mnIniciar = new JMenu("     Iniciar    ");
		mnIniciar.setFocusTraversalPolicyProvider(true);
		mnIniciar.setFocusPainted(true);
		mnIniciar.setFocusCycleRoot(true);
		mnIniciar.setBackground(Color.LIGHT_GRAY);
		mnIniciar.setHorizontalAlignment(SwingConstants.CENTER);
		mnIniciar.setFont(new Font("Tahoma", Font.BOLD, 16));
		menuBar.add(mnIniciar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setMaximumSize(new Dimension(1032767, 1032767));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(225, 440));
		panel.setBounds(new Rectangle(0, 0, 120, 200));
		mnIniciar.add(panel);
		
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSair.setBounds(10, 385, 205, 44);
		panel.add(btnSair);
		
		btnLogoff.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLogoff.setBounds(10, 330, 205, 44);
		panel.add(btnLogoff);
		
		JButton btnEditarMeusDados = new JButton("Editar Meus Dados");
		btnEditarMeusDados.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnEditarMeusDados.setBounds(10, 275, 205, 44);
		panel.add(btnEditarMeusDados);
		
		btnMeusDados.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnMeusDados.setBounds(10, 220, 205, 44);
		panel.add(btnMeusDados);
		
		JLabel label_1 = new JLabel("Bem Vindo(a) " + usuario.getLogin());
		label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setPreferredSize(new Dimension(140, 14));
		label_1.setMaximumSize(new Dimension(153, 14));
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(10, 11, 205, 33);
		panel.add(label_1);
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setBorder(null);
		lblImagem.setPreferredSize(new Dimension(22, 22));
		lblImagem.setMaximumSize(new Dimension(36, 35));
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setBackground(Color.WHITE);
		lblImagem.setBounds(53, 55, 126, 139);
		
		try {
			
			File imagem_file = new File("Imagens/ImagensUsuario/usuario"+ usuario.getPkUsuario() + ".jpg");
			imagem_buffered = null;
			
			try {
				imagem_buffered = ImageIO.read(imagem_file );
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			 BufferedImage aux = new BufferedImage(lblImagem.getSize().width, lblImagem.getSize().height, imagem_buffered.getType());//cria um buffer auxiliar com o tamanho desejado
			 Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao
			 AffineTransform at = AffineTransform.getScaleInstance((double) lblImagem.getSize().width / imagem_buffered.getWidth(), (double) lblImagem.getSize().height / imagem_buffered.getHeight());//cria a transformacao
			 g.drawRenderedImage(imagem_buffered, at);//pinta e transforma a imagem real no auxiliar
			
			 lblImagem.setIcon(new ImageIcon(aux));
			
		} catch (Exception e) {
			if(usuario.getPessoa().getPessoaFisica().getSexo().equals("masculino")){
				lblImagem.setIcon(new ImageIcon("Imagens/Padrao/padraoMasculino.png"));
			}
			else if(usuario.getPessoa().getPessoaFisica().getSexo().equals("femino")){
				lblImagem.setIcon(new ImageIcon("Imagens/Padrao/padraoFeminino.png"));
			}
			else{
				lblImagem.setIcon(new ImageIcon("Imagens/Padrao/padraoJuridico.png"));
			}
		}

		panel.add(lblImagem);
		
		btnCadastroProduto.setBounds(46, 21, 72, 67);
		contentPane.add(btnCadastroProduto);
		
		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de Produtos");
		lblCadastroDeProdutos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeProdutos.setBounds(10, 85, 147, 41);
		contentPane.add(lblCadastroDeProdutos);
		
		btnCadastroUsuario.setBounds(46, 145, 72, 67);
		contentPane.add(btnCadastroUsuario);
		
		JLabel lblCadastroDeUsurio = new JLabel(" Cadastro de Usu\u00E1rio");
		lblCadastroDeUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeUsurio.setBounds(10, 211, 147, 41);
		contentPane.add(lblCadastroDeUsurio);
		
		btnPesquisarProduto.setBounds(205, 21, 72, 67);
		contentPane.add(btnPesquisarProduto);
		
		JLabel lblPesquisarProduto = new JLabel(" Pesquisar Produto");
		lblPesquisarProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPesquisarProduto.setBounds(179, 84, 137, 43);
		contentPane.add(lblPesquisarProduto);
		
		btnCadastroDeCategoria.setBounds(46, 274, 72, 67);
		contentPane.add(btnCadastroDeCategoria);
		
		JLabel lblCadastroDeCategoria = new JLabel("Cadastro de Categoria");
		lblCadastroDeCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeCategoria.setBounds(10, 340, 147, 41);
		contentPane.add(lblCadastroDeCategoria);
		
		btnCadastroDeMarca.setBounds(46, 403, 72, 67);
		contentPane.add(btnCadastroDeMarca);
		
		JLabel lblCadastroDeMarca = new JLabel("Cadastro de Marca");
		lblCadastroDeMarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeMarca.setBounds(20, 469, 137, 41);
		contentPane.add(lblCadastroDeMarca);
		
		btnLancamentoDePermissao.setBounds(205, 147, 72, 67);
		contentPane.add(btnLancamentoDePermissao);
		
		JLabel lblLanamentoDeProduo = new JLabel("Lan\u00E7amento de Permiss\u00E3o");
		lblLanamentoDeProduo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLanamentoDeProduo.setBounds(160, 210, 172, 43);
		contentPane.add(lblLanamentoDeProduo);
		
		btnRelatorioProduto.setBounds(205, 274, 72, 67);
		contentPane.add(btnRelatorioProduto);
		
		JLabel lblRelatrioDeProduto = new JLabel("Relat\u00F3rio de Produto");
		lblRelatrioDeProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeProduto.setBounds(179, 340, 137, 41);
		contentPane.add(lblRelatrioDeProduto);
		
		btnRelatorioDeMarca.setBounds(205, 403, 72, 67);
		contentPane.add(btnRelatorioDeMarca);
		
		JLabel lblRelatrioDeMarca = new JLabel("Relat\u00F3rio de Marca");
		lblRelatrioDeMarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeMarca.setBounds(179, 469, 137, 41);
		contentPane.add(lblRelatrioDeMarca);
		
		btnRelatorioDeCategoria.setBounds(367, 19, 72, 67);
		contentPane.add(btnRelatorioDeCategoria);
		
		JLabel lblRelatrioDeCategoria = new JLabel("Relat\u00F3rio de Categoria");
		lblRelatrioDeCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeCategoria.setBounds(335, 85, 159, 41);
		contentPane.add(lblRelatrioDeCategoria);
		
		
		btnEditarProduto.setBounds(369, 145, 72, 67);
		contentPane.add(btnEditarProduto);
		
		lblEditarProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEditarProduto.setBounds(361, 211, 112, 41);
		contentPane.add(lblEditarProduto);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/fundo_easyShop.jpg")));
		label.setBounds(-216, -42, 1121, 593);
		contentPane.add(label);
	}
	
	public void disparaRelogio() {  
	      if (timer == null) {  
	         timer = new javax.swing.Timer(1000, this);  
	         timer.setInitialDelay(0);  
	         timer.start();  
	      } else if (!timer.isRunning()) {  
	         timer.restart();  
	      }  
	   }  
	  
	   public void actionPerformed(ActionEvent ae) {  
	      GregorianCalendar calendario = new GregorianCalendar();  
	      int h = calendario.get(GregorianCalendar.HOUR_OF_DAY);  
	      int m = calendario.get(GregorianCalendar.MINUTE);  
	      int s = calendario.get(GregorianCalendar.SECOND);  
	  
	      String hora =  
	         ((h < 10) ? "0" : "")  
	            + h  
	            + ":"  
	            + ((m < 10) ? "0" : "")  
	            + m  
	            + ":"  
	            + ((s < 10) ? "0" : "")  
	            + s;  
	  
	      lblRelogio.setText(hora);  
	   }  
	
	private class CadastroDeProduto implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			CadastroDeProdutos cadastroDeProdutos = new CadastroDeProdutos();
			cadastroDeProdutos.setLocationRelativeTo(null);  
			cadastroDeProdutos.setVisible(true);			
		}
	}
	
	private class MeusDado implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MeusDados meusDados = new MeusDados(usuario);
			meusDados.setLocationRelativeTo(null);  
			meusDados.setAlwaysOnTop(true);  
			meusDados.setVisible(true);			
		}
	}
	
	private class CadastroDeUsuarios implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			CadastroDeUsuario cadastroDeUsuario = new CadastroDeUsuario();
			cadastroDeUsuario.setLocationRelativeTo(null);  
			cadastroDeUsuario.setVisible(true);			
		}
	}
	
	private class PesquisaDeProduto implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PesquisarProduto cadastro = new PesquisarProduto();
			cadastro.setLocationRelativeTo(null);  
			cadastro.setVisible(true);			
		}
	}
	
	private class PesquisaDeCategoria implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CadastroDeCategoria cadastro = new CadastroDeCategoria();		
			cadastro.setLocationRelativeTo(null);  
			cadastro.setVisible(true);			
		}
	}
	
	private class PesquisaDeMarca implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CadastroDeMarca cadastro = new CadastroDeMarca();
			cadastro.setLocationRelativeTo(null);  
			cadastro.setVisible(true);			
		}
	}
	
	private class AbrirLancamentoDePermissao implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			LancamentoDePermissao cadastro = new LancamentoDePermissao();
			cadastro.setLocationRelativeTo(null);  
			cadastro.setVisible(true);			
		}
	}
	
	private class RelatorioDeProduto implements ActionListener {
		public void actionPerformed(ActionEvent e) {			
			try {
				relat = rep.gerar("easyShopRelatorioDeProduto.jasper");
				JasperViewer.viewReport(relat, false);
			} catch (ExcRepositorio e1) {
				JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
			}	
		}
	}
	
	private class RelatorioDeMarca implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				relat = rep.gerar("easyShopRelatorioDeMarca.jasper");
				JasperViewer.viewReport(relat, false);
			} catch (ExcRepositorio e1) {
				JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
			}	
		}
	}
	
	private class RelatorioDeCategoria implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				relat = rep.gerar("easyShopRelatorioDeCategoria.jasper");
				JasperViewer.viewReport(relat, false);
			} catch (ExcRepositorio e1) {
				JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
			}	
		}
	}
	
	private class Sair implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Janela.this.dispose();
			
			Frame[] fecharJanelasAbertas = getFrames();  
			for (int i = 0; i < fecharJanelasAbertas.length; i++) {  
				fecharJanelasAbertas[i].dispose();  
			}  
		}
	}
	
	private class Logoff implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Janela.this.dispose();
			MainEasyShopDesktop.main(null);
		}
	}
	
	private class EditarProduto implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			EditarProdutos cadastro = new EditarProdutos();		
			cadastro.setLocationRelativeTo(null);  
			cadastro.setVisible(true);	
		}
	}
}
