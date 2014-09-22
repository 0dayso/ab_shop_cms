package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IWeixinTypeDAO;
import net.rytong.entity.WeixinType;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for WeixinType
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see net.rytong.entity.WeixinType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class WeixinTypeDAO implements IWeixinTypeDAO {
	public static final String NAME = "name";
	public static final String CODE = "code";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved WeixinType entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WeixinTypeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinType entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WeixinType entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent WeixinType entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WeixinTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinType entity) {
		try {
			entity = em.getReference(WeixinType.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved WeixinType entity and return it or a copy of it to
	 * the sender. A copy of the WeixinType entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WeixinTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinType entity to update
	 * @return WeixinType the persisted WeixinType entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WeixinType update(WeixinType entity) {
		try {
			WeixinType result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public WeixinType findById(Long id) {
		try {
			WeixinType instance = em.find(WeixinType.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all WeixinType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinType property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<WeixinType> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WeixinType> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from WeixinType model where model."
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

	public List<WeixinType> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}
	
	public List<WeixinType> findByCode(Object code, int... rowStartIdxAndCount) {
		return findByProperty(CODE, code, rowStartIdxAndCount);
	}

	/**
	 * Find all WeixinType entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WeixinType> all WeixinType entities
	 */
	@SuppressWarnings("unchecked")
	public List<WeixinType> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from WeixinType model";
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

	public PagingEnumerator<WeixinType> findByProperties(String name,
			String departName, Integer beginTime,Integer endTime, int pageIndex, int pageSize) {
		try {
			StringBuffer queryString = new StringBuffer("select model from WeixinType model where model.isValid = 1 ");
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
			return new PagingEnumerator<WeixinType>(em, queryString.toString(), pageIndex, pageSize);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public PagingEnumerator<WeixinType> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try {
			StringBuffer queryString = new StringBuffer("select model from WeixinType model where 1= 1 ");
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%"+filterMap.get("name").toString()+"%'");
			}
			if(filterMap.containsKey("code")){
				queryString.append(" and model.departName like '%"+filterMap.get("code").toString()+"%'");
			}
			return new PagingEnumerator<WeixinType>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public boolean isWeixinTypeExist(String value){
		String hql="SELECT count(r) FROM WeixinType r where r.name =:name";
		Query q=em.createQuery(hql);
		q.setParameter("name", value.trim());
		long flag=(Long)q.getSingleResult();
		return flag==0;
	}

}