package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.Conference;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for TConferenceDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IConferenceDAO {
	/**
	 * Perform an initial save of a previously unsaved TConference entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITConferenceDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TConference entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Conference entity);

	/**
	 * Delete a persistent TConference entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITConferenceDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TConference entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Conference entity);

	/**
	 * Persist a previously saved TConference entity and return it or a copy of
	 * it to the sender. A copy of the TConference entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITConferenceDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TConference entity to update
	 * @return TConference the persisted TConference entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Conference update(Conference entity);

	public Conference findById(Long id);

	/**
	 * Find all TConference entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TConference property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TConference> found by query
	 */
	public List<Conference> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Conference> findByWeixinNo(Object weixinNo,
			int... rowStartIdxAndCount);

	public List<Conference> findByTitle(Object title,
			int... rowStartIdxAndCount);

	public List<Conference> findByLogoUrl(Object logoUrl,
			int... rowStartIdxAndCount);

	public List<Conference> findByCode(Object code, int... rowStartIdxAndCount);

	/**
	 * Find all TConference entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TConference> all TConference entities
	 */
	public List<Conference> findAll(int... rowStartIdxAndCount);
	public PagingEnumerator<Conference> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount);
	
	public List<Conference> getAllData(Map<String,Object> filterMap);
}