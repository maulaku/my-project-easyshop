package br.com.easyShop.service;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.persistencia.DAO.ClienteDAO;
import br.com.easyShop.persistencia.DAO.PessoaFisicaDAO;
import br.com.easyShop.persistencia.DAO.PessoaJuridicaDAO;

public class ClienteService {
	
	public void inserirCliente(Cliente cliente) {
		try {
			new ClienteDAO().inserir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResultJava getCliente(String nome, String senha) {
		try
		{
			return new ResultJava(new ClienteDAO().getClienteNome(nome, senha, 3));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar clientes!\n" + e }));
		}
	}
	
	public List<Cliente> getClientes() {
		try
		{
			return new ClienteDAO().getClientes(3);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultJava salvarCliente(Cliente cliente) {
		try
		{
			int i;
			
			if(cliente.getPessoa().getPessoaFisica()!=null){
				PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
				pessoaFisicaDAO.salvar(cliente.getPessoa().getPessoaFisica());	
			}else{
				PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
				pessoaJuridicaDAO.salvar(cliente.getPessoa().getPessoaJuridica());					
			}
			
			PessoaService pessoaService = new PessoaService();
			pessoaService.inserirPessoa(cliente.getPessoa());

			EnderecoService enderecoService = new EnderecoService();
			for(i=0;i<cliente.getPessoa().getEnderecos().size();i++){
				enderecoService.salvar(cliente.getPessoa().getEnderecos().get(i));
			}
			
			ContatoService contatoService = new ContatoService();
			for(i=0;i<cliente.getPessoa().getContatos().size();i++){
				contatoService.salvarContato(cliente.getPessoa().getContatos().get(i));
			}
			
			UsuarioService usuarioService = new UsuarioService();
			for(i=0;i<cliente.getPessoa().getUsuarios().size();i++){
				usuarioService.salvar(cliente.getPessoa().getUsuarios().get(i));
			}
			
			new ClienteDAO().salvar(cliente);
			
			File imagem_file = new File("C:/EasyShop/siteCompras/EasyShop/flex_src/br/com/easyShop/imagens/aplicacao/cliente/CadastroDeCliente.jpg");
			BufferedImage imagem_buffered = null;				
			imagem_buffered = ImageIO.read( imagem_file );					
			ImageIO.write(imagem_buffered, "jpg", new File("C:/EasyShop/siteCompras/EasyShop/flex_src/br/com/easyShop/imagens/aplicacao/cliente/"+cliente.getPkCliente()+".jpg"));
			ImageIO.write(imagem_buffered, "jpg", new File("C:/EasyShop/siteCompras/EasyShop/bin-debug/br/com/easyShop/imagens/aplicacao/cliente/"+cliente.getPkCliente()+".jpg"));
			
			return new ResultJava(null);
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao cadastrar clientes!\n" + e }));
		}
	}
	
	public ResultJava salvarImagem(byte[] im){
		try {
			File imagem1 = new File("C:/EasyShop/siteCompras/EasyShop/flex_src/br/com/easyShop/imagens/aplicacao/cliente/CadastroDeCliente.jpg");
			imagem1.createNewFile(); 
	         
			FileOutputStream outPut = new FileOutputStream(imagem1);  
			outPut.write(im); 
			outPut.flush();  
			outPut.close(); 
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Não foi possivel salvar o arquivo");
		}
			 
		return new ResultJava(null);
	}
	
	public ResultJava recuperaImagem(Cliente cliente) {  
		ImageIcon imagem_file = new ImageIcon("C:/EasyShop/siteCompras/EasyShop/flex_src/br/com/easyShop/imagens/aplicacao/cliente/CadastroDeCliente.jpg");
		Image image = imagem_file.getImage();	
		
		BufferedImage bi = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_RGB);  
	    Graphics bg = bi.getGraphics();  
	    bg.drawImage(image, 0, 0, null);  
	    bg.dispose();  
	      
	    ByteArrayOutputStream buff = new ByteArrayOutputStream();         
	    try {    
	        ImageIO.write(bi, "JPG", buff);    
	    } catch (IOException e) {    
	        e.printStackTrace();    
	    }    
	    return new ResultJava(buff.toByteArray());
	    
//	    
//	    InputStream is = null;  
//	    byte[] buffer = null;  
//	    try {  
//	        is = new FileInputStream(imagem_file);  
//	        buffer = new byte[is.available()];  
//	        is.read(buffer);  
//	        is.close();  
//	    } catch (FileNotFoundException e) {  
//	        e.printStackTrace();  
//	    } catch (IOException e) {  
//	        e.printStackTrace();  
//	    }  
//	    return new ResultJava(buffer);
	}  
}
