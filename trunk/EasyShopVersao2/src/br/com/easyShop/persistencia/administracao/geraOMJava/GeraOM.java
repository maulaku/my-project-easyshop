package br.com.easyShop.persistencia.administracao.geraOMJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import logs.Logs;
import logs.LogsUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.easyShop.persistencia.administracao.geraOMJava.auxiliar.ManyToOne;
import br.com.easyShop.persistencia.administracao.geraOMJava.estruturas.Coluna;
import br.com.easyShop.persistencia.administracao.geraOMJava.estruturas.Dicionario;
import br.com.easyShop.persistencia.administracao.geraOMJava.estruturas.Relacao;
import br.com.easyShop.persistencia.administracao.geraOMJava.estruturas.Tabela;

public class GeraOM
{
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Metodo que gera uma lista de Tabelas com todo o relacionamento contido em um arquivo XML .erm
	 * Esta estrutrua é utilizada para gerar as classes OMs do Java
	 * @para arquivo com o endereco do arquivo XML .erm
	 */
	public static List<Tabela> obterTabelas(String arquivo)
	{
		List<Tabela> tabelas = new ArrayList<Tabela>();
		
		try 
		{
			List<Dicionario> dicionarios = new ArrayList<Dicionario>();

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			FileInputStream fileInputStream = new FileInputStream(new File(arquivo));
			Document document = documentBuilder.parse(fileInputStream);
			Element documentElement = document.getDocumentElement();
			
			//Obtem todas os termos do dicionario
			NodeList nodeList = documentElement.getElementsByTagName("word");
			Element element;
			Dicionario dicionario;
			for(int i=0; i<nodeList.getLength(); i++) 
			{
				element = (Element)nodeList.item(i);
				
				dicionario = new Dicionario();
				dicionario.setNomeFisico(getStringElementTag(element, "physical_name"));
				dicionario.setNomeLogico(getStringElementTag(element, "logical_name"));
				dicionario.setId(getIntegerElementTag(element, "id"));
				dicionario.setTamanho(getIntegerElementTag(element, "length"));
				dicionarios.add(dicionario);
			}
			
			//Obtem todas as tabelas e suas relacoes
			nodeList = documentElement.getElementsByTagName("table");
			NodeList nodeListRelacao;
			NodeList nodeListConexao;
			Element elementRelacao;
			Element elementConexao;
			Tabela tabela;
			String nomeFisicoTabela;
			String nomeLogicoTabela;
			Relacao relacao;
			for(int i=0; i<nodeList.getLength(); i++) 
			{
				element = (Element)nodeList.item(i);

				tabela = new Tabela();
				
				nomeFisicoTabela = getStringElementTag(element, "physical_name");
				if(nomeFisicoTabela.trim().startsWith("[[") && nomeFisicoTabela.trim().endsWith("]]"))
				{
					tabela.setEspecificoInterface(true);
					tabela.setNomeFisico(nomeFisicoTabela.substring(2, nomeFisicoTabela.length()-2));
				}
				else if(nomeFisicoTabela.trim().startsWith("[") && nomeFisicoTabela.trim().endsWith("]"))
				{
					String nomes = nomeFisicoTabela.substring(1, nomeFisicoTabela.length()-1);
					int indexSeparador = nomes.indexOf("][");
					if(indexSeparador>-1)
					{
						tabela.setAttaInterface(nomes.substring(indexSeparador+2, nomes.length()));
						tabela.setNomeFisico(nomes.substring(0, indexSeparador));
					}
					else
					{
						tabela.setAttaInterface("atta");
						tabela.setNomeFisico(nomeFisicoTabela.substring(1, nomeFisicoTabela.length()-1));
					}
				}
				else
				{
					tabela.setAttaInterface(null);
					tabela.setEspecificoInterface(false);
					tabela.setNomeFisico(nomeFisicoTabela);
				}
				
				nomeLogicoTabela = getStringElementTag(element, "logical_name");
				if(nomeLogicoTabela.trim().startsWith("[") && nomeLogicoTabela.trim().endsWith("]"))
				{
					tabela.setNomeLogico(nomeLogicoTabela.substring(0, nomeLogicoTabela.length()-1));
				}
				else
				{
					tabela.setNomeLogico(nomeLogicoTabela);
				}
				
				tabela.setId(getIntegerElementTag(element, "id"));
				
				String descricao = getStringElementTag(element, "description");
				if(descricao!=null)
				{
					tabela.getDescricao().load(new StringReader(descricao));
				}
								
				nodeListConexao = element.getElementsByTagName("connections");
				elementConexao = (Element)nodeListConexao.item(0);
				if(elementConexao!=null)
				{
					nodeListRelacao = elementConexao.getElementsByTagName("relation");
					for(int j=0; j<nodeListRelacao.getLength(); j++) 
					{
						elementRelacao = (Element)nodeListRelacao.item(j);
						
						relacao = new Relacao();
						relacao.setId(getIntegerElementTag(elementRelacao, "id"));
						relacao.setSource(getIntegerElementTag(elementRelacao, "source"));
						tabela.getRelacoes().add(relacao);
					}
				}
				tabelas.add(tabela);
			}
			
			//Percorre novamente as tabelas para definir as colunas e seus tipos
			NodeList nodeListColuna;
			Element elementColuna;
			Coluna coluna;
			for(int i=0; i<nodeList.getLength(); i++) 
			{
				element = (Element)nodeList.item(i);
				
				tabela = getTabelaID(tabelas, getIntegerElementTag(element, "id"));
				if(tabela!=null)
				{
					nodeListColuna = element.getElementsByTagName("normal_column");
					for(int j=0; j<nodeListColuna.getLength(); j++) 
					{
						elementColuna = (Element)nodeListColuna.item(j);
						
						coluna = new Coluna();
						coluna.setDicionarioID(getIntegerElementTag(elementColuna, "word_id"));
						coluna.setId(getIntegerElementTag(elementColuna, "id"));
						coluna.setTipo(getStringElementTag(elementColuna, "type"));
						coluna.setNaoNulo(getBooleanElementTag(elementColuna, "not_null"));
						coluna.setPk(getBooleanElementTag(elementColuna, "primary_key"));
						coluna.setFk(getBooleanElementTag(elementColuna, "foreign_key"));

						if(coluna.isFk())
						{
							coluna.setRelacao(getRelacao(tabela, getIntegerElementTag(elementColuna, "relation")));
							
							String nomeFisico = getStringElementTag(elementColuna, "physical_name");
							String nomeLogico = getStringElementTag(elementColuna, "logical_name");
							if(nomeFisico.startsWith("[") && nomeFisico.endsWith("]") && nomeLogico.startsWith("[") && nomeLogico.endsWith("]"))
							{
								coluna.setTransiente(true);
								coluna.getRelacao().setTransiente(true);
								coluna.setNomeFisico(nomeFisico.substring(1, nomeFisico.length()-1));
								coluna.setNomeLogico(nomeLogico.substring(1, nomeLogico.length()-1));
							}
							else
							{
								coluna.setTransiente(false);
								coluna.getRelacao().setTransiente(false);
								coluna.setNomeFisico(nomeFisico);
								coluna.setNomeLogico(nomeLogico);
							}
							
							for(int m=0; m<tabelas.size(); m++)
							{
								if(coluna.getRelacao().getSource().equals(tabelas.get(m).getId()))
								{
									coluna.getRelacao().setAttaInterface(tabelas.get(m).getAttaInterface());
									coluna.getRelacao().setEspecificoInterface(tabelas.get(m).isEspecificoInterface());
									coluna.getRelacao().setNomeFisicoTabela(tabelas.get(m).getNomeFisico());
									coluna.getRelacao().setNomeLogicoTabela(tabelas.get(m).getNomeLogico());
									break;
								}
							}
						}
						else
						{
							dicionario = getDicionario(dicionarios, coluna.getDicionarioID());
							if(dicionario!=null)
							{
								String nomeFisico = dicionario.getNomeLogico();
								String nomeLogico = dicionario.getNomeFisico();
								if(nomeFisico.startsWith("[") && nomeFisico.endsWith("]") && nomeLogico.startsWith("[") && nomeLogico.endsWith("]"))
								{
									coluna.setTransiente(true);
									coluna.setNomeFisico(nomeFisico.substring(1, nomeFisico.length()-1));
									coluna.setNomeLogico(nomeLogico.substring(1, nomeLogico.length()-1));
								}
								else
								{
									coluna.setTransiente(false);
									coluna.setNomeFisico(nomeFisico);
									coluna.setNomeLogico(nomeLogico);
								}

								coluna.setTamanho(dicionario.getTamanho());
							}
							else
							{ Logs.addError("Coluna sem Dicionario: " + LogsUtil.classeToString(coluna)); }
						}

						tabela.getColunas().add(coluna);
					}
				}
				else
				{ Logs.addError("Tabela nao encontrada: " + getIntegerElementTag(element, "id")); }
			}
		}
		catch(Exception e)
		{ Logs.addError("Falha obtendo tabelas do XML", e); }
		
		return tabelas;
	}
	
	/**
	 * Metodo que gera os arquivos .java de OM a partir de uma lista de Tabelas
	 * @param tabelas lista
	 * @param destino endereco de destino dos arquivos OMs
	 * @param pacoteOMs com o pacote que deve ser iserido nos arquivos OMs
	 */
	public static void gerarOMs(List<Tabela> tabelas, String destino, String pacoteOMs)
	{
		List<String> imports;
		StringBuffer declaracoesImports;
		StringBuffer cabecalho;
		StringBuffer classe;
		StringBuffer construtor;
		StringBuffer metodos;
		StringBuffer getsSets;
		StringBuffer declaracoes;
		StringBuffer classeFinal;
		Tabela tabela = null;
		Coluna coluna;
		String annotation;
		
		try
		{
			//Percorre todas as tabelas
			for(int i=0; i<tabelas.size(); i++)
			{
				tabela = tabelas.get(i);
				
				if(tabela.getAttaInterface()!=null || tabela.isEspecificoInterface()) { continue; }
				
				imports = new ArrayList<String>();
				cabecalho = new StringBuffer();
				classe = new StringBuffer();
				construtor = new StringBuffer();
				metodos = new StringBuffer();
				declaracoes = new StringBuffer();
				getsSets = new StringBuffer();
				classeFinal = new StringBuffer();
				declaracoesImports = new StringBuffer();
				
				//Define o Cabecalho
				cabecalho.append("package " + pacoteOMs + ";");

				//Define a classe
				classe.append("\n@Entity");
				imports.add("javax.persistence.Entity");
				
				if(tabela.getNomeFisico().equalsIgnoreCase(tabela.getNomeLogico())==false)
				{ 
					classe.append("(name=\"" + tabela.getNomeLogico() + ")");
				}

				classe.append("\npublic class " + tabela.getNomeFisico());
				classe.append("\n{");
				
				//Define algumas decalaracoes e seus gets e sets
				for(int j=0; j<tabela.getColunas().size(); j++)
				{
					coluna = tabela.getColunas().get(j);

					if(coluna.isTransiente())
					{
						addImport("javax.persistence.Transient", imports);
						declaracoes.append((coluna.isFk() ? "\n" : "") + "\n\t@Transient");
					}
					annotation = getAnnotationJava(coluna, imports);
					if(annotation!=null) { declaracoes.append(annotation); }

					declaracoes.append("\n\tprivate ");
					declaracoes.append(getTipoJava(coluna, imports) + " ");
					
					declaracoes.append(coluna.getNomeFisico());

					if(coluna.getNomeFisico().equals("status"))
					{
						declaracoes.append(" = Constantes.STATUS_ATIVO;");
						imports.add("br.com.easyShop.utils.Constantes");
					}
					else
					{
						declaracoes.append(";");
					}
					
					if(tabela.getDescricao()!=null)
					{
						String descricao = tabela.getDescricao().getProperty(coluna.getNomeFisico());
						if(descricao!=null)
						{
							declaracoes.append(" //" + descricao);
						}
					}
					
					getsSets.append("\n\n\tpublic " + getTipoJava(coluna, imports) + " get" + primeiraMaiuscula(coluna.getNomeFisico()) + "() { return " + coluna.getNomeFisico() + "; }");
					getsSets.append("\n\tpublic void set" + primeiraMaiuscula(coluna.getNomeFisico()) + "(" + getTipoJava(coluna, imports) + " " + coluna.getNomeFisico() + ") { this." + coluna.getNomeFisico() + " = " + coluna.getNomeFisico() + "; }");
				}
				
				//Procura todos os relacionamentos ManyToOne com esta tabela e marca os repetidos para renomeacao
				List<ManyToOne> relacoesManyToOne = new ArrayList<ManyToOne>(); 
				ManyToOne manyToOne;
				for(int j=0; j<tabelas.size(); j++)
				{
					for(int k=0; k<tabelas.get(j).getRelacoes().size(); k++)
					{
						if(tabelas.get(j).getRelacoes().get(k).getSource().equals(tabela.getId()))
						{
							manyToOne = new ManyToOne();
							manyToOne.setTabela(tabela);
							manyToOne.setTabelaGeti(tabelas.get(j));
							manyToOne.setRelacao(tabelas.get(j).getRelacoes().get(k));
							
							if(relacoesManyToOne.contains(manyToOne))
							{
								manyToOne.setRenome(true);
								relacoesManyToOne.get(relacoesManyToOne.indexOf(manyToOne)).setRenome(true);
							}
							relacoesManyToOne.add(manyToOne);
						}
					}
				}
				
				//Define todas as declaracoes e gets e sets de relacionamentos ManyToOne
				for(int j=0; j<relacoesManyToOne.size(); j++)
				{
					addImport("java.util.ArrayList", imports);
					addImport("java.util.List", imports);
					

					if(relacoesManyToOne.get(j).getTabelaGeti().isEspecificoInterface())
					{
						addImport("javax.persistence.Transient", imports);
						
						declaracoes.append("\n\n\t@Transient");
						
						if(relacoesManyToOne.get(j).getTabelaGeti().getDescricao()!=null)
						{
							if(relacoesManyToOne.get(j).getTabelaGeti().getDescricao().getProperty("descricao")==null)
							{
								declaracoes.append(" //Campo Transiente, utilize a descrição da modelagem.erm para indicar esse comentário");
							}
							else
							{
								declaracoes.append(" //" + relacoesManyToOne.get(j).getTabelaGeti().getDescricao().getProperty("descricao"));
							}
						}
						
						declaracoes.append("\n\tprivate List<?> ");
						declaracoes.append(primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())));
						declaracoes.append(";"); // = new ArrayList<Object>()

						String nomeLista = primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()));
						String nomeListaMaiuscula = primeiraMaiuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()));
						getsSets.append("\n\n\tpublic List<?> get" + nomeListaMaiuscula + "() { if(" + nomeLista + "==null) { " + nomeLista + " = new ArrayList<Object>(); } return " + nomeLista + "; }");
						getsSets.append("\n\tpublic void set" + nomeListaMaiuscula + "(List<?> " + nomeLista + ") { this." + nomeLista + " = " + nomeLista + "; }");
					}
					else if(relacoesManyToOne.get(j).isRenome())
					{
						addImport("javax.persistence.OneToMany", imports);
						
						if(relacoesManyToOne.get(j).getRelacao().isTransiente())
						{
							addImport("javax.persistence.Transient", imports);
							declaracoes.append("\n\n\t@Transient");
						}

						declaracoes.append((relacoesManyToOne.get(j).getRelacao().isTransiente() ? "" : "\n") + "\n\t@OneToMany(mappedBy=\"" + primeiraMinuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()) + "\")");
						declaracoes.append("\n\tprivate List<");
						declaracoes.append(primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + "> ");
						declaracoes.append(primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()));
						declaracoes.append(";"); //= new ArrayList<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + ">();

						String nomeLista = primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico());
						getsSets.append("\n\n\tpublic List<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + "> get" + primeiraMaiuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()) + "() { if(" + nomeLista + "==null) { " + nomeLista + " = new ArrayList<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + ">(); } return " + nomeLista + "; }");
						getsSets.append("\n\tpublic void set" + primeiraMaiuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()) + "(List<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + "> " + primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()) + ") { this." + primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()) + " = " + primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()) + "; }");
					}
					else
					{
						addImport("javax.persistence.OneToMany", imports);
						
						if(relacoesManyToOne.get(j).getRelacao().isTransiente())
						{
							addImport("javax.persistence.Transient", imports);
							declaracoes.append("\n\n\t@Transient");
						}
						
						declaracoes.append((relacoesManyToOne.get(j).getRelacao().isTransiente() ? "" : "\n") + "\n\t@OneToMany(mappedBy=\"" + primeiraMinuscula(relacoesManyToOne.get(j).getTabelaGeti().getColunaRelacao(relacoesManyToOne.get(j).getRelacao()).getNomeFisico()) + "\")");
						declaracoes.append("\n\tprivate List<");
						declaracoes.append(primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + "> ");
						declaracoes.append(primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())));
						declaracoes.append(";"); //= new ArrayList<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + ">();"

						String nomeLista = primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()));
						getsSets.append("\n\n\tpublic List<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + "> get" + primeiraMaiuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + "() { if(" + nomeLista + "==null) { " + nomeLista + " = new ArrayList<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + ">(); } return " + nomeLista + "; }");
						getsSets.append("\n\tpublic void set" + primeiraMaiuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + "(List<" + primeiraMaiuscula(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico()) + "> " + primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + ") { this." + primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + " = " + primeiraMinuscula(plural(relacoesManyToOne.get(j).getTabelaGeti().getNomeFisico())) + "; }");
					}
				}

				//Define o Construtor
				construtor.append("\n\tpublic " + primeiraMaiuscula(tabela.getNomeFisico()) + "() { }");
				
				//Define possiveis metodos
				definirMetodos(tabela, metodos, imports);
				
				//Define os Imports
				for(int j=0; j<imports.size(); j++)
				{
					declaracoesImports.append("\nimport " + imports.get(j) + ";");
				}
				
				//Monta conteudo do arquivo
				classeFinal.append(cabecalho);
				classeFinal.append("\n");
				classeFinal.append(declaracoesImports);
				classeFinal.append("\n");
				classeFinal.append(classe);
				classeFinal.append("\n\t/*-*-*-* Variaveis e Objetos Privados *-*-*-*/");
				classeFinal.append(declaracoes);
				classeFinal.append("\n\n\n\t/*-*-*-* Construtores *-*-*-*/");
				classeFinal.append(construtor);

				if(metodos.toString().isEmpty()==false)
				{
					classeFinal.append("\n\n\t/*-*-*-* Metodos Publicos *-*-*-*/");
					classeFinal.append(metodos);
				}

				classeFinal.append("\n\n\t/*-*-*-* Metodos Gets e Sets *-*-*-*/");
				classeFinal.append(getsSets.toString().substring(1));
				classeFinal.append("\n}");
				
				//Cria ou sobreescreve o arquivo
				File file = new File(destino + (destino.charAt(destino.length()-1)!='/' ? "/" : "") + primeiraMaiuscula(tabela.getNomeFisico()) + ".java");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(classeFinal.toString().getBytes());
				fos.close();
			}
		}
		catch(Exception e)
		{
			Logs.addError("Falha gerando OMs Java, " + LogsUtil.classeToString(tabela), e);
		}
	}
	

	/*-*-*-* Metodos Auxiliares *-*-*-*/
	/**
	 * Converte um tipo do modelo erm para os tipos utilizados no java
	 * @param coluna a ter seu tipo analisado
	 * @param imports lista para adicionar os imports
	 */
	public static String getTipoJava(Coluna coluna, List<String> imports)
	{
		if(coluna.isFk())
		{ 
			if(coluna.getRelacao().getAttaInterface()!=null)
			{
				String pacote = coluna.getRelacao().getAttaInterface().equals("atta") ? "atta" : "atta." + coluna.getRelacao().getAttaInterface().toLowerCase().charAt(0) + coluna.getRelacao().getAttaInterface().substring(1);
				addImport("br.com.mresolucoes." + pacote + ".persistencia.om." + coluna.getRelacao().getNomeFisicoTabela(), imports);
			}
			return coluna.getRelacao().getNomeFisicoTabela();
		}
		else if(coluna.getTipo()!=null)
		{ 
			if(coluna.getTipo().equals("varchar")) 			{ return "String"; }
			else if(coluna.getTipo().equals("text")) 		{ return "String"; }
			else if(coluna.getTipo().equals("varchar(n)"))	{ return "String"; }
			else if(coluna.getTipo().equals("nvarchar(n)"))	{ return "String"; }
			else if(coluna.getTipo().equals("long"))		{ return "String"; }
			else if(coluna.getTipo().equals("clob"))		{ return "String"; }
			else if(coluna.getTipo().equals("blob")) 		{ return "byte[]"; }
			else if(coluna.getTipo().equals("integer")) 	{ return coluna.isNaoNulo() ? "int" : "Integer"; }
			else if(coluna.getTipo().equals("bigint")) 		{ return coluna.isNaoNulo() ? "long" : "Long"; }
			else if(coluna.getTipo().equals("bigserial")) 	{ return coluna.isNaoNulo() ? "long" : "Long"; }
			else if(coluna.getTipo().equals("boolean")) 	{ return coluna.isNaoNulo() ? "boolean" : "Boolean"; }
			else if(coluna.getTipo().equals("real")) 		{ return coluna.isNaoNulo() ? "double" : "Double"; }
			else if(coluna.getTipo().equals("datetime") || coluna.getTipo().equals("timestamp"))
			{ 
				addImport("utils.data.Data", imports);
				return "Data"; 
			}
		}

		Logs.addError("Tipo nao encontrado: " + LogsUtil.classeToString(coluna));
		return null;
	}
	
	/**
	 * Metodo que define os metodos na classe java de acordo com a descricao properties
	 * @param tabela a ser analisada
	 * @param metodos onde o resultado deve ser escrito
	 * @param imports para acrecentar imports necessarios
	 */
	public static void definirMetodos(Tabela tabela, StringBuffer metodos, List<String> imports)
	{
		if(tabela.getDescricao().getProperty("toString")!=null)
		{
			addImport("br.com.easyShop.persistencia.annotations.Implementa", imports);
			metodos.append("\n\t@Implementa(codigo=\"" + tabela.getDescricao().get("toString") + "\")");
			metodos.append("\n\tpublic String toString()");
			metodos.append("\n\t{");
			metodos.append("\n\t\treturn " + tabela.getDescricao().get("toString") + ";");
			metodos.append("\n\t}");
		}
	}
	
	/**
	 * Retorna a annotation correta para alguns tipos especificos
	 * @param coluna a ser verificada
	 * @param imports para acrecentar imports necessarios
	 * @return annotatio para a coluna ou null caso nao exista necessidade
	 */
	public static String getAnnotationJava(Coluna coluna, List<String> imports)
	{
		if(coluna.isFk())
		{ 
			addImport("javax.persistence.ManyToOne", imports);
			addImport("javax.persistence.JoinColumn", imports);
			return (coluna.isTransiente() ? "" : "\n") + "\n\t@ManyToOne " + "@JoinColumn(name=\"" + coluna.getNomeLogico() + "\")"; 
		}
		else if(coluna.getTipo()!=null)
		{ 
			if(coluna.getTipo().equals("datetime") || coluna.getTipo().equals("timestamp"))
			{
				addImport("org.hibernate.annotations.Type", imports);
				return "\n\t@Type(type=\"timestamp\")"; 
			}
			else if((coluna.getTipo().equals("varchar(n)") || coluna.getTipo().equals("nvarchar(n)")) && coluna.getTamanho()!=null && coluna.getTamanho().compareTo(new Integer(255))>0)
			{
				addImport("javax.persistence.Column", imports);
				return "\n\t@Column(length=" + coluna.getTamanho() + ")"; 
			}
			else if(coluna.getTipo().equals("long") || coluna.getTipo().equals("clob") || coluna.getTipo().equals("text"))
			{
				addImport("org.hibernate.annotations.Type", imports);
				return "\n\t@Type(type=\"text\")";  
			}
			else if(coluna.isPk())
			{
				addImport("javax.persistence.GeneratedValue", imports);
				addImport("javax.persistence.GenerationType", imports);
				addImport("javax.persistence.Id", imports);
				return "\n\t@Id @GeneratedValue(strategy=GenerationType.IDENTITY)"; 
			}
		}
		
		return null;
	}
	
	/**
	 * Metodo utilizada para informar qual o plural de certas palavras
	 * @param palavra
	 * @return plural ou palavra + s caso nao exista
	 */
	public static String plural(String palavra)
	{
		if(palavra.equalsIgnoreCase("setor")) 					{ return "setores"; }
		else if(palavra.equalsIgnoreCase("filial")) 			{ return "filiais"; }
		else if(palavra.equalsIgnoreCase("produtoFilial")) 		{ return "produtoFiliais"; }
		else if(palavra.equalsIgnoreCase("PerfilISS")) 			{ return "perfisISS"; }
		else if(palavra.equalsIgnoreCase("NotaFiscal")) 		{ return "NotaFiscais"; }
		else if(palavra.equalsIgnoreCase("FaturaNotaFiscal")) 	{ return "FaturaNotaFiscais"; }
		else if(palavra.equalsIgnoreCase("Seccional")) 			{ return "Seccionais"; }
		else if(palavra.equalsIgnoreCase("ProdutoNotaFiscal")) 	{ return "ProdutosNotaFiscal"; }	
		else if(palavra.equalsIgnoreCase("EquipamentoMovel")) 	{ return "EquipamentoMoveis"; }	
		else
		{
			return palavra + "s";
		}
	}
	
	/**
	 * Adiciona um import na lista caso ele nao exista
	 * @param novoImport
	 * @param imports
	 */
	public static void addImport(String novoImport, List<String> imports)
	{
		if(imports.contains(novoImport)==false)
		{
			imports.add(novoImport);
		}
	}

	/**
	 * Obtem uma relacao da tabela
	 * @param tabela
	 * @param id
	 * @return
	 */
	public static Relacao getRelacao(Tabela tabela, Integer id)
	{
		for(int i=0; i<tabela.getRelacoes().size(); i++)
		{
			if(tabela.getRelacoes().get(i).getId().equals(id))
			{
				return tabela.getRelacoes().get(i);
			}
		}
		return null;
	}
	
	/**
	 * Obtem um valor da tag
	 * @param element
	 * @param tag
	 * @return
	 */
	public static String getStringElementTag(Element element, String tag)
	{
		try
		{
			NodeList nodeList = element.getElementsByTagName(tag);
			Element nlElement = (Element)nodeList.item(0);
			return nlElement.getFirstChild().getNodeValue();
		}
		catch(Exception e) { }
		return null;
	}
	
	/**
	 * Obtem um valor da tag
	 * @param element
	 * @param tag
	 * @return
	 */
	public static Integer getIntegerElementTag(Element element, String tag)
	{
		try
		{
			NodeList nodeList = element.getElementsByTagName(tag);
			Element nlElement = (Element)nodeList.item(0);
			return new Integer(nlElement.getFirstChild().getNodeValue());
		}
		catch(Exception e) { }
		return null;
	}
	
	/**
	 * Obtem um valor da tag
	 * @param element
	 * @param tag
	 * @return
	 */
	public static Boolean getBooleanElementTag(Element element, String tag)
	{
		try
		{
			NodeList nodeList = element.getElementsByTagName(tag);
			Element nlElement = (Element)nodeList.item(0);
			return new Boolean(nlElement.getFirstChild().getNodeValue());
		}
		catch(Exception e) { }
		return null;
	}
	
	/**
	 * Obtem um determinado dicionario a partir de seu id
	 * @param dicionarios
	 * @param id
	 * @return
	 */
	public static Dicionario getDicionario(List<Dicionario> dicionarios, Integer id)
	{
		for(int i=0; i<dicionarios.size(); i++)
		{
			if(dicionarios.get(i).getId()!=null && dicionarios.get(i).getId().equals(id))
			{
				return dicionarios.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Obtem o registro da tabela de acordo com o id
	 * @param tabelas
	 * @param id
	 * @return
	 */
	public static Tabela getTabelaID(List<Tabela> tabelas, Integer id)
	{
		for(int i=0; i<tabelas.size(); i++)
		{
			if(tabelas.get(i).getId()!=null && tabelas.get(i).getId().equals(id))
			{
				return tabelas.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Define a string com a primeira letra maiuscula
	 * @param palavra
	 * @return
	 */
	public static String primeiraMaiuscula(String palavra)
	{
		return palavra.substring(0, 1).toUpperCase() + palavra.substring(1);
	}
	
	/**
	 * Define a string com a primeira letra minuscula
	 * @param palavra
	 * @return
	 */
	public static String primeiraMinuscula(String palavra)
	{
		return palavra.substring(0, 1).toLowerCase() + palavra.substring(1);
	}
	
	/**
	 * Metodo utilizado para extrair o pacote de classes apartir de seu path
	 * Busca a pasta 'src' e corta o path a partir dela trocando as barras por pontos
	 * @param path
	 * @return pacote
	 */
	public static String obterPacote(String path)
	{
		return path.substring(path.indexOf("src")+4).replace("/", ".");
	}
}
