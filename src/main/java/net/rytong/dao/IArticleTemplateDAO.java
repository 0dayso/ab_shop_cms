package net.rytong.dao;
import java.util.List;
import java.util.Map;

import net.rytong.entity.ArticleTemplate;
import net.rytong.utils.PagingEnumerator;

/**
 * Interface for CustomerDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IArticleTemplateDAO {
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
	public void save(ArticleTemplate entity);

	/**
	 * Delete a persistent ArticleTemplate entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IArticleTemplateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ArticleTemplate entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ArticleTemplate entity);

	/**
	 * Persist a previously saved ArticleTemplate entity and return it or a copy of it
	 * to the sender. A copy of the ArticleTemplate entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IArticleTemplateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ArticleTemplate entity to update
	 * @return ArticleTemplate the persisted ArticleTemplate entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ArticleTemplate update(ArticleTemplate entity);

	public ArticleTemplate findById(Long id);

	/**
	 * Find all ArticleTemplate entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ArticleTemplate property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ArticleTemplate> found by query
	 */
	public List<ArticleTemplate> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	/**
	 * 根据userCode查询ArticleTemplate
	 * @param userCode
	 * @return
	 */
	public ArticleTemplate findByUserCode(String userCode);

	/**
	 * Find all ArticleTemplate entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<ArticleTemplate> all ArticleTemplate entities
	 */
	public List<ArticleTemplate> findAll(int... rowStartIdxAndCount);
	
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
	public PagingEnumerator<ArticleTemplate> findByProperties(String shortName,
			String companyName, String address, String contactor,
			String username, int ArticleTemplateIndex, int ArticleTemplateCount);
	
	public PagingEnumerator<ArticleTemplate> pageList(Map<String, Object> filterMap,
			int ArticleTemplateNo, int ArticleTemplateSize);
	
	public List<ArticleTemplate> findByPageId(Long pageId);
	
}