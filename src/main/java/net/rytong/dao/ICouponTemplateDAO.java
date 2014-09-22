package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.CouponTemplate;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CouponTemplateDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ICouponTemplateDAO {
	/**
	 * Perform an initial save of a previously unsaved CouponTemplate entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICouponTemplateDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CouponTemplate entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CouponTemplate entity);

	/**
	 * Delete a persistent CouponTemplate entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICouponTemplateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CouponTemplate entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CouponTemplate entity);

	/**
	 * Persist a previously saved CouponTemplate entity and return it or a copy of it to
	 * the sender. A copy of the CouponTemplate entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ICouponTemplateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CouponTemplate entity to update
	 * @return CouponTemplate the persisted CouponTemplate entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CouponTemplate update(CouponTemplate entity);

	public CouponTemplate findById(Long id);

	/**
	 * Find all CouponTemplate entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CouponTemplate property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CouponTemplate> found by query
	 */
	public List<CouponTemplate> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	List<CouponTemplate> findByProperty(String certNo, String nameCn, String CouponTemplateCode,
			int... rowStartIdxAndCount);

	public List<CouponTemplate> findByProperty(Object nameCn, Object certType,
			Object certNo, Object phoneNo, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByCouponTemplateCode(Object CouponTemplateCode,
			int... rowStartIdxAndCount);

	public List<CouponTemplate> findByCouponTemplateCodePin(Object CouponTemplateCode, Object pin,
			int... rowStartIdxAndCount);

	public List<CouponTemplate> findByIdNoPin(Object idNo, Object pin,
			int... rowStartIdxAndCount);

	public List<CouponTemplate> findByName(Object name, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByNameEn(Object nameEn, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByIdType(Object idType, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByIdNo(Object idNo, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByGender(Object gender, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByEmail(Object email, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByCouponTemplateClass(Object CouponTemplateClass,
			int... rowStartIdxAndCount);

	public List<CouponTemplate> findByMobile(Object mobile, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByPhone(Object phone, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByArea(Object area, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByPostcode(Object postcode,
			int... rowStartIdxAndCount);

	public List<CouponTemplate> findByAddress(Object address, int... rowStartIdxAndCount);

	public Long getRegCouponTemplate();
	
	public Long getTotalCouponTemplate();

	public List<CouponTemplate> findByAccumulateFraction(Object accumulateFraction,
			int... rowStartIdxAndCount);

	public List<CouponTemplate> findByRemark(Object remark, int... rowStartIdxAndCount);

	public List<CouponTemplate> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);

	/**
	 * Find all CouponTemplate entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<CouponTemplate> all CouponTemplate entities
	 */
	public List<CouponTemplate> findAll(int... rowStartIdxAndCount);
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
	public PagingEnumerator<CouponTemplate> findByProperties(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone, String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount);
	
	public List<CouponTemplate> findByProperty(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone, String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany);
	public Long findByPropertyOfSum(String name, String gender,Integer registerType, String idType,String idNo, String email, String phone,String mileageCard,String mobileType,
			Long startDate, Long endDate,Long customer,String fromCompany);
	/**
     * 查询不为空的用户
     */
	public List<CouponTemplate>  findNotNullCouponTemplate(Long beginDate,Long endDate);
	
	/**
	 * 按注册时间查询用户
	 */
	public List<CouponTemplate> findByOptTime(Long optTimeBeginDate,Long optTimeEndDate);

	
	public List<CouponTemplate> findByBookingTime(Long beginDate,Long endDate);
	
	public PagingEnumerator<CouponTemplate> pageList(Map<String, Object> filterMap, int pageNo, int pageSize);
}