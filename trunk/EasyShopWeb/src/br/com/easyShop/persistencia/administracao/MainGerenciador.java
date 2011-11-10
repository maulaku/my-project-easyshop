package br.com.easyShop.persistencia.administracao;

import br.com.easyShop.configuracoes.Configuracoes;
import br.com.easyShop.persistencia.administracao.actionScript.PathASBase;
import br.com.easyShop.persistencia.administracao.comparaBases.ComparaBases;

public class MainGerenciador
{
	public static void main(String[] args)
	{
		try
		{
			//Carrega as configuracoes do sistema
			Configuracoes.carregar(Configuracoes.class.getResourceAsStream("configuracoes.properties"), Configuracoes.class.getResourceAsStream("log4j.properties"));

			//Configuracoes para Gerar OMs Java
			Gerenciador.arquivoERM = "C:/EasyShop/siteCompras/EasyShopVAI/src/br/com/easyShop/persistencia/modelagem.erm";
			Gerenciador.pathJavaOMs = "C:/EasyShop/siteCompras/EasyShopVAI/src/br/com/easyShop/model";

			//Configuracoes para Gerar OMs Flex
			Gerenciador.pathFlexOMs = "C:/EasyShop/siteCompras/EasyShopVAI/flex_src/br/com/easyShop/model";

			//Arquivo .acrtionScriptProperties
			Gerenciador.pathActionScriptProperties = "C:/EasyShop/siteCompras/EasyShopVAI/.actionScriptProperties";
			Gerenciador.pathModulos.add(new PathASBase("C:/EasyShop/siteCompras/EasyShopVAI", null));

			//Gerar Tabelas
			Gerenciador.baseURL = Configuracoes.propriedades.get("baseUrl");
			Gerenciador.baseBase = Configuracoes.propriedades.get("baseBase");
			Gerenciador.basePorta = Configuracoes.propriedades.getInt("basePorta");
			Gerenciador.baseLogin = Configuracoes.propriedades.get("baseLogin");
			Gerenciador.baseSenha = Configuracoes.propriedades.get("baseSenha");

			Gerenciador.pathOMs.add("C:/EasyShop/siteCompras/EasyShopVAI/src/br/com/easyShop/model");

			//Configuracoes o comparador de Bases
			ComparaBases.baseURLAtual = "localhost";
			ComparaBases.baseLoginAtual = "root";
			ComparaBases.baseSenhaAtual = "root";
			ComparaBases.basePortaAtual = 3306;
			
			ComparaBases.baseURLNova = "localhost";
			ComparaBases.baseLoginNova = "root";
			ComparaBases.baseSenhaNova = "root";
			ComparaBases.basePortaNova = 3306;
			
			Gerenciador.getInstance(args, "EasyShop", true).abrir();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
