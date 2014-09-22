package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Parameter;
import net.rytong.entity.WeixinUser;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Parameter entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see net.rytong.entity.Parameter
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component("parameterDAO")
public class ParameterDAO implements IParameterDAO {
	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	public static final String EFFECTIVE_DATE = "effectiveDate";
	public static final String EXPIRATION_DATE = "expirationDate";
	public static final String REMARK = "remark";
	public static final String VERSION_NUM = "versionNum";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved Parameter entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ParameterDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Parameter entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Parameter entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Parameter entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ParameterDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Parameter entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Parameter entity) {
		try {
			entity = em.getReference(Parameter.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Parameter entity and return it or a copy of it
	 * to the sender. A copy of the Parameter entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ParameterDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Parameter entity to update
	 * @return Parameter the persisted Parameter entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Parameter update(Parameter entity) {
		try {
			Parameter result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Parameter findById(Long id) {
		try {
			Parameter instance = em.find(Parameter.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Parameter entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Parameter property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Parameter> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Parameter> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Parameter model where model."
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

	public List<Parameter> findByCode(Object code, int... rowStartIdxAndCount) {
		return findByProperty(CODE, code, rowStartIdxAndCount);
	}

	public List<Parameter> findByName(Object name, int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<Parameter> findByValue(Object value, int... rowStartIdxAndCount) {
		return findByProperty(VALUE, value, rowStartIdxAndCount);
	}

	public List<Parameter> findByEffectiveDate(Object effectiveDate,
			int... rowStartIdxAndCount) {
		return findByProperty(EFFECTIVE_DATE, effectiveDate,
				rowStartIdxAndCount);
	}

	public List<Parameter> findByExpirationDate(Object expirationDate,
			int... rowStartIdxAndCount) {
		return findByProperty(EXPIRATION_DATE, expirationDate,
				rowStartIdxAndCount);
	}

	public List<Parameter> findByRemark(Object remark,
			int... rowStartIdxAndCount) {
		return findByProperty(REMARK, remark, rowStartIdxAndCount);
	}

	public List<Parameter> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

	/**
	 * Find all Parameter entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Parameter> all Parameter entities
	 */
	@SuppressWarnings("unchecked")
	public List<Parameter> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from Parameter model";
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

	public PagingEnumerator<Parameter> findByProperties(String code, String name,
			String value, String effectiveDate, String expirationDate, int pageIndex, int pageCount) {
		try{
			StringBuffer queryString=new StringBuffer();
			queryString.append("select model from Parameter model where ");
			if(code!=null&&!code.equals("")){
				queryString.append("model.code like '%"+code+"%' and ");
			}
			if(name!=null&&!name.equals("")){
				queryString.append("model.name like '%"+name+"%' and ");
			}
			if(value!=null&&!value.equals("")){
				queryString.append("model.value like '%"+value+"%' and ");
			}
			if(effectiveDate!=null&&!effectiveDate.equals("")){
				queryString.append(" model.effectiveDate >= "+effectiveDate+" and ");
			}
			if(expirationDate!=null&&!expirationDate.equals("")){
				queryString.append(" model.expirationDate <= "+expirationDate+" and ");
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
			return new PagingEnumerator<Parameter>(em, sql1, pageIndex, pageCount);
		}catch(RuntimeException re){
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Parameter> getParameterByCodeAndNameAndCustomer(String code, String name, String customer) {
		try{
			final String queryString = "select model from Parameter model where model.code =:code and model.name=:name and customer=:customer";
			Query query = em.createQuery(queryString);
			query.setParameter("code", code);
			query.setParameter("name",name);
			query.setParameter("customer", customer);
			return query.getResultList();
		}catch (RuntimeException re) {
			throw re;
		}
	}

	public PagingEnumerator<Parameter> matchCode(String code, String name,
			String remark,int pageIndex, int pageSize) {
		try{
			StringBuffer queryString=new StringBuffer();
			queryString.append("select model from Parameter model where model.id > 0 ");
			if(code!=null&&!code.equals("")){
				queryString.append("and model.code like '%"+code+"%' ");
			}
			if(name!=null&&!name.equals("")){
				queryString.append("and model.name = " + name );
			}
			if(remark !=null&&!remark.equals("-1")){
				if(remark.equals("0")){
					queryString.append(" and model.remark != model.value ");
				}else{
					queryString.append(" and model.remark = model.value ");
				}
			}
			return new PagingEnumerator<Parameter>(em, queryString.toString(), pageIndex, pageSize);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	public PagingEnumerator<Parameter> pageList(Map<String, Object> filterMap, int pageNo, int pageSize) {
		try{
			StringBuffer queryString=new StringBuffer();
			queryString.append("select model from Parameter model where ");
			if(filterMap.containsKey("code")){
				queryString.append("model.code like '%"+filterMap.get("code").toString()+"%' and ");
			}
			if(filterMap.containsKey("name")){
				queryString.append("model.name like '%"+filterMap.get("name").toString()+"%' and ");
			}
			if(filterMap.containsKey("customer")){
				queryString.append("model.customer = '"+filterMap.get("customer").toString()+"' and ");
			}
			if(filterMap.containsKey("value")){
				queryString.append("model.value like '%"+filterMap.get("value").toString()+"%' and ");
			}
			if(filterMap.containsKey("effectiveDate")){
				queryString.append(" model.effectiveDate >= "+filterMap.get("effectiveDate").toString()+" and ");
			}
			if(filterMap.containsKey("expirationDate")){
				queryString.append(" model.expirationDate <= "+filterMap.get("expirationDate").toString()+" and ");
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
			return new PagingEnumerator<Parameter>(em, sql1, pageNo, pageSize);
		}catch(RuntimeException re){
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Parameter> getParameterByCodeAndName(String code, String name) {
		try{
			final String queryString = "select model from Parameter model where model.code =:code and model.name=:name ";
			Query query = em.createQuery(queryString);
			query.setParameter("code", code);
			query.setParameter("name",name);
			return query.getResultList();
		}catch (RuntimeException re) {
			throw re;
		}
	}
}