package net.rytong.jpa;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IEmployeeTemplateDAO;
import net.rytong.entity.Employee;
import net.rytong.entity.EmployeeTemplate;
import net.rytong.entity.Resource;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Employee entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see EmployeeTemplate
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class EmployeeTemplateDAO implements IEmployeeTemplateDAO {
	// property constants
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String EMPLOYEE = "employeeId";
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved Employee entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EmployeeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(EmployeeTemplate entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Employee entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EmployeeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(EmployeeTemplate entity) {
		try {
			entity = em.getReference(EmployeeTemplate.class,
					entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Employee entity and return it or a copy of it
	 * to the sender. A copy of the Employee entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = EmployeeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to update
	 * @return Employee the persisted Employee entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public EmployeeTemplate update(EmployeeTemplate entity) {
		try {
			EmployeeTemplate result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public EmployeeTemplate findById(Long id) {
		try {
			EmployeeTemplate instance = em.find(EmployeeTemplate.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Employee entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Employee property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Employee> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeeTemplate> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from EmployeeTemplate model where model."
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

	public List<EmployeeTemplate> findByCode(Object code, int... rowStartIdxAndCount) {
		return findByProperty(this.CODE, code, rowStartIdxAndCount);
	}

	public List<EmployeeTemplate> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(this.NAME, name, rowStartIdxAndCount);
	}

	public List<EmployeeTemplate> findByEmployee(Object employee, int... rowStartIdxAndCount) {
		return findByProperty(this.EMPLOYEE, employee, rowStartIdxAndCount);
	}

	@Override
	public List<EmployeeTemplate> findAll(int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from EmployeeTemplate model";
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

	public PagingEnumerator<EmployeeTemplate> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
		    StringBuffer queryString = new StringBuffer("select model from EmployeeTemplate model where 1=1 ");
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%"+filterMap.get("name").toString()+"%'");
			}
			if(filterMap.containsKey("pageName")){
				queryString.append(" and model.pageName = '"+filterMap.get("pageName").toString()+"' ");
			} 
			if(filterMap.containsKey("customer")){
				queryString.append(" and model.customer = '" + filterMap.get("customer").toString() + "'");
			}
		    queryString.append(" order by  model.id asc");
		    return new PagingEnumerator<EmployeeTemplate>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException e) {
			throw e;
		}
	}
}