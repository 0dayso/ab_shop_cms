package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.ICustomerDAO;
import net.rytong.entity.Customer;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Customer entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see net.rytong.entity.Customer
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class CustomerDAO implements ICustomerDAO {
	// property constants
	public static final String SHOT_NAME = "shotName";
	public static final String COMPANY_NAME = "companyName";
	public static final String ADDRESS = "address";
	public static final String CONTACTOR = "contactor";
	public static final String MOBILE = "mobile";
	public static final String PHONE = "phone";
	public static final String SERVICE_ITEM = "serviceItem";
	public static final String IP_ADDRESS = "ipAddress";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String DES_KEY = "desKey";
	public static final String DES_IV = "desIv";
	public static final String IS_VALID = "isValid";
	public static final String RECORD_DATE = "recordDate";
	public static final String CHANNEL_DESC = "channelDesc";
	public static final String VERSION_NUM = "versionNum";

	@PersistenceContext
	private EntityManager em;

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
	 * CustomerDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Customer entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Customer entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Customer entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * CustomerDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Customer entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Customer entity) {
		try {
			entity = em.getReference(Customer.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Customer entity and return it or a copy of it
	 * to the sender. A copy of the Customer entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = CustomerDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Customer entity to update
	 * @return Customer the persisted Customer entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Customer update(Customer entity) {
		try {
			Customer result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Customer findById(Long id) {
		try {
			Customer instance = em.find(Customer.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Customer entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Customer property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Customer> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {

		try {
			final String queryString = "select model from Customer model where model."
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

	public List<Customer> findByShotName(Object shotName,
			int... rowStartIdxAndCount) {
		return findByProperty(SHOT_NAME, shotName, rowStartIdxAndCount);
	}

	public List<Customer> findByCompanyName(Object companyName,
			int... rowStartIdxAndCount) {
		return findByProperty(COMPANY_NAME, companyName, rowStartIdxAndCount);
	}

	public List<Customer> findByAddress(Object address,
			int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	public List<Customer> findByContactor(Object contactor,
			int... rowStartIdxAndCount) {
		return findByProperty(CONTACTOR, contactor, rowStartIdxAndCount);
	}

	public List<Customer> findByMobile(Object mobile,
			int... rowStartIdxAndCount) {
		return findByProperty(MOBILE, mobile, rowStartIdxAndCount);
	}

	public List<Customer> findByPhone(Object phone, int... rowStartIdxAndCount) {
		return findByProperty(PHONE, phone, rowStartIdxAndCount);
	}

	public List<Customer> findByServiceItem(Object serviceItem,
			int... rowStartIdxAndCount) {
		return findByProperty(SERVICE_ITEM, serviceItem, rowStartIdxAndCount);
	}

	public List<Customer> findByIpAddress(Object ipAddress,
			int... rowStartIdxAndCount) {
		return findByProperty(IP_ADDRESS, ipAddress, rowStartIdxAndCount);
	}

	public List<Customer> findByUsername(Object username,
			int... rowStartIdxAndCount) {
		return findByProperty(USERNAME, username, rowStartIdxAndCount);
	}

	public List<Customer> findByPassword(Object password,
			int... rowStartIdxAndCount) {
		return findByProperty(PASSWORD, password, rowStartIdxAndCount);
	}

	public List<Customer> findByDesKey(Object desKey,
			int... rowStartIdxAndCount) {
		return findByProperty(DES_KEY, desKey, rowStartIdxAndCount);
	}

	public List<Customer> findByDesIv(Object desIv, int... rowStartIdxAndCount) {
		return findByProperty(DES_IV, desIv, rowStartIdxAndCount);
	}

	public List<Customer> findByIsValid(Object isValid,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_VALID, isValid, rowStartIdxAndCount);
	}

	public List<Customer> findByRecordDate(Object recordDate,
			int... rowStartIdxAndCount) {
		return findByProperty(RECORD_DATE, recordDate, rowStartIdxAndCount);
	}

	public List<Customer> findByChannelDesc(Object channelDesc,
			int... rowStartIdxAndCount) {
		return findByProperty(CHANNEL_DESC, channelDesc, rowStartIdxAndCount);
	}

	public List<Customer> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

	/**
	 * Find all Customer entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Customer> all Customer entities
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Customer model";
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
	
	
	
	
	
	
	
	public Customer findByUserCode(String userCode) {
//		try {
//			final String queryUser = "select model from User model where model.userCode = :xusercode";
//			Query query = em.createQuery(queryUser);
//			query.setParameter("xusercode", userCode);
//
//			User user = (User) query.getResultList().get(0);
//			return user.getCustomer();
//
//		} catch (RuntimeException re) {
//
//			throw re;
//		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PagingEnumerator<Customer> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int pageIndex, int pageCount) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Customer model where ");
			
			if(shortName != null && !shortName.equals("")){
				queryString.append("model.shotName like '%" + shortName + "%' and ");
			}
			if(companyName != null && !"".equals(companyName)){
				queryString.append("model.companyName like '%" + companyName + "%' and ");
			}   
			if(address != null && !"".equals(address)){
				queryString.append("model.address like '%" + address + "%' and ");
			}
			if(contactor != null && !"".equals(contactor)){
				queryString.append("model.contactor like '%" + contactor + "%' and ");
			}
			if(username != null && !"".equals(username)){
				queryString.append("model.username like '%" + username + "%' and ");
			}
			String sql = queryString.toString().trim();
			String sql1 = null;
			if (sql.endsWith(" where")) {
				sql1 = sql.substring(0, sql.length() - 5);
			}else if(sql.endsWith(" and")) {
				sql1 = sql.substring(0, sql.length() - 3);
			}else if(sql.endsWith(" or")) {
				sql1 = sql.substring(0, sql.length() - 2);
			}
			return new PagingEnumerator(em, sql1, pageIndex, pageCount);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public PagingEnumerator<Customer> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Customer model where ");
			
			if(filterMap.containsKey("shotName")){
				queryString.append("model.shotName like '%" + filterMap.get("shotName") + "%' and ");
			}
			if(filterMap.containsKey("companyName")){
				queryString.append("model.companyName like '%" + filterMap.get("companyName") + "%' and ");
			}   
			if(filterMap.containsKey("address")){
				queryString.append("model.address like '%" + filterMap.get("address") + "%' and ");
			}
			if(filterMap.containsKey("contactor")){
				queryString.append("model.contactor like '%" + filterMap.get("contactor") + "%' and ");
			}
			if(filterMap.containsKey("username")){
				queryString.append("model.username like '%" + filterMap.get("username") + "%' and ");
			}
			String sql = queryString.toString().trim();
			String sql1 = null;
			if (sql.endsWith(" where")) {
				sql1 = sql.substring(0, sql.length() - 5);
			}else if(sql.endsWith(" and")) {
				sql1 = sql.substring(0, sql.length() - 3);
			}else if(sql.endsWith(" or")) {
				sql1 = sql.substring(0, sql.length() - 2);
			}
			return new PagingEnumerator(em, sql1, pageNo, pageSize);
		}catch(RuntimeException re){
			throw re;
		}
	}
}