package br.com.easyShop.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.service.UsuarioService;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private JPasswordField passSenha;
	private JTextField txtUsuario;
	private JPanel panelLogin;
	private JButton btnEntrar = new JButton("Entrar");
	private Long id;
	private JButton btnCancelar = new JButton("Cancelar");
	
	
	/**
	 * Create the frame.
	 */
	public Login() {
		super("Login");
		
		btnEntrar.addActionListener(new VerificarLogin());
		btnCancelar.addActionListener(new Cancelar());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio");
		lblUsuario.setBounds(503, 258, 46, 14);
		panelLogin.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(503, 290, 46, 14);
		panelLogin.add(lblSenha);
		
		passSenha = new JPasswordField();
		passSenha.setBounds(552, 287, 102, 20);
		panelLogin.add(passSenha);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(552, 255, 102, 20);
		panelLogin.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnEntrar.setBounds(485, 327, 89, 23);
		panelLogin.add(btnEntrar);
		
		btnCancelar.setBounds(592, 327, 89, 23);
		panelLogin.add(btnCancelar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/br/com/easyShop/telas/imagens/aplica\u00E7\u00E3o/fundo_easyShop.jpg")));
		label.setBounds(-382, -16, 1178, 578);
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
			
			try {
				usuario = usuarioService.getUsuario(getTxtUsuario());
			} catch (Exception e1) {
				// TODO: handle exception
				setTxtUsuario("");
				setPassSenha("");
				JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto!");
			}
			
				if(usuario.getSenha().equals(getPassSenha())){
					setVisible(false);
					Janela principal = new Janela(usuario);
					principal.setLocationRelativeTo(null);  
					principal.setVisible(true);
				}
				else{
					setTxtUsuario("");
					setPassSenha("");
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto!");
				}		
					
		}
	}
	
	private class Cancelar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0); 		
		}
	}
	
	public boolean verificar(){
		
		return false;
	}
}
