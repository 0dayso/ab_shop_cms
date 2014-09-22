package net.rytong.jpa;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IFunctionRightDAO;
import net.rytong.entity.FunctionRight;
import net.rytong.utils.PagingEnumerator;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object (DAO) providing persistence and search support for
 * FunctionRight entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see net.rytong.entity.FunctionRight
 * @author MyEclipse Persistence Tools
 */
@Transactional
@Component
public class FunctionRightDAO implements IFunctionRightDAO {
	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String PATH = "path";
	public static final String DESCRIPTION = "description";
	public static final String LEVEL = "level";
	public static final String IS_VALID = "isValid";
	public static final String VERSION_NUM = "versionNum";

	@PersistenceContext
	private EntityManager em;

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
	 * FunctionRightDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FunctionRight entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FunctionRight entity) {
		try {
			em.persist(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 * FunctionRightDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FunctionRight entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FunctionRight entity) {
		try {
			entity = em.getReference(FunctionRight.class, entity
					.getFunctionRightId());
			em.remove(entity);
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 * entity = FunctionRightDAO.update(entity);
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
	public FunctionRight update(FunctionRight entity) {
		try {
			FunctionRight result = em.merge(entity);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public FunctionRight findById(Long id) {
		try {
			FunctionRight instance = em.find(FunctionRight.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<FunctionRight> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<FunctionRight> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from FunctionRight model where model."
					+ propertyName + "= :propertyValue and model.isValid = 1 order by model.level,model.sort asc ";
			Query query = em.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<FunctionRight> findByCode(Object code,
			int... rowStartIdxAndCount) {
		return findByProperty(CODE, code, rowStartIdxAndCount);
	}

	public List<FunctionRight> findByName(Object name,
			int... rowStartIdxAndCount) {
		return findByProperty(NAME, name, rowStartIdxAndCount);
	}

	public List<FunctionRight> findByPath(Object path,
			int... rowStartIdxAndCount) {
		return findByProperty(PATH, path, rowStartIdxAndCount);
	}

	public List<FunctionRight> findByDescription(Object description,
			int... rowStartIdxAndCount) {
		return findByProperty(DESCRIPTION, description, rowStartIdxAndCount);
	}

	public List<FunctionRight> findByLevel(Object level,
			int... rowStartIdxAndCount) {
		return findByProperty(LEVEL, level, rowStartIdxAndCount);
	}

	public List<FunctionRight> findByIsValid(Object isValid,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_VALID, isValid, rowStartIdxAndCount);
	}

	public List<FunctionRight> findByVersionNum(Object versionNum,
			int... rowStartIdxAndCount) {
		return findByProperty(VERSION_NUM, versionNum, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<FunctionRight> findAll(final int... rowStartIdxAndCount) {
		try {
			final String queryString = "select model from FunctionRight model";
			Query query = em.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public PagingEnumerator<FunctionRight> findByProperties(String name, String path,
			String description, Integer level,Integer isValid, int pageIndex, int pageCount) {
		try {
			StringBuffer queryString = new StringBuffer("select model from FunctionRight model where model.functionRightId >=1 ");
			if(path != null && !"".equals(path)){
				queryString.append(" and model.path like '%"+path+"%'");
			}
			if(name != null && !"".equals(name)){
				queryString.append(" and model.name like '%"+name+"%'");
			}
			if(description != null && !"".equals(description)){
				queryString.append(" and model.description like '%"+description+"%'");
			}
			if(isValid != null && !isValid.equals(-1)){
				queryString.append(" and model.isValid =" +isValid);
			}
			if(level != null && !level.equals(0)){
				queryString.append(" and model.level =" +level);
			}
			return new PagingEnumerator<FunctionRight>(em, queryString.toString(), pageIndex, pageCount);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<FunctionRight> findByFunctionRight(FunctionRight functionRight) {
		try {
			final String queryString = "select model from FunctionRight model where model.functionRight = :functionRight";
			Query query = em.createQuery(queryString);
			query.setParameter("functionRight", functionRight);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FunctionRight> findByLikeCode(String code) {
		try {
			final String queryString = "select model from FunctionRight model where model.code like '%"+code+"%'";
			Query query = em.createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FunctionRight> findByLevelAndCode(Integer level, String code) {
		try {
			StringBuffer queryString = new StringBuffer("select model from FunctionRight model where  model.level = :level and model.code like '%"+code+"%'");
			Query query = em.createQuery(queryString.toString());
			query.setParameter("level", level);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FunctionRight> findByParentAndLevel(Long parentId,
			Integer level) {
		try {
			StringBuffer queryString = new StringBuffer("select model from FunctionRight model where model.functionRight.functionRightId = :parentId and model.level = :level");
			Query query = em.createQuery(queryString.toString());
			query.setParameter("parentId", parentId);
			query.setParameter("level", level);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	//通过传过来的function_right_id来查找上一级权限
	@SuppressWarnings("unchecked")
	public List<FunctionRight> findParentFunctionRight(Long parentId) {
		try {
			StringBuffer queryString = new StringBuffer("select model from FunctionRight model where model.functionRight.functionRightId = :parentId");
			Query query = em.createQuery(queryString.toString());
			query.setParameter("parentId", parentId);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FunctionRight> findByLevelAndIsVaild(Integer level,
			Integer isValid) {
		try {
			StringBuffer queryString = new StringBuffer("select model from FunctionRight model where  model.level = :xlevel and model.isValid = :xisValid order by model.level,model.sort asc");
			Query query = em.createQuery(queryString.toString());
			query.setParameter("xisValid", isValid);
			query.setParameter("xlevel", level);
			return query.getResultList();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public PagingEnumerator<FunctionRight> pageList(
			Map<String, Object> filterMap, int pageNo, int pageSize) {
		try {
			StringBuffer queryString = new StringBuffer("select model from FunctionRight model where model.functionRightId >=1 ");
			if(filterMap.containsKey("path")){
				queryString.append(" and model.path like '%"+filterMap.get("path").toString()+"%'");
			}
			if(filterMap.containsKey("name")){
				queryString.append(" and model.name like '%"+filterMap.get("name").toString()+"%'");
			}
			if(filterMap.containsKey("description")){
				queryString.append(" and model.description like '%"+filterMap.get("description").toString()+"%'");
			}
			if(filterMap.containsKey("isValid") && !"-1".equals(filterMap.get("isValid").toString())){
				queryString.append(" and model.isValid =" +filterMap.get("isValid").toString());
			}
			if(filterMap.containsKey("level") && !"0".equals(filterMap.get("level").toString())){
				queryString.append(" and model.level =" +filterMap.get("level").toString());
			}
			queryString.append(" order by model.level,model.sort asc ");
			return new PagingEnumerator<FunctionRight>(em, queryString.toString(), pageNo, pageSize);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}