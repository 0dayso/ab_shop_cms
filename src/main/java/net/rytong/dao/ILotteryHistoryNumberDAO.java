package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.LotteryHistoryNumber;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILotteryHistoryNumberDAO {
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
	public void save(LotteryHistoryNumber entity);

	/**
	 * Delete a persistent LotteryHistoryNumber entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILotteryHistoryNumberDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryHistoryNumber entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LotteryHistoryNumber entity);

	/**
	 * Persist a previously saved LotteryHistoryNumber entity and return it or a copy of it
	 * to the sender. A copy of the LotteryHistoryNumber entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILotteryHistoryNumberDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryHistoryNumber entity to update
	 * @return LotteryHistoryNumber the persisted LotteryHistoryNumber entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LotteryHistoryNumber update(LotteryHistoryNumber entity);

	public LotteryHistoryNumber findById(Long id);

	/**
	 * Find all LotteryHistoryNumber entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LotteryHistoryNumber property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryHistoryNumber> found by query
	 */
	public List<LotteryHistoryNumber> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * 根据userCode查询LotteryHistoryNumber
	 * @param userCode
	 * @return
	 */
	public LotteryHistoryNumber findByUserCode(String userCode);

	/**
	 * Find all LotteryHistoryNumber entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryHistoryNumber> all LotteryHistoryNumber entities
	 */
	public List<LotteryHistoryNumber> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<LotteryHistoryNumber> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int LotteryHistoryNumberIndex, int LotteryHistoryNumberCount);
	
	public PagingEnumerator<LotteryHistoryNumber> pageList(Map<String, Object> filterMap,
			int LotteryHistoryNumberNo, int LotteryHistoryNumberSize);
	
	public List<LotteryHistoryNumber> findByPageAndCustomer(Long pageId, String customer);
	public List<LotteryHistoryNumber> findByLinkPageAndCustomer(Long pageId, String customer);
	
	public List<LotteryHistoryNumber> findByIssue(String issue);
}