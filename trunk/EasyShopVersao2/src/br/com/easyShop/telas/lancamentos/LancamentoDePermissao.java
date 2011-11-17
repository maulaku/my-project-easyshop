package br.com.easyShop.telas.lancamentos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.easyShop.model.Tela;
import br.com.easyShop.model.TipoPermissao;
import br.com.easyShop.model.Usuario;
import br.com.easyShop.model.UsuarioTela;
import br.com.easyShop.service.TelaService;
import br.com.easyShop.service.TipoPermissaoService;
import br.com.easyShop.service.UsuarioService;
import br.com.easyShop.service.UsuarioTelaService;

public class LancamentoDePermissao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblTelas;
	private JTable tblPermissao;
	private JComboBox cboUsuarios = new JComboBox();
	private List<Usuario> usuarios;
	private final DefaultTableModel modelo = new DefaultTableModel();
	private final DefaultTableModel modeloPermissao = new DefaultTableModel();
	private JButton btnAdicionar = new JButton("");
	private JComboBox cboTipoDePermissao = new JComboBox();
	private JButton btnRemover = new JButton("");
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnCancelar = new JButton("Cancelar");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LancamentoDePermissao frame = new LancamentoDePermissao();
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
	public LancamentoDePermissao() {
		cboUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 17));
		cboUsuarios.addActionListener(new PreencherTabelaTela());
		btnAdicionar.addActionListener(new Adicionar());
		btnRemover.addActionListener(new Remover());
		btnCancelar.addActionListener(new Cancelar());
		btnSalvar.setIcon(new ImageIcon(LancamentoDePermissao.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Save.png")));
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSalvar.addActionListener(new Salvar());
		
		setTitle("Lan\u00E7amento de Permiss\u00E3o");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 909, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnSalvar.setBounds(430, 449, 124, 33);
		contentPane.add(btnSalvar);
		
		btnCancelar.setIcon(new ImageIcon(LancamentoDePermissao.class.getResource("/br/com/easyShop/telas/imagens/aplicacao/Close.png")));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCancelar.setBounds(284, 449, 133, 33);
		contentPane.add(btnCancelar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(40, 34, 240, 396);
		contentPane.add(scrollPane);
		
		modelo.addColumn("Telas");
		
		tblTelas = new JTable(modelo);		
		tblTelas.setRowHeight(30);
		tblTelas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(tblTelas);
		btnAdicionar.setToolTipText("Adicionar Permiss\u00E3o");
		btnAdicionar.setIcon(new ImageIcon(LancamentoDePermissao.class.getResource("/br/com/easyShop/telas/imagens/lancamentos/adicionar.gif")));
		btnAdicionar.setBounds(377, 188, 40, 33);
		contentPane.add(btnAdicionar);
		
		btnRemover.setToolTipText("Remover Permiss\u00E3o");
		btnRemover.setIcon(new ImageIcon(LancamentoDePermissao.class.getResource("/br/com/easyShop/telas/imagens/lancamentos/remover.gif")));
		btnRemover.setBounds(377, 233, 40, 33);
		contentPane.add(btnRemover);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(526, 34, 329, 396);
		contentPane.add(scrollPane_1);
		
		modeloPermissao.addColumn("Tela");
		modeloPermissao.addColumn("Permissão");
		
		tblPermissao = new JTable(modeloPermissao);
		tblPermissao.setFont(new Font("Tahoma", Font.PLAIN, 17));
		tblPermissao.setRowHeight(30);
		scrollPane_1.setViewportView(tblPermissao);
		cboUsuarios.setToolTipText("Usu\u00E1rio");
		cboUsuarios.setBounds(316, 75, 169, 26);
		contentPane.add(cboUsuarios);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(30, 466, 842, 12);
        contentPane.add(separator_1);
        cboTipoDePermissao.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        cboTipoDePermissao.setToolTipText("Funcion\u00E1rio");
        cboTipoDePermissao.setBounds(316, 351, 169, 26);
       
        contentPane.add(cboTipoDePermissao);
        
        preencherComboUsuario();
        preencherComboTipo();
	}
	
	private void preencherComboUsuario(){		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarios = usuarioService.getUsuarios();
		for(Usuario usuario : usuarios){
			cboUsuarios.addItem(usuario);
		}
	}
	
	private void preencherComboTipo(){		
		TipoPermissaoService tipoPermissaoService = new TipoPermissaoService();
		List<TipoPermissao> tipoPermissoes;
		
		tipoPermissoes = tipoPermissaoService.getTipoPermissao();
		for(TipoPermissao tipoPermissao : tipoPermissoes){
			cboTipoDePermissao.addItem(tipoPermissao);
		}
	}
	
	private class PreencherTabelaTela implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int qtd = modelo.getRowCount(), i, adiciona;
			for(i=0;i<qtd;i++){
				modelo.removeRow(0);
			}
			
			qtd = modeloPermissao.getRowCount();
			for(i=0;i<qtd;i++){
				modeloPermissao.removeRow(0);
			}
				
			UsuarioTelaService usuarioTelaService = new UsuarioTelaService();
			List<UsuarioTela> usuarioTelas;
			usuarioTelas = usuarioTelaService.getUsuarioTelas((Usuario)cboUsuarios.getSelectedItem());
			
			for(UsuarioTela usuarioTela : usuarioTelas){
				modeloPermissao.addRow(new Object[]{usuarioTela,usuarioTela.getTipoPermissao().getNome()});
			}
			
			TelaService telaService = new TelaService();
			List<Tela> telas;
			telas = telaService.getTelas();
			
			for(Tela tela : telas){
				adiciona = 0;
				for(UsuarioTela usuarioTela : usuarioTelas){
					if(usuarioTela.getTela().getPkTela()==tela.getPkTela()){
						adiciona = 1;
					}
				}
				if(adiciona==0){
					modelo.addRow(new Object[]{tela});
				}
			}
		}
	}
	
	private class Adicionar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try{
				UsuarioTela enviar = new UsuarioTela();
				enviar.setTela((Tela)modelo.getValueAt(tblTelas.getSelectedRow(),0));
				enviar.setUsuario((Usuario)cboUsuarios.getSelectedItem());
				
				if(cboTipoDePermissao.getSelectedItem().equals("Leitura")){ //Isso vai mudar, pois no banco est� como o Tipo em inteiro.
					enviar.setTipoPermissao((TipoPermissao) cboTipoDePermissao.getSelectedItem());
				}else{
					enviar.setTipoPermissao((TipoPermissao) cboTipoDePermissao.getSelectedItem());
				}
				
				modelo.removeRow(tblTelas.getSelectedRow());
				modeloPermissao.addRow(new Object[]{enviar,cboTipoDePermissao.getSelectedItem()});
			}catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Selecione a Tela que deseja adcionar à lista de Permissões");
			}
		}
	}
	
	private class Remover implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try{
				Tela remover = new Tela();
				UsuarioTela usuarioTela = new UsuarioTela();
				
				usuarioTela = (UsuarioTela) modeloPermissao.getValueAt(tblPermissao.getSelectedRow(),0);
				remover = usuarioTela.getTela();
				modeloPermissao.removeRow(tblPermissao.getSelectedRow());
				modelo.addRow(new Object[]{remover});	
			}catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Selecione a Tela que deseja remover da lista de Permissões");
			}
		}
	}
	
	private class Salvar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			UsuarioTelaService usuarioTelaService = new UsuarioTelaService();
			List<UsuarioTela> usuarioTelas;
			usuarioTelas = usuarioTelaService.getUsuarioTelas((Usuario)cboUsuarios.getSelectedItem());
			
			if(usuarioTelas.size()==0){
				try{
					UsuarioTela adicionar = new UsuarioTela();
					//UsuarioTelaService usuarioTelaService = new UsuarioTelaService();
					
					int qtd = modeloPermissao.getRowCount(), i;
					for(i=0;i<qtd;i++){
						adicionar = (UsuarioTela) modeloPermissao.getValueAt(i,0);
						usuarioTelaService.inserirUsuarioTela(adicionar);
					}
					
					JOptionPane.showMessageDialog(null, "Permissão(ões) inserida(s) com sucesso!!");
				}catch (Exception e1) {
					// TODO: handle exception
				}
			}
			else{
				for(UsuarioTela usuarioTela : usuarioTelas){
					usuarioTelaService.excluirUsuarioTela(usuarioTela);
				}
				
				try{
					UsuarioTela adicionar = new UsuarioTela();
					//UsuarioTelaService usuarioTelaService = new UsuarioTelaService();
					
					int qtd = modeloPermissao.getRowCount(), i;
					for(i=0;i<qtd;i++){
						adicionar = (UsuarioTela) modeloPermissao.getValueAt(i,0);
						usuarioTelaService.inserirUsuarioTela(adicionar);
					}
					
					JOptionPane.showMessageDialog(null, "Permissão(ões) alteradas(s) com sucesso!!");
				}catch (Exception e1) {
					// TODO: handle exception
				}
			}			
		}
	}
	
	private class Cancelar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			LancamentoDePermissao.this.dispose();
		}
	}
}
