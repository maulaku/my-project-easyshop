package br.com.easyShop.persistencia.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import logs.Logs;

import org.apache.log4j.Level;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import br.com.easyShop.persistencia.administracao.geraOMJava.GeraOM;

/**
 * Esta classe e responsavel por fornecer sessions para o hibernate (fabrica de sessions)
 */
public class HibernateFactory 
{
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
     * Cria as tabelas do banco de dados, caso elas ja existam elas serao apagadas e criadas novamente
     * @param hibernateConfigFile endereco e nome do arquivo com as configuracoes do hibernate para acessar o banco de dados
     */
	public static void criarTabelas(String baseURL, String baseBase, int basePorta, String baseLogin, String baseSenha, List<String> pathOMs) throws Exception
	{
		Logs.getLogger().setLevel(Level.ERROR);
		
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.url", "jdbc:mysql://" + baseURL + ":" + basePorta + "/" + baseBase); //?autoReconnect=true
		properties.setProperty("hibernate.connection.username", baseLogin);
		properties.setProperty("hibernate.connection.password", baseSenha);
		cfg.addProperties(properties);

		for(int i=0; i<pathOMs.size(); i++)
		{
			List<Class<?>> todasClasses = new ArrayList<Class<?>>();
			List<Class<?>> classes = obterClasses(new File(pathOMs.get(i)), GeraOM.obterPacote(pathOMs.get(i)));
			for(int j=0; j<classes.size(); j++)
			{
				if(contem(classes.get(j), todasClasses)==false)
				{
					todasClasses.add(classes.get(j));
					cfg.addAnnotatedClass(classes.get(j));
				}
			}
		}

		new SchemaExport(cfg).create(true, true);
		Logs.getLogger().setLevel(Level.INFO);
	}
	
	/**
	 * Verifica se a lista de classes contem uma determinada classe comparando pelo nome das classes
	 * @param classe procurada
	 * @param classes lista de classes
	 * @return true caso a classe exista na lista e false caso contrario
	 */
	private static boolean contem(Class<?> classe, List<Class<?>> classes)
	{
		String nomeClasse = classe.getSimpleName();
		for(int i=0; i<classes.size(); i++)
		{
			if(nomeClasse.equalsIgnoreCase(classes.get(i).getSimpleName()))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metodo que obtem todas as classes (.class e .java) e retorna uma lista de class
	 * Este metodo é recursivo e procura tambem em sub-diretorios
	 * @param diretorio dos arquivos
	 * @param pacote dos arquivos no projeto
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> obterClasses(File diretorio, String pacote) throws ClassNotFoundException
	{
        List<Class<?>> classes = new ArrayList<Class<?>>();
        
        if(!diretorio.exists())
        {
            return classes;
        }
        
        File[] files = diretorio.listFiles();
        for (File file : files)
        {
            if(file.isDirectory())
            {
                classes.addAll(obterClasses(file, pacote + "." + file.getName()));
            }
            else
            {
            	String nome = file.getName();
            	if(nome.endsWith(".class") || nome.endsWith(".java"))
            	{
            		nome = nome.replaceAll(".java", "");
            		nome = nome.replaceAll(".class", "");
                	classes.add(Class.forName(pacote + "." + nome));
            	}
            }
        }
        return classes;
    }
}
