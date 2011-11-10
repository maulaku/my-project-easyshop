package br.com.easyShop.persistencia.administracao.comparaServices;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.ArquivoUtil;
import br.com.easyShop.persistencia.administracao.actionScript.ArquivoModulo;
import br.com.easyShop.persistencia.administracao.actionScript.GeraActionScript;
import br.com.easyShop.persistencia.administracao.actionScript.PathASBase;

public class ComparaServices
{

	/**
	 * @param args
	 */
	public static void comparar(List<PathASBase> pathModulos, List<String> pathRaizProjeto) 
	{
		List<String> prePackages = new ArrayList<String>();
	
		for(int i=0; i<pathRaizProjeto.size(); i++)
		{
			System.out.println("Procurando Services no diretório: " + pathRaizProjeto.get(i));
			prePackages = constroiListaService(pathRaizProjeto.get(i));
			System.out.println("Encontrado Services nos pacotes:");
			for (String pacote : prePackages) { System.out.println(" 	- " + pacote);	}
		}
		System.out.println();
		System.out.println("Procurando arquivos Flex (.as e .mxml) nos diretórios:");
		List<ArquivoModulo> arquivos = new ArrayList<ArquivoModulo>();
		for(int i=0; i<pathModulos.size(); i++)
		{
			System.out.println("	- "+ pathModulos.get(i).getPath());
			String nomeModulo = pathModulos.get(i).getPath().substring(pathModulos.get(i).getPath().lastIndexOf("/")+1, pathModulos.get(i).getPath().length());
			arquivos.addAll(GeraActionScript.buscaModulos(pathModulos.get(i).getPath(), nomeModulo));
		}
		System.out.println("Encontrados " + arquivos.size() + " arquivos.");
		
		System.out.println();
		System.out.println("Procurando chamadas 'MRemoteObject.get' nos arquivos Flex...");
		List<ArquivoChamada> arquivosFinal = new ArrayList<ArquivoChamada>();
		for(int i=0; i<arquivos.size(); i++)
		{
			String conteudo, chamada;
			int index=0;
			List<String> chamadas = new ArrayList<String>();
			try 
			{
				conteudo = ArquivoUtil.getString(arquivos.get(i).getArquivo());
			} 
			catch (Exception e)
			{
				conteudo = "";
				e.printStackTrace();
			}

			do
			{
				index = conteudo.indexOf("MRemoteObject.get", index);

				if(index > -1)
				{
					int indexFinalLinha = conteudo.indexOf(";", index);
					chamada = conteudo.substring(index, indexFinalLinha);
					chamadas.add(chamada);
					index = indexFinalLinha;
				}
			} while(index > -1);
			if(chamadas.size()>0)
			{
				ArquivoChamada arquivoChamada = new ArquivoChamada(arquivos.get(i).getArquivo().getName(), chamadas, arquivos.get(i).getProjeto());
				arquivosFinal.add(arquivoChamada);
			}
		}
		System.out.println("Comparando chamadas do Flex com Services encontrados...");
		String moduloAnterior = "";
		String nomeArquivoAnterior = "";
		Boolean imprimiuModulo = false;
		StringBuffer erros = new StringBuffer();
		for (int i = 0; i < arquivosFinal.size(); i++)
		{
			Boolean achouErro = false;
			String modulo = arquivosFinal.get(i).getProjeto();
			String nomeArquivo = arquivosFinal.get(i).getNomeArquivo();
			if(!moduloAnterior.equals(modulo))
			{
				moduloAnterior = modulo;
				imprimiuModulo = false;
			}
			if(!nomeArquivoAnterior.equals(nomeArquivo))
			{
				nomeArquivoAnterior = nomeArquivo;
				erros.append("	Arquivo: " + arquivosFinal.get(i).getNomeArquivo());
				erros.append("\n");
			}
			String pacoteClasseAnterior = "";
			List<String> chamadas = arquivosFinal.get(i).getChamadas();
			for(int z=0; z<chamadas.size();z++)
			{
				String chamada = chamadas.get(z);
				String pacoteClasse = ArquivoChamada.getPacoteClasse(chamada);
				if(pacoteClasse==null) { continue; }
				String metodo = ArquivoChamada.getMetodo(chamada);
				if(metodo.length()==0) { continue; }
				
				Object instancia = null;
				for(int j=0; j<prePackages.size(); j++)
				{
					try
					{
						instancia = Class.forName(prePackages.get(j) + "." + pacoteClasse).newInstance();
						break;
					}
					catch(ClassNotFoundException e) 
					{ 
						if(j==prePackages.size()-1)
						{
							if(!pacoteClasseAnterior.equals(pacoteClasse))
							{
								achouErro = true;
								pacoteClasseAnterior = pacoteClasse;
								erros.append("		Service não encontrado: " + pacoteClasse);
								erros.append("\n");
							}
						}
						instancia = null;
					} 
					catch (InstantiationException e) { e.printStackTrace(); } 
					catch (IllegalAccessException e) { e.printStackTrace(); }
				}
				if(instancia!=null)
				{
					Class<?>[] fParametros;
					String numParametros = null;
					for(int k=0; k<instancia.getClass().getMethods().length; k++)
					{
						if(instancia.getClass().getMethods()[k].getName().equals(metodo))
						{
							fParametros = instancia.getClass().getMethods()[k].getParameterTypes();
							if(ArquivoChamada.getNumParametros(chamada) == fParametros.length)
							{
								break;
							}
							else
							{
								if(numParametros != null) 	{ numParametros += " ou " + Integer.toString(fParametros.length); }
								else 						{ numParametros = Integer.toString(fParametros.length); }
							}
						}
						else
						{
							if(k==instancia.getClass().getMethods().length-1)
							{
								if(numParametros!=null)
								{
									erros.append("		Número de parametros incorretos: " + pacoteClasse + "." + metodo);
									erros.append(" Esperado: " + numParametros + ". Encontrado: " + ArquivoChamada.getNumParametros(chamada));
									erros.append("\n");
									break;
								}
								achouErro = true;
								erros.append("		Método não encontrado: " + pacoteClasse + "." + metodo);
								erros.append("\n");
							}
						}
					}
				}
			}
			if(achouErro)
			{
				if(!imprimiuModulo) { erros.insert(0, modulo+"\n"); imprimiuModulo = true;}
				System.out.println(erros);
			}
			erros = new StringBuffer();
		}
		System.out.println("Fim da comparação de Services.");
		System.out.println("Observação: Se houver dois (ou mais) services com o mesmo NOME na mesma estrutura de PACOTE mas em módulos diferentes, \n" +
				"o Comparador irá usar o primeiro que encontrar. Portanto algum método pode ser dado como não encontrado mas estar nos outros services.");
	}

	private static List<String> constroiListaService(String pathBase)
	{
		List<File> arquivos = buscaServices(pathBase);
		Set<String> setPackage = new HashSet<String>();
		for(int i=0; i<arquivos.size(); i++) 
		{
			String packageServiceCompleto = arquivos.get(i).getAbsolutePath().substring(arquivos.get(i).getAbsolutePath().indexOf("src")+4).replace("\\", ".");
			String packageService = null;
			if(packageServiceCompleto.indexOf("service")>0) 
			{
				packageService = packageServiceCompleto.substring(0, packageServiceCompleto.indexOf("service")+8);
			}
			if(packageService!=null) 
			{
				if(setPackage.contains(packageService)) { continue; }
				else { setPackage.add(packageService); }
			}
		}
		return new ArrayList<String>(setPackage);
	}
	
	private static List<File> buscaServices(String pathBase)
	{
		if(!pathBase.endsWith("/")) { pathBase = pathBase + "/"; }
		File[] files = new File(pathBase).listFiles();
		
		List<File> arquivos = new ArrayList<File>();
		for(int i=0; i<files.length; i++)
		{
			if(files[i].isDirectory())
			{ arquivos.addAll(buscaServices(files[i].getAbsolutePath())); }
			else if((files[i].getName().endsWith(".java") && files[i].getAbsolutePath().contains("Service")) && (!files[i].getAbsolutePath().contains("\\.") && !files[i].getAbsolutePath().contains("/.")))
			{ arquivos.add(files[i]); }
		}
		return arquivos;
	}
}
