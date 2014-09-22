package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.WeixinType;
import net.rytong.utils.PagingEnumerator;

public interface IWeixinTypeDAO {

	public void save(WeixinType entity);

	/**
	 * Delete a persistent WeixinType entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IWeixinTypeDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinType entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(WeixinType entity);

	/**
	 * Persist a previously saved WeixinType entity and return it or a copy of it to
	 * the sender. A copy of the WeixinType entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IWeixinTypeDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            WeixinType entity to update
	 * @return WeixinType the persisted WeixinType entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public WeixinType update(WeixinType entity);

	public WeixinType findById(Long id);

	/**
	 * Find all WeixinType entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the WeixinType property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WeixinType> found by query
	 */
	public List<WeixinType> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<WeixinType> findByName(Object name, int... rowStartIdxAndCount);
	
	public List<WeixinType> findByCode(Object code, int... rowStartIdxAndCount);

	public PagingEnumerator<WeixinType> findByProperties(String name,String departName,Integer beginTime,Integer endTime,int pageIndex,int pageSize);
	/**
	 * Find all WeixinType entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<WeixinType> all WeixinType entities
	 */
	public List<WeixinType> findAll(int... rowStartIdxAndCount);
	
	public PagingEnumerator<WeixinType> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize);
}