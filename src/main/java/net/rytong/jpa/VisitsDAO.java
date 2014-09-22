package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.ICustomerDAO;
import net.rytong.dao.IVisitsDAO;
import net.rytong.entity.Visits;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Visits entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see net.rytong.entity.Visits
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class VisitsDAO implements IVisitsDAO {
	// property constants
	public static final String USER_NAME = "userName";
	public static final String VISIT_TIME = "visitTime";
	public static final String INPUT_INFO = "inputInfo";
	public static final String SYSTEM_INFO = "systemInfo";
	public static final String NICK_NAME = "nickname";

	@PersistenceContext
	private EntityManager em;

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
	 * CustomerDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Visits entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Visits entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Visits entity. This operation must be performed
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
	 *            Visits entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Visits entity) {
		try {
			entity = em.getReference(Visits.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 * entity = CustomerDAO.update(entity);
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
	public Visits update(Visits entity) {
		try {
			Visits result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Visits findById(Long id) {
		try {
			Visits instance = em.find(Visits.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<Visits> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Visits> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {

		try {
			final String queryString = "select model from Visits model where model."
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

	@Override
	public List<Visits> findByShotName(Object shotName,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByCompanyName(Object companyName,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByAddress(Object address,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByContactor(Object contactor,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByMobile(Object mobile, int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByPhone(Object phone, int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByServiceItem(Object serviceItem,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByIpAddress(Object ipAddress,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByUsername(Object username,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByPassword(Object password,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByDesKey(Object desKey, int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByDesIv(Object desIv, int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByIsValid(Object isValid,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByRecordDate(Object recordDate,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByChannelDesc(Object channelDesc,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Visits findByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visits> findAll(int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingEnumerator<Visits> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int pageIndex, int pageCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingEnumerator<Visits> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Visits model where ");
			
			if(filterMap.containsKey("beginDate")){
				queryString.append("model.visitTime >=" + filterMap.get("beginDate")+"000000" + " and");
			}
			if(filterMap.containsKey("endDate")){
				queryString.append(" model.visitTime <=" + filterMap.get("endDate")+"000000" + " and");
			}
			if(filterMap.containsKey("infoType")){
				queryString.append(" model.infoType =" + filterMap.get("infoType")+" and");
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
			PagingEnumerator<Visits> listpEnumerator=new PagingEnumerator(em, sql1, pageNo, pageSize);
			return listpEnumerator;
		}catch(RuntimeException re){
			throw re;
		}
	}

	public List<Object[]> getNumsByUser(Map<String, Object> filterMap) {
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model.userName, count(id) from Visits model where 1=1 ");
			
			if(filterMap.containsKey("beginDate")){
				queryString.append(" and model.visitTime >=" + filterMap.get("beginDate") + "000000");
			}
			if(filterMap.containsKey("endDate")){
				queryString.append(" and model.visitTime <=" + filterMap.get("endDate")+ "000000");
			}
			if(filterMap.containsKey("infoType")){
				queryString.append(" and model.infoType = '" + filterMap.get("infoType") + "' ");
			}
			if(filterMap.containsKey("customer")){
				queryString.append(" and model.customer = '" + filterMap.get("customer") + "' ");
			}
			queryString.append(" group by model.userName ");
			Query query = em.createQuery(queryString.toString());
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<Visits> getDetilsByUser(Map<String, Object> filterMap) {
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from Visits model where 1=1 ");
			
			if(filterMap.containsKey("beginDate")){
				queryString.append(" and model.visitTime >=" + filterMap.get("beginDate") + "000000");
			}
			if(filterMap.containsKey("endDate")){
				queryString.append(" and model.visitTime <=" + filterMap.get("endDate")+ "000000");
			}
			if(filterMap.containsKey("infoType")){
				queryString.append(" and model.infoType = '" + filterMap.get("infoType") + "' ");
			}
			if(filterMap.containsKey("userName")){
				queryString.append(" and model.userName = '" + filterMap.get("userName") + "' ");
			}
			queryString.append(" order by model.visitTime desc");
			Query query = em.createQuery(queryString.toString());
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}