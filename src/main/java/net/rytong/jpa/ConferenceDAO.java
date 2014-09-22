package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IConferenceDAO;
import net.rytong.entity.Conference;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * TConference entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.weixin.Conference
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class ConferenceDAO implements IConferenceDAO {
	// property constants
	public static final String WEIXIN_NO = "weixinNo";
	public static final String TITLE = "title";
	public static final String LOGO_URL = "logoUrl";
	public static final String CODE = "code";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved TConference entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TConferenceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TConference entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Conference entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent TConference entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TConferenceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TConference entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Conference entity) {
		try {
			entity = em.getReference(Conference.class,
					entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved TConference entity and return it or a copy of
	 * it to the sender. A copy of the TConference entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TConferenceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TConference entity to update
	 * @return TConference the persisted TConference entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Conference update(Conference entity) {
		try {
			Conference result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Conference findById(Long id) {
		try {
			Conference instance = em.find(Conference.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all TConference entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TConference property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<TConference> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Conference> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Conference model where model."
					+ propertyName + "= :propertyValue";
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

	public List<Conference> findByWeixinNo(Object weixinNo,
			int... rowStartIdxAndCount) {
		return findByProperty(WEIXIN_NO, weixinNo, rowStartIdxAndCount);
	}

	public List<Conference> findByTitle(Object title,
			int... rowStartIdxAndCount) {
		return findByProperty(TITLE, title, rowStartIdxAndCount);
	}

	public List<Conference> findByLogoUrl(Object logoUrl,
			int... rowStartIdxAndCount) {
		return findByProperty(LOGO_URL, logoUrl, rowStartIdxAndCount);
	}

	public List<Conference> findByCode(Object code, int... rowStartIdxAndCount) {
		return findByProperty(CODE, code, rowStartIdxAndCount);
	}

	/**
	 * Find all TConference entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TConference> all TConference entities
	 */
	@SuppressWarnings("unchecked")
	public List<Conference> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Conference model";
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
	
	public PagingEnumerator<Conference> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount) {
		final StringBuffer queryString = new StringBuffer("select model from Conference model where 1 = 1");
		if(filterMap.containsKey("title")){
			queryString.append(" and model.title like '%" + filterMap.get("title").toString() + "%'");
		}
		if(filterMap.containsKey("code")){
			queryString.append(" and model.code like '%" + filterMap.get("code").toString() + "%'");
		}
		if(filterMap.containsKey("customerId")){
			queryString.append(" and model.customer.id = " + Integer.parseInt(filterMap.get("customerId").toString()));
		}
		queryString.append(" order by  model.id asc");
		Query query = em.createQuery(queryString.toString());
		return new PagingEnumerator<Conference>(em, queryString.toString(), pageIndex, pageCount);
	}

	@SuppressWarnings("unchecked")
	public List<Conference> getAllData(Map<String, Object> filterMap) {
		final StringBuffer queryString = new StringBuffer("select model from Conference model where 1 = 1");
		if(filterMap.containsKey("title")){
			queryString.append(" and model.title like '%" + filterMap.get("title").toString() + "%'");
		}
		if(filterMap.containsKey("code")){
			queryString.append(" and model.code like '%" + filterMap.get("code").toString() + "%'");
		}
		if(filterMap.containsKey("state")){
			queryString.append(" and model.state like '%" + filterMap.get("state").toString() + "%'");
		}
		if(filterMap.containsKey("customerId")){
			queryString.append(" and model.customer.id = " + Integer.parseInt(filterMap.get("customerId").toString()));
		}
		queryString.append(" order by  model.id asc");
		Query query = em.createQuery(queryString.toString());
		return query.getResultList();
	}
	
	
	

}