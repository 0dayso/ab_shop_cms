package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IWeixinKeyDAO;
import net.rytong.entity.WeixinKey;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * WeixinKey entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class WeixinKeyDAO implements IWeixinKeyDAO {
	// property constants
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String SUB_ID = "subId";
	public static final String STATE = "state";
	public static final String CUSTOMER = "customer";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved WeixinKey entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WeixinKeyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinKey entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WeixinKey entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent WeixinKey entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WeixinKeyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinKey entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinKey entity) {
		try {
			entity = em.getReference(WeixinKey.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved WeixinKey entity and return it or a copy of
	 * it to the sender. A copy of the WeixinKey entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WeixinKeyDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinKey entity to update
	 * @return WeixinKey the persisted WeixinKey entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WeixinKey update(WeixinKey entity) {
		try {
			WeixinKey result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public WeixinKey findById(Integer id) {
		try {
			WeixinKey instance = em.find(WeixinKey.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all WeixinKey entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinKey property to query
	 * @param value
	 *            the property value to match
	 * @return List<WeixinKey> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WeixinKey> findByProperty(String propertyName, final Object value) {
		try {
			final String queryString = "select model from WeixinKey model where model." + propertyName + "= :propertyValue";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<WeixinKey> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<WeixinKey> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<WeixinKey> findBySubId(Object subId) {
		return findByProperty(SUB_ID, subId);
	}

	public List<WeixinKey> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	
	@Override
	public List<WeixinKey> findByCustomer(Object customer) {
		return findByProperty(CUSTOMER, customer);
	}

	/**
	 * Find all WeixinKey entities.
	 * 
	 * @return List<WeixinKey> all WeixinKey entities
	 */
	@SuppressWarnings("unchecked")
	public List<WeixinKey> findAll() {
		try {
			final String queryString = "select model from WeixinKey model";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<WeixinKey> findByState() {
		try {
			final String queryString = "select model from WeixinKey model where state=0  order by buttonNo";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	public PagingEnumerator<WeixinKey> findByProperties(
			String name, String type,Integer level,String keyId,  Integer state,String customer, Integer pageIndex,	int pageCount) {
		try{
		    StringBuffer queryString = new StringBuffer("select model from WeixinKey model where 1=1 ");
			if(name != null && !name.equals("")){
				queryString.append(" and model.name like '%"+name+"%'");
			}
			if(type != null && !type.equals("")){
				queryString.append(" and model.type like '%"+type+"%' ");
			}
			if(keyId != null && !keyId.equals("")){
				queryString.append(" and model.keyId like '%"+keyId+"%' ");
			}
			if(level != null && !level.equals("")){
				queryString.append(" and model.level ="+level);
			}
			if(state != null && !state.equals("")){
				queryString.append(" and model.state = "+state);
			}
			if (customer != null && !customer.equals("")){
				queryString.append("and model.customer like '%" + customer + "%'");
			}
			
		    queryString.append(" order by  model.id asc");
		    return new PagingEnumerator<WeixinKey>(em, queryString.toString(), pageIndex, pageCount);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	public PagingEnumerator<WeixinKey> pageList(Map<String, Object> filterMap, int pageNo, int pageSize) {
		try{
		    StringBuffer queryString = new StringBuffer("select model from WeixinKey model where 1=1 ");
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%"+filterMap.get("name").toString()+"%'");
			}
			if(filterMap.containsKey("type")){
				queryString.append(" and model.type like '%"+filterMap.get("type").toString()+"%' ");
			}
			if(filterMap.containsKey("keyId")){
				queryString.append(" and model.keyId like '%"+filterMap.get("keyId").toString()+"%' ");
			}
			if(filterMap.containsKey("level")){
				queryString.append(" and model.level ="+filterMap.get("level").toString());
			}
			if(filterMap.containsKey("state")){
				queryString.append(" and model.state = "+filterMap.get("state").toString());
			}
			if(filterMap.containsKey("customer")){
				queryString.append("and model.customer like '%" + filterMap.get("customer").toString() + "%'");
			}
			
		    queryString.append(" order by  model.id asc");
		    return new PagingEnumerator<WeixinKey>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public List<WeixinKey> findkeys() {
		try {
			final String queryString = "select model from WeixinKey model where name !='Attention' and name != 'AutoAnswer' order by sort";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<WeixinKey> findkeysAndCustomer(String customer) {
		try {
			final String queryString = "select model from WeixinKey model where name !='Attention' and name != 'AutoAnswer' and customer = '" + customer + "' order by sort";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<WeixinKey> findByNameAndCustomer(Object name, String customer) {
		try {
			final String queryString = "select model from WeixinKey model where model.name=:name and customer=:customer";
			Query query = em.createQuery(queryString);
			query.setParameter("name", name);
			query.setParameter("customer", customer);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<WeixinKey> findByTypeAndCustomer(Object type, String customer) {
		try {
			final String queryString = "select model from WeixinKey model where model.type=:type and customer=:customer";
			Query query = em.createQuery(queryString);
			query.setParameter("type", type);
			query.setParameter("customer", customer);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}