package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IZanVisitDAO;
import net.rytong.entity.ZanVisit;
import net.rytong.utils.PagingEnumerator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * ZanVisit entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see net.rytong.entity.ZanVisit
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class ZanVisitDAO implements IZanVisitDAO {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved ZanVisit entity. All
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
	 *            ZanVisit entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ZanVisit entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent ZanVisit entity. This operation must be performed
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
	 *            ZanVisit entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ZanVisit entity) {
		try {
			entity = em.getReference(ZanVisit.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved ZanVisit entity and return it or a copy of it
	 * to the sender. A copy of the ZanVisit entity parameter is returned when
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
	 *            ZanVisit entity to update
	 * @return ZanVisit the persisted ZanVisit entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ZanVisit update(ZanVisit entity) {
		try {
			ZanVisit result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public ZanVisit findById(Long id) {
		try {
			ZanVisit instance = em.find(ZanVisit.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all ZanVisit entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ZanVisit property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<ZanVisit> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ZanVisit> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {

		try {
			final String queryString = "select model from ZanVisit model where model."
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
	public PagingEnumerator<ZanVisit> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select model from ZanVisit model where ");
			
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
			PagingEnumerator<ZanVisit> listpEnumerator=new PagingEnumerator(em, sql1, pageNo, pageSize);
			return listpEnumerator;
		}catch(RuntimeException re){
			throw re;
		}
	}

	@Override
	public List<ZanVisit> findAll(int... rowStartIdxAndCount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ZanVisit> getZans(String ip, String subCode) {
		try {
			StringBuffer queryString = new StringBuffer("select model from ZanVisit model where 1= 1 ");
			if(StringUtils.isNotBlank(ip)){
				queryString.append(" and model.ip = '" + ip + "'");
			}
			if(StringUtils.isNotBlank(subCode)){
				queryString.append(" and model.subCode = '" + subCode + "'");
			}
			Query query = em.createQuery(queryString.toString());
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}