package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IRoleDAO;
import net.rytong.entity.Role;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for Role
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see net.rytong.entity.Role
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class RoleDAO implements IRoleDAO {
	// property constants
	public static final String NAME = "name";
	public static final String DEPART_NAME = "departName";
	public static final String CREATED_DATE = "createdDate";
	public static final String IS_VALID = "isValid";
	public static final String VERSION_NUM = "versionNum";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved Role entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RoleDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Role entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Role entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Role entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * RoleDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Role entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Role entity) {
		try {
			entity = em.getReference(Role.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Role entity and return it or a copy of it to
	 * the sender. A copy of the Role entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = RoleDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Role entity to update
	 * @return Role the persisted Role entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Role update(Role entity) {
		try {
			Role result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Role findById(Long id) {
		try {
			Role instance = em.find(Role.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Role entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Role property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Role> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Role> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Role model where model."
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

	public List<Role> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<Role> findByDepartName(Object departName,
			int... rowStartIdxAndCount) {
		return findByProperty(DEPART_NAME, departName, rowStartIdxAndCount);
	}

	public List<Role> findByCreatedDate(Object createdDate,
			int... rowStartIdxAndCount) {
		return findByProperty(CREATED_DATE, createdDate, rowStartIdxAndCount);
	}

	public List<Role> findByIsValid(Object isValid, int... rowStartIdxAndCount) {
		return findByProperty(IS_VALID, isValid, rowStartIdxAndCount);
	}

	public List<Role> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

	/**
	 * Find all Role entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Role> all Role entities
	 */
	@SuppressWarnings("unchecked")
	public List<Role> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Role model";
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

	public PagingEnumerator<Role> findByProperties(String name,
			String departName, Integer beginTime,Integer endTime, int pageIndex, int pageSize) {
		try {
			StringBuffer queryString = new StringBuffer("select model from Role model where model.isValid = 1 ");
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
			return new PagingEnumerator<Role>(em, queryString.toString(), pageIndex, pageSize);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public PagingEnumerator<Role> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try {
			StringBuffer queryString = new StringBuffer("select model from Role model where model.isValid = 1 ");
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%"+filterMap.get("name").toString()+"%'");
			}
			if(filterMap.containsKey("departName")){
				queryString.append(" and model.departName like '%"+filterMap.get("departName").toString()+"%'");
			}
			if(filterMap.containsKey("beginTime")){
				queryString.append(" and model.createdDate >= "+filterMap.get("beginTime").toString());
			}
			if(filterMap.containsKey("endTime")){
				queryString.append(" and model.createdDate <= "+filterMap.get("endTime").toString());
			}
			return new PagingEnumerator<Role>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	/**
	 * 验证角色是否存在
	 * @author RobotJi
	 * @param value
	 * @return boolean
	 * @date 2011-7-8
	 */
	public boolean isRoleExist(String value){
		String hql="SELECT count(r) FROM Role r where r.name =:name";
		Query q=em.createQuery(hql);
		q.setParameter("name", value.trim());
		long flag=(Long)q.getSingleResult();
		return flag==0;
	}

}