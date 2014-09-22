package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.ICouponTemplateDAO;
import net.rytong.entity.CouponTemplate;
import net.rytong.utils.DateUtils;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for CouponTemplate
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see net.rytong.entity.CouponTemplate
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class CouponTemplateDAO implements ICouponTemplateDAO {
	// property constants
	public static final String CouponTemplate_CODE = "CouponTemplateCode";
	public static final String NAME = "name";
	public static final String NAME_EN = "nameEn";
	public static final String ID_TYPE = "idType";
	public static final String ID_NO = "idNo";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String CouponTemplate_CLASS = "CouponTemplateClass";
	public static final String MOBILE = "mobile";
	public static final String PHONE = "phone";
	public static final String AREA = "area";
	public static final String POSTCODE = "postcode";
	public static final String ADDRESS = "address";
	public static final String OPT_TIME = "optTime";
	public static final String ACCUMULATE_FRACTION = "accumulateFraction";
	public static final String REMARK = "remark";
	public static final String VERSION_NUM = "versionNum";
	public static final String PIN = "pin";
	// 
	// @PersistenceContextprivate EntityManager em;
	// }

	@PersistenceContext
	private EntityManager em;

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
	 * CouponTemplateDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CouponTemplate entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(CouponTemplate entity) {
		// EntityManagerHelper.log("saving CouponTemplate instance", Level.INFO, null);
		try {
			em.persist(entity);
			// EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			// EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent CouponTemplate entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CouponTemplateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            CouponTemplate entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(CouponTemplate entity) {
		try {
			entity = em.getReference(CouponTemplate.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 * entity = CouponTemplateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            CouponTemplate entity to update
	 * @return CouponTemplate the persisted CouponTemplate entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public CouponTemplate update(CouponTemplate entity) {
		try {
			CouponTemplate result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public CouponTemplate findById(Long id) {
		try {
			CouponTemplate instance = em.find(CouponTemplate.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<CouponTemplate> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from CouponTemplate model where model."
					+ propertyName + "= :propertyValue";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<CouponTemplate> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findByProperty(final Object nameCn,
			final Object certType, final Object certNo, final Object phoneNo,
			final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from CouponTemplate usr where usr.name = :name and usr.phone = :phone and usr.idNo = :idNo and usr.idType = :idType";
			Query query = em.createQuery(queryString);
			query.setParameter("name", nameCn);
			query.setParameter("phone", phoneNo);
			query.setParameter("idNo", certNo);
			query.setParameter("idType", certType);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}

			// org.hibernate.ejb.QueryImpl hs = null;
			// org.hibernate.Query hbQuery = null;
			// List<CouponTemplate> list = null;
			// if(query instanceof org.hibernate.ejb.QueryImpl) {
			// hs = (org.hibernate.ejb.QueryImpl)query;
			// hbQuery = hs.getHibernateQuery();
			// hbQuery.setCacheable(true);//设置使用二级缓存
			// list = hbQuery.list();
			// } else {
			// list = query.getResultList();
			// }

			return query.getResultList();

			// return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<CouponTemplate> findByCouponTemplateCode(Object CouponTemplateCode,
			int... rowStartIdxAndCount) {
		return findByProperty(CouponTemplate_CODE, CouponTemplateCode, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByNameEn(Object nameEn, int... rowStartIdxAndCount) {
		return findByProperty(NAME_EN, nameEn, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByIdType(Object idType, int... rowStartIdxAndCount) {
		return findByProperty(ID_TYPE, idType, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByIdNo(Object idNo, int... rowStartIdxAndCount) {
		return findByProperty(ID_NO, idNo, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByGender(Object gender, int... rowStartIdxAndCount) {
		return findByProperty(GENDER, gender, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByCouponTemplateClass(Object CouponTemplateClass,
			int... rowStartIdxAndCount) {
		return findByProperty(CouponTemplate_CLASS, CouponTemplateClass, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByMobile(Object mobile, int... rowStartIdxAndCount) {
		return findByProperty(MOBILE, mobile, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByPhone(Object phone, int... rowStartIdxAndCount) {
		return findByProperty(PHONE, phone, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByArea(Object area, int... rowStartIdxAndCount) {
		return findByProperty(AREA, area, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByPostcode(Object postcode,
			int... rowStartIdxAndCount) {
		return findByProperty(POSTCODE, postcode, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByAddress(Object address, int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByOptTime(Object optTime, int... rowStartIdxAndCount) {
		return findByProperty(OPT_TIME, optTime, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByAccumulateFraction(Object accumulateFraction,
			int... rowStartIdxAndCount) {
		return findByProperty(ACCUMULATE_FRACTION, accumulateFraction,
				rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByRemark(Object remark, int... rowStartIdxAndCount) {
		return findByProperty(REMARK, remark, rowStartIdxAndCount);
	}

	public List<CouponTemplate> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from CouponTemplate model where model.id < 10000";
			Query query = em.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findByCouponTemplateCodePin(Object CouponTemplateCode, Object pin,
			int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from CouponTemplate usr where usr.CouponTemplateCode = :xCouponTemplateCode and usr.pin = :xpin";
			Query query = em.createQuery(queryString);
			query.setParameter("xCouponTemplateCode", CouponTemplateCode);
			query.setParameter("xpin", pin);

			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findByIdNoPin(Object idNo, Object pin,
			int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from CouponTemplate usr where usr.idNo = :xidNo and usr.pin = :xpin";
			Query query = em.createQuery(queryString);
			query.setParameter("xidNo", idNo);
			query.setParameter("xpin", pin);

			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findByProperty(String certNo, String nameCn, String CouponTemplateCode,
			int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from CouponTemplate usr where usr.idNo = :xidNo and usr.name = :xname and usr.CouponTemplateCode= :xCouponTemplateCode";
			Query query = em.createQuery(queryString);
			query.setParameter("xidNo", certNo);
			query.setParameter("xname", nameCn);
			query.setParameter("xCouponTemplateCode", CouponTemplateCode);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public PagingEnumerator<CouponTemplate> findByProperties(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone,
			String mileageCard,String mobileType, Long startDate, Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount){
		try{
			StringBuffer queryString=new StringBuffer("select model from CouponTemplate model where model.id > 0");
			if(name!=null&&!name.equals("")){
				queryString.append(" and model.name like '%"+name+"%'");
			}
			if(gender!=null&&!gender.equals("")){
				queryString.append(" and (model.gender = '"+gender+"'");
				if(gender.equals("F")){
					queryString.append(" or model.gender = '2') ");
				}else if(gender.equals("M")){
					queryString.append(" or model.gender = '1') ");
				}
				
			}
			if(registerType!=null&&!registerType.equals("")){
				queryString.append(" and model.isNewRegister = '"+registerType+"'");
			}
			if(idNo!=null&&!idNo.equals("")){
				queryString.append(" and  model.idNo like '%"+idNo+"%'");
			}
			if(idType!=null&&!idType.equals("")){
				queryString.append(" and  model.idType = '"+idType+"'");
			}
			if(email!=null&&!email.equals("")){
				queryString.append(" and  model.email like '%"+email+"%'");
			}
			if(phone!=null&&!phone.equals("")){
				queryString.append(" and  model.phone like '%"+phone+"%'");
			}
			if(mileageCard != null && !mileageCard.equals("")){
				queryString.append(" and model.mileageCard like '%"+mileageCard+"%'");
			}
			if(mobileType != null && !mobileType.equals("")){
				queryString.append(" and model.mobileType = '"+mobileType+"'");
			}
			if(startDate!=null&&!startDate.equals("")){
				queryString.append(" and  model.optTime >= "+(startDate+000000L)+"");
			}
			if(endDate!=null&&!endDate.equals("")){
				queryString.append(" and model.optTime <= "+(endDate+235959L)+"");
			}
			if(customer != null && !customer.equals("") && customer > 0){
				queryString.append(" and  model.customer.id = "+customer+"");
			}
			if(fromCompany != null && !fromCompany.equals("")){
				if(fromCompany.equals("ca") || fromCompany.equals("00")){
					queryString.append(" and (model.registerOrigin = 00 or model.registerOrigin is null )");
				}else{
					queryString.append(" and  model.registerOrigin = '"+fromCompany+"'");
				}
			}
			////add by RobotJi 2011-07-12
//			if(fromCompany.equals("00")){
//				queryString.append(" and  (model.registerOrigin = '"+fromCompany+"' or model.registerOrigin is null )");
//			}else if(fromCompany != null && !fromCompany.equals("")){
//				queryString.append(" and  model.registerOrigin = '"+fromCompany+"'");
//			}
			queryString.append(" order by model.optTime desc");
			return new PagingEnumerator<CouponTemplate>(em, queryString.toString(), pageIndex, pageCount);
		}catch(RuntimeException e){
			throw e;
		}
	}

	public Long getRegCouponTemplate() {
		try {
			Long beginDate  = Long.parseLong(DateUtils.getRYTDate()+ "000000");
			Long endDate = Long.parseLong(DateUtils.getRYTDate()+ "595959");
			final String queryString = "select count(model.id) from CouponTemplate model where model.optTime >= :beginDate and model.optTime <= :endDate";
			Query query = em.createQuery(queryString);
			query.setParameter("beginDate", beginDate);
			query.setParameter("endDate", endDate);
			return Long.parseLong(query.getSingleResult().toString());
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Long getTotalCouponTemplate() {
		try {
			final String queryString = "select count(model.id) from CouponTemplate model";
			Query query = em.createQuery(queryString);
			return Long.parseLong(query.getSingleResult().toString());
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findByProperty(String name, String gender,Integer registerType, String idType,
			String idNo, String email, String phone, String mileageCard,String mobileType, Long startDate, Long endDate,Long customer,String fromCompany) {
		try{
			StringBuffer queryString=new StringBuffer("select model from CouponTemplate model where model.id > 0");
			if(name!=null&&!name.equals("")){
				queryString.append(" and model.name like '%"+name+"%'");
			}
			if(gender!=null&&!gender.equals("")){
				queryString.append(" and (model.gender = '"+gender+"'");
				if(gender.equals("F")){
					queryString.append(" or model.gender = '2' ) ");
				}else if(gender.equals("M")){
					queryString.append(" or model.gender = '1' )");
				}
				
			}
			if(registerType!=null&&!registerType.equals("")){
				queryString.append(" and model.isNewRegister = '"+registerType+"'");
			}
			if(idNo!=null&&!idNo.equals("")){
				queryString.append(" and  model.idNo like '%"+idNo+"%'");
			}
			if(idType!=null&&!idType.equals("")){
				queryString.append(" and  model.idType = '"+idType+"'");
			}
			if(email!=null&&!email.equals("")){
				queryString.append(" and  model.email like '%"+email+"%'");
			}
			if(phone!=null&&!phone.equals("")){
				queryString.append(" and  model.phone like '%"+phone+"%'");
			}
			if(mileageCard != null && !mileageCard.equals("")){
				queryString.append(" and model.mileageCard like '%"+mileageCard+"%'");
			}
			if(mobileType != null && !mobileType.equals("")){
				queryString.append(" and model.mobileType = '"+mobileType+"'");
			}
			if(startDate!=null&&!startDate.equals("")){
				queryString.append(" and  model.optTime >= "+startDate+"");
			}
			if(endDate!=null&&!endDate.equals("")){
				queryString.append(" and model.optTime <= "+(endDate+235959L)+"");
			}
			if(customer != null && !customer.equals("") && customer > 0){
				queryString.append(" and  model.customer.id = "+customer+"");
			}
			if(fromCompany != null && !fromCompany.equals("")){
				if(fromCompany.equals("ca") || fromCompany.equals("00")){
					queryString.append(" and (model.registerOrigin = '00' or model.registerOrigin is null )");
				}else{
					queryString.append(" and  model.registerOrigin = '"+fromCompany+"'");
				}
			}
//			if(fromCompany.equals("00")){
//				queryString.append(" and (model.registerOrigin = '"+fromCompany+"' or model.registerOrigin is null)");
//			}else if(fromCompany!=null&&!fromCompany.equals("")){
//				queryString.append(" and  model.registerOrigin = '"+fromCompany+"'");
//			}
			queryString.append(" order by model.optTime desc");
			Query query = em.createQuery(queryString.toString());
			return query.getResultList();
		}catch(RuntimeException e){
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CouponTemplate> findByOptTime(Long optTimeBeginDate, Long optTimeEndDate) {
		try {
			final String queryString = "select model from CouponTemplate model where model.optTime>="+optTimeBeginDate+" and model.optTime<="+(optTimeEndDate+235959);
			Query query = this.em.createQuery(queryString);
			return query.getResultList();
		} catch(RuntimeException e){
			throw e;
		}
	}

	/**
     * 查询不为空的用户
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<CouponTemplate> findNotNullCouponTemplate(Long beginDate, Long endDate) {
		try {
			final String queryString = "select model from CouponTemplate model where length(model.mileageCard) > 0 and model.optTime >= "+beginDate+" and model.optTime <="+endDate;
			Query query = this.em.createQuery(queryString);
			return query.getResultList();
		}catch(RuntimeException e){
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CouponTemplate> findByBookingTime(Long beginDate,Long endDate){
		try {
			StringBuffer queryString = new StringBuffer("select model from CouponTemplate model,Passenger p,FlightTicketOrder fto where fto.CouponTemplate.id=model.id and fto.id=p.flightTicketOrder.id and fto.payState=2");
			if(beginDate!=null && !beginDate.equals("")){
				queryString.append(" and fto.bookingTime >= " + beginDate);  
			}
			if(endDate != null && !"".equals(endDate)){
				queryString.append(" and fto.bookingTime <=" + (endDate+235959));
			}
			queryString.append(" group by model.id");
			Query query = em.createQuery(queryString.toString());
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public Long findByPropertyOfSum(String name, String gender,Integer registerType, String idType,
			String idNo, String email, String phone, String mileageCard,String mobileType, Long startDate, Long endDate,Long customer,String fromCompany) {
		try{
			StringBuffer queryString=new StringBuffer("select count(model.id) from CouponTemplate model where model.id > 0");
			if(name!=null&&!name.equals("")){
				queryString.append(" and model.name like '%"+name+"%'");
			}
			if(gender!=null&&!gender.equals("")){
				queryString.append(" and (model.gender = '"+gender+"'");
				if(gender.equals("F")){
					queryString.append(" or model.gender = '2') ");
				}else if(gender.equals("M")){
					queryString.append(" or model.gender = '1') ");
				}
				
			}
			if(registerType!=null&&!registerType.equals("")){
				queryString.append(" and model.isNewRegister = '"+registerType+"'");
			}
			if(idNo!=null&&!idNo.equals("")){
				queryString.append(" and  model.idNo like '%"+idNo+"%'");
			}
			if(idType!=null&&!idType.equals("")){
				queryString.append(" and  model.idType = '"+idType+"'");
			}
			if(email!=null&&!email.equals("")){
				queryString.append(" and  model.email like '%"+email+"%'");
			}
			if(phone!=null&&!phone.equals("")){
				queryString.append(" and  model.phone like '%"+phone+"%'");
			}
			if(mileageCard != null && !mileageCard.equals("")){
				queryString.append(" and model.mileageCard like '%"+mileageCard+"%'");
			}
			if(mobileType != null && !mobileType.equals("")){
				queryString.append(" and model.mobileType = '"+mobileType+"'");
			}
			if(startDate!=null&&!startDate.equals("")){
				queryString.append(" and  model.optTime >= "+startDate+"");
			}
			if(endDate!=null&&!endDate.equals("")){
				queryString.append(" and model.optTime <= "+(endDate+235959L)+"");
			}
			if(customer != null && !customer.equals("") && customer > 0){
				queryString.append(" and  model.customer.id = "+customer+"");
			}
			if(fromCompany != null && !fromCompany.equals("")){
				if(fromCompany.equals("ca") || fromCompany.equals("00")){
					queryString.append(" and (model.registerOrigin = 00 or model.registerOrigin is null )");
				}else{
					queryString.append(" and  model.registerOrigin = '"+fromCompany+"'");
				}
			}
//			if(fromCompany.equals("00")){
//				queryString.append(" and (model.registerOrigin = '"+fromCompany+"' or model.registerOrigin is null)");
//			}else if(fromCompany != null && !fromCompany.equals("")){
//				queryString.append(" and  model.registerOrigin = '"+fromCompany+"'");
//			}
			queryString.append(" order by model.optTime desc");
			Query query = em.createQuery(queryString.toString());
			return Long.parseLong(query.getSingleResult().toString());
		}catch(RuntimeException e){
			throw e;
		}
	}
	@Override
	public PagingEnumerator<CouponTemplate> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer("select model from CouponTemplate model where 1=1 ");
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%"+filterMap.get("name").toString()+"%'");
			}
			if(filterMap.containsKey("mobile")){
				queryString.append(" and model.mobile like '%"+filterMap.get("mobile").toString()+"%' ");
			}
			if(filterMap.containsKey("idNo")){
				queryString.append(" and model.idNo like '%"+filterMap.get("idNo").toString()+"%' ");
			}
			if(filterMap.containsKey("email")){
				queryString.append(" and model.email ="+filterMap.get("email").toString());
			}
			queryString.append(" order by  model.id asc");
		    return new PagingEnumerator<CouponTemplate>(em, queryString.toString(), pageNo, pageSize);
		}catch(RuntimeException e){
			throw e;
		}
	}
	
	
	

}