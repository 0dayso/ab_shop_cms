package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IFunctionRightDAO;
import net.rytong.entity.FunctionRight;
import net.rytong.service.IFunctionRightService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FunctionRightServiceImpl implements IFunctionRightService, CRUDService<FunctionRight>{
	@Autowired
	private IFunctionRightDAO iFunctionRightDAO;
	
	public List<FunctionRight> findAll() {
		return iFunctionRightDAO.findAll(null);
	}

	public FunctionRight findById(Long functionRightId) {
		return iFunctionRightDAO.findById(functionRightId);
	}

	public PagingEnumerator<FunctionRight> findByProperties(String code, String name,
			String description, Integer level,Integer isValid,int pageIndex, int pageCount) {
		return iFunctionRightDAO.findByProperties(code,name,description,level,isValid,pageIndex,pageCount);
	}

	public void functionRightAdd(FunctionRight functionRight) {
		iFunctionRightDAO.save(functionRight);
	}

	public List<FunctionRight> findByParentAndLevel(Long parentId, Integer level) {
		return iFunctionRightDAO.findByParentAndLevel(parentId, level);
	}

	public void functionRightEdit(FunctionRight functionRight) {
		iFunctionRightDAO.update(functionRight);
	}

	public void deletefunctionRight(FunctionRight functionRight) {
		iFunctionRightDAO.delete(functionRight);
	}

	public List<FunctionRight> findByFunctionRight(FunctionRight functionRight) {
		return iFunctionRightDAO.findByFunctionRight(functionRight);
	}

	public List<FunctionRight> findByLevel(Integer level) {
		return iFunctionRightDAO.findByLevel(level, null);
	}

	public List<FunctionRight> findByProperty(String propertyName,Object object) {
		return iFunctionRightDAO.findByProperty(propertyName, object, null);
	}

	public List<FunctionRight> findByLikeCode(String code) {
		return iFunctionRightDAO.findByLikeCode(code);
	}
	
	public List<FunctionRight> findParentFunctionRight(Long parentId) {
		return iFunctionRightDAO.findParentFunctionRight(parentId);
	}

	public List<FunctionRight> findByLevelAndIsVaild(Integer level,
			Integer isValid) {
		return iFunctionRightDAO.findByLevelAndIsVaild(level, isValid);
	}

	@Override
	public void add(FunctionRight t) {
		iFunctionRightDAO.save(t);
	}

	@Override
	public void delete(FunctionRight t) {
		iFunctionRightDAO.delete(t);
	}

	@Override
	public List<FunctionRight> list() {
		return iFunctionRightDAO.findAll();
	}

	@Override
	public List<FunctionRight> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<FunctionRight> pageList(
			Map<String, Object> filterMap, int pageNo, int pageSize) {
		return iFunctionRightDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public FunctionRight update(FunctionRight t) {
		return iFunctionRightDAO.update(t);
	}

	@Override
	public FunctionRight view(String id) {
		return iFunctionRightDAO.findById(Long.valueOf(id));
	}
}
