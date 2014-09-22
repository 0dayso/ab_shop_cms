package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.Customer;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICustomerDAO {
	/**
	 * Perform an initial save of a previously unsaved Customer entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICustomerDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Customer entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Customer entity);

	/**
	 * Delete a persistent Customer entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICustomerDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Customer entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Customer entity);

	/**
	 * Persist a previously saved Customer entity and return it or a copy of it
	 * to the sender. A copy of the Customer entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICustomerDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Customer entity to update
	 * @return Customer the persisted Customer entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Customer update(Customer entity);

	public Customer findById(Long id);

	/**
	 * Find all Customer entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Customer property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Customer> found by query
	 */
	public List<Customer> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Customer> findByShotName(Object shotName,
			int... rowStartIdxAndCount);

	public List<Customer> findByCompanyName(Object companyName,
			int... rowStartIdxAndCount);

	public List<Customer> findByAddress(Object address,
			int... rowStartIdxAndCount);

	public List<Customer> findByContactor(Object contactor,
			int... rowStartIdxAndCount);

	public List<Customer> findByMobile(Object mobile,
			int... rowStartIdxAndCount);

	public List<Customer> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<Customer> findByServiceItem(Object serviceItem,
			int... rowStartIdxAndCount);

	public List<Customer> findByIpAddress(Object ipAddress,
			int... rowStartIdxAndCount);

	public List<Customer> findByUsername(Object username,
			int... rowStartIdxAndCount);

	public List<Customer> findByPassword(Object password,
			int... rowStartIdxAndCount);

	public List<Customer> findByDesKey(Object desKey,
			int... rowStartIdxAndCount);

	public List<Customer> findByDesIv(Object desIv, int... rowStartIdxAndCount);

	public List<Customer> findByIsValid(Object isValid,
			int... rowStartIdxAndCount);

	public List<Customer> findByRecordDate(Object recordDate,
			int... rowStartIdxAndCount);

	public List<Customer> findByChannelDesc(Object channelDesc,
			int... rowStartIdxAndCount);

	public List<Customer> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);
	
	/**
	 * 根据userCode查询customer
	 * @param userCode
	 * @return
	 */
	public Customer findByUserCode(String userCode);

	/**
	 * Find all Customer entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Customer> all Customer entities
	 */
	public List<Customer> findAll(int... rowStartIdxAndCount);
	
	/**
	 * 模糊查询大客户
	 * @param shortName
	 * @param companyName
	 * @param address
	 * @param contactor
	 * @param username
	 * @param rowStartIdxAndCount
	 * @return
	 */
	public PagingEnumerator<Customer> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int pageIndex, int pageCount);
	
	public PagingEnumerator<Customer> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize);
	
}