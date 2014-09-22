package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.LotteryBaseNumber;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ILotteryBaseNumberDAO {
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
	public void save(LotteryBaseNumber entity);

	/**
	 * Delete a persistent LotteryBaseNumber entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ILotteryBaseNumberDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBaseNumber entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(LotteryBaseNumber entity);

	/**
	 * Persist a previously saved LotteryBaseNumber entity and return it or a copy of it
	 * to the sender. A copy of the LotteryBaseNumber entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ILotteryBaseNumberDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            LotteryBaseNumber entity to update
	 * @return LotteryBaseNumber the persisted LotteryBaseNumber entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public LotteryBaseNumber update(LotteryBaseNumber entity);

	public LotteryBaseNumber findById(Long id);

	/**
	 * Find all LotteryBaseNumber entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the LotteryBaseNumber property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryBaseNumber> found by query
	 */
	public List<LotteryBaseNumber> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * 根据userCode查询LotteryBaseNumber
	 * @param userCode
	 * @return
	 */
	public LotteryBaseNumber findByUserCode(String userCode);

	/**
	 * Find all LotteryBaseNumber entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<LotteryBaseNumber> all LotteryBaseNumber entities
	 */
	public List<LotteryBaseNumber> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<LotteryBaseNumber> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int LotteryBaseNumberIndex, int LotteryBaseNumberCount);
	
	public PagingEnumerator<LotteryBaseNumber> pageList(Map<String, Object> filterMap,
			int LotteryBaseNumberNo, int LotteryBaseNumberSize);
	
	public List<LotteryBaseNumber> findByPageAndCustomer(Long pageId, String customer);
	public List<LotteryBaseNumber> findByLinkPageAndCustomer(Long pageId, String customer);
	
	public List<LotteryBaseNumber> findByIssue(String issue);
}