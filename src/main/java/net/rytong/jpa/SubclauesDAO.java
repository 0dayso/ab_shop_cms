package net.rytong.jpa;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.rytong.dao.ISubclauesDAO;
import net.rytong.entity.Subclaues;
import net.rytong.entity.Top;
import net.rytong.utils.PagingEnumerator;

/**
 * A data access object (DAO) providing persistence and search support for
 * TSubclaues entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.SubclauesVo.Subclaues
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class SubclauesDAO implements ISubclauesDAO {
	// property constants
	public static final String TOP_ID = "topId";
	public static final String TITLE = "title";
	public static final String LOGO_URL = "logoUrl";
	public static final String CONTENT = "content";
	public static final String LINK_URL = "linkUrl";
	public static final String CODE = "code";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved TSubclaues entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TSubclauesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TSubclaues entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Subclaues entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent TSubclaues entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TSubclauesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TSubclaues entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Subclaues entity) {
		try {
			entity = em.getReference(Subclaues.class,
					entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved TSubclaues entity and return it or a copy of
	 * it to the sender. A copy of the TSubclaues entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TSubclauesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TSubclaues entity to update
	 * @return TSubclaues the persisted TSubclaues entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Subclaues update(Subclaues entity) {
		try {
			Subclaues result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Subclaues findById(Long id) {
		try {
			Subclaues instance = em.find(Subclaues.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all TSubclaues entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TSubclaues property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<TSubclaues> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Subclaues> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			String queryString = "select model from Subclaues model where model."
					+ propertyName + "= :propertyValue";
			queryString += " order by model.createTime desc";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
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

	public List<Subclaues> findByTopId(Object topId,
			int... rowStartIdxAndCount) {
		return findByProperty("top.id", topId, rowStartIdxAndCount);
	}

	public List<Subclaues> findByTitle(Object title,
			int... rowStartIdxAndCount) {
		return findByProperty(TITLE, title, rowStartIdxAndCount);
	}

	public List<Subclaues> findByLogoUrl(Object logoUrl,
			int... rowStartIdxAndCount) {
		return findByProperty(LOGO_URL, logoUrl, rowStartIdxAndCount);
	}

	public List<Subclaues> findByContent(Object content,
			int... rowStartIdxAndCount) {
		return findByProperty(CONTENT, content, rowStartIdxAndCount);
	}

	public List<Subclaues> findByLinkUrl(Object linkUrl,
			int... rowStartIdxAndCount) {
		return findByProperty(LINK_URL, linkUrl, rowStartIdxAndCount);
	}

	public List<Subclaues> findByCode(Object code, int... rowStartIdxAndCount) {
		return findByProperty(CODE, code, rowStartIdxAndCount);
	}

	/**
	 * Find all TSubclaues entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TSubclaues> all TSubclaues entities
	 */
	@SuppressWarnings("unchecked")
	public List<Subclaues> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Subclaues model";
			Query query = em.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
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
	public PagingEnumerator<Subclaues> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount) {
		final StringBuffer queryString = new StringBuffer("select model from Top model where 1 = 1");
		if(filterMap.containsKey("name")){
			queryString.append(" and model.title like '%" + filterMap.get("title").toString() + "%'");
		}
		if(filterMap.containsKey("content")){
			queryString.append(" and model.content like '%" + filterMap.get("content").toString() + "%'");
		}
		if(filterMap.containsKey("code")){
			queryString.append(" and model.code like '%" + filterMap.get("code").toString() + "%'");
		}
		queryString.append(" order by  model.id asc");
		Query query = em.createQuery(queryString.toString());
		return new PagingEnumerator<Subclaues>(em, queryString.toString(), pageIndex, pageCount);
	}
	
	public List<Subclaues> list(Map<String, Object> filterMap) {
		final StringBuffer queryString = new StringBuffer("select model from Subclaues model where 1 = 1");
		if(filterMap.containsKey("topId")){
			queryString.append(" and model.top.id = " + filterMap.get("topId").toString() + "");
		}
		queryString.append(" order by model.createTime desc");
		Query query = em.createQuery(queryString.toString());
		return query.getResultList();
	}

}