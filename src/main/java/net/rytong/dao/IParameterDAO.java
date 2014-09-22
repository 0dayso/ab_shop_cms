package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.Parameter;
import net.rytong.entity.WeixinUser;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for ParameterDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IParameterDAO {
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
	 * IParameterDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Parameter entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Parameter entity);

	/**
	 * Delete a persistent Parameter entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IParameterDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Parameter entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Parameter entity);

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
	 * entity = IParameterDAO.update(entity);
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
	public Parameter update(Parameter entity);

	public Parameter findById(Long id);

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
	 *            count of results to return.
	 * @return List<Parameter> found by query
	 */
	public List<Parameter> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Parameter> findByCode(Object code, int... rowStartIdxAndCount);

	public List<Parameter> findByName(Object name, int... rowStartIdxAndCount);

	public List<Parameter> findByValue(Object value, int... rowStartIdxAndCount);

	public List<Parameter> findByEffectiveDate(Object effectiveDate,
			int... rowStartIdxAndCount);

	public List<Parameter> findByExpirationDate(Object expirationDate,
			int... rowStartIdxAndCount);

	public List<Parameter> findByRemark(Object remark,
			int... rowStartIdxAndCount);

	public List<Parameter> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount);

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
	public List<Parameter> findAll(int... rowStartIdxAndCount);
	
	/**
	 * 根据条件模糊查询参数列表
	 * @param code
	 * @param name
	 * @param value
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 */
	public PagingEnumerator<Parameter> findByProperties(String code,
			String name, String value, String effectiveDate,
			String expirationDate, int pageIndex, int pageCount);
	
	/**
	 * 通过参数类别和参数名称查询
	 * @param code
	 * @param name
	 * @return
	 */
	public List<Parameter> getParameterByCodeAndNameAndCustomer(String code, String name, String customer);
	
	/**
	 * 仅用于手机充值产品查询
	 * @param code
	 * @return
	 */
	public PagingEnumerator<Parameter> matchCode(String code, String name,
			String remark,int pageIndex, int pageSize);
	
	public PagingEnumerator<Parameter> pageList(Map<String, Object> filterMap, int pageNo, int pageSize);

	public List<Parameter> getParameterByCodeAndName(String code, String name);
}
