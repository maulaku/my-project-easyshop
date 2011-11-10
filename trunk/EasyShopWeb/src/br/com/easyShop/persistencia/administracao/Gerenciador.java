package br.com.easyShop.persistencia.administracao;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import swing.componentes.mCaixa.caixaCampoTexto.MCaixaCampoTexto;
import swing.componentes.mCaixa.caixaCheck.MCaixaCheck;
import swing.eventos.action.EscutaBotoes;
import swing.mensagens.MensagemBox;
import utils.ArquivoUtil;
import utils.IntegerUtil;
import br.com.easyShop.persistencia.administracao.actionScript.ArquivoModulo;
import br.com.easyShop.persistencia.administracao.actionScript.GeraActionScript;
import br.com.easyShop.persistencia.administracao.actionScript.PathASBase;
import br.com.easyShop.persistencia.administracao.comparaBases.ComparaBases;
import br.com.easyShop.persistencia.administracao.comparaServices.ComparaServices;
import br.com.easyShop.persistencia.administracao.geraOMFlex.GeraOMFlex;
import br.com.easyShop.persistencia.administracao.geraOMJava.GeraOM;
import br.com.easyShop.persistencia.administracao.geraOMJava.estruturas.Tabela;
import br.com.easyShop.persistencia.hibernate.HibernateFactory;

/**
 * Esta classe possui apenas um metodo "MAIN" que deve ser executado
 * quando se deseja refazer as estruturas do banco de dados, ele apaga (drop)
 * todas as tabelas e as cria novamente
 */
public class Gerenciador extends JFrame
{
	/*-*-*-* Contantes Publicas de configuracao *-*-*-*/
	public static String arquivoERM = null;
	public static String pathJavaOMs = null;
	
	public static String pathFlexOMs = null;
	
	public static List<String> pathOMs = new ArrayList<String>();
	public static String baseURL = null;
	public static String baseBase = null;
	public static int basePorta = 0;
	public static String baseLogin = null;
	public static String baseSenha = null;
	
	public static List<PathASBase> pathModulos = new ArrayList<PathASBase>();
	public static List<String> pathRaizProjetos = new ArrayList<String>();
	public static String pathActionScriptProperties = null;
	public static String pathAplication = null;
	
	/*-*-*-* Contantes Privadas *-*-*-*/
	private static final long serialVersionUID = 1L;
	public static final int USUARIO_NORMAL = 0;
	public static final int USUARIO_ROOT = 101;
	
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	private static Gerenciador instance = null;
	private static int usuario = USUARIO_NORMAL;

	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel painelTab1 = new JPanel();
	private JPanel painelTab2 = new JPanel();
	private JPanel painelTab3 = new JPanel();
	private JPanel painelTab4 = new JPanel();
	
	private JPanel painelOMs = new JPanel();
	private JButton btOMJava = new JButton("Gerar OMs - JAVA");
	private JButton btOMFlex = new JButton("Gerar OMs - FLEX");
	
	private JPanel painelGeraTabelas = new JPanel();
	private JButton btTabelas = new JButton("Gerar Tabelas");
	private JLabel labelTabela = new JLabel();
	
	private JPanel painelComparaTabelas = new JPanel();
	private MCaixaCampoTexto ctBaseAtual = new MCaixaCampoTexto("ATUAL", SwingConstants.TOP, MCaixaCampoTexto.TIPO_LIVRE, MCaixaCampoTexto.FORMATO_TEXTO_LIVRE);
	private MCaixaCampoTexto ctBaseNova = new MCaixaCampoTexto("NOVA", SwingConstants.TOP, MCaixaCampoTexto.TIPO_LIVRE, MCaixaCampoTexto.FORMATO_TEXTO_LIVRE);
	private MCaixaCheck chComparaSQL = new MCaixaCheck("SQL Separado", SwingConstants.RIGHT);
	private JButton btComparar = new JButton("Comparar");

	private JPanel painelComparaService = new JPanel();
	private JButton btService = new JButton("Comparar Services");
	
	private List<ArquivoModulo> arquivosModulos;
	private static boolean checkMode = false;
	private JButton btBuscarModulos = new JButton("Buscar Módulos");
	private JButton btCheck = new JButton("Check");
	private JButton btGerar = new JButton("Gerar");
	
	private DefaultTableModel defaultTableModel;
	private JTable tabela;
	private JScrollPane scrollPane;
	
	/*-*-*-* Listeners *-*-*-*/
	private EscutaBotoes escutaBotoes = new EscutaBotoes(this);
	
	/*-*-*-* Construtores *-*-*-*/
	public Gerenciador(String projeto, boolean permiteGeraTabela)
	{
		this.setVisible(false);
		this.setLayout(null);
		this.setSize(370, 330);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setTitle("Atta Gerenciador [" + projeto + "]");
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent windowEvent)
			{
				System.exit(0);
			}
		});

		tabbedPane.setBounds(7, 5, 350, 290);
		addComponente(tabbedPane);

		painelTab1.setLayout(new BoxLayout(painelTab1, BoxLayout.Y_AXIS));
		painelTab2.setLayout(new BoxLayout(painelTab2, BoxLayout.Y_AXIS));
		painelTab3.setLayout(null);
		painelTab4.setLayout(null);
		
		//Painel Classes e Tabelas
		painelOMs.setLayout(null);
		painelOMs.setBorder(BorderFactory.createTitledBorder("Classes"));
		painelTab1.add(painelOMs);
		
		btOMJava.setBounds(10, 25, 320, 40);
		btOMJava.addActionListener(escutaBotoes);
		painelOMs.add(btOMJava);
		
		btOMFlex.setBounds(10, 80, 320, 40);
		btOMFlex.addActionListener(escutaBotoes);
		painelOMs.add(btOMFlex);
		
		painelGeraTabelas.setLayout(null);
		painelGeraTabelas.setBorder(BorderFactory.createTitledBorder("Tabelas"));
		painelTab1.add(painelGeraTabelas);

		if(baseURL!=null && baseBase!=null) { labelTabela.setText(baseURL + "     >     " + baseBase); }
		labelTabela.setBounds(10, 40, 320, 20);
		labelTabela.setHorizontalAlignment(SwingConstants.CENTER);
		painelGeraTabelas.add(labelTabela);
		
		btTabelas.setBounds(10, 65, 320, 40);
		btTabelas.addActionListener(escutaBotoes);
		painelGeraTabelas.add(btTabelas);
		
		
		//Painel Comparar Bases
		painelComparaTabelas.setLayout(null);
		painelComparaTabelas.setBounds(0, 3, 345, 130);
		painelComparaTabelas.setBorder(BorderFactory.createTitledBorder("Tabela"));
		painelTab2.add(painelComparaTabelas);
		
		ctBaseAtual.setLimites(10, 25, 200, 20, 200, 20, 0);
		ctBaseAtual.getMLabel().setText("ATUAL  [" + ComparaBases.baseURLAtual + "]");
		ctBaseAtual.getMLabel().setToolTipText(ComparaBases.baseURLAtual);
		painelComparaTabelas.add(ctBaseAtual);
		
		ctBaseNova.setLimites(10, 70, 200, 20, 200, 20, 0);
		ctBaseNova.getMLabel().setText("NOVA  [" + ComparaBases.baseURLNova + "]");
		ctBaseNova.getMLabel().setToolTipText(ComparaBases.baseURLNova);
		painelComparaTabelas.add(ctBaseNova);

		chComparaSQL.setLimites(225, 45, 100, 20, 20, 20, 5);
		chComparaSQL.getMComponente().setSelected(true);
		painelComparaTabelas.add(chComparaSQL);
		
		btComparar.setBounds(228, 70, 105, 35);
		btComparar.addActionListener(escutaBotoes);
		painelComparaTabelas.add(btComparar);
		
		//Painel Comparar Service
		painelComparaService.setLayout(null);
		painelComparaService.setBorder(BorderFactory.createTitledBorder("Services"));
		painelTab2.add(painelComparaService);

		btService.setBounds(10, 65, 320, 40);
		btService.addActionListener(escutaBotoes);
		painelComparaService.add(btService);
		
		//Painel Modulos
		btBuscarModulos.setBounds(10, 5, 130, 20);
		btBuscarModulos.addActionListener(escutaBotoes);
		painelTab3.add(btBuscarModulos);
		
		btCheck.setBounds(145, 5, 70, 20);
		btCheck.addActionListener(escutaBotoes);
		btCheck.setEnabled(false);
		painelTab3.add(btCheck);
		
		btGerar.setBounds(243, 5, 90, 20);
		btGerar.addActionListener(escutaBotoes);
		btGerar.setEnabled(false);
		painelTab3.add(btGerar);
		
	    tabela = new JTable()
	    {  
			private static final long serialVersionUID = 1L;
			private List<Color> tiposCores;
			private HashMap<String, Color> cores = new HashMap<String, Color>();
			
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex)
    		{  
				Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);  
				String modulo = (String)getValueAt(rowIndex, 1);
    			
				if(tiposCores==null || tiposCores.size()<=0)
				{
					tiposCores = new ArrayList<Color>(Arrays.asList(cor(180, 180, 255), cor(255, 255, 180), cor(180, 255, 180)));
				}
				
    			if(cores.containsKey(modulo))
    			{
    				c.setBackground(cores.get(modulo));
    			}
    			else
    			{
    				if(tiposCores.size()>0)
    				{
    					cores.put(modulo, tiposCores.remove(0));
    					c.setBackground(cores.get(modulo));
    				}
    				else
    				{ c.setBackground(getBackground()); }
    			}
    			return c;  
    		}
	    };  
	    
	    defaultTableModel = new DefaultTableModel(null, new String[]{"X", "Módulo", "Nome"})
	    {
			private static final long serialVersionUID = 1L;
			private Class<?>[] types = new Class[]{Boolean.class, String.class, String.class};
			public Class<?> getColumnClass(int columnIndex)
			{
			    return types[columnIndex];
			}
	    };
		tabela.setModel(defaultTableModel);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(200);

		scrollPane = new JScrollPane(tabela);
	    scrollPane.setBounds(10, 30, 325, 230);
	    painelTab3.add(scrollPane);
		
		
		//TabbedPane
		tabbedPane.addTab("Classes", null, painelTab1, "OMs e Banco de dados");
		tabbedPane.addTab("Comparar", null, painelTab2, "Compara bancos de dados");
		tabbedPane.addTab("ActionScript", null, painelTab3, "Define modulos para o flex");
		tabbedPane.addTab("Popula", null, painelTab4, "Popula o banco de dados");
		
		if(pathActionScriptProperties==null || pathModulos.size()<=0)
		{
			tabbedPane.setEnabledAt(2, false);
		}
		if(ComparaBases.baseURLNova==null || ComparaBases.baseURLAtual==null)
		{
			tabbedPane.setEnabledAt(1, false);
		}
		if(arquivoERM==null || pathJavaOMs==null)
		{
			btOMJava.setEnabled(false);
		}
		if(pathFlexOMs==null || pathJavaOMs==null)
		{
			btOMFlex.setEnabled(false);
		}
		btTabelas.setEnabled(permiteGeraTabela);
		tabbedPane.setEnabledAt(3, false);
	}

	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Método utilizado para abrir (exibir) esta janela
	 */
	public void abrir()
	{
		this.setVisible(true);
	}
	
	/**
	 * Metodo utilizado para fechar esta janela
	 * @return true caso ela tenha sido fechada com sucesso e false caso não tenha sido fechada
	 */
	public boolean fechar()
	{
		this.setVisible(false);
		return true;
	}
	
	/*-*-*-* Listeners *-*-*-*/
	/**
	 * Escuta eventos em botoes
	 * @param actionEvent
	 */
	public void escutaBotoes(ActionEvent actionEvent)
	{
		try
		{
			if(actionEvent.getSource()==btOMJava) 				{ gerarOMJava(); }
			else if(actionEvent.getSource()==btOMFlex)			{ gerarOMFlex(); }
			else if(actionEvent.getSource()==btTabelas) 		{ gerarTabelas(); }
			else if(actionEvent.getSource()==btBuscarModulos)	{ buscarModulos(); }
			else if(actionEvent.getSource()==btCheck)			{ checkTodos(); }
			else if(actionEvent.getSource()==btGerar)			{ gerarArquivoActionScript(); }
			else if(actionEvent.getSource()==btComparar)		{ compararBases(); }
			else if(actionEvent.getSource()==btService)			{ compararServices(); }
		}
		catch(Exception e) 
		{ e.printStackTrace(); }
	}

	/**
	 * Gera os OMs do java (A partir do desenho .erm)
	 */
	public void gerarOMJava()
	{
		System.out.println("Gerando OMs JAVA");
		List<Tabela> tabelas = GeraOM.obterTabelas(arquivoERM);
		GeraOM.gerarOMs(tabelas, pathJavaOMs, GeraOM.obterPacote(pathJavaOMs));
		System.out.println("OMs JAVA Gerados com Sucesso");
	}
	
	/**
	 * Gera os OMs do flex (A partir das classes OM Java)
	 */
	public void gerarOMFlex() throws Exception
	{
		System.out.println("Gerando OMs FLEX");
		List<Class<?>> classes = HibernateFactory.obterClasses(new File(pathJavaOMs), GeraOM.obterPacote(pathJavaOMs));
		GeraOMFlex.flexOMs(classes, pathFlexOMs, GeraOM.obterPacote(pathFlexOMs));
		System.out.println("OMs FLEX Gerados com Sucesso");
	}
	
	/**
	 * Gera as tabelas do banco de dados
	 */
	public void gerarTabelas() throws Exception
	{
		if(usuario==USUARIO_ROOT || baseURL.equalsIgnoreCase("localhost") || baseURL.equalsIgnoreCase("127.0.0.1"))
		{
			System.out.println("Gerando Tabelas");
			HibernateFactory.criarTabelas(baseURL, baseBase, basePorta, baseLogin, baseSenha, pathOMs);
			System.out.println("Tabelas Geradas com Sucesso");
		}
		else
		{
			MensagemBox.mensagemOk("Voce nao pode criar tabelas em um endereco diferente de localhost", baseURL, MensagemBox.ICONE_ERRO, null);
		}
	}
	
	/**
	 * Compara bases do banco de dados
	 */
	public void compararBases() throws Exception
	{
		System.out.println("------------------------------ Comparando Bases -------------------------------");
		System.out.println(ComparaBases.comparar(ctBaseNova.getMComponente().getText(), ctBaseAtual.getMComponente().getText(), chComparaSQL.getMComponente().isSelected()));
	}
	
	/**
	 * Compara chamadas de services do Flex com Services existentes no Java indicando os erros no console
	 */
	public void compararServices()
	{
		System.out.println("------------------------------ Comparando Services -------------------------------");
		ComparaServices.comparar(pathModulos, pathRaizProjetos);
	}
	
	/**
	 * Metodo que busca os modulos dos projetos
	 */
	public void buscarModulos() throws Exception
	{
		System.out.println("Iniciando Busca");
		
		while(defaultTableModel.getRowCount()>0) { defaultTableModel.removeRow(0); }
		
		arquivosModulos = GeraActionScript.obtemModulos(pathModulos);
		
		String conteudo = ArquivoUtil.getString(pathActionScriptProperties);
		String modulo;
		boolean contem;
		for(int i=0; i<arquivosModulos.size(); i++)
		{
			modulo = arquivosModulos.get(i).getArquivo().getAbsolutePath();
			modulo = modulo.substring(modulo.indexOf("atta")+5);
			modulo = modulo.substring(0, modulo.indexOf("\\"));
			if(modulo.equals("rogramacao")) 	{ modulo = "Específico"; }
			else if(modulo.equals("modulos")) 	{ modulo = "Atta"; }
			
			contem = conteudo.contains(arquivosModulos.get(i).getArquivo().getAbsolutePath().substring(arquivosModulos.get(i).getArquivo().getAbsolutePath().indexOf("flex_src")).replace("\\", "/"));
			defaultTableModel.addRow(new Object[]{contem, modulo, arquivosModulos.get(i).getArquivo().getName()});
		}
		btCheck.setEnabled(true);
		btGerar.setEnabled(true);
		System.out.println("Fim da Busca");
	}
	
	/**
	 * Metodo que intercaladamente define a selecao de todos os itens da lista, iniciando por selecionado
	 */
	public void checkTodos()
	{
		checkMode = !checkMode;
		for(int i=0; i<defaultTableModel.getRowCount(); i++)
		{
			defaultTableModel.setValueAt(checkMode, i, 0);
		}
	}
	
	/**
	 * Metodo que gera o arquivo actionScript de acordo com os modulos selecionados na tabela
	 * @param pathAplication endereco do aplication, exemplo flex_src/br/com/mresolucoes/aplicacao/MainCemara.mxml
	 * @throws Exception
	 */
	public void gerarArquivoActionScript() throws Exception
	{
		List<ArquivoModulo> arquivosModulosIn = new ArrayList<ArquivoModulo>();
		for(int i=0; i<defaultTableModel.getRowCount(); i++)
		{
			if((Boolean)defaultTableModel.getValueAt(i, 0))
			{
				arquivosModulosIn.add(arquivosModulos.get(i));
			}
		}
		
		File fileAction = new File(pathActionScriptProperties);
		String conteudo = GeraActionScript.gerarActionScript(arquivosModulosIn, fileAction, pathAplication);
		FileOutputStream fos = new FileOutputStream(fileAction);
		fos.write(conteudo.getBytes());
		fos.flush();
		fos.close();
		MensagemBox.mensagemOk("Arquivo gerado com sucesso", "actionScriptProperties", MensagemBox.ICONE_INFORMACAO, this);
	}
	
	/**
	 * Metodo que instancia uma nova cor
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	private Color cor(int r, int g, int b)
	{
		return new Color(r, g, b);
	}

	/**
	 * Adiciona um componente nesta classe com seu zorder "0", ou seja, afrente de todos os outros componentes
	 * @param component a ser adicionado
	 */
	public void addComponente(JComponent component)
	{
		this.getContentPane().add(component);
		this.getContentPane().setComponentZOrder(component, 0);
	}
	
	/**
	 * Metodo statico para obetencao da instancia singleton da classe
	 * @return uma instancia singleton da classe
	 */
	public static Gerenciador getInstance(String[] args, String projeto, boolean permiteGeraTabela)
	{
		if(instance==null)				{ instance = new Gerenciador(projeto, permiteGeraTabela); }
		if(args!=null && args.length>0)	{ usuario = IntegerUtil.toInteger(args[0]); }
		return instance;
	}
}
