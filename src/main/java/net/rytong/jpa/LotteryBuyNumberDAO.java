package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.utils.PagingEnumerator;
import net.rytong.dao.ILotteryBuyNumberDAO;
import net.rytong.entity.Employee;
import net.rytong.entity.LotteryBuyNumber;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Visits entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see net.rytong.entity.Visits
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class LotteryBuyNumberDAO implements ILotteryBuyNumberDAO {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved LotteryBuyNumber entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CustomerDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBuyNumber entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LotteryBuyNumber entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent LotteryBuyNumber entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CustomerDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBuyNumber entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LotteryBuyNumber entity) {
		try {
			entity = em.getReference(LotteryBuyNumber.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved LotteryBuyNumber entity and return it or a copy of it
	 * to the sender. A copy of the LotteryBuyNumber entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CustomerDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBuyNumber entity to update
	 * @return LotteryBuyNumber the persisted LotteryBuyNumber entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LotteryBuyNumber update(LotteryBuyNumber entity) {
		try {
			LotteryBuyNumber result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public LotteryBuyNumber findById(Long id) {
		try {
			LotteryBuyNumber instance = em.find(LotteryBuyNumber.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	/**
	 * Find all Employee entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Employee> all Employee entities
	 */
	@SuppressWarnings("unchecked")
	public List<LotteryBuyNumber> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from LotteryBuyNumber model";
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

	/**
	 * Find all LotteryBuyNumber entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LotteryBuyNumber property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<LotteryBuyNumber> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<LotteryBuyNumber> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {

		try {
			final String queryString = "select model from LotteryBuyNumber model where model."
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

	@Override
	public PagingEnumerator<LotteryBuyNumber> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from LotteryBuyNumber model ");
			
			if(filterMap.containsKey("moban")){
				queryString.append("model.moban =" + filterMap.get("moban"));
			}
			PagingEnumerator<LotteryBuyNumber> listpEnumerator=new PagingEnumerator(em, queryString.toString(), pageNo, pageSize);
			return listpEnumerator;
		}catch(RuntimeException re){
			throw re;
		}
	}
}