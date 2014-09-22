package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.Visits;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IVisitsDAO {
	/**
	 * Perform an initial save of a previously unsaved Visits entity. All
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
	 *            Visits entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Visits entity);

	/**
	 * Delete a persistent Visits entity. This operation must be performed
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
	 *            Visits entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Visits entity);

	/**
	 * Persist a previously saved Visits entity and return it or a copy of it
	 * to the sender. A copy of the Visits entity parameter is returned when
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
	 *            Visits entity to update
	 * @return Visits the persisted Visits entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Visits update(Visits entity);

	public Visits findById(Long id);

	/**
	 * Find all Visits entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Visits property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Visits> found by query
	 */
	public List<Visits> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Visits> findByShotName(Object shotName,
			int... rowStartIdxAndCount);

	public List<Visits> findByCompanyName(Object companyName,
			int... rowStartIdxAndCount);

	public List<Visits> findByAddress(Object address,
			int... rowStartIdxAndCount);

	public List<Visits> findByContactor(Object contactor,
			int... rowStartIdxAndCount);

	public List<Visits> findByMobile(Object mobile,
			int... rowStartIdxAndCount);

	public List<Visits> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<Visits> findByServiceItem(Object serviceItem,
			int... rowStartIdxAndCount);

	public List<Visits> findByIpAddress(Object ipAddress,
			int... rowStartIdxAndCount);

	public List<Visits> findByUsername(Object username,
			int... rowStartIdxAndCount);

	public List<Visits> findByPassword(Object password,
			int... rowStartIdxAndCount);

	public List<Visits> findByDesKey(Object desKey,
			int... rowStartIdxAndCount);

	public List<Visits> findByDesIv(Object desIv, int... rowStartIdxAndCount);

	public List<Visits> findByIsValid(Object isValid,
			int... rowStartIdxAndCount);

	public List<Visits> findByRecordDate(Object recordDate,
			int... rowStartIdxAndCount);

	public List<Visits> findByChannelDesc(Object channelDesc,
			int... rowStartIdxAndCount);

	public List<Visits> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);
	
	/**
	 * 根据userCode查询customer
	 * @param userCode
	 * @return
	 */
	public Visits findByUserCode(String userCode);

	/**
	 * Find all Visits entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Visits> all Visits entities
	 */
	public List<Visits> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<Visits> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int pageIndex, int pageCount);
	
	public PagingEnumerator<Visits> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize);	
	public List<Object[]> getNumsByUser(Map<String, Object> filterMap);
	public List<Visits> getDetilsByUser(Map<String, Object> filterMap);
}