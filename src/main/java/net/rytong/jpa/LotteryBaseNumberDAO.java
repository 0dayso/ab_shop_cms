package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.ILotteryBaseNumberDAO;
import net.rytong.entity.LotteryBaseNumber;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Page entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see net.rytong.entity.Page
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class LotteryBaseNumberDAO implements ILotteryBaseNumberDAO {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved Page entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PageDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Page entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(LotteryBaseNumber entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent LotteryBaseNumber entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * LotteryBaseNumberDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBaseNumber entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LotteryBaseNumber entity) {
		try {
			entity = em.getReference(LotteryBaseNumber.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved LotteryBaseNumber entity and return it or a copy of it
	 * to the sender. A copy of the LotteryBaseNumber entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = LotteryBaseNumberDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBaseNumber entity to update
	 * @return LotteryBaseNumber the persisted LotteryBaseNumber entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LotteryBaseNumber update(LotteryBaseNumber entity) {
		try {
			LotteryBaseNumber result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public LotteryBaseNumber findById(Long id) {
		try {
			LotteryBaseNumber instance = em.find(LotteryBaseNumber.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all LotteryBaseNumber entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LotteryBaseNumber property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<LotteryBaseNumber> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<LotteryBaseNumber> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {

		try {
			final String queryString = "select model from LotteryBaseNumber model where model."
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
	
	public List<LotteryBaseNumber> findByIssue(String issue) {
		try {
			final String queryString = "select * from t_bet_lottery_number where t_issue='" + issue + "'";
			Query query = em.createNativeQuery(queryString.toString());
			return query.getResultList();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	/**
	 * Find all LotteryBaseNumber entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryBaseNumber> all LotteryBaseNumber entities
	 */
	@SuppressWarnings("unchecked")
	public List<LotteryBaseNumber> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from LotteryBaseNumber model";
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
	
	public LotteryBaseNumber findByUserCode(String userCode) {
//		try {
//			final String queryUser = "select model from User model where model.userCode = :xusercode";
//			Query query = em.createQuery(queryUser);
//			query.setParameter("xusercode", userCode);
//
//			User user = (User) query.getResultList().get(0);
//			return user.getLotteryBaseNumber();
//
//		} catch (RuntimeException re) {
//
//			throw re;
//		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PagingEnumerator<LotteryBaseNumber> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int pageIndex, int pageCount) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from LotteryBaseNumber model where ");
			
			if(shortName != null && !shortName.equals("")){
				queryString.append("model.shotName like '%" + shortName + "%' and ");
			}
			if(companyName != null && !"".equals(companyName)){
				queryString.append("model.companyName like '%" + companyName + "%' and ");
			}   
			if(address != null && !"".equals(address)){
				queryString.append("model.address like '%" + address + "%' and ");
			}
			if(contactor != null && !"".equals(contactor)){
				queryString.append("model.contactor like '%" + contactor + "%' and ");
			}
			if(username != null && !"".equals(username)){
				queryString.append("model.username like '%" + username + "%' and ");
			}
			String sql = queryString.toString().trim();
			String sql1 = null;
			if (sql.endsWith(" where")) {
				sql1 = sql.substring(0, sql.length() - 5);
			}else if(sql.endsWith(" and")) {
				sql1 = sql.substring(0, sql.length() - 3);
			}else if(sql.endsWith(" or")) {
				sql1 = sql.substring(0, sql.length() - 2);
			}
			return new PagingEnumerator(em, sql1, pageIndex, pageCount);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public PagingEnumerator<LotteryBaseNumber> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from LotteryBaseNumber model ");
			
			if(filterMap.containsKey("type")){
				queryString.append("where model.type = '" + filterMap.get("type") + "' ");
			}
			String sql = queryString.toString().trim();
			return new PagingEnumerator(em, sql, pageNo, pageSize);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public List<LotteryBaseNumber> findByPageAndCustomer(Long pageId, String customer) {
		try {
			final String queryString = "select model from LotteryBaseNumber model where model.page.id=" + pageId + 
									   " and model.customer='" + customer + "' order by model.position";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<LotteryBaseNumber> findByLinkPageAndCustomer(Long pageId, String customer) {
		try {
			final String queryString = "select model from LotteryBaseNumber model where model.linkPage.id=" + pageId + 
									   " and model.customer='" + customer + "' order by model.position";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}