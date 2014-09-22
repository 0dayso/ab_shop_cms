package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IResourceDAO;
import net.rytong.entity.Resource;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * Resource entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class ResourceDAO implements IResourceDAO {
	// property constants
	public static final String TYPE = "type";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String PIC_NAME = "picName";
	public static final String URL = "url";
	public static final String CUSTOMER = "customer";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Perform an initial save of a previously unsaved Resource entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ResourceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Resource entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Resource entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Delete a persistent Resource entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ResourceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Resource entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Resource entity) {
		try {
			entity = em.getReference(Resource.class, entity.getId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Persist a previously saved Resource entity and return it or a copy of
	 * it to the sender. A copy of the Resource entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ResourceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Resource entity to update
	 * @return Resource the persisted Resource entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Resource update(Resource entity) {
		try {
			Resource result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Resource findById(Integer id) {
		try {
			Resource instance = em.find(Resource.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Find all Resource entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Resource property to query
	 * @param value
	 *            the property value to match
	 * @return List<Resource> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> findByProperty(String propertyName, final Object value) {
		try {
			final String queryString = "select model from Resource model where model." + propertyName + "= :propertyValue";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Resource> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<Resource> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Resource> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Resource> findByPicName(Object picName) {
		return findByProperty(PIC_NAME, picName);
	}

	public List<Resource> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	/**
	 * Find all Resource entities.
	 * 
	 * @return List<Resource> all Resource entities
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> findAll() {
		try {
			final String queryString = "select model from Resource model";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public PagingEnumerator<Resource> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		try{
		    StringBuffer queryString = new StringBuffer("select model from Resource model where 1=1 ");
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
		    return new PagingEnumerator<Resource>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Resource> findAttention() {
		try {
			final String queryString = "select model from Resource model where model.type = 'attention'";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Resource> findByCustomer(Object customer) {
		return findByProperty(CUSTOMER, customer);
	}
	
	public List<Resource> findByTypeAndCustomer(Object type, String customer) {
		try {
			final String queryString = "select model from Resource model where model.type= :propertyValue1 and model.customer=:propertyValue2";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue1", type);
			query.setParameter("propertyValue2", customer);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List<Object[]> countsByCustomer(Object customer) {
		try{
			final String queryString = "select model.type,count(*) from Resource model where model.customer = :propertyValue1 group by model.type";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue1", customer);
			return query.getResultList();
		}catch(RuntimeException e){
			throw e;
		}
	}
	
}