package br.com.easyShop.utils;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
/*
 * Classe desenvolvida por WALLDEMAR THEODORO.
 * Criei esta classe por não achar nada fácil de se implementar na internet.
 * Todos os calendários continham muitas sub-classes, e de difícel aplicação
 * PEÇO SÓ UM FAVOR: NÃO APAGAR OS CRÉDITOS
 * Sem mais, façãm bom uso da mesma.
 */

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Calendario extends javax.swing.JFrame {
	private JComboBox jComboBox1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JButton jButton16;
	private JButton jButton15;
	private JButton jButton14;
	private JButton jButton13;
	private JButton jButton12;
	private JButton jButton11;
	private JButton jButton10;
	private JButton jButton9;
	private JButton jButton8;
	private JButton jButton7;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton1;
	private JLabel jLabel8;
	private JButton jButton4;
	private JButton jButton3;
	private JButton jButton2;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JLabel jLabel1;
	private JButton jButton26;
	private JButton jButton28;
	private JComboBox jComboBox3;
	private JButton jButton37;
	private JButton jButton36;
	private JButton jButton35;
	private JButton jButton34;
	private JButton jButton33;
	private JButton jButton32;
	private JButton jButton31;
	private JButton jButton30;
	private JButton jButton29;
	private JButton jButton27;
	private JButton jButton25;
	private JButton jButton24;
	private JButton jButton23;
	private JButton jButton22;
	private JButton jButton21;
	private JButton jButton20;
	private JButton jButton19;
	private JButton jButton18;
	private JButton jButton17;
	private Date data = new Date();
	private Calendar data1 = new GregorianCalendar();


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Calendario inst = new Calendario();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Calendario() {
		super();
		initGUI();
}
	
	private void initGUI() {

		try {

			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7, 7, 7, 7};
			getContentPane().setLayout(thisLayout);
			this.setVisible(false);
			this.setResizable(false);
//*****CRIANDO MENU DE MESES
			{
				ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(
						new String[] { "Janeiro", "Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro" });
				jComboBox1 = new JComboBox();
				getContentPane().add(jComboBox1, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setPreferredSize(new java.awt.Dimension(15, 20));
				jComboBox1.setFont(new java.awt.Font("Arial",1,12));
				jComboBox1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jComboBox1ActionPerformed(evt);
					}
				});
			}
//*****CRIANDO MENU DE ANOS
			{
				ComboBoxModel jComboBox3Model = new DefaultComboBoxModel(
							new String[] {"2009", "2010","2011", "2012","2013", "2014","2015", "2016"});
				jComboBox3 = new JComboBox();
				getContentPane().add(jComboBox3, new GridBagConstraints(5, 0, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				jComboBox3.setModel(jComboBox3Model);
				jComboBox3.setPreferredSize(new java.awt.Dimension(15, 20));
				jComboBox3.setFont(new java.awt.Font("Arial",1,12));
				jComboBox3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jComboBox3ActionPerformed(evt);
					}
				});
			}
//****CONSTRUINDO A CARA DO CALENDÁRIO*****
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabel1.setText("Dom");
				jLabel1.setBackground(new java.awt.Color(255,0,0));
				jLabel1.setForeground(new java.awt.Color(255,0,0));
				jLabel1.setFont(new java.awt.Font("Arial",1,12));
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabel2.setText("Seg");
				jLabel2.setForeground(new java.awt.Color(0,128,192));
				jLabel2.setFont(new java.awt.Font("Arial",1,12));
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabel3.setText("Ter");
				jLabel3.setForeground(new java.awt.Color(0,128,192));
				jLabel3.setFont(new java.awt.Font("Arial",1,12));
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabel4.setText("Qua");
				jLabel4.setForeground(new java.awt.Color(0,128,192));
				jLabel4.setFont(new java.awt.Font("Arial",1,12));
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabel5.setText("Qui");
				jLabel5.setForeground(new java.awt.Color(0,128,192));
				jLabel5.setFont(new java.awt.Font("Arial",1,12));
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabel6.setText("Sex");
				jLabel6.setForeground(new java.awt.Color(0,128,192));
				jLabel6.setFont(new java.awt.Font("Arial",1,12));
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7, new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jLabel7.setText("Sab");
				jLabel7.setForeground(new java.awt.Color(0,124,185));
				jLabel7.setFont(new java.awt.Font("Arial",1,12));
			}
			
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton2 = new JButton();
				getContentPane().add(jButton2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton3 = new JButton();
				getContentPane().add(jButton3, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton4 = new JButton();
				getContentPane().add(jButton4, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton5 = new JButton();
				getContentPane().add(jButton5, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton6 = new JButton();
				getContentPane().add(jButton6, new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton7 = new JButton();
				getContentPane().add(jButton7, new GridBagConstraints(6, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton8 = new JButton();
				getContentPane().add(jButton8, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton9 = new JButton();
				getContentPane().add(jButton9, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton10 = new JButton();
				getContentPane().add(jButton10, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton11 = new JButton();
				getContentPane().add(jButton11, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton12 = new JButton();
				getContentPane().add(jButton12, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton13 = new JButton();
				getContentPane().add(jButton13, new GridBagConstraints(5, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton14 = new JButton();
				getContentPane().add(jButton14, new GridBagConstraints(6, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton15 = new JButton();
				getContentPane().add(jButton15, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton16 = new JButton();
				getContentPane().add(jButton16, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton17 = new JButton();
				getContentPane().add(jButton17, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton18 = new JButton();
				getContentPane().add(jButton18, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton19 = new JButton();
				getContentPane().add(jButton19, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton20 = new JButton();
				getContentPane().add(jButton20, new GridBagConstraints(5, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton21 = new JButton();
				getContentPane().add(jButton21, new GridBagConstraints(6, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton22 = new JButton();
				getContentPane().add(jButton22, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton23 = new JButton();
				getContentPane().add(jButton23, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton24 = new JButton();
				getContentPane().add(jButton24, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton25 = new JButton();
				getContentPane().add(jButton25, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton26 = new JButton();
				getContentPane().add(jButton26, new GridBagConstraints(4, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton27 = new JButton();
				getContentPane().add(jButton27, new GridBagConstraints(5, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton28 = new JButton();
				getContentPane().add(jButton28, new GridBagConstraints(6, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton29 = new JButton();
				getContentPane().add(jButton29, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton30 = new JButton();
				getContentPane().add(jButton30, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton31 = new JButton();
				getContentPane().add(jButton31, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton32 = new JButton();
				getContentPane().add(jButton32, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton33 = new JButton();
				getContentPane().add(jButton33, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton34 = new JButton();
				getContentPane().add(jButton34, new GridBagConstraints(5, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton35 = new JButton();
				getContentPane().add(jButton35, new GridBagConstraints(6, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton36 = new JButton();
				getContentPane().add(jButton36, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jButton37 = new JButton();
				getContentPane().add(jButton37, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				jLabel8 = new JLabel();
				getContentPane().add(jLabel8, new GridBagConstraints(6, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabel8.setText("by Wall");
				jLabel8.setFont(new java.awt.Font("Arial",2,8));
			}

			pack();
			setSize(350, 300);
			
			tratandoData();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jComboBox1ActionPerformed(ActionEvent evt) {
		tratandoData();// A CADA ESCOLHA DO MES, REFAZ TODO O CALENDÁRIO
	}
	private void jComboBox3ActionPerformed(ActionEvent evt) {
		tratandoData();// A CADA ESCOLHA DO ANO, REFAZ TODO O CALENDÁRIO
	}	
	private void tratandoData(){

		
		//**************TRATANDO DA DATA*******************
		
	    int year = data1.get(Calendar.YEAR);             // 2002
	    int month = data1.get(Calendar.MONTH);           // 0=Jan, 1=Feb, ...
	    int day = data1.get(Calendar.DAY_OF_MONTH);      // 1...
	    int dayOfWeek = data1.get(Calendar.DAY_OF_WEEK); // 1=Sunday, 2=Monday, ...
	    int diasDoMes;

	    String dia_semana1 = "";
		int dia, mes, ano, dia_semana;		
		dia = data.getDate();//
		mes = data.getMonth();
		ano = (1900+data.getYear());
		dia_semana = data.getDay();// 0 a 6
		
		String mes1 = (String) jComboBox1.getSelectedItem();
		if(mes1.equals("Janeiro")){mes = 0;}
		if(mes1.equals("Fevereiro")){mes = 1;}
		if(mes1.equals("Março")){mes = 2;}
		if(mes1.equals("Abril")){mes = 3;}
		if(mes1.equals("Maio")){mes = 4;}
		if(mes1.equals("Junho")){mes = 5;}
		if(mes1.equals("Julho")){mes = 6;}
		if(mes1.equals("Agosto")){mes = 7;}
		if(mes1.equals("Setembro")){mes = 8;}
		if(mes1.equals("Outubro")){mes = 9;}
		if(mes1.equals("Novembro")){mes = 10;}
		if(mes1.equals("Dezembro")){mes = 11;}
		data.setMonth(mes);	
		
		switch(dayOfWeek){
		case 1: dia_semana1 = "Domingo";break;
		case 2: dia_semana1 = "Segunda";break;
		case 3: dia_semana1 = "Terça-feira";break;
		case 4: dia_semana1 = "Quarta-feira";break;
		case 5: dia_semana1 = "Quinta-feira";break;
		case 6: dia_semana1 = "Sexta-feira";break;
		case 7: dia_semana1 = "Sábado";break;
		}

		String ano1 = (String) jComboBox3.getSelectedItem();
		int ano3=0;
		if(ano1.equals("2009")){ano3 = 2009;}
		if(ano1.equals("2010")){ano3 = 2010;}
		if(ano1.equals("2011")){ano3 = 2011;}
		if(ano1.equals("2012")){ano3 = 2012;}
		if(ano1.equals("2013")){ano3 = 2013;}
		if(ano1.equals("2014")){ano3 = 2014;}
		if(ano1.equals("2015")){ano3 = 2015;}
		if(ano1.equals("2016")){ano3 = 2015;}
		data.setYear(ano3);
		
		data1.set(ano3,mes,1);
	    year = data1.get(Calendar.YEAR);             
	    month = data1.get(Calendar.MONTH);          
	    day = data1.get(Calendar.DAY_OF_MONTH);     
	    dayOfWeek = data1.get(Calendar.DAY_OF_WEEK); 
	    diasDoMes = dayOfWeek;
	    
		int mes2, dia2, ano2;
		dia2 = data.getDate();
		mes2 = data.getMonth()+1;
		ano2 = data.getYear();

		String data = 
			((dia2 < 10) ? "0" : "") 
			+ dia2 	
			+ "/" 
			+ ((mes2 < 10) ? "0" : "") 
			+ mes2 
			+ "/" 
			+ ano2; 
//        System.out.println(data+" "+dia_semana1+" "+dayOfWeek);  //PARA SABER QUAL DIA É HOJE
//        System.out.println(""+day+"/"+(month+1)+"/"+year); //PARA DEFINIR EM QUAL DIA DA SEMANA O MES COMEÇA
	
//**************FIM TRATAMENTO DA DATA******************
//**************TRATANDO BOTOES*************************

        		if(dayOfWeek==1){
        		jButton1.setVisible(true);
        		jButton2.setVisible(true);
        		jButton3.setVisible(true);
        		jButton4.setVisible(true);
        		jButton5.setVisible(true);
        		jButton6.setVisible(true);
        		jButton7.setVisible(true);
        		}

        		if(dayOfWeek==2){
        		jButton1.setVisible(false);
           		jButton2.setVisible(true);
        		jButton3.setVisible(true);
        		jButton4.setVisible(true);
        		jButton5.setVisible(true);
        		jButton6.setVisible(true);
        		jButton7.setVisible(true);

        	}
        	if(dayOfWeek==3){
        		jButton1.setVisible(false);
        		jButton2.setVisible(false);
        		jButton3.setVisible(true);
        		jButton4.setVisible(true);
        		jButton5.setVisible(true);
        		jButton6.setVisible(true);
        		jButton7.setVisible(true);

        	}
        	if(dayOfWeek==4){
        		jButton1.setVisible(false);
        		jButton2.setVisible(false);
        		jButton3.setVisible(false);
        		jButton4.setVisible(true);
        		jButton5.setVisible(true);
        		jButton6.setVisible(true);
        		jButton7.setVisible(true);

        	}
        	if(dayOfWeek==5){
        		jButton1.setVisible(false);
        		jButton2.setVisible(false);
        		jButton3.setVisible(false);
        		jButton4.setVisible(false);
           		jButton5.setVisible(true);
        		jButton6.setVisible(true);
        		jButton7.setVisible(true);

        	}
        	if(dayOfWeek==6){
        		jButton1.setVisible(false);
        		jButton2.setVisible(false);
        		jButton3.setVisible(false);
        		jButton4.setVisible(false);
        		jButton5.setVisible(false);
        		jButton6.setVisible(true);
        		jButton7.setVisible(true);
        	}
        	if(dayOfWeek==7){
        		jButton1.setVisible(false);
        		jButton2.setVisible(false);
        		jButton3.setVisible(false);
        		jButton4.setVisible(false);
        		jButton5.setVisible(false);
        		jButton6.setVisible(false);
        		jButton7.setVisible(true);
        	}

        	month=month+1;
    		ano1 = (String) jComboBox3.getSelectedItem();
    		if(ano1.equals("2009")){ano3 = 2009;}
    		if(ano1.equals("2010")){ano3 = 2010;}
    		if(ano1.equals("2011")){ano3 = 2011;}
    		if(ano1.equals("2012")){ano3 = 2012;}
    		if(ano1.equals("2013")){ano3 = 2013;}
    		if(ano1.equals("2014")){ano3 = 2014;}
    		if(ano1.equals("2015")){ano3 = 2015;}
    		if(ano1.equals("2016")){ano3 = 2015;}
        	data1.set(ano3,month,1);
 
    		data1.add(Calendar.DAY_OF_MONTH,-1);
    		day = data1.get(Calendar.DAY_OF_MONTH);      
//    		System.out.println(""+day+"/"+month+"/"+year); // PARA SABER QUAL O ÚLTIMO DIA DO MÊS
    		diasDoMes=diasDoMes+day;
//    		System.out.println("dias do mes: "+diasDoMes);  //PARA DEFINIR ATE QUAL BOTAO DEVE SER TRUE
//********************************

    		if(diasDoMes==29){
        		jButton29.setVisible(false);
        		jButton30.setVisible(false);
        		jButton31.setVisible(false);
        		jButton32.setVisible(false);
        		jButton33.setVisible(false);
        		jButton34.setVisible(false);
        		jButton35.setVisible(false);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}

    		if(diasDoMes==30){
        		jButton29.setVisible(true);
        		jButton30.setVisible(false);
        		jButton31.setVisible(false);
        		jButton32.setVisible(false);
        		jButton33.setVisible(false);
        		jButton34.setVisible(false);
        		jButton35.setVisible(false);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}
    		
    		if(diasDoMes==31){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(false);
        		jButton32.setVisible(false);
        		jButton33.setVisible(false);
        		jButton34.setVisible(false);
        		jButton35.setVisible(false);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}
    		
    		if(diasDoMes==32){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(true);
        		jButton32.setVisible(false);
        		jButton33.setVisible(false);
        		jButton34.setVisible(false);
        		jButton35.setVisible(false);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}
    		
    		if(diasDoMes==33){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(true);
        		jButton32.setVisible(true);
        		jButton33.setVisible(false);
        		jButton34.setVisible(false);
        		jButton35.setVisible(false);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}

    		if(diasDoMes==34){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(true);
        		jButton32.setVisible(true);
        		jButton33.setVisible(true);
        		jButton34.setVisible(false);
        		jButton35.setVisible(false);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}
    		
    		if(diasDoMes==35){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(true);
        		jButton32.setVisible(true);
        		jButton33.setVisible(true);
        		jButton34.setVisible(true);
        		jButton35.setVisible(false);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}
    		
    		if(diasDoMes==36){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(true);
        		jButton32.setVisible(true);
        		jButton33.setVisible(true);
        		jButton34.setVisible(true);
        		jButton35.setVisible(true);
        		jButton36.setVisible(false);
        		jButton37.setVisible(false);
        		}
    		
    		if(diasDoMes==37){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(true);
        		jButton32.setVisible(true);
        		jButton33.setVisible(true);
        		jButton34.setVisible(true);
        		jButton35.setVisible(true);
        		jButton36.setVisible(true);
        		jButton37.setVisible(false);
        		}
    		
    		if(diasDoMes==38){
        		jButton29.setVisible(true);
        		jButton30.setVisible(true);
        		jButton31.setVisible(true);
        		jButton32.setVisible(true);
        		jButton33.setVisible(true);
        		jButton34.setVisible(true);
        		jButton35.setVisible(true);
        		jButton36.setVisible(true);
        		jButton37.setVisible(true);
        		}
    		
//******Escrevendo nos botoes (setText)*********    		
    		int i = 1;
    		if (jButton1.isVisible()){jButton1.setText(String.valueOf(i));i++;}
    		if (jButton2.isVisible()){jButton2.setText(String.valueOf(i));i++;}
    		if (jButton3.isVisible()){jButton3.setText(String.valueOf(i));i++;}
    		if (jButton4.isVisible()){jButton4.setText(String.valueOf(i));i++;}
    		if (jButton5.isVisible()){jButton5.setText(String.valueOf(i));i++;}
    		if (jButton6.isVisible()){jButton6.setText(String.valueOf(i));i++;}
    		if (jButton7.isVisible()){jButton7.setText(String.valueOf(i));i++;}
    		if (jButton8.isVisible()){jButton8.setText(String.valueOf(i));i++;}
    		if (jButton9.isVisible()){jButton9.setText(String.valueOf(i));i++;}
    		if (jButton10.isVisible()){jButton10.setText(String.valueOf(i));i++;}
    		if (jButton11.isVisible()){jButton11.setText(String.valueOf(i));i++;}
    		if (jButton12.isVisible()){jButton12.setText(String.valueOf(i));i++;}
    		if (jButton13.isVisible()){jButton13.setText(String.valueOf(i));i++;}
    		if (jButton14.isVisible()){jButton14.setText(String.valueOf(i));i++;}
    		if (jButton15.isVisible()){jButton15.setText(String.valueOf(i));i++;}
    		if (jButton16.isVisible()){jButton16.setText(String.valueOf(i));i++;}
    		if (jButton17.isVisible()){jButton17.setText(String.valueOf(i));i++;}
    		if (jButton18.isVisible()){jButton18.setText(String.valueOf(i));i++;}
    		if (jButton19.isVisible()){jButton19.setText(String.valueOf(i));i++;}
    		if (jButton20.isVisible()){jButton20.setText(String.valueOf(i));i++;}
    		if (jButton21.isVisible()){jButton21.setText(String.valueOf(i));i++;}
    		if (jButton22.isVisible()){jButton22.setText(String.valueOf(i));i++;}
    		if (jButton23.isVisible()){jButton23.setText(String.valueOf(i));i++;}
    		if (jButton24.isVisible()){jButton24.setText(String.valueOf(i));i++;}
    		if (jButton25.isVisible()){jButton25.setText(String.valueOf(i));i++;}
    		if (jButton26.isVisible()){jButton26.setText(String.valueOf(i));i++;}
    		if (jButton27.isVisible()){jButton27.setText(String.valueOf(i));i++;}
    		if (jButton28.isVisible()){jButton28.setText(String.valueOf(i));i++;}
    		if (jButton29.isVisible()){jButton29.setText(String.valueOf(i));i++;}
    		if (jButton30.isVisible()){jButton30.setText(String.valueOf(i));i++;}
    		if (jButton31.isVisible()){jButton31.setText(String.valueOf(i));i++;}
    		if (jButton32.isVisible()){jButton32.setText(String.valueOf(i));i++;}
    		if (jButton33.isVisible()){jButton33.setText(String.valueOf(i));i++;}
    		if (jButton34.isVisible()){jButton34.setText(String.valueOf(i));i++;}
    		if (jButton35.isVisible()){jButton35.setText(String.valueOf(i));i++;}
    		if (jButton36.isVisible()){jButton36.setText(String.valueOf(i));i++;}
    		if (jButton37.isVisible()){jButton37.setText(String.valueOf(i));i++;}
//*************FIM TRATAMENTO DOS BOTOES*********************     
	}

}//****FIM DA CLASSE********
