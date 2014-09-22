package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.LotteryIssue;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILotteryIssueDAO {
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
	public void save(LotteryIssue entity);

	/**
	 * Delete a persistent LotteryIssue entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILotteryIssueDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryIssue entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LotteryIssue entity);

	/**
	 * Persist a previously saved LotteryIssue entity and return it or a copy of it
	 * to the sender. A copy of the LotteryIssue entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILotteryIssueDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryIssue entity to update
	 * @return LotteryIssue the persisted LotteryIssue entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LotteryIssue update(LotteryIssue entity);

	public LotteryIssue findById(Long id);

	/**
	 * Find all LotteryIssue entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LotteryIssue property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryIssue> found by query
	 */
	public List<LotteryIssue> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * 根据userCode查询LotteryIssue
	 * @param userCode
	 * @return
	 */
	public LotteryIssue findByUserCode(String userCode);
	
	public LotteryIssue findByIssue(String issue);

	/**
	 * Find all LotteryIssue entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryIssue> all LotteryIssue entities
	 */
	public List<LotteryIssue> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<LotteryIssue> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int LotteryIssueIndex, int LotteryIssueCount);
	
	public PagingEnumerator<LotteryIssue> pageList(Map<String, Object> filterMap,
			int LotteryIssueNo, int LotteryIssueSize);
	
	public List<LotteryIssue> findByPageAndCustomer(Long pageId, String customer);
	public List<LotteryIssue> findByLinkPageAndCustomer(Long pageId, String customer);
}