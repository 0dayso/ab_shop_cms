package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.Role;
import net.rytong.utils.PagingEnumerator;

public interface IRoleDAO {
	
	/**
	 * 验证角色是否存在
	 * @author RobotJi
	 * @param value
	 * @return boolean
	 * @date 2011-7-8
	 */
	public boolean isRoleExist(String value);
	/**
	 * Perform an initial save of a previously unsaved Role entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRoleDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Role entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Role entity);

	/**
	 * Delete a persistent Role entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IRoleDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Role entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Role entity);

	/**
	 * Persist a previously saved Role entity and return it or a copy of it to
	 * the sender. A copy of the Role entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IRoleDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Role entity to update
	 * @return Role the persisted Role entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Role update(Role entity);

	public Role findById(Long id);

	/**
	 * Find all Role entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Role property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Role> found by query
	 */
	public List<Role> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Role> findByName(Object name, int... rowStartIdxAndCount);

	public List<Role> findByDepartName(Object departName,
			int... rowStartIdxAndCount);

	public List<Role> findByCreatedDate(Object createdDate,
			int... rowStartIdxAndCount);

	public List<Role> findByIsValid(Object isValid, int... rowStartIdxAndCount);

	public List<Role> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);
	public PagingEnumerator<Role> findByProperties(String name,String departName,Integer beginTime,Integer endTime,int pageIndex,int pageSize);
	/**
	 * Find all Role entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Role> all Role entities
	 */
	public List<Role> findAll(int... rowStartIdxAndCount);
	
	public PagingEnumerator<Role> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize);
}