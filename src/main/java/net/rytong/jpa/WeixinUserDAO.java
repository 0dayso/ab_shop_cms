package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import net.rytong.dao.IWeixinUserDAO;
import net.rytong.entity.WeixinUser;
import net.rytong.utils.PagingEnumerator;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * WeixinUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.zgc.WeixinUser
 * @author MyEclipse Persistence Tools
 */

@Transactional
@Component
public class WeixinUserDAO implements IWeixinUserDAO {
	// property constants
	public static final String WEIXIN_NO = "weixinNo";
	public static final String NAME = "name";
	public static final String PIN = "pin";
	public static final String USER_NAME = "userName";
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
	public static final String MILEAGE_CARD = "mileageCard";
	public static final String MOBILE_TYPE = "mobileType";
	public static final String USER_GROUP = "userGroup";
	public static final String VERSION_NUM = "versionNum";
	public static final String IS_NEW_REGISTER = "isNewRegister";
	public static final String REGISTER_ORIGIN = "registerOrigin";
	public static final String LAST_LOGIN_TIME = "lastLoginTime";
	
	@PersistenceContext
	private EntityManager em;

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
	 * WeixinUserDAO.save(entity);
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
	public void save(WeixinUser entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 * WeixinUserDAO.delete(entity);
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
	public void delete(WeixinUser entity) {
		try {
			entity = em.getReference(WeixinUser.class,
					entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 * entity = WeixinUserDAO.update(entity);
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
	public WeixinUser update(WeixinUser entity) {
		try {
			WeixinUser result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public WeixinUser findById(Long id) {
		try {
			WeixinUser instance = em.find(WeixinUser.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<WeixinUser> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WeixinUser> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from WeixinUser model where model."
					+ propertyName + "= :propertyValue";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null
					&& rowStartIdxAndCount.length > 0) {
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
    
	
	@Override
	public List<WeixinUser> findByWeixinNo(Object weixinNo,
			int... rowStartIdxAndCount) {
		return findByProperty(WEIXIN_NO, weixinNo, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByPin(Object pin, int... rowStartIdxAndCount) {
		return findByProperty(PIN, pin, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByUserName(Object userName,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_NAME, userName, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByNameEn(Object nameEn,
			int... rowStartIdxAndCount) {
		return findByProperty(NAME_EN, nameEn, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByIdType(Object idType,
			int... rowStartIdxAndCount) {
		return findByProperty(ID_TYPE, idType, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByIdNo(Object idNo, int... rowStartIdxAndCount) {
		return findByProperty(ID_NO, idNo, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByGender(Object gender,
			int... rowStartIdxAndCount) {
		return findByProperty(GENDER, gender, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByEmail(Object email,
			int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByUserClass(Object userClass,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_CLASS, userClass, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByMobile(Object mobile,
			int... rowStartIdxAndCount) {
		return findByProperty(MOBILE, mobile, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByPhone(Object phone,
			int... rowStartIdxAndCount) {
		return findByProperty(PHONE, phone, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByArea(Object area, int... rowStartIdxAndCount) {
		return findByProperty(AREA, area, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByPostcode(Object postcode,
			int... rowStartIdxAndCount) {
		return findByProperty(POSTCODE, postcode, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByAddress(Object address,
			int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByOptTime(Object optTime,
			int... rowStartIdxAndCount) {
		return findByProperty(OPT_TIME, optTime, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByAccumulateFraction(Object accumulateFraction,
			int... rowStartIdxAndCount) {
		return findByProperty(ACCUMULATE_FRACTION, accumulateFraction,
				rowStartIdxAndCount);
	}

	public List<WeixinUser> findByRemark(Object remark,
			int... rowStartIdxAndCount) {
		return findByProperty(REMARK, remark, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByMileageCard(Object mileageCard,
			int... rowStartIdxAndCount) {
		return findByProperty(MILEAGE_CARD, mileageCard, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByMobileType(Object mobileType,
			int... rowStartIdxAndCount) {
		return findByProperty(MOBILE_TYPE, mobileType, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByUserGroup(Object userGroup,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_GROUP, userGroup, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

	public List<WeixinUser> findByIsNewRegister(Object isNewRegister,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_NEW_REGISTER, isNewRegister,
				rowStartIdxAndCount);
	}

	public List<WeixinUser> findByRegisterOrigin(Object registerOrigin,
			int... rowStartIdxAndCount) {
		return findByProperty(REGISTER_ORIGIN, registerOrigin,
				rowStartIdxAndCount);
	}

	public List<WeixinUser> findByLastLoginTime(Object lastLoginTime,
			int... rowStartIdxAndCount) {
		return findByProperty(LAST_LOGIN_TIME, lastLoginTime,
				rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<WeixinUser> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from WeixinUser model";
			Query query = em.createQuery(queryString);
			if (rowStartIdxAndCount != null
					&& rowStartIdxAndCount.length > 0) {
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
	@Override
	public PagingEnumerator<WeixinUser> weixinUserMatch(String name,
			String idNo, String mobile,String email, int startIndex, int maxCount) {
		StringBuffer queryString=new StringBuffer();
		queryString.append("select model from WeixinUser model where 1=1 ");
		if(name != null && !"".equals(name)){
			queryString.append("and model.name like '%"+name+"%'");
		}
		if(idNo != null && !"".equals(idNo)){
			queryString.append("and model.idNo like '%"+ idNo + "%'");
		}
		if(mobile != null && !"".equals(mobile)){
			queryString.append("and model.mobile like '%"+ mobile + "%'");
		}
		if(email != null && !"".equals(email)){
			queryString.append("and model.email like '%"+ email + "%'");
		}
		
		return new PagingEnumerator<WeixinUser>(em,queryString.toString(),startIndex,maxCount);
	}
	
	public PagingEnumerator<WeixinUser> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		StringBuffer queryString=new StringBuffer();
		queryString.append("select model from WeixinUser model where 1=1 ");
		if(filterMap.containsKey("name")){
			queryString.append("and model.name like '%"+filterMap.get("name")+"%'");
		}
		if(filterMap.containsKey("idNo")){
			queryString.append("and model.idNo like '%"+ filterMap.get("idNo") + "%'");
		}
		if(filterMap.containsKey("mobile")){
			queryString.append("and model.mobile like '%"+ filterMap.get("mobile") + "%'");
		}
		if(filterMap.containsKey("email")){
			queryString.append("and model.email like '%"+ filterMap.get("email") + "%'");
		}
		if(filterMap.containsKey("customer")){
			queryString.append("and model.customer = '"+ filterMap.get("customer") + "'");
		}
		return new PagingEnumerator<WeixinUser>(em,queryString.toString(),pageNo, pageSize);
	}
	
	public List<WeixinUser> findByWeixinNoAndCustomer(String weixinNo, String customer) {
		try {
			final String queryString = "select model from WeixinUser model where model.weixinNo= :propertyValue1 and model.customer=:propertyValue2";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue1", weixinNo);
			query.setParameter("propertyValue2", customer);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}