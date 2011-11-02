package br.com.easyShop.telas.consultas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Usuario;
import br.com.easyShop.service.EnderecoService;
import br.com.easyShop.service.UsuarioService;
import br.com.easyShop.telas.calendario.JDateChooser;
import br.com.easyShop.utils.TipoEndereco;

public class MeusDados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtSenha;
	private JTextField txtUsuario;
	private JTextField txtLogradouro;
	private JTextField txtCEP;
	private JTextField txtBairro;
	private JTextField txtComplemento;
	private JTextField txtNumero;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTextField txtApelido;
	private JTextField txtRG;
	private JTextField txtCNPJ;
	private JTextField txtInscricaoEstadual;
	private JTextField txtRazaoSocial;
	private JTextField txtNomeFantasia;
	private JButton btnCancelar = new JButton("Cancelar");
	private Usuario usuario = new Usuario();
	private JDateChooser calendarDataDeNascimento = new JDateChooser();
	private JComboBox cboSexo = new JComboBox();
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JPanel pnlPessoaJuridica = new JPanel();
	private JPanel pnlPessoaFisica = new JPanel();
	private JComboBox cboPais; 
	private JComboBox cboTipo;
	private JComboBox cboEstado;
	private JComboBox cboCidade;
	private BufferedImage imagem_buffered;
	private JLabel lblImagem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final Usuario usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeusDados frame = new MeusDados(usuario);
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
	public MeusDados(Usuario usuario) {
		
		btnCancelar.addActionListener(new Cancelar());
		
		this.usuario = usuario;
		
		setTitle("Meus Dados");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 859, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_6 = new JLabel("Senha");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(161, 513, 70, 26);
		contentPane.add(label_6);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSenha.setBounds(231, 513, 242, 26);
		contentPane.add(txtSenha);
		
		JLabel label_7 = new JLabel("Usu\u00E1rio");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_7.setBounds(161, 476, 70, 26);
		contentPane.add(label_7);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(231, 476, 242, 26);
		contentPane.add(txtUsuario);
		
		JLabel label_8 = new JLabel("Logradouro");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_8.setBounds(37, 202, 97, 26);
		contentPane.add(label_8);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(133, 203, 256, 26);
		contentPane.add(txtLogradouro);
		
		cboTipo = new JComboBox();
		cboTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTipo.setBounds(534, 250, 103, 26);
		contentPane.add(cboTipo);
		
		JLabel label_9 = new JLabel("Tipo");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_9.setBounds(495, 249, 42, 26);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("CEP");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_10.setBounds(495, 202, 48, 26);
		contentPane.add(label_10);
		
		txtCEP = new JTextField();
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCEP.setColumns(10);
		txtCEP.setBounds(527, 203, 110, 26);
		contentPane.add(txtCEP);
		
		JLabel label_11 = new JLabel("Bairro");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_11.setBounds(37, 250, 59, 26);
		contentPane.add(label_11);
		
		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBairro.setColumns(10);
		txtBairro.setBounds(91, 250, 165, 26);
		contentPane.add(txtBairro);
		
	    cboPais = new JComboBox();
		cboPais.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboPais.setBounds(69, 299, 131, 26);
		contentPane.add(cboPais);
		
		JLabel label_12 = new JLabel("Pa\u00EDs");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_12.setBounds(37, 299, 42, 26);
		contentPane.add(label_12);
		
	    cboEstado = new JComboBox();
		cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboEstado.setBounds(286, 299, 130, 26);
		contentPane.add(cboEstado);
		
		JLabel label_13 = new JLabel("Estado");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_13.setBounds(229, 299, 63, 26);
		contentPane.add(label_13);
		
	    cboCidade = new JComboBox();
		cboCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboCidade.setBounds(485, 299, 152, 26);
		contentPane.add(cboCidade);
		
		JLabel label_14 = new JLabel("Cidade");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_14.setBounds(426, 299, 59, 26);
		contentPane.add(label_14);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(99, 463, 444, 2);
		contentPane.add(separator);
		
		JLabel label_16 = new JLabel("Complemento");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_16.setBounds(260, 249, 119, 26);
		contentPane.add(label_16);
		
		txtComplemento = new JTextField();
		txtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(374, 250, 103, 26);
		contentPane.add(txtComplemento);
		
		JLabel label_17 = new JLabel("N\u00BA");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_17.setBounds(396, 202, 29, 26);
		contentPane.add(label_17);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumero.setColumns(10);
		txtNumero.setBounds(427, 203, 42, 26);
		contentPane.add(txtNumero);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 351, 348, 80);
		contentPane.add(scrollPane);
		
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(663, 351, 160, 41);
		contentPane.add(btnCancelar);
		
		tabbedPane.setEnabled(false);
		tabbedPane.setBounds(10, 11, 636, 180);
		contentPane.add(tabbedPane);
		
		pnlPessoaFisica.setLayout(null);
		pnlPessoaFisica.setBackground(SystemColor.menu);
		tabbedPane.addTab("Pessoa F\u00EDsica", null, pnlPessoaFisica, null);
		
		JLabel label = new JLabel("Nome");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(24, 11, 53, 26);
		pnlPessoaFisica.add(label);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNome.setColumns(10);
		txtNome.setBounds(78, 11, 527, 26);
		pnlPessoaFisica.add(txtNome);
		
		JLabel label_1 = new JLabel("CPF");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(24, 102, 42, 26);
		pnlPessoaFisica.add(label_1);
		
		txtCPF = new JTextField();
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCPF.setColumns(10);
		txtCPF.setBounds(56, 103, 152, 26);
		pnlPessoaFisica.add(txtCPF);
		
		JLabel label_2 = new JLabel("Apelido");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(24, 56, 70, 25);
		pnlPessoaFisica.add(label_2);
		
		txtApelido = new JTextField();
		txtApelido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtApelido.setColumns(10);
		txtApelido.setBounds(90, 56, 227, 26);
		pnlPessoaFisica.add(txtApelido);
		
		JLabel label_3 = new JLabel("RG");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(218, 102, 35, 26);
		pnlPessoaFisica.add(label_3);
		
		txtRG = new JTextField();
		txtRG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRG.setColumns(10);
		txtRG.setBounds(247, 103, 165, 26);
		pnlPessoaFisica.add(txtRG);
		
		cboSexo.setName("");
		cboSexo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboSexo.setBounds(463, 103, 142, 26);
		pnlPessoaFisica.add(cboSexo);
		
		JLabel label_4 = new JLabel("Sexo");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(422, 102, 59, 26);
		pnlPessoaFisica.add(label_4);
		
		calendarDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		calendarDataDeNascimento.setBounds(453, 56, 152, 26);
		pnlPessoaFisica.add(calendarDataDeNascimento);
		
		JLabel label_5 = new JLabel("Data de Nasc.");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(335, 55, 118, 26);
		pnlPessoaFisica.add(label_5);
		
		pnlPessoaJuridica.setLayout(null);
		pnlPessoaJuridica.setBackground(SystemColor.menu);
		tabbedPane.addTab("Pessoa Jur\u00EDdica", null, pnlPessoaJuridica, null);
		
		JLabel label_15 = new JLabel("CNPJ");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_15.setBounds(107, 11, 48, 26);
		pnlPessoaJuridica.add(label_15);
		
		txtCNPJ = new JTextField();
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(21, 39, 236, 26);
		pnlPessoaJuridica.add(txtCNPJ);
		
		JLabel label_18 = new JLabel("Inscri\u00E7\u00E3o Estadual");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_18.setBounds(57, 76, 152, 27);
		pnlPessoaJuridica.add(label_18);
		
		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setColumns(10);
		txtInscricaoEstadual.setBounds(21, 101, 236, 26);
		pnlPessoaJuridica.add(txtInscricaoEstadual);
		
		JLabel label_19 = new JLabel("Raz\u00E3o Social");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_19.setBounds(413, 76, 108, 26);
		pnlPessoaJuridica.add(label_19);
		
		txtRazaoSocial = new JTextField();
		txtRazaoSocial.setColumns(10);
		txtRazaoSocial.setBounds(331, 101, 270, 26);
		pnlPessoaJuridica.add(txtRazaoSocial);
		
		JLabel label_20 = new JLabel("Nome Fantasia");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_20.setBounds(403, 11, 136, 26);
		pnlPessoaJuridica.add(label_20);
		
		txtNomeFantasia = new JTextField();
		txtNomeFantasia.setColumns(10);
		txtNomeFantasia.setBounds(331, 39, 270, 26);
		pnlPessoaJuridica.add(txtNomeFantasia);
		
	    lblImagem = new JLabel("");
		lblImagem.setBounds(663, 140, 160, 200);
		contentPane.add(lblImagem);
		
		preencherDados();
	}
	
	private void preencherDados(){
		try {
			if(usuario.getPessoa().getPessoaFisica().getNome().equals(null)==false){
				tabbedPane.setSelectedIndex(0);
				txtNome.setText(usuario.getPessoa().getPessoaFisica().getNome());
				txtApelido.setText(usuario.getPessoa().getPessoaFisica().getApelido());
				calendarDataDeNascimento.setCalendar(usuario.getPessoa().getPessoaFisica().getDataNascimento());
				txtCPF.setText(usuario.getPessoa().getPessoaFisica().getCpf());
				txtRG.setText(usuario.getPessoa().getPessoaFisica().getRg());
				
				if(usuario.getPessoa().getPessoaFisica().getSexo().equals("masculino")){
					cboSexo.addItem("Masculino");
				}else{
					cboSexo.addItem("Feminino");
				}			
			}
		} catch (Exception e) {
			tabbedPane.setSelectedIndex(1);
			txtCNPJ.setText(usuario.getPessoa().getPessoaJuridica().getCnpj());
			txtInscricaoEstadual.setText(usuario.getPessoa().getPessoaJuridica().getInscricaoEstadual());
			txtNomeFantasia.setText(usuario.getPessoa().getPessoaJuridica().getNomeFantasia());
			txtRazaoSocial.setText(usuario.getPessoa().getPessoaJuridica().getRazaoSocial());
		}
		
		EnderecoService enderecoService = new EnderecoService();
		Endereco endereco = new Endereco();
		endereco = enderecoService.getEndereco(usuario.getPessoa());
		
		txtLogradouro.setText(endereco.getLogradouro());
		txtBairro.setText(endereco.getBairro());
		txtNumero.setText(endereco.getNumero());
		txtComplemento.setText(endereco.getComplemento());
		txtCEP.setText(endereco.getCep());
		
		cboCidade.addItem(endereco.getCidade());
		cboEstado.addItem(endereco.getCidade().getEstado());
		cboPais.addItem(endereco.getCidade().getEstado().getPais());
		 
		cboTipo.addItem(TipoEndereco.getNomeTipo(endereco.getTipo()));
		
		txtUsuario.setText(usuario.getLogin());
		txtSenha.setText(usuario.getSenha());
		
		carregarImagem();
	}
	
	private class Cancelar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MeusDados.this.dispose();
		}
	}
	
	private void carregarImagem(){
		
    try {
			
			URL url = getClass().getResource("/br/com/easyShop/telas/imagens/usuario"+usuario.getPkUsuario()+".jpg");
			File imagem_file = new File(url.getFile());
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
				lblImagem.setIcon(new ImageIcon(getClass().getResource("/br/com/easyShop/telas/imagens/padrao/padraoMasculino.png")));
			}
			else{
				lblImagem.setIcon(new ImageIcon(getClass().getResource("/br/com/easyShop/telas/imagens/padrao/padraoFeminino.png")));
			}

		}
	}
}
