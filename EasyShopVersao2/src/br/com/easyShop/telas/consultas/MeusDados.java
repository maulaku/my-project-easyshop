package br.com.easyShop.telas.consultas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.BoldAction;

import utils.data.Data;
import br.com.easyShop.model.Cidade;
import br.com.easyShop.model.Contato;
import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Estado;
import br.com.easyShop.model.Pais;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.model.Usuario;
import br.com.easyShop.service.CidadeService;
import br.com.easyShop.service.ContatoService;
import br.com.easyShop.service.EnderecoService;
import br.com.easyShop.service.EstadoService;
import br.com.easyShop.service.PaisService;
import br.com.easyShop.service.PessoaFisicaService;
import br.com.easyShop.service.PessoaJuridicaService;
import br.com.easyShop.service.PessoaService;
import br.com.easyShop.service.UsuarioService;
import br.com.easyShop.telas.calendario.JDateChooser;
import br.com.easyShop.utils.Constantes;
import br.com.easyShop.utils.TipoContato;
import br.com.easyShop.utils.TipoEndereco;

public class MeusDados extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlCadastro;
	private JButton btnCarregarImagem = new JButton("Carregar Imagem");
	private JButton btnCancelar = new JButton("Sair");
	private JButton btnLimpar = new JButton("Limpar");
	private final JComboBox cboSexo = new JComboBox();
	private final JDateChooser calendarDataDeNasc = new JDateChooser();
	private JTextField txtUsuario;
	private JTextField txtCPF;
	private JTextField txtApelido;
	private JTextField txtRG;
	private JTextField txtCNPJ;
	private JTextField txtInscricaoEstadual;
	private final DefaultTableModel modelo = new DefaultTableModel();
	private JPasswordField txtPassword;
	private JTextField txtLogin;
	private JTextField txtLogradouro;
	private JTextField txtCEP;
	private JTextField txtBairro;
	private JTextField txtContato;
	private JTextField txtComplemento;
	private JTextField txtNumero;
	private JComboBox cboTipo = new JComboBox();
	private JComboBox cboPais = new JComboBox();
	private JComboBox cboContato = new JComboBox();
	private JComboBox cboCidade = new JComboBox();
	private JComboBox cboEstado = new JComboBox();
	private JButton btnInserirContato = new JButton("");
	private JTable tblContato;
	private Pais pais;
	private Estado estado;
	private Cidade cidade;
	private List<Contato> listaContatos = new ArrayList<Contato>();
	private Contato contato = new Contato();
	private JTextField txtRazao;
	private JTextField txtFantasia;
	private String caminhoImagem;
	private JLabel lblImagem = new JLabel("");
	private BufferedImage imagem_buffered;
	private Usuario usuario = new Usuario();
	private JTabbedPane tabbedPane;
	private JButton btnEditar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private String tipoEndereco;

	
	public MeusDados(Usuario usuario, boolean verificar) {

		this.usuario = usuario;
		
		estado = new Estado();
		pais = new Pais();
		new Cidade();
		btnCarregarImagem
				.setIcon(new ImageIcon(
						MeusDados.class
								.getResource("/br/com/easyShop/telas/imagens/aplicacao/Picture.png")));
		btnCancelar
				.setIcon(new ImageIcon(
						MeusDados.class
								.getResource("/br/com/easyShop/telas/imagens/aplicacao/Close.png")));
		btnCancelar.addActionListener(new Cancelar());
		btnLimpar
				.setIcon(new ImageIcon(
						MeusDados.class
								.getResource("/br/com/easyShop/telas/imagens/aplicacao/Trash.png")));
		btnLimpar.addActionListener(new Limpar());
		btnInserirContato
				.setIcon(new ImageIcon(
						MeusDados.class
								.getResource("/br/com/easyShop/telas/imagens/aplicacao/Create.png")));
		btnInserirContato.addActionListener(new Inserir());

		setTitle("Meus Dados");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 881, 642);
		pnlCadastro = new JPanel();
		pnlCadastro.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlCadastro);
		pnlCadastro.setLayout(null);

		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelar.setBounds(684, 480, 160, 41);
		pnlCadastro.add(btnCancelar);

		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimpar.setBounds(684, 428, 160, 41);
		pnlCadastro.add(btnLimpar);

		btnCarregarImagem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCarregarImagem.setBounds(677, 217, 177, 41);
		pnlCadastro.add(btnCarregarImagem);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(21, 21, 636, 180);
		pnlCadastro.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Pessoa F\u00EDsica", null, panel, null);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(24, 11, 53, 26);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblNome);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsuario.setBounds(78, 11, 527, 26);
		txtUsuario.setColumns(10);
		panel.add(txtUsuario);

		JLabel label_3 = new JLabel("CPF");
		label_3.setBounds(24, 102, 42, 26);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_3);

		txtCPF = new JTextField();
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCPF.setBounds(56, 103, 152, 26);
		txtCPF.setColumns(10);
		panel.add(txtCPF);

		JLabel label_5 = new JLabel("Apelido");
		label_5.setBounds(24, 56, 70, 25);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_5);

		txtApelido = new JTextField();
		txtApelido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtApelido.setBounds(90, 56, 227, 26);
		txtApelido.setColumns(10);
		panel.add(txtApelido);

		JLabel label_6 = new JLabel("RG");
		label_6.setBounds(218, 102, 35, 26);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(label_6);

		txtRG = new JTextField();
		txtRG.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRG.setBounds(247, 103, 165, 26);
		txtRG.setColumns(10);
		panel.add(txtRG);
		cboSexo.setName("");

		cboSexo.setBounds(463, 103, 142, 26);
		cboSexo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel.add(cboSexo);
		cboSexo.addItem("Masculino");
		cboSexo.addItem("Feminino");

		JLabel label_7 = new JLabel("Sexo");
		label_7.setBounds(422, 102, 59, 26);
		panel.add(label_7);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));

		calendarDataDeNasc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		calendarDataDeNasc.setBounds(453, 56, 152, 26);
		panel.add(calendarDataDeNasc);

		JLabel lblDataDeNasc = new JLabel("Data de Nasc.");
		lblDataDeNasc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataDeNasc.setBounds(335, 55, 118, 26);
		panel.add(lblDataDeNasc);
		// panel.add());

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Pessoa Jur\u00EDdica", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCnpj.setBounds(107, 11, 48, 26);
		panel_1.add(lblCnpj);

		txtCNPJ = new JTextField();
		txtCNPJ.setColumns(10);
		txtCNPJ.setBounds(21, 39, 236, 26);
		panel_1.add(txtCNPJ);

		JLabel lblInscrioEstadual = new JLabel("Inscri\u00E7\u00E3o Estadual");
		lblInscrioEstadual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInscrioEstadual.setBounds(57, 76, 152, 27);
		panel_1.add(lblInscrioEstadual);

		txtInscricaoEstadual = new JTextField();
		txtInscricaoEstadual.setColumns(10);
		txtInscricaoEstadual.setBounds(21, 101, 236, 26);
		panel_1.add(txtInscricaoEstadual);

		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social");
		lblRazoSocial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRazoSocial.setBounds(413, 76, 108, 26);
		panel_1.add(lblRazoSocial);

		txtRazao = new JTextField();
		txtRazao.setColumns(10);
		txtRazao.setBounds(331, 101, 270, 26);
		panel_1.add(txtRazao);

		JLabel lblNomeFantasia = new JLabel("Nome Fantasia");
		lblNomeFantasia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeFantasia.setBounds(403, 11, 136, 26);
		panel_1.add(lblNomeFantasia);

		txtFantasia = new JTextField();
		txtFantasia.setColumns(10);
		txtFantasia.setBounds(331, 39, 270, 26);
		panel_1.add(txtFantasia);

		btnSalvar = new JButton("Salvar");
		btnSalvar
				.setIcon(new ImageIcon(
						MeusDados.class
								.getResource("/br/com/easyShop/telas/imagens/aplicacao/Save.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSalvar.setBounds(684, 376, 160, 41);
		pnlCadastro.add(btnSalvar);

		JPanel pnlEndereco = new JPanel();
		pnlEndereco.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		pnlEndereco.setBackground(UIManager.getColor("Button.background"));
		pnlEndereco.setBounds(21, 217, 636, 386);
		pnlCadastro.add(pnlEndereco);
		pnlEndereco.setLayout(null);

		JLabel label_1 = new JLabel("Senha");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(157, 349, 70, 26);
		pnlEndereco.add(label_1);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(227, 349, 242, 26);
		pnlEndereco.add(txtPassword);

		JLabel label_2 = new JLabel("Usu\u00E1rio");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(157, 312, 70, 26);
		pnlEndereco.add(label_2);

		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLogin.setColumns(10);
		txtLogin.setBounds(227, 312, 242, 26);
		pnlEndereco.add(txtLogin);

		JLabel label_16 = new JLabel("Logradouro");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_16.setBounds(26, 24, 97, 26);
		pnlEndereco.add(label_16);

		txtLogradouro = new JTextField();
		txtLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(122, 25, 256, 26);
		pnlEndereco.add(txtLogradouro);

		cboTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboTipo.setBounds(523, 72, 103, 26);
		pnlEndereco.add(cboTipo);

		JLabel label_18 = new JLabel("Tipo");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_18.setBounds(484, 71, 42, 26);
		pnlEndereco.add(label_18);

		JLabel label_19 = new JLabel("CEP");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_19.setBounds(484, 24, 48, 26);
		pnlEndereco.add(label_19);

		txtCEP = new JTextField();
		txtCEP.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCEP.setColumns(10);
		txtCEP.setBounds(516, 25, 110, 26);
		pnlEndereco.add(txtCEP);

		JLabel label_20 = new JLabel("Bairro");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_20.setBounds(26, 72, 59, 26);
		pnlEndereco.add(label_20);

		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBairro.setColumns(10);
		txtBairro.setBounds(80, 72, 165, 26);
		pnlEndereco.add(txtBairro);

		cboPais.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboPais.setBounds(58, 121, 131, 26);
		pnlEndereco.add(cboPais);

		JLabel label_21 = new JLabel("Pa\u00EDs");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_21.setBounds(26, 121, 42, 26);
		pnlEndereco.add(label_21);

		cboEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboEstado.setBounds(275, 121, 130, 26);
		pnlEndereco.add(cboEstado);

		JLabel label_22 = new JLabel("Estado");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_22.setBounds(218, 121, 63, 26);
		pnlEndereco.add(label_22);

		cboCidade.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboCidade.setBounds(474, 121, 152, 26);
		pnlEndereco.add(cboCidade);

		JLabel label_23 = new JLabel("Cidade");
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_23.setBounds(415, 121, 59, 26);
		pnlEndereco.add(label_23);

		cboContato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cboContato.setBounds(91, 171, 131, 26);
		pnlEndereco.add(cboContato);
		cboContato.addItem(Constantes.CONTATO_TELEFONE);
		cboContato.addItem(Constantes.CONTATO_CELULAR);
		cboContato.addItem(Constantes.CONTATO_EMAIL);
		cboContato.addItem(Constantes.CONTATO_FAX);

		JLabel label_24 = new JLabel("Contato");
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_24.setBounds(26, 168, 70, 26);
		pnlEndereco.add(label_24);

		btnInserirContato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInserirContato.setBounds(536, 171, 35, 33);
		pnlEndereco.add(btnInserirContato);

		txtContato = new JTextField();
		txtContato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtContato.setColumns(10);
		txtContato.setBounds(232, 171, 294, 26);
		pnlEndereco.add(txtContato);

		JSeparator separator = new JSeparator();
		separator.setBounds(95, 299, 444, 2);
		pnlEndereco.add(separator);

		JLabel label_25 = new JLabel("Complemento");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_25.setBounds(249, 71, 119, 26);
		pnlEndereco.add(label_25);

		txtComplemento = new JTextField();
		txtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(363, 72, 103, 26);
		pnlEndereco.add(txtComplemento);

		JLabel label_26 = new JLabel("N\u00BA");
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_26.setBounds(385, 24, 29, 26);
		pnlEndereco.add(label_26);

		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNumero.setColumns(10);
		txtNumero.setBounds(416, 25, 42, 26);
		pnlEndereco.add(txtNumero);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 208, 348, 80);
		pnlEndereco.add(scrollPane);

		modelo.addColumn("Tipo");
		modelo.addColumn("Contato");

		tblContato = new JTable(modelo);
		scrollPane.setViewportView(tblContato);
		tblContato.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));

		btnRemover = new JButton("");
		btnRemover
				.setIcon(new ImageIcon(
						MeusDados.class
								.getResource("/br/com/easyShop/telas/imagens/aplicacao/Remove.png")));
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRemover.setBounds(581, 171, 35, 33);
		pnlEndereco.add(btnRemover);

		lblImagem.setBorder(null);
		lblImagem.setBounds(684, 41, 160, 180);
		pnlCadastro.add(lblImagem);

		btnEditar = new JButton("Editar");
		btnEditar
				.setIcon(new ImageIcon(
						MeusDados.class
								.getResource("/br/com/easyShop/telas/imagens/aplicacao/edit.png")));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditar.setBounds(684, 324, 160, 41);
		pnlCadastro.add(btnEditar);

		desabilitarCampos();
		preencherDados();

		btnSalvar.addActionListener(new SalvarListener());
		btnCarregarImagem.addActionListener(new Abrir());
		btnEditar.addActionListener(new EditarListener());
		btnRemover.addActionListener(new Remover());
		btnInserirContato.addActionListener(new Inserir());
		
		if(verificar){
			habilitarCampos();
			btnEditar.setEnabled(false);
		}
	}

	private String obtemSexo(int sexo) {
		if (sexo == 0) {
			return "masculino";
		} else {
			return "feminino";
		}
	}

	private class PaisListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pais = (Pais) cboPais.getSelectedItem();

			EstadoService estadoService = new EstadoService();
			List<Estado> estados = estadoService.getEstados(pais);

			cboEstado.removeAllItems();

			for (Estado estado : estados) {
				cboEstado.addItem(estado);
			}
		}
	}

	private class EditarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			habilitarCampos();
		}
	}

	private class EstadoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			estado = (Estado) cboEstado.getSelectedItem();

			CidadeService cidadeService = new CidadeService();
			List<Cidade> cidades = cidadeService.getCidades(estado);
			cboCidade.removeAllItems();

			for (Cidade cidade : cidades) {
				cboCidade.addItem(cidade);
			}
		}
	}

	private class Abrir implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(MeusDados.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				fc.getSelectedFile().toString();

				// *********************************************************************//
				// Salvar imagem na pasta

				try {
					imagem_buffered = null;
					File imagem_file = new File(fc.getSelectedFile().toString());
					imagem_buffered = ImageIO.read(imagem_file);
					ImageIO.write(imagem_buffered, "jpg", new File(
							"Imagens/ImagensUsuario/usuarioTela.jpg"));
					lblImagem.setIcon(new ImageIcon(
							"Imagens/ImagensUsuario/usuarioTela.jpg"));
					caminhoImagem = fc.getSelectedFile().toString();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Arquivo selecionado n�o � uma imagem!");
				}
				// *********************************************************************//

				BufferedImage aux = new BufferedImage(
						lblImagem.getSize().width, lblImagem.getSize().height,
						imagem_buffered.getType());

				Graphics2D g = aux.createGraphics();

				AffineTransform at = AffineTransform.getScaleInstance(
						(double) lblImagem.getSize().width
								/ imagem_buffered.getWidth(),
						(double) lblImagem.getSize().height
								/ imagem_buffered.getHeight());

				g.drawRenderedImage(imagem_buffered, at);

				lblImagem.setIcon(new ImageIcon(aux));
			}
		}
	}

	private class Cancelar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MeusDados.this.dispose();
		}
	}

	private class Inserir implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (txtContato.getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Digite primeiro o valor do Contato.");
			} else {

				Contato contato = new Contato();
				contato.setContato(txtContato.getText());
				contato.setStatus(Constantes.STATUS_ATIVO);
				contato.setTipo(TipoContato.getIndexTipoContato(cboContato
						.getSelectedItem().toString()));

				listaContatos.add(contato);
				modelo.addRow(new Object[] { TipoContato.getNomeTipo(contato.getTipo()),
						contato.getContato() });
				txtContato.setText("");
			}
		}
	}

	private class Remover implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				modelo.removeRow(tblContato.getSelectedRow());
				listaContatos.remove(tblContato.getSelectedRow());
			} catch (Exception se2) {
				JOptionPane.showMessageDialog(null,
						"Selecione o Contado que deseja remover da tabela.");
			}
		}
	}

	private class Limpar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtUsuario.setText("");
			txtApelido.setText("");
			calendarDataDeNasc.setCalendar(null);
			txtCPF.setText("");
			txtRG.setText("");
			cboSexo.setSelectedIndex(0);
			txtLogradouro.setText("");
			txtCEP.setText("");
			txtBairro.setText("");
			txtComplemento.setText("");
			cboTipo.setSelectedIndex(0);
			txtContato.setText("");
			txtNumero.setText("");
			txtRazao.setText("");
			txtFantasia.setText("");
			txtCNPJ.setText("");
			txtInscricaoEstadual.setText("");
			txtLogin.setText("");
			txtPassword.setText("");

			int qtd = modelo.getRowCount(), i;
			for (i = 0; i < qtd; i++) {
				modelo.removeRow(0);
			}
		}
	}

	private void preencherPais() {
		PaisService paisService = new PaisService();
		List<Pais> paises = paisService.getPaises();

		for (Pais pais : paises) {
			cboPais.addItem(pais);
		}
	}

	private void preencherDados() {

		if (usuario.getPessoa().getPessoaFisica() != null) {
				tabbedPane.setSelectedIndex(0);
				txtUsuario.setText(usuario.getPessoa().getPessoaFisica()
						.getNome());
				txtApelido.setText(usuario.getPessoa().getPessoaFisica()
						.getApelido());

				Data data = new Data();
				data = usuario.getPessoa().getPessoaFisica().getDataNascimento();

				Date dataNascimento = new Date();
				dataNascimento = data.getTime();

				calendarDataDeNasc.setDate(dataNascimento);
				txtCPF.setText(usuario.getPessoa().getPessoaFisica().getCpf());
				txtRG.setText(usuario.getPessoa().getPessoaFisica().getRg());

				if (usuario.getPessoa().getPessoaFisica().getSexo()
						.equals("masculino")) {
					cboSexo.addItem("Masculino");
				} else {
					cboSexo.addItem("Feminino");
				}
			}
			else{
				tabbedPane.setSelectedIndex(1);
				txtCNPJ.setText(usuario.getPessoa().getPessoaJuridica().getCnpj());
				txtInscricaoEstadual.setText(usuario.getPessoa()
						.getPessoaJuridica().getInscricaoEstadual());
				txtFantasia.setText(usuario.getPessoa().getPessoaJuridica()
						.getNomeFantasia());
				txtRazao.setText(usuario.getPessoa().getPessoaJuridica()
						.getRazaoSocial());
			}

		EnderecoService enderecoService = new EnderecoService();
		Endereco endereco = new Endereco();
		endereco = enderecoService.getEnderecoPessoa(usuario.getPessoa());

		txtLogradouro.setText(endereco.getLogradouro());
		txtBairro.setText(endereco.getBairro());
		txtNumero.setText(endereco.getNumero());
		txtComplemento.setText(endereco.getComplemento());
		txtCEP.setText(endereco.getCep());

		cboCidade.addItem(endereco.getCidade());
		cboEstado.addItem(endereco.getCidade().getEstado());
		cboPais.addItem(endereco.getCidade().getEstado().getPais());

		cboTipo.addItem(TipoEndereco.getNomeTipo(endereco.getTipo()));

		ContatoService contatoService = new ContatoService();
		List<Contato> contatos = new ArrayList<Contato>();
		contatos = contatoService.getContatos(usuario.getPessoa());

		for (Contato contato : contatos) {
			modelo.addRow(new Object[] {
					TipoContato.getNomeTipo(contato.getTipo()),
					contato.getContato() });
		}

		txtLogin.setText(usuario.getLogin());
		txtPassword.setText(usuario.getSenha());

		carregarImagem();
	}

	private void carregarImagem() {

		try {
			File imagem_file = new File("Imagens/ImagensUsuario/usuario"
					+ usuario.getPkUsuario() + ".jpg");
			imagem_buffered = null;

			imagem_buffered = ImageIO.read(imagem_file);
			
			BufferedImage aux = new BufferedImage(lblImagem.getSize().width,
					lblImagem.getSize().height, imagem_buffered.getType());
			Graphics2D g = aux.createGraphics();

			AffineTransform at = AffineTransform.getScaleInstance(
					(double) lblImagem.getSize().width
							/ imagem_buffered.getWidth(),
					(double) lblImagem.getSize().height
							/ imagem_buffered.getHeight());
			g.drawRenderedImage(imagem_buffered, at);

			lblImagem.setIcon(new ImageIcon(aux));

		} catch (Exception e) {
			if (usuario.getPessoa().getPessoaFisica().getSexo()
					.equals("masculino")) {
				lblImagem.setIcon(new ImageIcon(
						"Imagens/Padrao/padraoMasculino.png"));
			} else {
				lblImagem.setIcon(new ImageIcon(
						"Imagens/Padrao/padraoFeminino.png"));
			}
		}
	}

	private class SalvarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			salvarDados();
		}
	}

	@SuppressWarnings("deprecation")
	private void salvarDados() {
		usuario.getPessoa().setStatus(Constantes.STATUS_ATIVO);
		usuario.getPessoa().setClientes(null);

		if (tabbedPane.getSelectedIndex() == 0) {
			this.usuario.getPessoa().getPessoaFisica()
					.setNome(txtUsuario.getText());
			this.usuario.getPessoa().getPessoaFisica()
					.setApelido(txtApelido.getText());
			this.usuario.getPessoa().getPessoaFisica().setCpf(txtCPF.getText());
			this.usuario.getPessoa().getPessoaFisica()
					.setDataNascimento(new Data(calendarDataDeNasc.getDate()));
			this.usuario.getPessoa().getPessoaFisica().setRg(txtRG.getText());
			this.usuario.getPessoa().getPessoaFisica()
					.setSexo(obtemSexo(cboSexo.getSelectedIndex()));
			this.usuario.getPessoa().getPessoaFisica()
					.setStatus(Constantes.STATUS_ATIVO);
		} else {
			this.usuario.getPessoa().getPessoaJuridica()
					.setCnpj(txtCNPJ.getText());
			this.usuario.getPessoa().getPessoaJuridica()
					.setInscricaoEstadual(txtInscricaoEstadual.getText());
			this.usuario.getPessoa().getPessoaJuridica()
					.setNomeFantasia(txtFantasia.getText());
			this.usuario.getPessoa().getPessoaJuridica()
					.setRazaoSocial(txtRazao.getText());
			this.usuario.getPessoa().getPessoaJuridica()
					.setStatus(Constantes.STATUS_ATIVO);
		}

		pais = (Pais) cboPais.getSelectedItem();
		estado = (Estado) cboEstado.getSelectedItem();
		estado.setPais(pais);
		cidade = (Cidade) cboCidade.getSelectedItem();
		cidade.setEstado(estado);

		Endereco endereco = new Endereco();
		EnderecoService enderecoServico = new EnderecoService();
		endereco = enderecoServico.getEnderecoPessoa(this.usuario.getPessoa());

		Pessoa pessoa = new Pessoa();
		PessoaService pessoaService = new PessoaService();
		pessoa = pessoaService.getPessoa(usuario.getPessoa().getPessoaFisica(),-1);
		pessoa.setPessoaFisica(usuario.getPessoa().getPessoaFisica());

		endereco.setBairro(txtBairro.getText());
		endereco.setCep(txtCEP.getText());
		endereco.setComplemento(txtComplemento.getText());
		endereco.setLogradouro(txtLogradouro.getText());
		endereco.setStatus(Constantes.STATUS_ATIVO);
		endereco.setTipo(TipoEndereco.getIndexTipoEndereco(cboTipo
				.getSelectedItem().toString()));
		endereco.setNumero(txtNumero.getText());

		this.usuario.setLogin(txtLogin.getText());
		this.usuario.setSenha(txtPassword.getText());
		this.usuario.setStatus(Constantes.STATUS_ATIVO);

		if (tabbedPane.getSelectedIndex() == 0) {
			PessoaFisicaService pessoaFisicaService = new PessoaFisicaService();
			pessoaFisicaService.atualizar(this.usuario.getPessoa().getPessoaFisica());
		} else {
			PessoaJuridicaService pessoaJuridicaService = new PessoaJuridicaService();
			pessoaJuridicaService.atualizar(this.usuario.getPessoa().getPessoaJuridica());
		}

		pessoaService.atulizar(pessoa);

		for (Contato contatoAdd : listaContatos) {
			contato = contatoAdd;
			contato.setPessoa(pessoa);
			ContatoService contatoService = new ContatoService();
			contatoService.salvarContato(contato);
		}

		UsuarioService usuarioService = new UsuarioService();
		usuarioService.salvar(this.usuario);

		enderecoServico.atualizar(endereco);

		try {
			// *********************************************************************//
			// Salvar imagem na pasta
			File imagem_file = new File(caminhoImagem);
			BufferedImage imagem_buffered = null;
			try {
				imagem_buffered = ImageIO.read(imagem_file);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				ImageIO.write(
						imagem_buffered,
						"jpg",
						new File("Imagens/ImagensUsuario/usuario"
								+ usuario.getPkUsuario() + ".jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// *********************************************************************//
		} catch (Exception e) {
			e.getStackTrace();
		}

		JOptionPane.showMessageDialog(null,
				"Dado(s) atualizado(s) com sucesso!!");
	}

	private void habilitarCampos() {

		txtUsuario.setEnabled(true);
		txtApelido.setEnabled(true);
		calendarDataDeNasc.setEnabled(true);
		txtCPF.setEnabled(true);
		txtRG.setEnabled(true);
		txtLogradouro.setEnabled(true);
		txtCEP.setEnabled(true);
		txtBairro.setEnabled(true);
		txtComplemento.setEnabled(true);
		txtContato.setEnabled(true);
		txtNumero.setEnabled(true);
		txtRazao.setEnabled(true);
		txtFantasia.setEnabled(true);
		txtCNPJ.setEnabled(true);
		txtInscricaoEstadual.setEnabled(true);
		txtLogin.setEnabled(true);
		txtPassword.setEnabled(true);

		cboTipo.setEnabled(true);
		cboCidade.setEnabled(true);
		cboEstado.setEnabled(true);
		cboPais.setEnabled(true);
		cboContato.setEnabled(true);
		cboSexo.setEnabled(true);

		btnCarregarImagem.setEnabled(true);
		btnInserirContato.setEnabled(true);
		btnLimpar.setEnabled(true);
		btnRemover.setEnabled(true);
		btnSalvar.setEnabled(true);

		tipoEndereco = cboTipo.getSelectedItem().toString();
		cboTipo.removeAllItems();
		preencherTipoEndereco();
		cboTipo.setSelectedIndex(TipoEndereco.posicaoCombo(tipoEndereco));

		cboPais.removeAllItems();
		cboCidade.removeAllItems();
		cboEstado.removeAllItems();

		preencherPais();

		cboPais.addActionListener(new PaisListener());
		cboEstado.addActionListener(new EstadoListener());
	}

	private void desabilitarCampos() {

		txtUsuario.setEnabled(false);
		txtApelido.setEnabled(false);
		calendarDataDeNasc.setEnabled(false);
		txtCPF.setEnabled(false);
		txtRG.setEnabled(false);
		txtLogradouro.setEnabled(false);
		txtCEP.setEnabled(false);
		txtBairro.setEnabled(false);
		txtComplemento.setEnabled(false);
		txtContato.setEnabled(false);
		txtNumero.setEnabled(false);
		txtRazao.setEnabled(false);
		txtFantasia.setEnabled(false);
		txtCNPJ.setEnabled(false);
		txtInscricaoEstadual.setEnabled(false);
		txtLogin.setEnabled(false);
		txtPassword.setEnabled(false);

		cboTipo.setEnabled(false);
		cboCidade.setEnabled(false);
		cboEstado.setEnabled(false);
		cboPais.setEnabled(false);
		cboContato.setEnabled(false);
		cboSexo.setEnabled(false);

		btnCarregarImagem.setEnabled(false);
		btnInserirContato.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnRemover.setEnabled(false);
		btnSalvar.setEnabled(false);
	}

	private void preencherTipoEndereco() {
		cboTipo.addItem(Constantes.RESIDENCIA);
		cboTipo.addItem(Constantes.APARTAMENTO);
		cboTipo.addItem(Constantes.COMERCIAL);
	}
}
