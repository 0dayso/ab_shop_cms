package net.rytong.service;

import java.util.List;

import net.rytong.entity.FunctionRight;
import net.rytong.utils.PagingEnumerator;

public interface IFunctionRightService {
	/**
	 * 
	 * @return
	 */
	public List<FunctionRight> findAll();
	
	/**
	 * 
	 * @param functionRight
	 */
	public void functionRightAdd(FunctionRight functionRight);
	
	/**
	 * 
	 * @param functionRightId
	 * @return
	 */
	public FunctionRight findById(Long functionRightId);
	
	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param level
	 * @return
	 */
	public PagingEnumerator<FunctionRight> findByProperties(String code,String name,String description,Integer level,Integer isValid,int pageIndex, int pageCount);
	
	/**
	 * 
	 * @param parentId
	 * @param level
	 * @return
	 */
	public List<FunctionRight> findByParentAndLevel(Long parentId,Integer level);
	
	/**
	 * 
	 * @param functionRight
	 */
	public void functionRightEdit(FunctionRight functionRight);
	
	/**
	 * 
	 * @param functionRight
	 */
	public void deletefunctionRight(FunctionRight functionRight);
	
	/**
	 * 
	 * @param functionRight
	 * @return
	 */
	public List<FunctionRight>  findByFunctionRight(FunctionRight functionRight);
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	public List<FunctionRight> findByLevel(Integer level);
	
	/**
	 * 
	 * @param propertyName
	 * @param object
	 * @return
	 */
	public List<FunctionRight> findByProperty(String propertyName,Object object);
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public List<FunctionRight> findByLikeCode(String code);
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	public List<FunctionRight> findParentFunctionRight(Long parentId);
	
	/**
	 * 
	 * @param level
	 * @param isValid
	 * @return
	 */
	public List<FunctionRight> findByLevelAndIsVaild(Integer level,Integer isValid);
}
