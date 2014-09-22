package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.ITopDAO;
import net.rytong.entity.Top;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for TTop
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see com.TopVo.Top
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class TopDAO implements ITopDAO {
	// property constants
	public static final String CONFERENCE_ID = "conferenceId";
	public static final String NAME = "name";
	public static final String CODE = "code";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved TTop entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TTopDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TTop entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Top entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent TTop entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * TTopDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TTop entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Top entity) {
		try {
			entity = em
					.getReference(Top.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved TTop entity and return it or a copy of it to
	 * the sender. A copy of the TTop entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = TTopDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TTop entity to update
	 * @return TTop the persisted TTop entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Top update(Top entity) {
		try {
			Top result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Top findById(Long id) {
		try {
			Top instance = em.find(Top.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all TTop entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TTop property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<TTop> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Top> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		try {
			String queryString = "select model from Top model where model."
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

	public List<Top> findByConferenceId(Object conferenceId,
			int... rowStartIdxAndCount) {
		return findByProperty("conference.id", conferenceId, rowStartIdxAndCount);
	}

	public List<Top> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<Top> findByCode(Object code, int... rowStartIdxAndCount) {
		return findByProperty(CODE, code, rowStartIdxAndCount);
	}

	/**
	 * Find all TTop entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TTop> all TTop entities
	 */
	@SuppressWarnings("unchecked")
	public List<Top> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from TTop model";
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
	public PagingEnumerator<Top> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount) {
		final StringBuffer queryString = new StringBuffer("select model from Top model where 1 = 1");
		if(filterMap.containsKey("name")){
			queryString.append(" and model.name like '%" + filterMap.get("name").toString() + "%'");
		}
		if(filterMap.containsKey("code")){
			queryString.append(" and model.code like '%" + filterMap.get("code").toString() + "%'");
		}
		queryString.append(" order by  model.id asc");
		Query query = em.createQuery(queryString.toString());
		return new PagingEnumerator<Top>(em, queryString.toString(), pageIndex, pageCount);
	}
	
	public List<Top> list(Map<String, Object> filterMap) {
		final StringBuffer queryString = new StringBuffer("select model from Top model where 1 = 1");
		if(filterMap.containsKey("conference")){
			queryString.append(" and model.conference.id = " + filterMap.get("conference").toString() + "");
		}
		if(filterMap.containsKey("state")){
			queryString.append(" and model.state =" + filterMap.get("state").toString() + "");
		}
		queryString.append(" order by  model.id asc");
		Query query = em.createQuery(queryString.toString());
		return query.getResultList();
	}
}