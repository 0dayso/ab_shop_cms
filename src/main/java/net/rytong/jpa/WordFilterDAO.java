package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IVisitDAO;
import net.rytong.entity.WordFilter;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Event
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see net.rytong.entity.WordFilter
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class WordFilterDAO implements IVisitDAO {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved Event entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EventDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Event entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WordFilter entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Event entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EventDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Event entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WordFilter entity) {
		try {
			entity = em.getReference(WordFilter.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Event entity and return it or a copy of it to
	 * the sender. A copy of the Event entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = EventDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Event entity to update
	 * @return Event the persisted Event entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WordFilter update(WordFilter entity) {
		try {
			WordFilter result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public WordFilter findById(Long id) {
		try {
			WordFilter instance = em.find(WordFilter.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Event entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Event property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Event> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WordFilter> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Event model where model."
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
	/**
	 * Find all Event entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Event> all Event entities
	 */
	@SuppressWarnings("unchecked")
	public List<WordFilter> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Event model";
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

	public PagingEnumerator<WordFilter> findByProperties(String name,
			String departName, Integer beginTime,Integer endTime, int pageIndex, int pageSize) {
		try {
			StringBuffer queryString = new StringBuffer("select model from Event model where model.isValid = 1 ");
			if(name != null && !name.equals("")){
				queryString.append(" and model.name like '%"+name+"%'");
			}
			if(departName != null && !departName.equals("")){
				queryString.append(" and model.departName like '%"+departName+"%'");
			}
			if(beginTime != null && !beginTime.equals("")){
				queryString.append(" and model.createdDate >= "+beginTime);
			}
			if(endTime != null && !endTime.equals("")){
				queryString.append(" and model.createdDate <= "+endTime);
			}
			return new PagingEnumerator<WordFilter>(em, queryString.toString(), pageIndex, pageSize);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public PagingEnumerator<WordFilter> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try {
			StringBuffer queryString = new StringBuffer("select model from Event model where 1= 1 ");
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%"+filterMap.get("name").toString()+"%'");
			}
			if(filterMap.containsKey("code")){
				queryString.append(" and model.departName like '%"+filterMap.get("code").toString()+"%'");
			}
			return new PagingEnumerator<WordFilter>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<WordFilter> getAllWord(Map<String, Object> filterMap) {
		try {
			StringBuffer queryString = new StringBuffer("select model from WordFilter model where 1= 1 ");
			if(filterMap.containsKey("word")){
				queryString.append(" and model.word like '%"+filterMap.get("word").toString()+"%'");
			}
			if(filterMap.containsKey("status")){
				queryString.append(" and model.status = " + filterMap.get("status") + "");
			}
			Query query = em.createQuery(queryString.toString());
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public boolean isEventExist(String value){
		String hql="SELECT count(r) FROM Event r where r.name =:name";
		Query q=em.createQuery(hql);
		q.setParameter("name", value.trim());
		long flag=(Long)q.getSingleResult();
		return flag==0;
	}

}