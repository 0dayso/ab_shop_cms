package net.rytong.jpa;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IEmployeeDAO;
import net.rytong.entity.Employee;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Employee entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see net.rytong.entity.Employee
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class EmployeeDAO implements IEmployeeDAO {
	// property constants
	public static final String LOGIN = "login";
	public static final String ROLE = "role";
	public static final String NAME = "name";
	public static final String NAME_EN = "nameEn";
	public static final String ID_TYPE = "idType";
	public static final String ID_NO = "idNo";
	public static final String GENDER = "gender";
	public static final String EMAIL = "email";
	public static final String PIN = "pin";
	public static final String USER_CLASS = "userClass";
	public static final String MOBILE = "mobile";
	public static final String PHONE = "phone";
	public static final String AREA = "area";
	public static final String POSTCODE = "postcode";
	public static final String ADDRESS = "address";
	public static final String LOGGED_IN_AT = "loggedInAt";
	public static final String OPERATOR = "operator";
	public static final String OPT_TIME = "optTime";
	public static final String REMARK = "remark";
	public static final String VERSION_NUM = "versionNum";

	@PersistenceContext 
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved Employee entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EmployeeDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Employee entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Employee entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * EmployeeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Employee entity) {
		try {
			entity = em.getReference(Employee.class,
					entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Employee entity and return it or a copy of it
	 * to the sender. A copy of the Employee entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = EmployeeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Employee entity to update
	 * @return Employee the persisted Employee entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Employee update(Employee entity) {
		try {
			Employee result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Employee findById(Long id) {
		try {
			Employee instance = em.find(Employee.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Employee entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Employee property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Employee> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Employee model where model."
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

	public List<Employee> findByLogin(Object login, int... rowStartIdxAndCount) {
		return findByProperty(LOGIN, login, rowStartIdxAndCount);
	}

	public List<Employee> findByRole(Object role, int... rowStartIdxAndCount) {
		return findByProperty(ROLE, role, rowStartIdxAndCount);
	}

	public List<Employee> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<Employee> findByNameEn(Object nameEn,
			int... rowStartIdxAndCount) {
		return findByProperty(NAME_EN, nameEn, rowStartIdxAndCount);
	}

	public List<Employee> findByIdType(Object idType,
			int... rowStartIdxAndCount) {
		return findByProperty(ID_TYPE, idType, rowStartIdxAndCount);
	}

	public List<Employee> findByIdNo(Object idNo, int... rowStartIdxAndCount) {
		return findByProperty(ID_NO, idNo, rowStartIdxAndCount);
	}

	public List<Employee> findByGender(Object gender,
			int... rowStartIdxAndCount) {
		return findByProperty(GENDER, gender, rowStartIdxAndCount);
	}

	public List<Employee> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	public List<Employee> findByPin(Object pin, int... rowStartIdxAndCount) {
		return findByProperty(PIN, pin, rowStartIdxAndCount);
	}

	public List<Employee> findByUserClass(Object userClass,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_CLASS, userClass, rowStartIdxAndCount);
	}

	public List<Employee> findByMobile(Object mobile,
			int... rowStartIdxAndCount) {
		return findByProperty(MOBILE, mobile, rowStartIdxAndCount);
	}

	public List<Employee> findByPhone(Object phone, int... rowStartIdxAndCount) {
		return findByProperty(PHONE, phone, rowStartIdxAndCount);
	}

	public List<Employee> findByArea(Object area, int... rowStartIdxAndCount) {
		return findByProperty(AREA, area, rowStartIdxAndCount);
	}

	public List<Employee> findByPostcode(Object postcode,
			int... rowStartIdxAndCount) {
		return findByProperty(POSTCODE, postcode, rowStartIdxAndCount);
	}

	public List<Employee> findByAddress(Object address,
			int... rowStartIdxAndCount) {
		return findByProperty(ADDRESS, address, rowStartIdxAndCount);
	}

	public List<Employee> findByLoggedInAt(Object loggedInAt,
			int... rowStartIdxAndCount) {
		return findByProperty(LOGGED_IN_AT, loggedInAt, rowStartIdxAndCount);
	}

	public List<Employee> findByOperator(Object operator,
			int... rowStartIdxAndCount) {
		return findByProperty(OPERATOR, operator, rowStartIdxAndCount);
	}

	public List<Employee> findByOptTime(Object optTime,
			int... rowStartIdxAndCount) {
		return findByProperty(OPT_TIME, optTime, rowStartIdxAndCount);
	}

	public List<Employee> findByRemark(Object remark,
			int... rowStartIdxAndCount) {
		return findByProperty(REMARK, remark, rowStartIdxAndCount);
	}

	public List<Employee> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

	/**
	 * Find all Employee entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Employee> all Employee entities
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Employee model";
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
	public List<Employee> findByLoginPin(String login, String pin) {
		try {
			final String queryString = "select model from Employee model where model.login =:xlogin and model.pin =:xpin ";
			Query query = em.createQuery(queryString);
			query.setParameter("xlogin", login);
			query.setParameter("xpin", pin);
			
			return query.getResultList();
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	public PagingEnumerator<Employee> findByProperties(String login,
			String name, String idType, String idNo, String gender,
			String mobile, String area, String address, Long signStartTime,
			Long signEndTime, String email, String postcode, int pageIndex,
			int pageCount) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Employee model where model.id > 0 ");
			
			if(login != null && !login.equals("")){
				queryString.append(" and model.login like '%" + login + "%'");
			}
			if(name != null && !"".equals(name)){
				queryString.append(" and model.name like '%" + name + "%'");
			}
			if(idType != null && !"".equals(idType) && !"-1".equals(idType)){
				queryString.append(" and model.idType like '%" + idType + "%'");
			}
			
			if(idNo != null && !idNo.equals("")){
				queryString.append(" and model.idNo like '%" + idNo + "%'");
			}
			if(gender != null && !"".equals(gender) && !"-1".equals(gender)){
				queryString.append(" and  model.gender = '" + gender + "'");
			}
			if(mobile != null && !"".equals(mobile)){
				queryString.append(" and model.mobile like '%" + mobile + "%'");
			}
			if(area != null && !"".equals(area)  && !"-1".equals(area)){
				queryString.append(" and model.area like '%" + area + "%'");
			}
			if(address != null && !"".equals(address)){
				queryString.append(" and model.address like '%" + address + "%'");
			}
			if(email != null && !"".equals(email)){
				queryString.append(" and model.email like '%" + email + "%'");
			}
			if(postcode != null && !"".equals(postcode)){
				queryString.append(" and model.postcode like '%" + postcode + "%'");
			}
			if(signStartTime!=null&&!signStartTime.equals("")&& signEndTime!=null&&!signEndTime.equals("")){
				if(signStartTime.equals(signEndTime)){
					queryString.append(" and  model.optTime>"+signStartTime+" and model.optTime <"+(signEndTime+235959L) +"");
				}else{
					queryString.append(" and  model.optTime >"+signStartTime+" and model.optTime <"+signEndTime +"");
				}
			}
			if( signStartTime!=null && !signStartTime.equals("") && (signEndTime == null || "".equals(signEndTime))){
				queryString.append(" and  model.optTime > " + signStartTime +"");  
			}
			if((signStartTime == null || "".equals(signStartTime))&& signEndTime!=null&&!signEndTime.equals("")){
				queryString.append(" and  model.optTime < " + signEndTime +"");
			}
			return new PagingEnumerator<Employee>(em, queryString.toString(), pageIndex, pageCount);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public PagingEnumerator<Employee> pageList(Map<String, Object> filterMap,int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Employee model where model.id > 0 ");
			
			if(filterMap.containsKey("login")){
				queryString.append(" and model.login like '%" + filterMap.get("login").toString() + "%'");
			}
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%" + filterMap.get("name").toString() + "%'");
			}
			if(filterMap.containsKey("idType") && !"-1".equals(filterMap.get("idType").toString())){
				queryString.append(" and model.idType like '%" + filterMap.get("idType").toString() + "%'");
			}
			
			if(filterMap.containsKey("idNo")){
				queryString.append(" and model.idNo like '%" + filterMap.get("idNo").toString() + "%'");
			}
			if(filterMap.containsKey("gender") && !"-1".equals(filterMap.get("gender").toString())){
				queryString.append(" and  model.gender = '" + filterMap.get("gender").toString() + "'");
			}
			if(filterMap.containsKey("mobile")){
				queryString.append(" and model.mobile like '%" + filterMap.get("mobile").toString() + "%'");
			}
			if(filterMap.containsKey("area") && !"-1".equals(filterMap.get("area").toString())){
				queryString.append(" and model.area like '%" + filterMap.get("area").toString() + "%'");
			}
			if(filterMap.containsKey("address")){
				queryString.append(" and model.address like '%" + filterMap.get("address").toString() + "%'");
			}
			if(filterMap.containsKey("email")){
				queryString.append(" and model.email like '%" + filterMap.get("email").toString() + "%'");
			}
			if(filterMap.containsKey("postcode")){
				queryString.append(" and model.postcode like '%" + filterMap.get("postcode").toString() + "%'");
			}
			if(filterMap.containsKey("signStartTime") && filterMap.containsKey("signEndTime")){
				if(filterMap.get("signStartTime").toString().equals(filterMap.get("signEndTime").toString())){
					queryString.append(" and  model.optTime>"+filterMap.get("signStartTime").toString()+" and model.optTime <"+(filterMap.get("signEndTime").toString()+235959L) +"");
				}else{
					queryString.append(" and  model.optTime >"+filterMap.get("signStartTime").toString()+" and model.optTime <"+filterMap.get("signEndTime").toString() +"");
				}
			}
			if(filterMap.containsKey("signStartTime") && !filterMap.containsKey("signEndTime")){
				queryString.append(" and  model.optTime > " + filterMap.get("signStartTime").toString() +"");  
			}
			if(!filterMap.containsKey("signStartTime") && filterMap.containsKey("signEndTime")){
				queryString.append(" and  model.optTime < " + filterMap.get("signEndTime").toString() +"");
			}
			return new PagingEnumerator<Employee>(em, queryString.toString(), pageNo, pageSize);
		}catch(RuntimeException re){
			throw re;
		}
	}
}