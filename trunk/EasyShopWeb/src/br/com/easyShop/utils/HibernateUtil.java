package br.com.easyShop.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.easyShop.model.*;


public class HibernateUtil {

	private static SessionFactory factory;

	static {

		try 
		{
			AnnotationConfiguration cfg = new AnnotationConfiguration();
			cfg.addAnnotatedClass(PessoaFisica.class);
			cfg.addAnnotatedClass(PessoaJuridica.class);
			cfg.addAnnotatedClass(Pais.class);
			cfg.addAnnotatedClass(Estado.class);
			cfg.addAnnotatedClass(Cidade.class);
			cfg.addAnnotatedClass(Endereco.class);
			cfg.addAnnotatedClass(Contato.class);
			cfg.addAnnotatedClass(Tela.class);
			cfg.addAnnotatedClass(UsuarioTela.class);			
			cfg.addAnnotatedClass(Usuario.class);			
			cfg.addAnnotatedClass(Pessoa.class);			
			cfg.addAnnotatedClass(Cliente.class);
			cfg.addAnnotatedClass(Preferencia.class);
			cfg.addAnnotatedClass(Desejo.class);
			cfg.addAnnotatedClass(DesejoProduto.class);
			cfg.addAnnotatedClass(PerfilPagamento.class);
			cfg.addAnnotatedClass(Categoria.class);
			cfg.addAnnotatedClass(Marca.class);
			cfg.addAnnotatedClass(Produto.class);
			cfg.addAnnotatedClass(CarrinhoProduto.class);
			cfg.addAnnotatedClass(Carrinho.class);
			cfg.addAnnotatedClass(PedidoProduto.class);
			cfg.addAnnotatedClass(Pedido.class);
			cfg.addAnnotatedClass(Preferencia.class);
			cfg.addAnnotatedClass(TipoContato.class);
			cfg.addAnnotatedClass(TipoPermissao.class);
			
			factory = cfg.buildSessionFactory();

		}
		catch (Exception e) {
			factory = null;
			e.printStackTrace();
		}
	}

	public static Session getSession() {
		return factory.openSession();
	}
}
