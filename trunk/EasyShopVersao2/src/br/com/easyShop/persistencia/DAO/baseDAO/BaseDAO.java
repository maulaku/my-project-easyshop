package br.com.easyShop.persistencia.DAO.baseDAO;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;

import logs.Logs;
import br.com.easyShop.persistencia.DAO.baseDAO.auxiliar.OMObtido;
import br.com.easyShop.persistencia.conexao.servidores.base.BaseJDBC;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.persistencia.utils.ResultSQL;


/**
 * Classe responsavel conter os metodos basicos de acesso ao banco de dados
 */
public class BaseDAO extends BaseDAOSQL
{
	/*-*-*-* Constantes Publicas *-*-*-*/
	/**
	 * Constante utilizada para definir se o metodo deve considerar as classes em uma busca de profundidade
	 */
	public static final int CONSIDERAR_CLASSES = 8080;
	
	/**
	 * Constante utilizada para definir se o metodo deve ignorar as classes em uma busca de profundidade
	 */
	public static final int IGNORAR_CLASSES = 8081;
	
	
	/*-*-*-* Metodos Publicos *-*-*-*/
	/**
	 * Metodo que Salva (Insere ou Altera) um registro no banco de dados dependendo do valor da pk (pk nulla ou <=0 insere o registro, pk não nulla ou >0 altera o registro), a pk do registro e preenchida no campo com a annotation Id
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * <BR>{@link BaseDAOSQL#objectToUpdateSQL(Object, BaseJDBC, Class[], int)}
	 * @param objeto a ser persistido
	 * @param classes parametro utilizado somente em caso de alteração, verificar link para detalhes
	 * @param classesVerifica parametro utilizado somente em caso de alteração, verificar link para detalhes
	 * @return resultado SQL do insert OU caso seja uma alteracao o Integer com o numero de linhas afetadas, retorna null caso o metodo tenha tido problemas para analisar a pk do objeto
	 * @throws Exception
	 */
	public Object salvar(BaseJDBC baseJDBC, Object objeto, Class<?>[] classes, int classesVerifica) throws Exception
	{
		Object idValue = getIdValue(objeto);
		if(idValue!=null)
		{
			if((idValue instanceof Integer && (Integer)idValue>0) || (idValue instanceof Long && (Long)idValue>0))
			{
				return alterar(baseJDBC, objeto, classes, classesVerifica);
			}
		}
		return inserir(baseJDBC, objeto);
	}
	
	/**
	 * Metodo que Insere um registro no banco de dados, a pk do registro e preenchida no campo com a annotation Id
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * @param objeto a ser persistido
	 * @return resultado SQL do insert
	 * @throws Exception
	 */
	public ResultSQL inserir(BaseJDBC baseJDBC, Object objeto) throws Exception
	{
		QuerySQL querySQL = objectToInsertSQL(objeto, baseJDBC);
		ResultSQL resultSQL = baseJDBC.executaInsert(querySQL.toString(baseJDBC), false, false);
		
		if(resultSQL!=null)
		{
			Annotation[] annotations;
			Field[] fields = objeto.getClass().getDeclaredFields();
			for(int i=0; i<fields.length; i++)
			{
				annotations = fields[i].getAnnotations();
				for(int j=0; j<annotations.length; j++)
				{
					if(annotations[j] instanceof Id)
					{
						fields[i].setAccessible(true);
						fields[i].set(objeto, resultSQL.get(0, fields[i].getName()));
						//fields[i].set(objeto, resultSQL.get(0, 0));
						return resultSQL;
					}
				}
			}
		}
		
		return resultSQL;
	}
	
	/**
	 * Metodo que altera um registro no banco de dados utilizando de referencia seu campo com a annotations Id, todos os campos do objeto serao utilizados no update
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * <BR>{@link BaseDAOSQL#objectToUpdateSQL(Object, BaseJDBC, Class[], int)}
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int alterar(BaseJDBC baseJDBC, Object objeto, Class<?>[] classes, int classesVerifica) throws Exception
	{
		QuerySQL querySQL = objectToUpdateSQL(objeto, baseJDBC, classes, classesVerifica);
		return baseJDBC.executaUpdate(querySQL.toString(baseJDBC), false, false);
	}
	
	/**
	 * Metodo que remove um registro no banco de dados utilizando de referencia seu campo com a annotations Id
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * @param objeto a ser removido
	 * @return numero de linhas afetadas
	 * @throws Exception
	 */
	public int remover(BaseJDBC baseJDBC, Object objeto) throws Exception
	{
		QuerySQL querySQL = BaseDAOSQL.objectToDeleteSQL(objeto, baseJDBC);
		return baseJDBC.executaDelete(querySQL.toString(baseJDBC), false, false);
	}
	
	/**
	 * Obtem uma lista de objetos a partir de um resultado de pesquisa no banco de dados, explicacao de funcionamento:
	 * <BR>Uma query e executada no banco de dados e uma matriz com os resultados sera retornada
	 * <BR>Uma classe para cada linha da matiz e criada, formando uma lista de classes
	 * <BR>Para cada linha da matriz a classe da lista e percorrida por reflection e seus campos sao preenchidos de acordo com o cabecalho da matriz
	 * <BR>Os campos nao encontrados no cabecalho da query ou no objeto serao ignorados
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * @param baseJDBC conexao a ser utilizada para executar as operacoes no banco de dados
	 * @param classe do objeto a ser criado
	 * @param condicao parametros possiveis:
	 * <BR>String: com a query a ser executada (devidamente preenchida, pronta para ser executada)
	 * <BR>QuerySQL: com a query a ser executada
	 * <BR>Long: pk valor com campo de primaryKey da tabela do banco de dados, neste casso a classe do primeiro parametro deve contar a annotations ID indicando o campo de pk 
	 * @param profundidade da obtencao dos dados:
	 * <BR>-1 obtem apenas os dados da classe, nao leva em consideracao do campos com as annotations ManyToOne ou OneToMany
	 * <BR>0 Obtem os dados da profuncidade -1 e os objetos das annotations ManyToOne sendo que esses objetos sao preenchidos em profundidade -1
	 * <BR>1 Obtem os dados da profuncidade 0 e os objetos das annotations ManyToOne sao preenchidos em profundidade 0
	 * <BR>...
	 * @param classes vetor de classes que devem ser incluidas ou excluidas do preenchimento (depende do parametro classesVerifica, verifique o javaDOC)
	 * @param classesVerifica pode assumir as constantes: CONSIDERAR_CLASSES ou IGNORAR_CLASSES
	 * <BR>CONSIDERAR_CLASSES: Ao realizar uma busca de profundidade onde os relacionamentos ManyToOne sao retornados sera verificado se eles estao no vetor de classes e assim serao obtidos, caso o vetor de classes seja nulo todas os relacionamentos serao considerados
	 * <BR>IGNORAR_CLASSES: Ao realizar uma busca de profundidade onde os relacionamentos ManyToOne sao retornados sera verificado se eles estao no vetor de classes e assim serao ignorados, caso o vetor de classes seja nulo todas os relacionamentos serao ignorados
	 * @param jaObtidos lista de registro ja obtidos no banco de dados para melhor o desempenho de buscas de profundidade, utiliza a annotation ID para identificacao do registro
	 * @return Lista de objetos da pesquisa devidademente populada
	 * @throws Exception
	 */
	public <T> List<T> obtem(BaseJDBC baseJDBC, Class<T> classe, Object condicao, int profundidade, Class<?>[] classes, int classesVerifica, List<OMObtido> jaObtidos) throws Exception
	{
		Object objeto;
		Annotation[] annotations;
		Annotation annotation;
		Field[] fields;
		Field field;
		Object id;
		boolean consideraIgnora = classesVerifica==CONSIDERAR_CLASSES;
		
		ResultSQL resultSQL=null;
		if(condicao instanceof String)
		{ 
			resultSQL = baseJDBC.executaSelect((String)condicao, false);
		}
		else if(condicao instanceof QuerySQL)
		{
			resultSQL = baseJDBC.executaSelect(((QuerySQL)condicao).toString(baseJDBC), false); 
		}
		else if(condicao instanceof Long)
		{
			QuerySQL querySQL = BaseDAOSQL.classeToSelectRegistroSQL(classe, condicao, baseJDBC);
			resultSQL = baseJDBC.executaSelect(querySQL.toString(baseJDBC), false);
		}
		else
		{
			throw new Exception("Parametro desconhecido: 'condicao' em obtem, BaseDAO: " + condicao);
		}
		
		List<T> lista = BaseDAOSQL.resultSQLToList(classe, resultSQL);
		//jaObtidos.addAll(lista);
		if(profundidade>-1)
		{
			for(int i=0; i<lista.size(); i++)
			{
				objeto = lista.get(i);
				fields = getFields(objeto.getClass());
				for(int j=0; j<fields.length; j++)
				{
					field = fields[j];
					annotations = getAnnotations(field);
					for(int k=0; k<annotations.length; k++)
					{
						annotation = annotations[k];
						if(annotation instanceof JoinColumn)
						{
							id = resultSQL.get(i, ((JoinColumn)annotation).name());
							if(id!=null)
							{
								field.setAccessible(true);
								if((classes==null && classesVerifica==CONSIDERAR_CLASSES) || (classes!=null && contem(field.getType(), classes)==consideraIgnora))
								{
									field.set(objeto, buscaRegistro(baseJDBC, field.getType(), id, profundidade-1, classes, classesVerifica, jaObtidos));
								}
							}
						}
					}
				}
			}
		}
		return lista;
	}
	
	/**
	 * {@link BaseDAO#obtem(BaseJDBC, Class, Object, int, Class[], int, List)}
	 */
	public <T> List<T> obtem(BaseJDBC baseJDBC, Class<T> classe, Object condicao, int profundidade, Class<?>[] classes, int classesVerifica) throws Exception
	{
		return obtem(baseJDBC, classe, condicao, profundidade, classes, classesVerifica, new ArrayList<OMObtido>());
	}
	
	/**
	 * Este metodo considera todas os relacionamentos ManyToOne
	 * {@link BaseDAO#obtem(BaseJDBC, Class, Object, int, Class[], int, List)}
	 */
	public <T> List<T> obtem(BaseJDBC baseJDBC, Class<T> classe, Object condicao, int profundidade) throws Exception
	{
		return obtem(baseJDBC, classe, condicao, profundidade, null, CONSIDERAR_CLASSES);
	}

	/**
	 * Obtem um objeto a partir de um resultado de pesquisa no banco de dados, explicacao de funcionamento:
	 * <BR>O primeiro elemento da lista sera retornado ou null caso a pesquisa nao traga resultados (execute querys de apenas uma resposta para maior eficiencia)
	 * <BR><B>Nao controla a conexao NAO sendo responsavel por comitar ou liberar a conexao</B>
	 * <BR>{@link BaseDAO#obtem(BaseJDBC, Class, Object, int, Class[], int, List)}
	 * @param classe do objeto a ser criado
	 * @return Objeto da pesquisa devidademente populado
	 * @see 
	 * @throws Exception
	 */
	public <T> T obtemUnico(BaseJDBC baseJDBC, Class<T> classe, Object condicao, int profundidade, Class<?>[] classes, int classesVerifica, List<OMObtido> jaObtidos) throws Exception
	{
		List<T> lista = obtem(baseJDBC, classe, condicao, profundidade, classes, classesVerifica, jaObtidos);
		return lista.size()>0 ? lista.get(0) : null;
	}
	
	/**
	 * {@link BaseDAO#obtemUnico(BaseJDBC, Class, Object, int, Class[], int, List)}
	 */
	public <T> T obtemUnico(BaseJDBC baseJDBC, Class<T> classe, Object condicao, int profundidade, Class<?>[] classes, int classesVerifica) throws Exception
	{
		return obtemUnico(baseJDBC, classe, condicao, profundidade, classes, classesVerifica, new ArrayList<OMObtido>());
	}
	
	/**
	 * Este metodo considera todas os relacionamentos ManyToOne
	 * {@link BaseDAO#obtemUnico(BaseJDBC, Class, Object, int, Class[], int, List)}
	 */
	public <T> T obtemUnico(BaseJDBC baseJDBC, Class<T> classe, Object condicao, int profundidade) throws Exception
	{
		return obtemUnico(baseJDBC, classe, condicao, profundidade, null, CONSIDERAR_CLASSES, new ArrayList<OMObtido>());
	}
	
	/*-*-*-* Metodos Auxiliares *-*-*-*/
	/**
	 * Metodo que pesquisa na lista de registros jaObtidos se a classe e o ID ja existem (se o objeto ja foi obtido da base de dados)
	 * Caso ele ja exista retorna o mesmo, caso nao exista executa a operacao de busca (obtemUnico)
	 * @param baseJDBC
	 * @param classe
	 * @param id
	 * @param profundidade
	 * @param classes
	 * @param classesVerifica
	 * @param jaObtidos
	 * @return objeto
	 */
	private Object buscaRegistro(BaseJDBC baseJDBC, Class<?> classe, Object id, int profundidade, Class<?>[] classes, int classesVerifica, List<OMObtido> jaObtidos) throws Exception
	{
		Object objetoAux, idAux;

		try
		{
			for(int i=0; i<jaObtidos.size(); i++)
			{
				objetoAux = jaObtidos.get(i).getOm();
				if(objetoAux!=null && objetoAux.getClass()==classe)
				{
					idAux = getIdValue(objetoAux);
					if(idAux!=null && idAux.equals(id))
					{
						if(jaObtidos.get(i).getProfundidade()>=profundidade) 	{ return objetoAux; }
						else 													{ break; }
					}
				}
			}
		}
		catch(Exception e)
		{
			Logs.addWarn("Nao foi possivel buscar o registro", e);
		}
		
		objetoAux = obtemUnico(baseJDBC, classe, id, profundidade, classes, classesVerifica, jaObtidos);
		jaObtidos.add(new OMObtido(objetoAux, profundidade));
		return objetoAux;
	}
	
	/**
	 * Verifica se o vetor de classe contem uma determinada classe
	 * @param classe
	 * @param vetor
	 * @return true caso o vetor contenha ou false caso contrario
	 */
	public static boolean contem(Class<?> classe, Class<?>[] vetor)
	{
		if(vetor==null || vetor.length<=0) { return false; }
		for(int i=0; i<vetor.length; i++)
		{
			if(vetor[i]==classe) { return true; }
		}
		return false;
	}
	
	/**
	 * Converte n parametros do tipo Class em um vetor de Classes
	 * @param classes
	 * @return vetor de Class
	 */
	public static <T> Class<T>[] classes(Class<T>... classes)
	{
		return classes;
	}
}
