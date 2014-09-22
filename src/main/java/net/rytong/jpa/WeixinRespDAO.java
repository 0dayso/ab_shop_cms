package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IWeixinRespDAO;
import net.rytong.entity.WeixinResp;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * WeixinResp entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class WeixinRespDAO implements IWeixinRespDAO {
	// property constants
	public static final String KEY_ID = "keyId";
	public static final String TYPE = "type";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String PIC_NAME = "picName";
	public static final String URL = "url";
	public static final String CUSTOMER = "customer";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved WeixinResp entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WeixinRespDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinResp entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WeixinResp entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent WeixinResp entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * WeixinRespDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinResp entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinResp entity) {
		try {
			entity = em.getReference(WeixinResp.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved WeixinResp entity and return it or a copy of
	 * it to the sender. A copy of the WeixinResp entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = WeixinRespDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinResp entity to update
	 * @return WeixinResp the persisted WeixinResp entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WeixinResp update(WeixinResp entity) {
		try {
			WeixinResp result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public WeixinResp findById(Integer id) {
		try {
			WeixinResp instance = em.find(WeixinResp.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all WeixinResp entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinResp property to query
	 * @param value
	 *            the property value to match
	 * @return List<WeixinResp> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<WeixinResp> findByProperty(String propertyName, final Object value) {
		try {
			final String queryString = "select model from WeixinResp model where model." + propertyName + "= :propertyValue";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<WeixinResp> findByKeyId(Object keyId) {
		return findByProperty(KEY_ID, keyId);
	}

	public List<WeixinResp> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<WeixinResp> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<WeixinResp> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<WeixinResp> findByPicName(Object picName) {
		return findByProperty(PIC_NAME, picName);
	}

	public List<WeixinResp> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	/**
	 * Find all WeixinResp entities.
	 * 
	 * @return List<WeixinResp> all WeixinResp entities
	 */
	@SuppressWarnings("unchecked")
	public List<WeixinResp> findAll() {
		try {
			final String queryString = "select model from WeixinResp model";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	public PagingEnumerator<WeixinResp> findByProperties(
			String title, String type,String keyId,String picName,String customer, int pageIndex,	int pageCount) {
		try{
		    StringBuffer queryString = new StringBuffer("select model from WeixinResp model where 1=1 ");
			if(title != null && !title.equals("")){
				queryString.append(" and model.title like '%"+title+"%'");
			}
			if(type != null && !type.equals("")){
				queryString.append(" and model.type = '"+type+"' ");
			} else {
				queryString.append(" and model.type in('news', 'text') ");
			}
			if(keyId != null && !keyId.equals("")){
				queryString.append(" and model.keyId like '%"+keyId+"%'");
			} else {
				queryString.append(" and model.keyId like 'key_%'");
			}
			if(picName != null && !picName.equals("")){
				queryString.append(" and model.picName like '%"+picName+"%'");
			}	
			if(customer != null && !customer.equals("")){
				queryString.append(" and model.customer like '%" + customer + "%'");
			}
			
		    queryString.append(" order by  model.id asc");
		    return new PagingEnumerator<WeixinResp>(em, queryString.toString(), pageIndex, pageCount);
		} catch (RuntimeException e) {
			throw e;
		}
	}
	
	public PagingEnumerator<WeixinResp> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
		    StringBuffer queryString = new StringBuffer("select model from WeixinResp model where 1=1 ");
			if(filterMap.containsKey("title")){
				queryString.append(" and model.title like '%"+filterMap.get("title").toString()+"%'");
			}
			if(filterMap.containsKey("type")){
				queryString.append(" and model.type = '"+filterMap.get("type").toString()+"' ");
			} 
			if(filterMap.containsKey("keyId")){
				queryString.append(" and model.keyId like '%"+filterMap.get("keyId").toString()+"%'");
			} 
			if(filterMap.containsKey("picName")){
				queryString.append(" and model.picName like '%"+filterMap.get("picName").toString()+"%'");
			}	
			if(filterMap.containsKey("customer")){
				queryString.append(" and model.customer = '" + filterMap.get("customer").toString() + "'");
			}
			
		    queryString.append(" order by  model.id asc");
		    return new PagingEnumerator<WeixinResp>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<WeixinResp> findAttention() {
		try {
			final String queryString = "select model from WeixinResp model where model.type = 'attention'";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<WeixinResp> findByCustomer(Object customer) {
		return findByProperty(CUSTOMER, customer);
	}
	
	
	
}