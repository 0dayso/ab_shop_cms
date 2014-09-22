package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.LotteryBetNumber;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILotteryBetNumberDAO {
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
	public void save(LotteryBetNumber entity);

	/**
	 * Delete a persistent LotteryBetNumber entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILotteryBetNumberDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBetNumber entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LotteryBetNumber entity);

	/**
	 * Persist a previously saved LotteryBetNumber entity and return it or a copy of it
	 * to the sender. A copy of the LotteryBetNumber entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILotteryBetNumberDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBetNumber entity to update
	 * @return LotteryBetNumber the persisted LotteryBetNumber entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LotteryBetNumber update(LotteryBetNumber entity);

	public LotteryBetNumber findById(Long id);

	/**
	 * Find all LotteryBetNumber entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LotteryBetNumber property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryBetNumber> found by query
	 */
	public List<LotteryBetNumber> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * 根据userCode查询LotteryBetNumber
	 * @param userCode
	 * @return
	 */
	public LotteryBetNumber findByUserCode(String userCode);

	/**
	 * Find all LotteryBetNumber entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryBetNumber> all LotteryBetNumber entities
	 */
	public List<LotteryBetNumber> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<LotteryBetNumber> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int LotteryBetNumberIndex, int LotteryBetNumberCount);
	
	public PagingEnumerator<LotteryBetNumber> pageList(Map<String, Object> filterMap,
			int LotteryBetNumberNo, int LotteryBetNumberSize);
	
	public List<LotteryBetNumber> findByPageAndCustomer(Long pageId, String customer);
	public List<LotteryBetNumber> findByLinkPageAndCustomer(Long pageId, String customer);
	
	public List<LotteryBetNumber> findByIssue(String issue);
}