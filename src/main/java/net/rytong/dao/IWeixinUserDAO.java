package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.WeixinUser;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for WeixinUserDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWeixinUserDAO {
	/**
	 * Perform an initial save of a previously unsaved WeixinUser entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * IWeixinUserDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            WeixinUser entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WeixinUser entity);

	/**
	 * Delete a persistent WeixinUser entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * IWeixinUserDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            WeixinUser entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinUser entity);

	/**
	 * Persist a previously saved WeixinUser entity and return it or a copy of
	 * it to the sender. A copy of the WeixinUser entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * entity = IWeixinUserDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            WeixinUser entity to update
	 * @return WeixinUser the persisted WeixinUser entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WeixinUser update(WeixinUser entity);

	public WeixinUser findById(Long id);

	/**
	 * Find all WeixinUser entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinUser property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WeixinUser> found by query
	 */
	public List<WeixinUser> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByWeixinNo(Object weixinNo,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByName(Object name, int... rowStartIdxAndCount);

	public List<WeixinUser> findByPin(Object pin, int... rowStartIdxAndCount);

	public List<WeixinUser> findByUserName(Object userName,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByNameEn(Object nameEn,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByIdType(Object idType,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByIdNo(Object idNo, int... rowStartIdxAndCount);

	public List<WeixinUser> findByGender(Object gender,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByEmail(Object email,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByUserClass(Object userClass,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByMobile(Object mobile,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByPhone(Object phone,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByArea(Object area, int... rowStartIdxAndCount);

	public List<WeixinUser> findByPostcode(Object postcode,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByAddress(Object address,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByOptTime(Object optTime,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByAccumulateFraction(Object accumulateFraction,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByRemark(Object remark,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByMileageCard(Object mileageCard,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByMobileType(Object mobileType,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByUserGroup(Object userGroup,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByIsNewRegister(Object isNewRegister,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByRegisterOrigin(Object registerOrigin,
			int... rowStartIdxAndCount);

	public List<WeixinUser> findByLastLoginTime(Object lastLoginTime,
			int... rowStartIdxAndCount);

	/**
	 * Find all WeixinUser entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WeixinUser> all WeixinUser entities
	 */
	public List<WeixinUser> findAll(int... rowStartIdxAndCount);
	
	public PagingEnumerator<WeixinUser> weixinUserMatch(String name,
			String idNo, String mobile,String email, int startIndex, int maxCount);
	
	public PagingEnumerator<WeixinUser> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize);
	public List<WeixinUser> findByWeixinNoAndCustomer(String weixinNo, String customer);
}