package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.Page;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPageDAO {
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
	public void save(Page entity);

	/**
	 * Delete a persistent Page entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPageDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Page entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Page entity);

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
	 * entity = IPageDAO.update(entity);
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
	public Page update(Page entity);

	public Page findById(Long id);

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
	 *            count of results to return.
	 * @return List<Page> found by query
	 */
	public List<Page> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);
	
	/**
	 * 根据userCode查询Page
	 * @param userCode
	 * @return
	 */
	public Page findByUserCode(String userCode);

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
	public List<Page> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<Page> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int pageIndex, int pageCount);
	
	public PagingEnumerator<Page> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize);
	
	public List<Page> findByCustomer(String customer);
	
	public List<Page> findByLevelAndCustomer(String customer, int level);
	
	public Page findById(Long id, Long time);
}