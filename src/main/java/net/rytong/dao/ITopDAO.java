package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.Top;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for TTopDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITopDAO {
	/**
	 * Perform an initial save of a previously unsaved TTop entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITTopDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TTop entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Top entity);

	/**
	 * Delete a persistent TTop entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITTopDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TTop entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Top entity);

	/**
	 * Persist a previously saved TTop entity and return it or a copy of it to
	 * the sender. A copy of the TTop entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITTopDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TTop entity to update
	 * @return TTop the persisted TTop entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Top update(Top entity);

	public Top findById(Long id);

	/**
	 * Find all TTop entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TTop property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TTop> found by query
	 */
	public List<Top> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Top> findByConferenceId(Object conferenceId,
			int... rowStartIdxAndCount);

	public List<Top> findByName(Object name, int... rowStartIdxAndCount);

	public List<Top> findByCode(Object code, int... rowStartIdxAndCount);

	/**
	 * Find all TTop entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TTop> all TTop entities
	 */
	public List<Top> findAll(int... rowStartIdxAndCount);
	public PagingEnumerator<Top> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount);
	
	public List<Top> list(Map<String, Object> filterMap);
}