package net.rytong.service;

import java.util.List;

import net.rytong.entity.Parameter;
import net.rytong.utils.PagingEnumerator;

public interface IParameterService {

	/**
	 * 增加参数
	 * @param parameter
	 */
	public void addParameter(Parameter parameter);

	/**
	 * 删除参数
	 * @param parameter
	 */
	public void deleteParameter(Parameter parameter);

	/**
	 * 通过ID查询参数
	 * @param id
	 * @return
	 */
	public Parameter getParameterById(Long id);

	/**
	 * 模糊查询参数
	 * @param code
	 * @param name
	 * @param value
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 */
	public PagingEnumerator<Parameter> parameterMatch(String code, String name,
			String value, String effectiveDate, String expirationDate, int pageIndex, int pageCount);

	/**
	 * 修改参数信息
	 * @param parameter
	 * @return
	 */
	public Parameter updateParameter(Parameter parameter);
	
	/**
	 * 通过参数类别和参数名称查询
	 * @param code
	 * @param name
	 * @return
	 */
	public List<Parameter> getParameterByCodeAndNameAndCustomer(String code, String name, String customer);
	
	/**
	 * 通过参数类别查询
	 * @param code
	 * @return
	 */
	public List<Parameter> getParameterByCode(String code);
	
	/**
	 * 通过参数类别模糊查询
	 * @param code
	 * @return
	 */
	public PagingEnumerator<Parameter> getParameterMatchCode(String code, String name,
			String remark,int pageIndex, int pageSize);
}
