package br.com.easyShop.persistencia.administracao.actionScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.ArquivoUtil;

public class GeraActionScript
{
	/**
	 * Metodo que regera um arquivo de actionScriptPorpoerties a partir de um ja existente
	 * Este metodo remove todas as entradas "Module" do xml e as adiciona novamente de acordo com a lista de arquivos do parametro
	 * @param modulos lista de arquivos de modulo 
	 * @param arquivoActionScript arquivo a ser regerado
	 * @param pathAplication endereco do aplication, exemplo flex_src/br/com/mresolucoes/aplicacao/MainCemara.mxml
	 * @return string com o conteudo do arquivo recriado
	 * @throws Exception
	 */
	public static String gerarActionScript(List<ArquivoModulo> modulos, File arquivoActionScript, String pathAplication) throws Exception
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		FileInputStream fileInputStream = new FileInputStream(arquivoActionScript);
		Document document = documentBuilder.parse(fileInputStream);
		Element documentElement = document.getDocumentElement();
		
		//Removendo os Modulos existentes
		Node node = documentElement.getElementsByTagName("modules").item(0);
		NodeList nodeList = node.getChildNodes();
		while(nodeList.getLength()>0)
		{
			node.removeChild(nodeList.item(0));
			nodeList = node.getChildNodes();
		}
		
		//Adicionando Modulos
		//<module application="" optimize="false" destPath="br/com/mresolucoes/atta/modulos/cadastros/tela/Aba.swf" sourcePath="[source path] Atta-flex_src/br/com/mresolucoes/atta/modulos/cadastros/tela/Aba.mxml"/>
		Element element;
		String destino;
		for(int i=0; i<modulos.size(); i++) 
		{
			element = document.createElement("module");
			element.setAttribute("application", pathAplication);
			element.setAttribute("optimize", "true");
			destino = modulos.get(i).getArquivo().getAbsolutePath().substring(modulos.get(i).getArquivo().getAbsolutePath().indexOf("flex_src")+9).replace("\\", "/");
			destino = destino.substring(0, destino.lastIndexOf(".")) + ".swf";
			element.setAttribute("destPath", destino);
			element.setAttribute("sourcePath", (modulos.get(i).getProjeto()!=null ? "[source path] " + modulos.get(i).getProjeto() + "-": "") + modulos.get(i).getArquivo().getAbsolutePath().substring(modulos.get(i).getArquivo().getAbsolutePath().indexOf("flex_src")).replace("\\", "/"));

			node.appendChild(document.createTextNode("\n    "));
			node.appendChild(element);
		}
		node.appendChild(document.createTextNode("\n  "));
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StringWriter buffer = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(buffer));
		return buffer.toString();
	}
	
	/**
	 * Metodo que obtem a partir de varios enderecos bases todos os modulos exitentes nas pastas e suas subpastas
	 * @param paths
	 * @return lista de modulos .as e .mxml
	 * @throws Exception
	 */
	public static List<ArquivoModulo> obtemModulos(List<PathASBase> paths) throws Exception
	{
		List<ArquivoModulo> arquivos = new ArrayList<ArquivoModulo>();
		for(int i=0; i<paths.size(); i++)
		{
			arquivos.addAll(buscaModulos(paths.get(i).getPath(), paths.get(i).getProjetoImport()));
		}

		List<ArquivoModulo> modulos = new ArrayList<ArquivoModulo>();
		String conteudo;
		for(int i=0; i<arquivos.size(); i++)
		{
			conteudo = ArquivoUtil.getString(arquivos.get(i).getArquivo());
			if(conteudo.contains("<aba:Aba") || conteudo.contains("extends BaseModulo"))
			{
				modulos.add(arquivos.get(i));
			}
		}
		return modulos;
	}
	
	/**
	 * Metodo privado utilizado recursivamente para buscar todos os arquivos de modulos a partir de um path base
	 * @param pathBase
	 * @return lista dos arquivos de modulo, as e mxml
	 */
	public static List<ArquivoModulo> buscaModulos(String pathBase, String projeto)
	{
		if(!pathBase.endsWith("/")) { pathBase = pathBase + "/"; }
		File[] files = new File(pathBase).listFiles();
		
		List<ArquivoModulo> arquivos = new ArrayList<ArquivoModulo>();
		for(int i=0; i<files.length; i++)
		{
			if(files[i].isDirectory() && !files[i].getName().equals("bin-debug") && !files[i].getName().equals("bin-release"))
			{ arquivos.addAll(buscaModulos(files[i].getAbsolutePath(), projeto)); }
			else if((files[i].getName().endsWith(".as") || files[i].getName().endsWith(".mxml")) && (!files[i].getAbsolutePath().contains("\\.") && !files[i].getAbsolutePath().contains("/.")))
			{ arquivos.add(new ArquivoModulo(files[i], projeto)); }
		}
		return arquivos;
	}
}
