package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IUserDAO;
import net.rytong.entity.User;
import net.rytong.utils.DateUtils;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * must be handled externally by senders of these methods or must be manually
 * added to each of these methods for data to be persisted to the JPA datastore.
 * 
 * @see net.rytong.entity.User
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class UserDAO implements IUserDAO {
	// property constants
	public static final String USER_CODE = "userCode";
	public static final String NAME = "name";
	public static final String NAME_EN = "nameEn";
	public static final String ID_TYPE = "idType";
	public static final String ID_NO = "idNo";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String USER_CLASS = "userClass";
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
	 * UserDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            User entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(User entity) {
		// EntityManagerHelper.log("saving User instance", Level.INFO, null);
		try {
			em.persist(entity);
			// EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			// EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent User entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            User entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(User entity) {
		try {
			entity = em.getReference(User.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 * entity = UserDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            User entity to update
	 * @return User the persisted User entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public User update(User entity) {
		try {
			User result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public User findById(Long id) {
		try {
			User instance = em.find(User.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<User> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from User model where model."
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
	 *            number of results to return.
	 * @return List<User> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByProperty(final Object nameCn,
			final Object certType, final Object certNo, final Object phoneNo,
			final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from User usr where usr.name = :name and usr.phone = :phone and usr.idNo = :idNo and usr.idType = :idType";
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
			// List<User> list = null;
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

	public List<User> findByUserCode(Object userCode,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_CODE, userCode, rowStartIdxAndCount);
	}

	public List<User> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<User> findByNameEn(Object nameEn, int... rowStartIdxAndCount) {
		return findByProperty(NAME_EN, nameEn, rowStartIdxAndCount);
	}

	public List<User> findByIdType(Object idType, int... rowStartIdxAndCount) {
		return findByProperty(ID_TYPE, idType, rowStartIdxAndCount);
	}

	public List<User> findByIdNo(Object idNo, int... rowStartIdxAndCount) {
		return findByProperty(ID_NO, idNo, rowStartIdxAndCount);
	}

	public List<User> findByGender(Object gender, int... rowStartIdxAndCount) {
		return findByProperty(GENDER, gender, rowStartIdxAndCount);
	}

	public List<User> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<User> findByUserClass(Object userClass,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_CLASS, userClass, rowStartIdxAndCount);
	}

	public List<User> findByMobile(Object mobile, int... rowStartIdxAndCount) {
		return findByProperty(MOBILE, mobile, rowStartIdxAndCount);
	}

	public List<User> findByPhone(Object phone, int... rowStartIdxAndCount) {
		return findByProperty(PHONE, phone, rowStartIdxAndCount);
	}

	public List<User> findByArea(Object area, int... rowStartIdxAndCount) {
		return findByProperty(AREA, area, rowStartIdxAndCount);
	}

	public List<User> findByPostcode(Object postcode,
			int... rowStartIdxAndCount) {
		return findByProperty(POSTCODE, postcode, rowStartIdxAndCount);
	}

	public List<User> findByAddress(Object address, int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	public List<User> findByOptTime(Object optTime, int... rowStartIdxAndCount) {
		return findByProperty(OPT_TIME, optTime, rowStartIdxAndCount);
	}

	public List<User> findByAccumulateFraction(Object accumulateFraction,
			int... rowStartIdxAndCount) {
		return findByProperty(ACCUMULATE_FRACTION, accumulateFraction,
				rowStartIdxAndCount);
	}

	public List<User> findByRemark(Object remark, int... rowStartIdxAndCount) {
		return findByProperty(REMARK, remark, rowStartIdxAndCount);
	}

	public List<User> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<User> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from User model where model.id < 10000";
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
	public List<User> findByUserCodePin(Object userCode, Object pin,
			int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from User usr where usr.userCode = :xuserCode and usr.pin = :xpin";
			Query query = em.createQuery(queryString);
			query.setParameter("xuserCode", userCode);
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
	public List<User> findByIdNoPin(Object idNo, Object pin,
			int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from User usr where usr.idNo = :xidNo and usr.pin = :xpin";
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
	public List<User> findByProperty(String certNo, String nameCn, String userCode,
			int... rowStartIdxAndCount) {
		try {
			final String queryString = "select usr from User usr where usr.idNo = :xidNo and usr.name = :xname and usr.userCode= :xuserCode";
			Query query = em.createQuery(queryString);
			query.setParameter("xidNo", certNo);
			query.setParameter("xname", nameCn);
			query.setParameter("xuserCode", userCode);
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

	public PagingEnumerator<User> findByProperties(String name, String gender,Integer registerType,
			String idType, String idNo, String email, String phone,
			String mileageCard,String mobileType, Long startDate, Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount){
		try{
			StringBuffer queryString=new StringBuffer("select model from User model where model.id > 0");
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
			return new PagingEnumerator<User>(em, queryString.toString(), pageIndex, pageCount);
		}catch(RuntimeException e){
			throw e;
		}
	}

	public Long getRegUser() {
		try {
			Long beginDate  = Long.parseLong(DateUtils.getRYTDate()+ "000000");
			Long endDate = Long.parseLong(DateUtils.getRYTDate()+ "595959");
			final String queryString = "select count(model.id) from User model where model.optTime >= :beginDate and model.optTime <= :endDate";
			Query query = em.createQuery(queryString);
			query.setParameter("beginDate", beginDate);
			query.setParameter("endDate", endDate);
			return Long.parseLong(query.getSingleResult().toString());
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Long getTotalUser() {
		try {
			final String queryString = "select count(model.id) from User model";
			Query query = em.createQuery(queryString);
			return Long.parseLong(query.getSingleResult().toString());
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findByProperty(String name, String gender,Integer registerType, String idType,
			String idNo, String email, String phone, String mileageCard,String mobileType, Long startDate, Long endDate,Long customer,String fromCompany) {
		try{
			StringBuffer queryString=new StringBuffer("select model from User model where model.id > 0");
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
	public List<User> findByOptTime(Long optTimeBeginDate, Long optTimeEndDate) {
		try {
			final String queryString = "select model from User model where model.optTime>="+optTimeBeginDate+" and model.optTime<="+(optTimeEndDate+235959);
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
	public List<User> findNotNullUser(Long beginDate, Long endDate) {
		try {
			final String queryString = "select model from User model where length(model.mileageCard) > 0 and model.optTime >= "+beginDate+" and model.optTime <="+endDate;
			Query query = this.em.createQuery(queryString);
			return query.getResultList();
		}catch(RuntimeException e){
			throw e;
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			Query query = this.em.createQuery("update User u set u.mileageCard="+ user.getMileageCard() + " where u.id="+ user.getId());
			query.executeUpdate();
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findByBookingTime(Long beginDate,Long endDate){
		try {
			StringBuffer queryString = new StringBuffer("select model from User model,Passenger p,FlightTicketOrder fto where fto.user.id=model.id and fto.id=p.flightTicketOrder.id and fto.payState=2");
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
			StringBuffer queryString=new StringBuffer("select count(model.id) from User model where model.id > 0");
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
	public PagingEnumerator<User> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer("select model from User model where 1=1 ");
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
		    return new PagingEnumerator<User>(em, queryString.toString(), pageNo, pageSize);
		}catch(RuntimeException e){
			throw e;
		}
	}
	
	
	

}