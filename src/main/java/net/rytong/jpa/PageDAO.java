package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IPageDAO;
import net.rytong.entity.Page;
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
public class PageDAO implements IPageDAO {
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
	public void save(Page entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Page entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * PageDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Page entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Page entity) {
		try {
			entity = em.getReference(Page.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Page entity and return it or a copy of it
	 * to the sender. A copy of the Page entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = PageDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Page entity to update
	 * @return Page the persisted Page entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Page update(Page entity) {
		try {
			Page result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Page findById(Long id) {
		try {
			Page instance = em.find(Page.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Page findById(Long id, Long time) {
		try {
			final String queryString = "select model from Page model where model.id=" + id + " and " + time + " = " + time;
			Query query = em.createQuery(queryString);
			List<Page> page = query.getResultList();
			return page.get(0);
		} catch (RuntimeException re) {

			throw re;
		}
	}

	/**
	 * Find all Page entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Page property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Page> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Page> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {

		try {
			final String queryString = "select model from Page model where model."
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
	 * Find all Page entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Page> all Page entities
	 */
	@SuppressWarnings("unchecked")
	public List<Page> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Page model";
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
	
	
	
	public Page findByUserCode(String userCode) {
//		try {
//			final String queryUser = "select model from User model where model.userCode = :xusercode";
//			Query query = em.createQuery(queryUser);
//			query.setParameter("xusercode", userCode);
//
//			User user = (User) query.getResultList().get(0);
//			return user.getPage();
//
//		} catch (RuntimeException re) {
//
//			throw re;
//		}
		return null;
	}
	
	public List<Page> findByCustomer(String customer) {
		try {
			final String queryString = "select model from Page model where customer = '" + customer + "'";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<Page> findByLevelAndCustomer(String customer, int level) {
		try {
			final String queryString = "select model from Page model where customer = '" + customer + "' and model.level = " + level;
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public PagingEnumerator<Page> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int pageIndex, int pageCount) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Page model where ");
			
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
	
	public PagingEnumerator<Page> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Page model where ");
			
			if(filterMap.containsKey("shotName")){
				queryString.append("model.shotName like '%" + filterMap.get("shotName") + "%' and ");
			}
			if(filterMap.containsKey("companyName")){
				queryString.append("model.companyName like '%" + filterMap.get("companyName") + "%' and ");
			}   
			if(filterMap.containsKey("address")){
				queryString.append("model.address like '%" + filterMap.get("address") + "%' and ");
			}
			if(filterMap.containsKey("contactor")){
				queryString.append("model.contactor like '%" + filterMap.get("contactor") + "%' and ");
			}
			if(filterMap.containsKey("username")){
				queryString.append("model.username like '%" + filterMap.get("username") + "%' and ");
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
			return new PagingEnumerator(em, sql1, pageNo, pageSize);
		}catch(RuntimeException re){
			throw re;
		}
	}
}