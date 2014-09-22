package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.WeixinResp;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for WeixinRespDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IWeixinRespDAO {
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
	 * IWeixinRespDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinResp entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(WeixinResp entity);

	/**
	 * Delete a persistent WeixinResp entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWeixinRespDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinResp entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinResp entity);

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
	 * entity = IWeixinRespDAO.update(entity);
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
	public WeixinResp update(WeixinResp entity);

	public WeixinResp findById(Integer id);

	/**
	 * Find all WeixinResp entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinResp property to query
	 * @param value
	 *            the property value to match
	 * @return List<WeixinResp> found by query
	 */
	public List<WeixinResp> findByProperty(String propertyName, Object value);

	public List<WeixinResp> findByKeyId(Object keyId);

	public List<WeixinResp> findByType(Object type);

	public List<WeixinResp> findByTitle(Object title);

	public List<WeixinResp> findByDescription(Object description);

	public List<WeixinResp> findByPicName(Object picName);

	public List<WeixinResp> findByUrl(Object url);
	
	public List<WeixinResp> findByCustomer(Object customer);
	

	/**
	 * Find all WeixinResp entities.
	 * 
	 * @return List<WeixinResp> all WeixinResp entities
	 */
	public List<WeixinResp> findAll();

	public PagingEnumerator<WeixinResp> findByProperties(String title, String type,String keyId,String picName,String customer,int pageIndex, int pageCount);

	public PagingEnumerator<WeixinResp> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize);
	
	public List<WeixinResp> findAttention();
}