package br.com.easyShop.telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import br.com.easyShop.aplicacao.MainEasyShopDesktop;
import br.com.easyShop.model.Usuario;
import br.com.easyShop.model.UsuarioTela;
import br.com.easyShop.persistencia.conexao.BancoDeDados;
import br.com.easyShop.relatorios.ExcRepositorio;
import br.com.easyShop.relatorios.repositorioProduto;
import br.com.easyShop.service.UsuarioTelaService;
import br.com.easyShop.telas.cadastros.CadastroDeCategoria;
import br.com.easyShop.telas.cadastros.CadastroDeMarca;
import br.com.easyShop.telas.cadastros.CadastroDeProdutos;
import br.com.easyShop.telas.cadastros.CadastroDeUsuario;
import br.com.easyShop.telas.consultas.MeusDados;
import br.com.easyShop.telas.edicao.EditarProdutos;
import br.com.easyShop.telas.lancamentos.LancamentoDePermissao;

public class Janela extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCadastroProduto = new JButton("");
	private JButton btnCadastroUsuario = new JButton("");
	private JButton btnCadastroDeCategoria = new JButton("");
	private JButton btnCadastroDeMarca = new JButton("");
	private JButton btnLancamentoDePermissao = new JButton("");
	private JButton btnRelatorioProduto = new JButton("");
	private JButton btnRelatorioDeMarca = new JButton("");
	private JButton btnSair = new JButton("   Sair");
	private JButton btnLogoff = new JButton("Logoff");
	private JButton btnMeusDados = new JButton("Meus Dados");
	private JButton btnRelatorioDeCategoria = new JButton("");
	private repositorioProduto rep = new repositorioProduto();
	private JasperPrint relat;
	private JLabel lblRelogio = new JLabel("Rel\u00F3gio");
	private javax.swing.Timer timer;  
	private Usuario usuario = new Usuario();
	private BufferedImage imagem_buffered;
	private JLabel lblEditarProduto = new JLabel("Editar Produto");
	private JButton btnEditarProduto = new JButton("");
	private JMenu mnIniciar = new JMenu("     Iniciar    ");
	private JLabel lblImagem = new JLabel("");
	private JButton btnEditarMeusDados = new JButton("Editar Meus Dados");
	private JButton btnRelatorioDePessoa = new JButton("");
	private JButton btnRelatorioDePFisica = new JButton("");
	private JButton btnRelatorioDePJuridica = new JButton("");
	private JButton btnRelatorioDeEndereco = new JButton("");
	private JButton btnRelatorioDeCliente = new JButton("");
	private JButton btnRelatorioDeUsuario = new JButton("");
	private JButton btnRelatorioDePedido = new JButton("");
	
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
		btnCadastroProduto.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/addProduto.png")));
		
		btnCadastroProduto.addActionListener(new CadastroDeProduto());
		btnCadastroUsuario.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/addUsuario.png")));
		btnCadastroUsuario.addActionListener(new CadastroDeUsuarios());
		btnCadastroDeCategoria.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/addcategoria.png")));
		btnCadastroDeCategoria.addActionListener(new PesquisaDeCategoria());
		btnCadastroDeMarca.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/addmarca2.png")));
		btnCadastroDeMarca.addActionListener(new PesquisaDeMarca());
		btnLancamentoDePermissao.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/lancamentoDePermissao.png")));
		btnLancamentoDePermissao.addActionListener(new AbrirLancamentoDePermissao());
		btnRelatorioProduto.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioProduto.addActionListener(new RelatorioDeProduto());
		btnRelatorioDeMarca.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDeMarca.addActionListener(new RelatorioDeMarca());
		btnRelatorioDeCategoria.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDeCategoria.addActionListener(new RelatorioDeCategoria());
		btnSair.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Turn off.png")));
		btnSair.addActionListener(new Sair());
		btnLogoff.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/1320376266_login.png")));
		btnLogoff.addActionListener(new Logoff());
		btnMeusDados.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/dados.png")));
		btnMeusDados.addActionListener(new MeusDado());
		btnEditarMeusDados.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/edit.png")));
		btnEditarMeusDados.addActionListener(new EditarMeusDados());
		btnEditarProduto.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/editarPr.png")));
		btnEditarProduto.addActionListener(new EditarProduto());
		btnRelatorioDePessoa.addActionListener(new RelatorioDePessoa());
		btnRelatorioDePFisica.addActionListener(new RelatorioDePFisica());
		btnRelatorioDePJuridica.addActionListener(new RelatorioDePJuridica());
		btnRelatorioDeEndereco.addActionListener(new RelatorioDeEndereco());
		btnRelatorioDeCliente.addActionListener(new RelatorioDeCliente());
		btnRelatorioDeUsuario.addActionListener(new RelatorioDeUsuario());
		btnRelatorioDePedido.addActionListener(new RelatorioDePedido());
		
		mnIniciar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				preencherImagem();
			}
		});
		

		lblRelogio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelogio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelogio.setBounds(713, 531, 147, 20);
		contentPane.add(lblRelogio);
		disparaRelogio();  
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 521, 839, 41);
		contentPane.add(menuBar);
		
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
		
		btnEditarMeusDados.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		
		lblImagem.setBorder(null);
		lblImagem.setPreferredSize(new Dimension(22, 22));
		lblImagem.setMaximumSize(new Dimension(36, 35));
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setBackground(Color.WHITE);
		lblImagem.setBounds(53, 55, 126, 139);
		
		try {			
			File imagem_file = new File("Imagens/ImagensUsuario/usuario"+ usuario.getPkUsuario() + ".jpg");
			imagem_buffered = null;
			imagem_buffered = ImageIO.read(imagem_file );
			
			 BufferedImage aux = new BufferedImage(lblImagem.getSize().width, lblImagem.getSize().height, imagem_buffered.getType());//cria um buffer auxiliar com o tamanho desejado
			 Graphics2D g = aux.createGraphics();//pega a classe graphics do aux para edicao
			 AffineTransform at = AffineTransform.getScaleInstance((double) lblImagem.getSize().width / imagem_buffered.getWidth(), (double) lblImagem.getSize().height / imagem_buffered.getHeight());//cria a transformacao
			 g.drawRenderedImage(imagem_buffered, at);//pinta e transforma a imagem real no auxiliar
			
			 lblImagem.setIcon(new ImageIcon(aux));
			
		} catch (Exception e) {
			
			if (usuario.getPessoa() != null && usuario.getPessoa().getPessoaFisica() != null)
			{
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
		}

		panel.add(lblImagem);
		
		btnCadastroProduto.setBounds(46, 21, 72, 67);
		btnCadastroProduto.setBorder(null);
		btnCadastroProduto.setBorderPainted(false);
		btnCadastroProduto.setContentAreaFilled(false);
		btnCadastroProduto.setOpaque(false);
		contentPane.add(btnCadastroProduto);
		
		JLabel lblCadastroDeProdutos = new JLabel("Cadastro de Produtos");
		lblCadastroDeProdutos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeProdutos.setBounds(10, 87, 147, 41);
		contentPane.add(lblCadastroDeProdutos);
		
		btnCadastroUsuario.setBounds(46, 145, 72, 67);
		btnCadastroUsuario.setBorder(null);
		btnCadastroUsuario.setBorderPainted(false);
		btnCadastroUsuario.setContentAreaFilled(false);
		btnCadastroUsuario.setOpaque(false);
		contentPane.add(btnCadastroUsuario);
		
		JLabel lblCadastroDeUsurio = new JLabel(" Cadastro de Usu\u00E1rio");
		lblCadastroDeUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeUsurio.setBounds(10, 211, 147, 41);
		contentPane.add(lblCadastroDeUsurio);
		
		btnCadastroDeCategoria.setBounds(46, 274, 72, 67);
		btnCadastroDeCategoria.setBorder(null);
		btnCadastroDeCategoria.setBorderPainted(false);
		btnCadastroDeCategoria.setContentAreaFilled(false);
		btnCadastroDeCategoria.setOpaque(false);
		contentPane.add(btnCadastroDeCategoria);
		
		JLabel lblCadastroDeCategoria = new JLabel("Cadastro de Categoria");
		lblCadastroDeCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeCategoria.setBounds(10, 340, 147, 41);
		contentPane.add(lblCadastroDeCategoria);
		
		btnCadastroDeMarca.setBounds(46, 403, 72, 67);
		btnCadastroDeMarca.setBorder(null);
		btnCadastroDeMarca.setBorderPainted(false);
		btnCadastroDeMarca.setContentAreaFilled(false);
		btnCadastroDeMarca.setOpaque(false);
		contentPane.add(btnCadastroDeMarca);
		
		JLabel lblCadastroDeMarca = new JLabel("Cadastro de Marca");
		lblCadastroDeMarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCadastroDeMarca.setBounds(20, 469, 137, 41);
		contentPane.add(lblCadastroDeMarca);
		
		btnLancamentoDePermissao.setBounds(205, 147, 72, 67);
		btnLancamentoDePermissao.setBorder(null);
		btnLancamentoDePermissao.setBorderPainted(false);
		btnLancamentoDePermissao.setContentAreaFilled(false);
		btnLancamentoDePermissao.setOpaque(false);
		contentPane.add(btnLancamentoDePermissao);
		
		JLabel lblLanamentoDeProduo = new JLabel("Lan\u00E7amento de Permiss\u00E3o");
		lblLanamentoDeProduo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLanamentoDeProduo.setBounds(160, 210, 172, 43);
		contentPane.add(lblLanamentoDeProduo);
		
		btnRelatorioProduto.setBounds(205, 274, 72, 67);
		btnRelatorioProduto.setBorder(null);
		btnRelatorioProduto.setBorderPainted(false);
		btnRelatorioProduto.setContentAreaFilled(false);
		btnRelatorioProduto.setOpaque(false);
		contentPane.add(btnRelatorioProduto);
		
		JLabel lblRelatrioDeProduto = new JLabel("Relat\u00F3rio de Produto");
		lblRelatrioDeProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeProduto.setBounds(179, 340, 137, 41);
		contentPane.add(lblRelatrioDeProduto);
		
		btnRelatorioDeMarca.setBounds(205, 403, 72, 67);
		btnRelatorioDeMarca.setBorder(null);
		btnRelatorioDeMarca.setBorderPainted(false);
		btnRelatorioDeMarca.setContentAreaFilled(false);
		btnRelatorioDeMarca.setOpaque(false);
		contentPane.add(btnRelatorioDeMarca);
		
		JLabel lblRelatrioDeMarca = new JLabel("Relat\u00F3rio de Marca");
		lblRelatrioDeMarca.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeMarca.setBounds(179, 469, 137, 41);
		contentPane.add(lblRelatrioDeMarca);
		
		btnRelatorioDeCategoria.setBounds(374, 21, 72, 67);
		btnRelatorioDeCategoria.setBorder(null);
		btnRelatorioDeCategoria.setBorderPainted(false);
		btnRelatorioDeCategoria.setContentAreaFilled(false);
		btnRelatorioDeCategoria.setOpaque(false);
		contentPane.add(btnRelatorioDeCategoria);
		
		JLabel lblRelatrioDeCategoria = new JLabel("Relat\u00F3rio de Categoria");
		lblRelatrioDeCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeCategoria.setBounds(342, 87, 159, 41);
		contentPane.add(lblRelatrioDeCategoria);
		
		
		btnEditarProduto.setBounds(212, 21, 72, 67);
		btnEditarProduto.setBorder(null);
		btnEditarProduto.setBorderPainted(false);
		btnEditarProduto.setContentAreaFilled(false);
		btnEditarProduto.setOpaque(false);
		contentPane.add(btnEditarProduto);
		
		lblEditarProduto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEditarProduto.setBounds(204, 87, 112, 41);
		contentPane.add(lblEditarProduto);
		
		btnRelatorioDePessoa.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDePessoa.setOpaque(false);
		btnRelatorioDePessoa.setContentAreaFilled(false);
		btnRelatorioDePessoa.setBorderPainted(false);
		btnRelatorioDePessoa.setBounds(374, 145, 72, 67);
		contentPane.add(btnRelatorioDePessoa);
		
		JLabel lblRelatrioDePessoa = new JLabel("  Relat\u00F3rio de Pessoa");
		lblRelatrioDePessoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDePessoa.setBounds(342, 211, 159, 41);
		contentPane.add(lblRelatrioDePessoa);
		
		btnRelatorioDePFisica.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDePFisica.setOpaque(false);
		btnRelatorioDePFisica.setContentAreaFilled(false);
		btnRelatorioDePFisica.setBorderPainted(false);
		btnRelatorioDePFisica.setBounds(374, 274, 72, 67);
		contentPane.add(btnRelatorioDePFisica);
		
		JLabel lblRelatrioDeP = new JLabel(" Relat\u00F3rio de P. F\u00EDsica");
		lblRelatrioDeP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeP.setBounds(342, 340, 147, 41);
		contentPane.add(lblRelatrioDeP);
		
		btnRelatorioDePJuridica.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDePJuridica.setOpaque(false);
		btnRelatorioDePJuridica.setContentAreaFilled(false);
		btnRelatorioDePJuridica.setBorderPainted(false);
		btnRelatorioDePJuridica.setBounds(374, 403, 72, 67);
		contentPane.add(btnRelatorioDePJuridica);
		
		JLabel lblRelatrioDeP_1 = new JLabel("Relat\u00F3rio de P. Jur\u00EDdica");
		lblRelatrioDeP_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeP_1.setBounds(342, 469, 159, 41);
		contentPane.add(lblRelatrioDeP_1);
		
		btnRelatorioDeEndereco.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDeEndereco.setOpaque(false);
		btnRelatorioDeEndereco.setContentAreaFilled(false);
		btnRelatorioDeEndereco.setBorderPainted(false);
		btnRelatorioDeEndereco.setBounds(557, 21, 72, 67);
		contentPane.add(btnRelatorioDeEndereco);
		
		JLabel lblRelatrioDeEndereo = new JLabel("Relat\u00F3rio de Endere\u00E7o");
		lblRelatrioDeEndereo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeEndereo.setBounds(525, 87, 159, 41);
		contentPane.add(lblRelatrioDeEndereo);
		
		btnRelatorioDeCliente.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDeCliente.setOpaque(false);
		btnRelatorioDeCliente.setContentAreaFilled(false);
		btnRelatorioDeCliente.setBorderPainted(false);
		btnRelatorioDeCliente.setBounds(557, 147, 72, 67);
		contentPane.add(btnRelatorioDeCliente);
		
		JLabel lblRelatrioDeCliente = new JLabel("  Relat\u00F3rio de Cliente");
		lblRelatrioDeCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeCliente.setBounds(525, 213, 159, 41);
		contentPane.add(lblRelatrioDeCliente);
		
		btnRelatorioDeUsuario.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDeUsuario.setOpaque(false);
		btnRelatorioDeUsuario.setContentAreaFilled(false);
		btnRelatorioDeUsuario.setBorderPainted(false);
		btnRelatorioDeUsuario.setBounds(557, 274, 72, 67);
		contentPane.add(btnRelatorioDeUsuario);
		
		JLabel lblRelatrioDeUsurio = new JLabel("  Relat\u00F3rio de Usu\u00E1rio");
		lblRelatrioDeUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDeUsurio.setBounds(525, 340, 159, 41);
		contentPane.add(lblRelatrioDeUsurio);
		
		btnRelatorioDePedido.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/relatorio.png")));
		btnRelatorioDePedido.setOpaque(false);
		btnRelatorioDePedido.setContentAreaFilled(false);
		btnRelatorioDePedido.setBorderPainted(false);
		btnRelatorioDePedido.setBounds(557, 403, 72, 67);
		contentPane.add(btnRelatorioDePedido);
		
		JLabel lblRelatrioDePedido = new JLabel("  Relat\u00F3rio de Pedido");
		lblRelatrioDePedido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatrioDePedido.setBounds(525, 469, 159, 41);
		contentPane.add(lblRelatrioDePedido);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Janela.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/fundo_easyShop.jpg")));
		label.setBounds(-213, -42, 1121, 593);
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
			if(abrirLancamentoDePermissaoADM()){
				Long fkTela = Long.parseLong("2");
				Long fkTipoPermissao = Long.parseLong("1");
				Long fkTipoPermissao2 = Long.parseLong("2");
				UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
				UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
				
				if(usuarioTela!=null || usuarioTela2!=null){
					CadastroDeProdutos cadastroDeProdutos = new CadastroDeProdutos(usuario);
					cadastroDeProdutos.setLocationRelativeTo(null);  
					cadastroDeProdutos.setVisible(true);	
				}
				else{
					JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
				}	
			}
			else{
				CadastroDeProdutos cadastroDeProdutos = new CadastroDeProdutos(usuario);
				cadastroDeProdutos.setLocationRelativeTo(null);  
				cadastroDeProdutos.setVisible(true);		
			}	
		}
	}
	
	private class MeusDado implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MeusDados meusDados = new MeusDados(usuario,false);
			meusDados.setLocationRelativeTo(null);  
			meusDados.setAlwaysOnTop(true);  
			meusDados.setVisible(true);			
		}
	}
	
	private class EditarMeusDados implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MeusDados meusDados = new MeusDados(usuario,true);
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
	
	private class PesquisaDeCategoria implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(abrirLancamentoDePermissaoADM()){
				Long fkTela = Long.parseLong("3");
				Long fkTipoPermissao = Long.parseLong("1");
				UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
				Long fkTipoPermissao2 = Long.parseLong("2");
				UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
				
				if(usuarioTela!=null || usuarioTela2!=null){
					CadastroDeCategoria cadastro = new CadastroDeCategoria(usuario);		
					cadastro.setLocationRelativeTo(null);  
					cadastro.setVisible(true);	
				}
				else{
					JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
				}	
			}
			else{
				CadastroDeCategoria cadastro = new CadastroDeCategoria(usuario);		
				cadastro.setLocationRelativeTo(null);  
				cadastro.setVisible(true);			
			}	
		}
	}
	
	private class PesquisaDeMarca implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(abrirLancamentoDePermissaoADM()){
				Long fkTela = Long.parseLong("4");
				Long fkTipoPermissao = Long.parseLong("1");
				UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
				Long fkTipoPermissao2 = Long.parseLong("2");
				UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
				
				if(usuarioTela!=null || usuarioTela2!=null){
					CadastroDeMarca cadastro = new CadastroDeMarca(usuario);
					cadastro.setLocationRelativeTo(null);  
					cadastro.setVisible(true);			
				}
				else{
					JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
				}		
			}
			else{
				CadastroDeMarca cadastro = new CadastroDeMarca(usuario);
				cadastro.setLocationRelativeTo(null);  
				cadastro.setVisible(true);	
			}
		}
	}
	
	private class AbrirLancamentoDePermissao implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(abrirLancamentoDePermissaoADM()){
				Long fkTela = Long.parseLong("5");
				Long fkTipoPermissao = Long.parseLong("1");
				UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
				Long fkTipoPermissao2 = Long.parseLong("2");
				UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
				
				if(usuarioTela!=null || usuarioTela2!=null){
					LancamentoDePermissao cadastro = new LancamentoDePermissao(usuario);
					cadastro.setLocationRelativeTo(null);  
					cadastro.setVisible(true);			
				}
				else{
					JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
				}	
			}
			else{
				LancamentoDePermissao cadastro = new LancamentoDePermissao(usuario);
				cadastro.setLocationRelativeTo(null);  
				cadastro.setVisible(true);
			}
		}
	}
	
	private boolean abrirLancamentoDePermissaoADM(){
		if(usuario.getLogin().equals("adm")){			
			return false;
		}
		return true;
	}
	
	private class RelatorioDeProduto implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDeProduto.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}	
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDeMarca implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDeMarca.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDePedido implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDePedido.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDeUsuario implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDeUsuario.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDeCliente implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDeCliente.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDeEndereco implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDeEndereco.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDePJuridica implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDePessoaJuridica.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDePFisica implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDePessoaFisica.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDePessoa implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Long fkTela = Long.parseLong("7");
			Long fkTipoPermissao = Long.parseLong("1");
			UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
			Long fkTipoPermissao2 = Long.parseLong("2");
			UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
			
			if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
				try {
					relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDePessoa.jasper");
					JasperViewer.viewReport(relat, false);
				} catch (ExcRepositorio e1) {
					JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
				}		
			}
			else{
				JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
			}
		}
	}
	
	private class RelatorioDeCategoria implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				Long fkTela = Long.parseLong("7");
				Long fkTipoPermissao = Long.parseLong("1");
				UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
				Long fkTipoPermissao2 = Long.parseLong("2");
				UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
				
				if(usuarioTela!=null || usuarioTela2!=null || abrirLancamentoDePermissaoADM()==false){
					try {
						relat = rep.gerar("src/br/com/easyShop/relatorios/easyShopRelatorioDeCategoria.jasper");
						JasperViewer.viewReport(relat, false);
					} catch (ExcRepositorio e1) {
						JOptionPane.showMessageDialog(null, "Erro: " + e1.getMessage());
					}			
				}
				else{
					JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
				}
		
			}
		}
	
	private class Sair implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Janela.this.dispose();
			BancoDeDados.desconectar();
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
			if(abrirLancamentoDePermissaoADM()){
				Long fkTela = Long.parseLong("6");
				Long fkTipoPermissao = Long.parseLong("1");
				UsuarioTela usuarioTela = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao);
				Long fkTipoPermissao2 = Long.parseLong("2");
				UsuarioTela usuarioTela2 = new UsuarioTelaService().getUsuarioTelasSelecionado(usuario,fkTela ,fkTipoPermissao2);
				
				if(usuarioTela!=null || usuarioTela2!=null){
					EditarProdutos cadastro = new EditarProdutos(usuario);		
					cadastro.setLocationRelativeTo(null);  
					cadastro.setVisible(true);			
				}
				else{
					JOptionPane.showMessageDialog(null, "O usuário não tem permissão de Leitura");
				}	
			}
			else{
				EditarProdutos cadastro = new EditarProdutos(usuario);		
				cadastro.setLocationRelativeTo(null);  
				cadastro.setVisible(true);	
			}
		}
	}
	
	public void preencherImagem(){
		try {			
			File imagem_file = new File("Imagens/ImagensUsuario/usuario"+ usuario.getPkUsuario() + ".jpg");
			imagem_buffered = null;
			
			imagem_buffered = ImageIO.read(imagem_file);
			
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
	}
}
