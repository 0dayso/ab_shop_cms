package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.Coupon;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CouponDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICouponDAO {
	/**
	 * Perform an initial save of a previously unsaved Coupon entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICouponDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Coupon entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Coupon entity);

	/**
	 * Delete a persistent Coupon entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICouponDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Coupon entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Coupon entity);

	/**
	 * Persist a previously saved Coupon entity and return it or a copy of it to
	 * the sender. A copy of the Coupon entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICouponDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Coupon entity to update
	 * @return Coupon the persisted Coupon entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Coupon update(Coupon entity);

	public Coupon findById(Long id);

	/**
	 * Find all Coupon entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Coupon property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Coupon> found by query
	 */
	public List<Coupon> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	List<Coupon> findByProperty(String certNo, String nameCn, String CouponCode,
			int... rowStartIdxAndCount);

	public List<Coupon> findByProperty(Object nameCn, Object certType,
			Object certNo, Object phoneNo, int... rowStartIdxAndCount);

	public List<Coupon> findByCouponCode(Object CouponCode,
			int... rowStartIdxAndCount);

	public List<Coupon> findByCouponCodePin(Object CouponCode, Object pin,
			int... rowStartIdxAndCount);

	public List<Coupon> findByIdNoPin(Object idNo, Object pin,
			int... rowStartIdxAndCount);

	public List<Coupon> findByName(Object name, int... rowStartIdxAndCount);

	public List<Coupon> findByNameEn(Object nameEn, int... rowStartIdxAndCount);

	public List<Coupon> findByIdType(Object idType, int... rowStartIdxAndCount);

	public List<Coupon> findByIdNo(Object idNo, int... rowStartIdxAndCount);

	public List<Coupon> findByGender(Object gender, int... rowStartIdxAndCount);

	public List<Coupon> findByEmail(Object email, int... rowStartIdxAndCount);

	public List<Coupon> findByCouponClass(Object CouponClass,
			int... rowStartIdxAndCount);

	public List<Coupon> findByMobile(Object mobile, int... rowStartIdxAndCount);

	public List<Coupon> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<Coupon> findByArea(Object area, int... rowStartIdxAndCount);

	public List<Coupon> findByPostcode(Object postcode,
			int... rowStartIdxAndCount);

	public List<Coupon> findByAddress(Object address, int... rowStartIdxAndCount);

	public Long getRegCoupon();
	
	public Long getTotalCoupon();

	public List<Coupon> findByAccumulateFraction(Object accumulateFraction,
			int... rowStartIdxAndCount);

	public List<Coupon> findByRemark(Object remark, int... rowStartIdxAndCount);

	public List<Coupon> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);

	/**
	 * Find all Coupon entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Coupon> all Coupon entities
	 */
	public List<Coupon> findAll(int... rowStartIdxAndCount);
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
	public PagingEnumerator<Coupon> findByProperties(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone, String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount);
	
	public List<Coupon> findByProperty(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone, String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany);
	public Long findByPropertyOfSum(String name, String gender,Integer registerType, String idType,String idNo, String email, String phone,String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany);
	/**
     * 查询不为空的用户
     */
	public List<Coupon>  findNotNullCoupon(Long beginDate,Long endDate);
	
	/**
	 * 按注册时间查询用户
	 */
	public List<Coupon> findByOptTime(Long optTimeBeginDate,Long optTimeEndDate);

	
	public List<Coupon> findByBookingTime(Long beginDate,Long endDate);
	
	public PagingEnumerator<Coupon> pageList(Map<String, Object> filterMap, int pageNo, int pageSize);
}