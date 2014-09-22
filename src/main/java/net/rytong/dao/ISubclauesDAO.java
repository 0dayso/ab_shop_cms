package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.Conference;
import net.rytong.entity.Subclaues;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for TSubclauesDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISubclauesDAO {
	/**
	 * Perform an initial save of a previously unsaved TSubclaues entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITSubclauesDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TSubclaues entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Subclaues entity);

	/**
	 * Delete a persistent TSubclaues entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ITSubclauesDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            TSubclaues entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Subclaues entity);

	/**
	 * Persist a previously saved TSubclaues entity and return it or a copy of
	 * it to the sender. A copy of the TSubclaues entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ITSubclauesDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            TSubclaues entity to update
	 * @return TSubclaues the persisted TSubclaues entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Subclaues update(Subclaues entity);

	public Subclaues findById(Long id);

	/**
	 * Find all TSubclaues entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TSubclaues property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TSubclaues> found by query
	 */
	public List<Subclaues> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Subclaues> findByTopId(Object topId,
			int... rowStartIdxAndCount);

	public List<Subclaues> findByTitle(Object title,
			int... rowStartIdxAndCount);

	public List<Subclaues> findByLogoUrl(Object logoUrl,
			int... rowStartIdxAndCount);

	public List<Subclaues> findByContent(Object content,
			int... rowStartIdxAndCount);

	public List<Subclaues> findByLinkUrl(Object linkUrl,
			int... rowStartIdxAndCount);

	public List<Subclaues> findByCode(Object code, int... rowStartIdxAndCount);

	/**
	 * Find all TSubclaues entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TSubclaues> all TSubclaues entities
	 */
	public List<Subclaues> findAll(int... rowStartIdxAndCount);
	public PagingEnumerator<Subclaues> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount);
	public List<Subclaues> list(Map<String, Object> filterMap);
}