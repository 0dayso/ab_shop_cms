package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.WeixinKey;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for WeixinKeyDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWeixinKeyDAO {
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
	 * IWeixinKeyDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinKey entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WeixinKey entity);

	/**
	 * Delete a persistent WeixinKey entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWeixinKeyDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinKey entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinKey entity);

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
	 * entity = IWeixinKeyDAO.update(entity);
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
	public WeixinKey update(WeixinKey entity);

	public WeixinKey findById(Integer id);

	/**
	 * Find all WeixinKey entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinKey property to query
	 * @param value
	 *            the property value to match
	 * @return List<WeixinKey> found by query
	 */
	public List<WeixinKey> findByProperty(String propertyName, Object value);

	public List<WeixinKey> findByName(Object name);
	
	public List<WeixinKey> findkeys();

	public List<WeixinKey> findByType(Object type);

	public List<WeixinKey> findBySubId(Object subId);

	public List<WeixinKey> findByState(Object state);
	
	public List<WeixinKey> findByCustomer(Object customer);

	/**
	 * Find all WeixinKey entities.
	 * 
	 * @return List<WeixinKey> all WeixinKey entities
	 */
	public List<WeixinKey> findAll();
	
	public List<WeixinKey> findByState();
	
	public PagingEnumerator<WeixinKey> findByProperties(
			String name, String type,Integer level,String keyId,Integer state,String customer, Integer pageIndex,int pageCount) ;
	
	public PagingEnumerator<WeixinKey> pageList(Map<String, Object> filterMap,int pageNo, int pageSize);
	public List<WeixinKey> findkeysAndCustomer(String customer);
	public List<WeixinKey> findByNameAndCustomer(Object name, String customer);
	public List<WeixinKey> findByTypeAndCustomer(Object type, String customer);
}