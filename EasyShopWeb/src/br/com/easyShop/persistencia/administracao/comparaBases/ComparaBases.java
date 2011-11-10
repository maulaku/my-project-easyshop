package br.com.easyShop.persistencia.administracao.comparaBases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.easyShop.persistencia.administracao.comparaBases.auxiliar.Coluna;
import br.com.easyShop.persistencia.administracao.comparaBases.auxiliar.Tabela;
import br.com.easyShop.persistencia.conexao.servidores.PostgresJDBC;
import br.com.easyShop.persistencia.utils.ResultSQL;

public class ComparaBases
{
	public static String baseURLNova = null;
	public static int basePortaNova = 0;
	public static String baseLoginNova = null;
	public static String baseSenhaNova = null;
	
	public static String baseURLAtual = null;
	public static int basePortaAtual = 0;
	public static String baseLoginAtual = null;
	public static String baseSenhaAtual = null;
	
	/**
	 * Gera um relatorio texto das diferencas entre as bases de dados 
	 * @param baseNova
	 * @param baseAntiga
	 * @return relatorio texto
	 */
	public static String comparar(String baseNova, String baseAntiga, boolean isolarScripts)
	{
		StringBuffer sb = new StringBuffer();
		StringBuffer script = new StringBuffer();
		StringBuffer scriptTabelasInserir = new StringBuffer();
		
		try
		{
			PostgresJDBC jdbcNova = new PostgresJDBC(baseURLNova, baseNova, basePortaNova, baseLoginNova, baseSenhaNova, null);
			PostgresJDBC jdbcAntiga = new PostgresJDBC(baseURLAtual, baseAntiga, basePortaAtual, baseLoginAtual, baseSenhaAtual, null);
						
			List<Tabela> tabelasOrigem = obtemTabelas(jdbcAntiga);
			List<Tabela> tabelasDestino = obtemTabelas(jdbcNova);

			List<Tabela> tabelasRemover = new ArrayList<Tabela>();

			Tabela tabelaO;
			Tabela tabelaD;
			Coluna colunaO;
			Coluna colunaD;
			while(tabelasOrigem.size()>0)
			{
				tabelaO = tabelasOrigem.remove(0);
				
				if(tabelasDestino.contains(tabelaO))
				{
					tabelaD = tabelasDestino.remove(tabelasDestino.indexOf(tabelaO));
					
					boolean igual = true;
					while(tabelaO.getColunas().size()>0)
					{
						colunaO = tabelaO.getColunas().remove(0);
						
						if(tabelaD.getColunas().contains(colunaO))
						{
							colunaD = tabelaD.getColunas().remove(tabelaD.getColunas().indexOf(colunaO));
							
							if(colunaO.getTipo()!=colunaD.getTipo())
							{
								if(igual) { sb.append("\n\nVerificando " + tabelaD.getNome().toUpperCase()); igual=false; }
								sb.append("\n\tTipo: " + colunaO + "\n\t\t[" + colunaO.toTipo(tabelaD, colunaD.getTipo()) + "]");
								script.append("\n" + colunaO.toTipo(tabelaD, colunaD.getTipo()) + ";");
							}
							else if(colunaO.isNotNull()==false && colunaD.isNotNull())
							{
								if(igual) { sb.append("\n\nVerificando " + tabelaD.getNome().toUpperCase()); igual=false; }
								sb.append("\n\tNot Nullo: " + colunaO + "\n\t\t[" + colunaO.toNotNull(tabelaD) + "]");
								script.append("\n" + colunaO.toNotNull(tabelaD) + ";");
							}
							else if(colunaO.isNotNull() && colunaD.isNotNull()==false)
							{
								if(igual) { sb.append("\n\nVerificando " + tabelaD.getNome().toUpperCase()); igual=false; }
								sb.append("\n\tNullo: " + colunaO + "\n\t\t[" + colunaO.toNull(tabelaD) + "]");
								script.append("\n" + colunaO.toNull(tabelaD) + ";");
							}
						}
						else
						{
							if(igual) { sb.append("\n\nVerificando " + tabelaD.getNome().toUpperCase()); igual=false; }
							sb.append("\n\tRemover: " + colunaO + "\n\t\t[" + colunaO.toRemove(tabelaD) + "]");
							script.append("\n" + colunaO.toRemove(tabelaD) + ";");
						}
					}
					
					for(int i=0; i<tabelaD.getColunas().size(); i++)
					{
						if(igual) { sb.append("\n\nVerificando " + tabelaD.getNome().toUpperCase()); igual=false; }
						sb.append("\n\tInserir: " + tabelaD.getColunas().get(i) + "\n\t\t[" + tabelaD.getColunas().get(i).toInsert(tabelaD, "\t\t ") + "]");
						script.append("\n" + tabelaD.getColunas().get(i).toInsert(tabelaD, "") + ";");
					}
				}
				else
				{
					tabelasRemover.add(tabelaO);
				}
			}
			
			if(tabelasRemover.size()>0) { sb.append("\n\nTabelas para REMOVER"); }
			for(int i=0; i<tabelasRemover.size(); i++)
			{
				sb.append("\n\t" + tabelasRemover.get(i) + "\n\t\t[" + tabelasRemover.get(i).toRemove() + "]");
				script.append("\n" + tabelasRemover.get(i).toRemove() + ";");
			}
			
			if(tabelasDestino.size()>0) { sb.append("\n\nTabelas para INSERIR"); }
			//Ordena as tabelas por quantidade de Fks, euristica que busca colocar as tabelas com menos dependencias para serem inseridas primeiro
			Collections.sort(tabelasDestino, new Comparator<Tabela>()
			{
				@Override
				public int compare(Tabela o1, Tabela o2)
				{
					int nrFksO1=0, nrFksO2=0;
					for(int j=0; j<o1.getColunas().size(); j++) { nrFksO1 = nrFksO1 + (o1.getColunas().get(j).getNome().startsWith("fk") ? 1 : 0); }
					for(int j=0; j<o2.getColunas().size(); j++) { nrFksO2 = nrFksO2 + (o2.getColunas().get(j).getNome().startsWith("fk") ? 1 : 0); }
					return nrFksO1 - nrFksO2;
				}
				
			});
			for(int i=0; i<tabelasDestino.size(); i++)
			{
				sb.append("\n\n\t" + tabelasDestino.get(i) + "\n\t\t[" + tabelasDestino.get(i).toInsert("\t\t") + "]");
				scriptTabelasInserir.append("\n" + tabelasDestino.get(i).toInsert("") + ";");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(sb.length()<=0)
		{
			return "\nAs bases de dados estão iguais =D";
		}
		
		if(isolarScripts)
		{
			sb.append("\n\n\n----------------------------------- SCRIPTS -----------------------------------\n");
			sb.append(scriptTabelasInserir);
			sb.append(script);
		}

		return sb.toString().startsWith("\n\n") ? sb.toString().substring(2) : sb.toString();
	}
	
	
	/**
	 * Obtem a lista de tabelas e suas colunas de uma base de dados
	 * @param jdbc conexao com a base de dados
	 * @return lista de Tabela preenchida
	 */
	public static List<Tabela> obtemTabelas(PostgresJDBC jdbc)
	{
		Tabela tabela;
		Coluna coluna;
		List<Coluna> colunas;
		List<Tabela> tabelas = new ArrayList<Tabela>();
		ResultSQL resultSQLColuna;
		
		try
		{
			ResultSQL resultSQLTabela = jdbc.executaSelect("SELECT * FROM pg_tables WHERE tablename NOT LIKE 'pg\\_%' AND tablename NOT LIKE 'sql\\_%' ORDER BY tablename");
			for(int i=0; i<resultSQLTabela.size(); i++)
			{
				tabela = new Tabela();
				tabela.setNome((String)resultSQLTabela.get(i, "tablename"));
				
				resultSQLColuna = jdbc.executaSelect("SELECT * FROM pg_attribute, pg_type WHERE typname='" + tabela.getNome() + "' AND attrelid=typrelid AND attname NOT IN ('cmin', 'cmax', 'ctid', 'oid', 'tableoid', 'xmin', 'xmax') AND attname NOT LIKE '...%'");
				colunas = new ArrayList<Coluna>();
				for(int j=0; j<resultSQLColuna.size(); j++)
				{
					coluna = new Coluna();
					coluna.setNome((String)resultSQLColuna.get(j, "attname"));
					coluna.setTipo((Long)resultSQLColuna.get(j, "atttypid"));
					coluna.setNotNull((Boolean)resultSQLColuna.get(j, "attnotnull"));
					colunas.add(coluna);
				}
				
				tabela.setColunas(colunas);
				tabelas.add(tabela);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return tabelas;
	}
}
