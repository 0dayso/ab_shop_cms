package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import net.rytong.dao.ITemplateDAO;
import net.rytong.entity.Resource;
import net.rytong.entity.Template;
import net.rytong.utils.PagingEnumerator;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Template entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.zgc.template.Template
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class TemplateDAO implements ITemplateDAO {
	// property constants
	public static final String _TNAME = "name";
	public static final String _TCODE = "code";
	public static final String _TURL = "url";
	public static final String _TLAY = "lay";
	public static final String _TSTATE = "state";
	public static final String _TSTYLE = "style";
	public static final String _TSOURCE = "source";

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Perform an initial save of a previously unsaved Template entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * TemplateDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            Template entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Template entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Template entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * TemplateDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            Template entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Template entity) {
		try {
			entity = em.getReference(Template.class,
					entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Template entity and return it or a copy of it
	 * to the sender. A copy of the Template entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * entity = TemplateDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            Template entity to update
	 * @return Template the persisted Template entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Template update(Template entity) {
		try {
			Template result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Template findById(Long id) {
		try {
			Template instance = em.find(Template.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Template entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Template property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Template> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Template> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Template model where model."
					+ propertyName + "= :propertyValue";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null
					&& rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Template> findByName(Object TName, int... rowStartIdxAndCount) {
		return findByProperty(_TNAME, TName, rowStartIdxAndCount);
	}

	public List<Template> findByCode(Object TCode, int... rowStartIdxAndCount) {
		return findByProperty(_TCODE, TCode, rowStartIdxAndCount);
	}

	public List<Template> findByUrl(Object TUrl, int... rowStartIdxAndCount) {
		return findByProperty(_TURL, TUrl, rowStartIdxAndCount);
	}

	public List<Template> findByLay(Object TLay, int... rowStartIdxAndCount) {
		return findByProperty(_TLAY, TLay, rowStartIdxAndCount);
	}

	public List<Template> findByState(Object TState,
			int... rowStartIdxAndCount) {
		return findByProperty(_TSTATE, TState, rowStartIdxAndCount);
	}

	public List<Template> findByStyle(Object TStyle,
			int... rowStartIdxAndCount) {
		return findByProperty(_TSTYLE, TStyle, rowStartIdxAndCount);
	}

	public List<Template> findBySource(Object TSource,
			int... rowStartIdxAndCount) {
		return findByProperty(_TSOURCE, TSource, rowStartIdxAndCount);
	}

	/**
	 * Find all Template entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Template> all Template entities
	 */
	@SuppressWarnings("unchecked")
	public List<Template> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Template model";
			Query query = em.createQuery(queryString);
			if (rowStartIdxAndCount != null
					&& rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public PagingEnumerator<Template> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount) {
		final StringBuffer queryString = new StringBuffer("select model from Template model where 1 = 1");
		if(filterMap.containsKey("name")){
			queryString.append(" and model.name like '%" + filterMap.get("name").toString() + "%'");
		}
		if(filterMap.containsKey("code")){
			queryString.append(" and model.code like '%" + filterMap.get("code").toString() + "%'");
		}
		if(filterMap.containsKey("state") && !(Integer.parseInt(filterMap.get("state").toString()) == -1)){
			queryString.append(" and model.state  = " + filterMap.get("state"));
		}
		queryString.append(" order by  model.id asc");
		Query query = em.createQuery(queryString.toString());
		return new PagingEnumerator<Template>(em, queryString.toString(), pageIndex, pageCount);
	}
	
}