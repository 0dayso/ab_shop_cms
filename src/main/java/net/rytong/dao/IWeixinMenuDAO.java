package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.WeixinMenu;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for WeixinMenuDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWeixinMenuDAO {
	/**
	 * Perform an initial save of a previously unsaved WeixinMenu entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWeixinMenuDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinMenu entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WeixinMenu entity);

	/**
	 * Delete a persistent WeixinMenu entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWeixinMenuDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinMenu entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinMenu entity);

	/**
	 * Persist a previously saved WeixinMenu entity and return it or a copy of
	 * it to the sender. A copy of the WeixinMenu entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWeixinMenuDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinMenu entity to update
	 * @return WeixinMenu the persisted WeixinMenu entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WeixinMenu update(WeixinMenu entity);

	public WeixinMenu findById(Integer id);

	/**
	 * Find all WeixinMenu entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinMenu property to query
	 * @param value
	 *            the property value to match
	 * @return List<WeixinMenu> found by query
	 */
	public List<WeixinMenu> findByProperty(String propertyName, Object value);

	public List<WeixinMenu> findByLevel(Object level);

	public List<WeixinMenu> findByName(Object name);

	public List<WeixinMenu> findByType(Object type);

	public List<WeixinMenu> findBySubId(Object subId);

	public List<WeixinMenu> findByState(Object state);
	
	public List<WeixinMenu> findByCustomer(Object customer);

	/**
	 * Find all WeixinMenu entities.
	 * 
	 * @return List<WeixinMenu> all WeixinMenu entities
	 */
	public List<WeixinMenu> findAll();
	
	public List<WeixinMenu> findByState();
	
	public PagingEnumerator<WeixinMenu> findByProperties(String name, String type,Integer level,String keyId,Integer state,String customer, Integer pageIndex,int pageCount) ;
	
	public PagingEnumerator<WeixinMenu> pageList(Map<String, Object> filterMap, int pageNo, int pageSize);
	public List<WeixinMenu> findByLevelAndParent(Object level, Object subId);
	public List<WeixinMenu> findByLevelAndCustomer(Object level, String customer);
	public List<WeixinMenu> findByTypeAndCustomer(Object type, String customer);
	public List<WeixinMenu> findByNameAndCustomer(Object name, String customer);

}