package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.Article;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IArticleDAO {
	/**
	 * Perform an initial save of a previously unsaved Customer entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ICustomerDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Customer entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Article entity);

	/**
	 * Delete a persistent Article entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IArticleDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Article entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Article entity);

	/**
	 * Persist a previously saved Article entity and return it or a copy of it
	 * to the sender. A copy of the Article entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IArticleDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Article entity to update
	 * @return Article the persisted Article entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Article update(Article entity);

	public Article findById(Long id);

	/**
	 * Find all Article entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Article property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Article> found by query
	 */
	public List<Article> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * 根据userCode查询Article
	 * @param userCode
	 * @return
	 */
	public Article findByUserCode(String userCode);

	/**
	 * Find all Article entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Article> all Article entities
	 */
	public List<Article> findAll(int... rowStartIdxAndCount);
	
	/**
	 * 模糊查询大客户
	 * @param shortName
	 * @param companyName
	 * @param address
	 * @param contactor
	 * @param username
	 * @param rowStartIdxAndCount
	 * @return
	 */
	public PagingEnumerator<Article> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int ArticleIndex, int ArticleCount);
	
	public PagingEnumerator<Article> pageList(Map<String, Object> filterMap,
			int ArticleNo, int ArticleSize);
	
	public List<Article> findByPageAndCustomer(Long pageId, String customer);
	public List<Article> findByLinkPageAndCustomer(Long pageId, String customer);
}