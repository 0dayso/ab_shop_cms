package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.PageTemplate;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IPageTemplateDAO {
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
	public void save(PageTemplate entity);

	/**
	 * Delete a persistent PageTemplate entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IPageTemplateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            PageTemplate entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(PageTemplate entity);

	/**
	 * Persist a previously saved PageTemplate entity and return it or a copy of it
	 * to the sender. A copy of the PageTemplate entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IPageTemplateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            PageTemplate entity to update
	 * @return PageTemplate the persisted PageTemplate entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public PageTemplate update(PageTemplate entity);

	public PageTemplate findById(Long id);

	/**
	 * Find all PageTemplate entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the PageTemplate property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PageTemplate> found by query
	 */
	public List<PageTemplate> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);
	
	/**
	 * 根据userCode查询PageTemplate
	 * @param userCode
	 * @return
	 */
	public PageTemplate findByUserCode(String userCode);

	/**
	 * Find all PageTemplate entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<PageTemplate> all PageTemplate entities
	 */
	public List<PageTemplate> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<PageTemplate> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int PageTemplateIndex, int PageTemplateCount);
	
	public PagingEnumerator<PageTemplate> PageList(Map<String, Object> filterMap, int PageNo, int PageSize);
	
	public List<PageTemplate> findByCustomer(String customer);
	
	public List<PageTemplate> findByLevelAndCustomer(String customer, int level);
	
	public PageTemplate findById(Long id, Long time);
}