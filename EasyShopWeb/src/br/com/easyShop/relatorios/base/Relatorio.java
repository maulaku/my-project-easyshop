package br.com.easyShop.relatorios.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import relatorios.BaseJDS;
import utils.data.Data;
import br.com.easyShop.configuracoes.Configuracoes;

/**
 * Classe responsavel por realizar a criacao do relatorio utilizando o jasperreports
 */
public class Relatorio 
{
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Metodo que gera um arquivo PDF para donwload
	 * @param impressaoFinal com o relatorio gerado a ser transformado em pdf
	 * @param preFixo do nome do arquivo .pdf
	 * @param extensao do arquivo a ser gerado, utilize: .pdf .xls .rtf .html e .shtml (retorna uma String contendo o Html do relatorio)
	 * @return nome do arquivo gerado
	 */
	public static String gerarRelatorioDownload(JasperPrint impressaoFinal, String preFixo, String extensao) throws Exception
	{
		if(extensao!=null)
		{
			byte[] bytes;
			String extensaoFinal;
			if(extensao.endsWith("pdf"))
			{
				bytes = JasperExportManager.exportReportToPdf(impressaoFinal);
				extensaoFinal = "pdf";
			}
			else if(extensao.endsWith("xls"))
			{
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				JRXlsExporter exporterXLS = new JRXlsExporter();
				exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, impressaoFinal);
				exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, output);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
				exporterXLS.exportReport(); 
				bytes = output.toByteArray();
				extensaoFinal = "xls";
			}
			else if(extensao.endsWith("rtf"))
			{
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				JRRtfExporter exporterRTF = new JRRtfExporter();
				exporterRTF.setParameter(JRExporterParameter.JASPER_PRINT, impressaoFinal);    		   
				exporterRTF.setParameter(JRExporterParameter.OUTPUT_STREAM, output);  
				exporterRTF.exportReport(); 
				bytes = output.toByteArray();
				extensaoFinal = "rtf";
			}
			else if(extensao.endsWith("shtml"))
			{
				StringBuffer output = new StringBuffer();
				JRHtmlExporter exporterHTML = new JRHtmlExporter();
				exporterHTML.setParameter(JRHtmlExporterParameter.JASPER_PRINT, impressaoFinal);    		   
				exporterHTML.setParameter(JRHtmlExporterParameter.OUTPUT_STRING_BUFFER, output);  
				exporterHTML.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);  
				exporterHTML.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, false);
				exporterHTML.exportReport(); 
				return output.toString();
			}
			else if(extensao.endsWith("html"))
			{
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				JRHtmlExporter exporterHTML = new JRHtmlExporter();
				exporterHTML.setParameter(JRExporterParameter.JASPER_PRINT, impressaoFinal);    		   
				exporterHTML.setParameter(JRExporterParameter.OUTPUT_STREAM, output);  
				exporterHTML.exportReport(); 
				bytes = output.toByteArray();
				extensaoFinal = "html";
			}
			else
			{
				return "error, extensao desconhecida";
			}

			String nome = preFixo + "." + new Data().getTimeInMillis() + "." + extensaoFinal;
			String pasta = Configuracoes.propriedades.get("pastaRelatorios");
//			String pasta = "C:\\EasyShop\\siteCompras\\EasyShopVAI\\bin-debug\\br\\com\\easyShop\\relatorios";
			pasta = (pasta.charAt(pasta.length()-1)!='/') ? pasta + "/" + nome : pasta + nome;
			File file = new File(pasta);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bytes);
			fileOutputStream.close();

			return nome;
		}
		return "error, extensao invalida";
	}
	
	/**
	 * Metodo que gera um arquivo PDF para donwload
	 * @param impressaoFinal com o relatorio gerado a ser transformado em pdf
	 * @param preFixo do nome do arquivo .pdf
	 * @return nome do arquivo gerado
	 */
	public static String gerarRelatorioDownload(JasperPrint impressaoFinal, String preFixo) throws Exception
	{
		byte[] bytes = JasperExportManager.exportReportToPdf(impressaoFinal);
		String nome = preFixo + "." + new Data().getTimeInMillis() + ".pdf";
		
		String pasta = Configuracoes.propriedades.get("pastaRelatorios");
		pasta = (pasta.charAt(pasta.length()-1)!='/') ? pasta + "/" + nome : pasta + nome;
		File file = new File(pasta);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(bytes);
		fileOutputStream.close();

		return nome;
	}
	
	/**
	 * Metodo que gera um arquivo PDF para donwload. Este metodo permite ao usuario concatenar uma serie de arquivos pdf no final do arquivo gerado
	 * @param impressaoFinal com o relatorio gerado a ser transformado em pdf
	 * @param preFixo do nome do arquivo .pdf
	 * @param listPath lista de patha dos arquivos que você deseja concatenar
	 * @return nome do arquivo gerado
	 */
	public static String gerarRelatorioDownloadConcatenaPDF(JasperPrint impressaoFinal, String preFixo, List<String> listPath) throws Exception
	{
		byte[] bytes = JasperExportManager.exportReportToPdf(impressaoFinal);
		String nome = preFixo + "." + new Data().getTimeInMillis() + ".pdf";
		
		String pasta = Configuracoes.propriedades.get("pastaRelatorios");
		pasta = (pasta.charAt(pasta.length()-1)!='/') ? pasta + "/" + nome : pasta + nome;
		
		PdfCopyFields copy = new PdfCopyFields(new FileOutputStream(pasta));
		copy.addDocument(new PdfReader(bytes));
		
		pasta= Configuracoes.propriedades.get("pastaAnexos");
		for (String path : listPath) 
		{
			copy.addDocument(new PdfReader(pasta+"/"+path));
		}
		copy.close();
		return nome;
	}
	
	/**
	 * Metodo que gera um relatorio, a partir de uma lista de registros
	 * <BR>Este metodo adiciona os subrelatorios nos parametros dos relatorios, utilizando o nome do arquivo para identifica-los
	 * @param registros lista de registros do relatorio
	 * @param classe utilizada para obter o arquivo do relatorio com o getResourceAsStream apartir do path da classe
	 * @param relatorios vetor com os nomes dos arquivos de relatorio e subrelatorios, obrigatoriamente o relatorio pai deve ser o primeiro
	 * @return arquivo do jasperPrint para ser gravado ou visualizado
	 * @throws JRException no caso de algum erro ocorrer
	 */
	public static JasperPrint gerarRelatorio(List<?> registros, Class<?> classe, String... relatorios) throws JRException
	{
		return gerarRelatorio(registros, new HashMap<String, Object>(), classe, relatorios);
	}
	
	/**
	 * Metodo que gera um relatorio, a partir de uma lista de registros
	 * <BR>Este metodo adiciona os subrelatorios nos parametros dos relatorios, utilizando o nome do arquivo para identifica-los
	 * @param registros lista de registros do relatorio
	 * @param parametros a serem considerados pelo jasperreports
	 * @param classe utilizada para obter o arquivo do relatorio com o getResourceAsStream apartir do path da classe
	 * @param relatorios vetor com os nomes dos arquivos de relatorio e subrelatorios, obrigatoriamente o relatorio pai deve ser o primeiro
	 * @return arquivo do jasperPrint para ser gravado ou visualizado
	 * @throws JRException no caso de algum erro ocorrer
	 */
	public static JasperPrint gerarRelatorio(List<?> registros, Map<String, Object> parametros, Class<?> classe, String... relatorios) throws JRException
	{
		if(relatorios!=null && relatorios.length>0)
		{
			BaseJDS ds = new BaseJDS(registros);

			for(int i=1; i<relatorios.length; i++)
			{
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(classe.getResourceAsStream(relatorios[i]));
				String nomeRelatorio = relatorios[i].contains("/") ? relatorios[i].substring(relatorios[i].lastIndexOf("/")+1, relatorios[i].lastIndexOf(".")) : relatorios[i];
				parametros.put(nomeRelatorio, jasperReport);
			}

			return JasperFillManager.fillReport(classe.getResourceAsStream(relatorios[0]), parametros, ds);
		}
		return null;
	}
	
	/**
	 * Metodo que gera um relatorio, a partir de uma lista de registros
	 * @param registros lista de registros do relatorio
	 * @param arquivoJasper arquivo jasper do relatorio
	 * @return arquivo do jasperPrint para ser gravado ou visualizado
	 * @throws JRException no caso de algum erro ocorrer
	 */
	public static JasperPrint gerarRelatorio(List<?> registros, InputStream arquivo) throws JRException
	{
		return gerarRelatorio(registros, arquivo, new HashMap<String, String>());
	}
	
	/**
	 * Metodo que gera um relatorio, a partir de uma lista de registros
	 * @param registros lista de registros do relatorio
	 * @param arquivoJasper arquivo jasper do relatorio
	 * @param parametros a serem considerados pelo jasperreports
	 * @return arquivo do jasperPrint para ser gravado ou visualizado
	 * @throws JRException no caso de algum erro ocorrer
	 */
	public static JasperPrint gerarRelatorio(List<?> registros, InputStream arquivo, Map<String, ?> parametros) throws JRException
	{
		BaseJDS ds = new BaseJDS(registros);
		return JasperFillManager.fillReport(arquivo, parametros, ds);
	}
	
	/**
	 * Metodo que salva efetivamente o arquivo do relatorio
	 * @param impressaoFinal relatorio gerado pelo jasperReports
	 * @param destino para o relatorio ser gravado
	 * @return o endereco completo ate o arquivo (retorna o parametro destino)
	 * @throws JRException exection lancada pelo JasperReports no caso de algum erro na operacao de gravacao
	 */
	public static String salvarRelatorio(JasperPrint impressaoFinal, String destino) throws JRException
	{
		JasperExportManager.exportReportToPdfFile(impressaoFinal, destino);
		return destino;
	}
	
	/**
	 * Metodo que abre um relatorio no JasperViewer
	 * @param impressaoFinal relatorio gerado pelo jasperReports
	 */
	public static void abrirRelatorio(JasperPrint impressaoFinal)
	{
		JasperViewer.viewReport(impressaoFinal, true);
	}
	
	/**
	 * Retorna a URL base dos relatorios para que seja utilizado na composicao do endereco no navegador e o relatorio possa ser aberto ou copiado
	 * @return url base dos anexos
	 */
	public static String getURLBase()
	{
		return Configuracoes.propriedades.get("urlRelatorios");
	}
}
