package br.com.easyShop.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.service.UsuarioService;
import br.com.easyShop.telas.cadastros.CadastroDeUsuario;

import java.awt.Font;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private JPasswordField passSenha;
	private JTextField txtUsuario = new JTextField();
	private JPanel panelLogin;
	private JButton btnEntrar = new JButton("Entrar");
	private Long id;
	private JButton btnCancelar = new JButton("Cancelar");
	private JButton btnRegistrar = new JButton("Cadastrar");
	
	public Login() {
		super("Login");
		
		btnEntrar.addActionListener(new VerificarLogin());
		btnCancelar.addActionListener(new Cancelar());
		btnRegistrar.addActionListener(new Registrar());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 846, 600);
		panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(538, 249, 63, 14);
		panelLogin.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSenha.setBounds(538, 281, 46, 14);
		panelLogin.add(lblSenha);
		
		passSenha = new JPasswordField();
		passSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passSenha.setBounds(594, 278, 119, 20);
		panelLogin.add(passSenha);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsuario.setBounds(595, 246, 118, 20);
		panelLogin.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnEntrar.setBounds(481, 320, 99, 23);
		panelLogin.add(btnEntrar);
		
		btnCancelar.setBounds(707, 320, 101, 23);
		panelLogin.add(btnCancelar);
		
		btnRegistrar.setBounds(592, 320, 105, 23);
		panelLogin.add(btnRegistrar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/fundo_easyShop.jpg")));
		label.setBounds(-389, -27, 1235, 589);
		panelLogin.add(label);
	}
	
	@SuppressWarnings("deprecation")
	public String getPassSenha() {
		return passSenha.getText();
	}

	public void setPassSenha(String passSenha) {
		this.passSenha.setText(passSenha);
	}

	public String getTxtUsuario() {
		return txtUsuario.getText();
	}

	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario.setText(txtUsuario);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private class VerificarLogin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			usuario = new Usuario();
			UsuarioService usuarioService = new UsuarioService();

			usuario = usuarioService.getUsuario(getTxtUsuario(), getPassSenha());
				
			if(usuario!=null){
				setVisible(false);
				Janela principal = new Janela(usuario);
				principal.setLocationRelativeTo(null);  
				principal.setVisible(true);
			}
			else{
				setTxtUsuario("");
				setPassSenha("");
				JOptionPane.showMessageDialog(null, "Usuário ou senha incorreta!");
			}
		}
	}
	
	private class Cancelar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0); 		
		}
	}
	
	private class Registrar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CadastroDeUsuario cadastroDeUsuario = new CadastroDeUsuario();
			cadastroDeUsuario.setLocationRelativeTo(null);  
			cadastroDeUsuario.setVisible(true);			
		}
	}
	
	public boolean verificar(){
		
		return false;
	}
}
