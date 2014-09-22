package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Parameter;
import net.rytong.service.IParameterService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParameterServiceImpl implements IParameterService, CRUDService<Parameter> {
	@Autowired
	private IParameterDAO iParameterDAO;

	/**
	 * 增加参数
	 */
	public void addParameter(Parameter parameter) {
		iParameterDAO.save(parameter);
	}

	/**
	 * 删除参数
	 */
	public void deleteParameter(Parameter parameter) {
		iParameterDAO.delete(parameter);
	}

	/**
	 * 通过ID获得参数
	 */
	public Parameter getParameterById(Long id) {
		return iParameterDAO.findById(id);
	}

	/**
	 * 修改参数
	 */
	public Parameter updateParameter(Parameter parameter) {
		return iParameterDAO.update(parameter);
	}

	/**
	 * 通过条件进行模糊查询参数
	 */
	public PagingEnumerator<Parameter> parameterMatch(String code, String name,
			String value, String effectiveDate, String expirationDate, int pageIndex, int pageCount) {
		return iParameterDAO.findByProperties(code, name, value, effectiveDate, expirationDate, pageIndex, pageCount);
	}
	
	/**
	 * 通过参数类别和参数名称查询
	 */
	public List<Parameter> getParameterByCodeAndNameAndCustomer(String code, String name, String customer) {
		return iParameterDAO.getParameterByCodeAndNameAndCustomer(code, name, customer);
	}

	public List<Parameter> getParameterByCode(String code) {
		return iParameterDAO.findByCode(code, null);
	}

	public PagingEnumerator<Parameter> getParameterMatchCode(String code, String name,
			String remark,int pageIndex, int pageSize) {
		return iParameterDAO.matchCode(code, name, remark, pageIndex, pageSize);
	}

	@Override
	public void add(Parameter t) {
		iParameterDAO.save(t);
	}

	@Override
	public void delete(Parameter t) {
		iParameterDAO.delete(t);
	}

	@Override
	public List<Parameter> list() {
		return iParameterDAO.findAll();
	}

	@Override
	public List<Parameter> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Parameter> pageList(Map<String, Object> filterMap, int pageNo, int pageSize) {
		return iParameterDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Parameter update(Parameter t) {
		return iParameterDAO.update(t);
	}

	@Override
	public Parameter view(String id) {
		return iParameterDAO.findById(Long.valueOf(id));
	}
}
