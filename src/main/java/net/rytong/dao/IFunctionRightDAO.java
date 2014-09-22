package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.FunctionRight;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for FunctionRightDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFunctionRightDAO {
	/**
	 * Perform an initial save of a previously unsaved FunctionRight entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFunctionRightDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FunctionRight entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FunctionRight entity);

	/**
	 * Delete a persistent FunctionRight entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFunctionRightDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FunctionRight entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FunctionRight entity);

	/**
	 * Persist a previously saved FunctionRight entity and return it or a copy
	 * of it to the sender. A copy of the FunctionRight entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFunctionRightDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FunctionRight entity to update
	 * @return FunctionRight the persisted FunctionRight entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FunctionRight update(FunctionRight entity);

	public FunctionRight findById(Long id);

	/**
	 * Find all FunctionRight entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FunctionRight property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<FunctionRight> found by query
	 */
	public List<FunctionRight> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	public List<FunctionRight> findByCode(Object code,
			int... rowStartIdxAndCount);

	public List<FunctionRight> findByName(Object name,
			int... rowStartIdxAndCount);

	public List<FunctionRight> findByPath(Object path,
			int... rowStartIdxAndCount);

	public List<FunctionRight> findByDescription(Object description,
			int... rowStartIdxAndCount);

	public List<FunctionRight> findByLevel(Object level,
			int... rowStartIdxAndCount);

	public List<FunctionRight> findByIsValid(Object isValid,
			int... rowStartIdxAndCount);

	public List<FunctionRight> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);

	public PagingEnumerator<FunctionRight> findByProperties(String name, String path,
			String description, Integer level,Integer isValid,int pageIndex, int pageCount);
	
	public List<FunctionRight> findByParentAndLevel(Long parentId,Integer level);
	public List<FunctionRight> findByLevelAndCode(Integer level,String code);
	public List<FunctionRight> findByLevelAndIsVaild(Integer level,Integer isValid);
	
	public List<FunctionRight> findByFunctionRight(FunctionRight functionRight);
	
	public List<FunctionRight> findParentFunctionRight(Long parentId);
	
	public List<FunctionRight> findByLikeCode(String code);
	/**
	 * Find all FunctionRight entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<FunctionRight> all FunctionRight entities
	 */
	public List<FunctionRight> findAll(int... rowStartIdxAndCount);
	
	public PagingEnumerator<FunctionRight> pageList(
			Map<String, Object> filterMap, int pageNo, int pageSize);
}