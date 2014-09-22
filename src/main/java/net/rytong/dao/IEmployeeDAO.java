package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.Employee;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for EmployeeDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IEmployeeDAO {
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
	 * IEmployeeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Employee entity);

	/**
	 * Delete a persistent Employee entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IEmployeeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Employee entity);

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
	 * entity = IEmployeeDAO.update(entity);
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
	public Employee update(Employee entity);

	public Employee findById(Long id);

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
	 *            count of results to return.
	 * @return List<Employee> found by query
	 */
	public List<Employee> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Employee> findByLogin(Object login, int... rowStartIdxAndCount);

	public List<Employee> findByRole(Object role, int... rowStartIdxAndCount);

	public List<Employee> findByName(Object name, int... rowStartIdxAndCount);

	public List<Employee> findByNameEn(Object nameEn,
			int... rowStartIdxAndCount);

	public List<Employee> findByIdType(Object idType,
			int... rowStartIdxAndCount);

	public List<Employee> findByIdNo(Object idNo, int... rowStartIdxAndCount);

	public List<Employee> findByGender(Object gender,
			int... rowStartIdxAndCount);

	public List<Employee> findByEmail(Object email, int... rowStartIdxAndCount);

	public List<Employee> findByPin(Object pin, int... rowStartIdxAndCount);

	public List<Employee> findByUserClass(Object userClass,
			int... rowStartIdxAndCount);

	public List<Employee> findByMobile(Object mobile,
			int... rowStartIdxAndCount);

	public List<Employee> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<Employee> findByArea(Object area, int... rowStartIdxAndCount);

	public List<Employee> findByPostcode(Object postcode,
			int... rowStartIdxAndCount);

	public List<Employee> findByAddress(Object address,
			int... rowStartIdxAndCount);

	public List<Employee> findByLoggedInAt(Object loggedInAt,
			int... rowStartIdxAndCount);

	public List<Employee> findByOperator(Object operator,
			int... rowStartIdxAndCount);

	public List<Employee> findByOptTime(Object optTime,
			int... rowStartIdxAndCount);

	public List<Employee> findByRemark(Object remark,
			int... rowStartIdxAndCount);

	public List<Employee> findByLoginPin(String login,String pin);

	public List<Employee> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);

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
	public List<Employee> findAll(int... rowStartIdxAndCount);
	
	public PagingEnumerator<Employee> findByProperties(String login,
			String name, String idType, String idNo, String gender,
			String mobile, String area, String address, Long signStartTime,
			Long signEndTime, String email, String postcode, int pageIndex,
			int pageCount);
	
	public PagingEnumerator<Employee> pageList(Map<String, Object> filterMap,int pageNo, int pageSize);
}