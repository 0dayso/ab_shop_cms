package net.rytong.dao;

import java.util.List;
import java.util.Map;

import net.rytong.entity.Template;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for TTemplateDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ITemplateDAO {
	/**
	 * Perform an initial save of a previously unsaved TTemplate entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * ITTemplateDAO.save(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            TTemplate entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Template entity);

	/**
	 * Delete a persistent TTemplate entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * ITTemplateDAO.delete(entity);
	 * txManager.commit(txn);
	 * entity = null;
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            TTemplate entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Template entity);

	/**
	 * Persist a previously saved TTemplate entity and return it or a copy of it
	 * to the sender. A copy of the TTemplate entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * <p>
	 * User-managed Spring transaction example:
	 * 
	 * <pre>
	 * TransactionStatus txn = txManager
	 * 		.getTransaction(new DefaultTransactionDefinition());
	 * entity = ITTemplateDAO.update(entity);
	 * txManager.commit(txn);
	 * </pre>
	 * 
	 * @see <a href =
	 *      "http://www.myeclipseide.com/documentation/quickstarts/jpaspring#containermanaged">Spring
	 *      container-managed transaction examples</a>
	 * @param entity
	 *            TTemplate entity to update
	 * @return TTemplate the persisted TTemplate entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Template update(Template entity);

	public Template findById(Long id);

	/**
	 * Find all TTemplate entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the TTemplate property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TTemplate> found by query
	 */
	public List<Template> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Template> findByName(Object name, int... rowStartIdxAndCount);

	public List<Template> findByCode(Object code, int... rowStartIdxAndCount);

	public List<Template> findByUrl(Object url, int... rowStartIdxAndCount);

	public List<Template> findByLay(Object lay, int... rowStartIdxAndCount);

	public List<Template> findByState(Object state,
			int... rowStartIdxAndCount);

	public List<Template> findByStyle(Object style,
			int... rowStartIdxAndCount);

	public List<Template> findBySource(Object source,
			int... rowStartIdxAndCount);

	/**
	 * Find all TTemplate entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<TTemplate> all TTemplate entities
	 */
	public List<Template> findAll(int... rowStartIdxAndCount);
	
	public PagingEnumerator<Template> pageList(Map<String, Object> filterMap, int pageIndex, int pageCount);
}