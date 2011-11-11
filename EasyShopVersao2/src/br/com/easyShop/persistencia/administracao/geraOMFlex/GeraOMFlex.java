package br.com.easyShop.persistencia.administracao.geraOMFlex;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import br.com.easyShop.persistencia.annotations.Implementa;

import utils.data.Data;

/**
 * Esta classe possui apenas um metodo "MAIN" que deve ser executado
 * quando se deseja refazer as estruturas dos oms do flex, ele apaga
 * todos os arquivos e os cria novamente
 */
public class GeraOMFlex
{
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Metodo que gera os OMs do Flex apartir dos OMs do Java
	 * @param classes lista de classes a serem geradas
	 * @param destino
	 * @param pacoteOMsFlex
	 */
	public static void flexOMs(List<Class<?>> classes, String destino, String pacoteOMsFlex)
	{
		try
		{
			StringBuffer stringBuffer;
			Class<?> classe;
			List<String> importsFlex;
			List<String> importsOM;
			List<String> importsAttaOM;
			Field field;
			
			for(int i=0; i<classes.size(); i++)
			{
				stringBuffer = new StringBuffer();
				importsOM = new ArrayList<String>();
				importsAttaOM = new ArrayList<String>();
				importsFlex = new ArrayList<String>();
				classe = classes.get(i);
				
				System.out.println("public static var " +  new String(classe.getSimpleName().charAt(0) + "").toLowerCase() + classe.getSimpleName().substring(1) + ":" + classe.getSimpleName() + " = null;");
				
				stringBuffer.append("package " + pacoteOMsFlex);
				stringBuffer.append("\n{");
				
				for(int j=0; j<classe.getDeclaredFields().length; j++)
				{
					field = classe.getDeclaredFields()[j];
					if(field.getType().equals(List.class)) 
					{ 
						if(importsFlex.contains("mx.collections.ArrayCollection")==false) { importsFlex.add("mx.collections.ArrayCollection"); }
					}
					else if(field.getType().equals(byte[].class)) 
					{ 
						if(importsFlex.contains("flash.utils.ByteArray")==false) { importsFlex.add("flash.utils.ByteArray"); }
					}
					
					if(field.getType().getName().startsWith("br.com.mresolucoes.atta"))
					{
						if(importsAttaOM.contains(field.getType().getSimpleName())==false) { importsAttaOM.add(field.getType().getName()); }
					}
					else if(field.getType().getName().startsWith("br.com.mresolucoes"))
					{
						if(importsOM.contains(field.getType().getSimpleName())==false) { importsOM.add(field.getType().getSimpleName()); }
					}
					
					if(field.getName().equals("status"))
					{
						importsFlex.add("br.com.easyShop.utils.Constantes");
					}
				}
				
				for(int j=0; j<importsFlex.size(); j++)
				{
					stringBuffer.append("\n\timport " + importsFlex.get(j) + ";");
				}
				if(importsFlex.size()>0) { stringBuffer.append("\n"); }
				
				for(int j=0; j<importsAttaOM.size(); j++)
				{
					stringBuffer.append("\n\timport " + importsAttaOM.get(j).replace("persistencia", "comunicacao") + ";");
				}
				if(importsAttaOM.size()>0) { stringBuffer.append("\n"); }
				
				for(int j=0; j<importsOM.size(); j++)
				{
					stringBuffer.append("\n\timport " + pacoteOMsFlex + "." + importsOM.get(j) + ";");
				}
				if(importsOM.size()>0) { stringBuffer.append("\n"); }
				
				stringBuffer.append("\n\t[RemoteClass(alias=\"" + classe.getName() + "\")]");
				stringBuffer.append("\n\tpublic class " + classe.getSimpleName());
				stringBuffer.append("\n\t{");
				
				if(classe.getDeclaredFields().length>0)
				{
					stringBuffer.append("\n\t\t/*-*-*-*-* Objetos e Variaveis *-*-*-*-*/");
				}
				for(int j=0; j<classe.getDeclaredFields().length; j++)
				{
					field = classe.getDeclaredFields()[j];
					
					if(field.getType().equals(List.class))
					{
						String classeAS = field.getGenericType().toString().substring(field.getGenericType().toString().indexOf("<")+1, field.getGenericType().toString().lastIndexOf(">"));
						
						if(classeAS.equals("?"))
						{
							stringBuffer.append("\n\n\t\tprivate var _" + field.getName() + ":" + getFlexTipo(field.getType()) + ";");
						}
						else
						{
							classeAS = classeAS.substring(classeAS.lastIndexOf(".")+1, classeAS.length());
							stringBuffer.append("\n\n\t\t[ArrayElementType(\"" + pacoteOMsFlex + "." + classeAS + "\")]");
							stringBuffer.append("\n\t\tprivate var _" + field.getName() + ":" + getFlexTipo(field.getType()) + ";");
						}
					}
					else
					{
						if(field.getName().equals("status"))
						{
							stringBuffer.append("\n\t\tpublic var " + field.getName() + ":" + getFlexTipo(field.getType()) + " = Constantes.instance.STATUS_ATIVO;");
						}
						else
						{
							stringBuffer.append("\n\t\tpublic var " + field.getName() + ":" + getFlexTipo(field.getType()) + ";");
						}
						
						for(int k=0; k<field.getAnnotations().length; k++)
						{
							if(field.getAnnotations()[k] instanceof Transient)
							{
								stringBuffer.append(" //Transient");
								break;
							}
						}
					}
				}
				
				for(int j=0; j<classe.getDeclaredMethods().length; j++)
				{
					if(classe.getDeclaredMethods()[j].getName().equals("toString"))
					{
						String codigo=null;
						for(int k=0; k<classe.getDeclaredMethods()[j].getAnnotations().length; k++)
						{
							if(classe.getDeclaredMethods()[j].getAnnotations()[k] instanceof Implementa)
							{
								codigo = ((Implementa)classe.getDeclaredMethods()[j].getAnnotations()[k]).codigo();
							}
						}
						
						stringBuffer.append("\n\n\t\tpublic function " + classe.getDeclaredMethods()[j].getName() + "():" + getFlexTipo(classe.getDeclaredMethods()[j].getReturnType()));
						stringBuffer.append("\n\t\t{");
						stringBuffer.append("\n\t\t\t return " + codigo + ";");
						stringBuffer.append("\n\t\t}");
					}
				}
				
				boolean primeiroArrayList=true;
				for(int j=0; j<classe.getDeclaredFields().length; j++)
				{
					field = classe.getDeclaredFields()[j];
					
					if(field.getType().equals(List.class))
					{
						if(primeiroArrayList)
						{ 
							stringBuffer.append("\n\n\n\t\t/*-*-*-*-* Metodos Gets e Sets *-*-*-*-*/");
							primeiroArrayList=false;
						}
						else
						{
							stringBuffer.append("\n");
						}
						
						stringBuffer.append("\n\t\tpublic function set " + field.getName() + "(" + field.getName() + ":ArrayCollection):void	{ _" + field.getName() + " = " + field.getName() + "; }");
						stringBuffer.append("\n\t\tpublic function get " + field.getName() + "():ArrayCollection	{ if(_" + field.getName() + "==null) { _" + field.getName() + " = new ArrayCollection(); } return _" + field.getName() + "; }");
					}
				}
				
				stringBuffer.append("\n\t}");
				stringBuffer.append("\n}");
				
				File file = new File(destino + (destino.charAt(destino.length()-1)!='/' ? "/" : "") + classe.getSimpleName() + ".as");
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(stringBuffer.toString().getBytes());
				fos.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que converte um tipo do java para o tipo equivalente no Flex
	 * @param classe
	 * @return
	 */
	public static String getFlexTipo(Class<?> classe)
	{
		if(classe.equals(Double.class) || classe.equals(double.class) ||
		   classe.equals(Long.class)   || classe.equals(long.class) ||
		   classe.equals(Integer.class))
		{
			return "Number";
		}
		else if(classe.equals(int.class))
		{
			return "int";
		}
		else if(classe.equals(byte[].class))
		{
			return "ByteArray";
		}
		else if(classe.equals(List.class))
		{
			return "ArrayCollection";
		}
		else if(classe.equals(Data.class))
		{
			return "Date";
		}
		else if(classe.equals(Boolean.class) || classe.equals(boolean.class))
		{
			return "Boolean";
		}
		else
		{
			return classe.getSimpleName();
		}
	}
}
