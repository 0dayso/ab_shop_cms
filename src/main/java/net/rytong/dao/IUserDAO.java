package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.User;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for UserDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IUserDAO {
	/**
	 * Perform an initial save of a previously unsaved User entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            User entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(User entity);

	/**
	 * Delete a persistent User entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IUserDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            User entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(User entity);

	/**
	 * Persist a previously saved User entity and return it or a copy of it to
	 * the sender. A copy of the User entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IUserDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            User entity to update
	 * @return User the persisted User entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public User update(User entity);

	public User findById(Long id);

	/**
	 * Find all User entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the User property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<User> found by query
	 */
	public List<User> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	List<User> findByProperty(String certNo, String nameCn, String userCode,
			int... rowStartIdxAndCount);

	public List<User> findByProperty(Object nameCn, Object certType,
			Object certNo, Object phoneNo, int... rowStartIdxAndCount);

	public List<User> findByUserCode(Object userCode,
			int... rowStartIdxAndCount);

	public List<User> findByUserCodePin(Object userCode, Object pin,
			int... rowStartIdxAndCount);

	public List<User> findByIdNoPin(Object idNo, Object pin,
			int... rowStartIdxAndCount);

	public List<User> findByName(Object name, int... rowStartIdxAndCount);

	public List<User> findByNameEn(Object nameEn, int... rowStartIdxAndCount);

	public List<User> findByIdType(Object idType, int... rowStartIdxAndCount);

	public List<User> findByIdNo(Object idNo, int... rowStartIdxAndCount);

	public List<User> findByGender(Object gender, int... rowStartIdxAndCount);

	public List<User> findByEmail(Object email, int... rowStartIdxAndCount);

	public List<User> findByUserClass(Object userClass,
			int... rowStartIdxAndCount);

	public List<User> findByMobile(Object mobile, int... rowStartIdxAndCount);

	public List<User> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<User> findByArea(Object area, int... rowStartIdxAndCount);

	public List<User> findByPostcode(Object postcode,
			int... rowStartIdxAndCount);

	public List<User> findByAddress(Object address, int... rowStartIdxAndCount);

	public Long getRegUser();
	
	public Long getTotalUser();

	public List<User> findByAccumulateFraction(Object accumulateFraction,
			int... rowStartIdxAndCount);

	public List<User> findByRemark(Object remark, int... rowStartIdxAndCount);

	public List<User> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);

	/**
	 * Find all User entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<User> all User entities
	 */
	public List<User> findAll(int... rowStartIdxAndCount);
	/**
	 * 根据输入的条件进行查询
	 * @param name
	 * @param gender
	 * @param mobile
	 * @param idNo
	 * @param email
	 * @param phone
	 * @param startDate
	 * @param endDate
	 * @param indexCount
	 * @return用户列表
	 */
	public PagingEnumerator<User> findByProperties(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone, String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount);
	
	public List<User> findByProperty(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone, String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany);
	public Long findByPropertyOfSum(String name, String gender,Integer registerType, String idType,String idNo, String email, String phone,String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany);
	/**
     * 查询不为空的用户
     */
	public List<User>  findNotNullUser(Long beginDate,Long endDate);
	
	/**
	 * 按注册时间查询用户
	 */
	public List<User> findByOptTime(Long optTimeBeginDate,Long optTimeEndDate);
	
	public void updateUser(User user);
	
	public List<User> findByBookingTime(Long beginDate,Long endDate);
	
	public PagingEnumerator<User> pageList(Map<String, Object> filterMap, int pageNo, int pageSize);
}