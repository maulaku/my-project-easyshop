package br.com.easyShop.persistencia.DAO.baseDAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import br.com.easyShop.utils.HibernateUtil;

public class BaseDAO
{
	/**
	 * Insere no banco um objeto
	 * @param object a ser inserido
	 * @throws Exception
	 */
	public static void inserir(Object object) throws Exception
	{
		Session session = HibernateUtil.getSession();
		Transaction trans = session.beginTransaction();

		session.save(object);

		trans.commit();
		session.close();
	}

	/**
	 * Atualiza um objeto no banco
	 * @param object a ser atualizado
	 * @throws Exception
	 */
	public static void atualizar(Object object) throws Exception
	{
		Session session = HibernateUtil.getSession();
		Transaction trans = session.beginTransaction();

		//session.update(object);
		session.saveOrUpdate(object);

		trans.commit();
		session.close();
	}
	
	/**
	 * Remove um objeto no banco
	 * @param object a ser removido
	 * @throws Exception
	 */
	public static void remover(Object object) throws Exception
	{
		Session session = HibernateUtil.getSession();
		Transaction trans = session.beginTransaction();

		session.delete(object);

		trans.commit();
		session.close();
	}

	/**
	 * faz um select no banco e retorna uma lista de com os resultados
	 * @param query a ser executada
	 * @return lista de objetos
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> obtemLista(Class<T> classe, String querySQL) throws Exception
	{
		Session session = HibernateUtil.getSession();
		Transaction trans = session.beginTransaction();
		Query query =  session.createSQLQuery(querySQL).addEntity(classe);

		List<T> objetos = query.list();

		trans.commit();
		session.close();

		return objetos;
	}


    public static <T> int getCount(Class<T> classe) {
        Criteria criteria = HibernateUtil.getSession().createCriteria(classe);
        criteria.setProjection(Projections.rowCount());
        return (Integer) criteria.uniqueResult();
    }

	/**
	 * Obtem um unico registro, através da condição passada como parametro, se retornar mais de um objeto não adiantara o metodo
	 * @param classe do tipo que quer o retorno
	 * @param condicao que quer seja executada, ex: "pkPais = 2"
	 * @return o objeto encontrado
	 * @throws Exception
	 */
	public static <T>Object obtemUnico(Class<T> classe, String condicao) throws Exception
	{
		List<T> objetos = obtemLista(classe, "SELECT * FROM " +classe.getSimpleName()+ " WHERE "+condicao);
		return objetos.get(0);
	}
	
}
