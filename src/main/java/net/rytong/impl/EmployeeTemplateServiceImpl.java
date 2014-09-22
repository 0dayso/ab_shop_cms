package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.IEmployeeTemplateDAO;
import net.rytong.entity.EmployeeTemplate;
import net.rytong.service.IEmployeeTemplateService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeTemplateServiceImpl implements IEmployeeTemplateService, CRUDService<EmployeeTemplate>{

	@Autowired
    private IEmployeeTemplateDAO  iEmployeeTemplateDAO;
	
	@Override
	public List<EmployeeTemplate> list() {
		return null;
	}

	@Override
	public List<EmployeeTemplate> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<EmployeeTemplate> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iEmployeeTemplateDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public EmployeeTemplate view(String id) {
		return iEmployeeTemplateDAO.findById(Long.parseLong(id));
	}

	@Override
	public void add(EmployeeTemplate t) {
		iEmployeeTemplateDAO.save(t);
	}

	@Override
	public EmployeeTemplate update(EmployeeTemplate t) {
		return iEmployeeTemplateDAO.update(t);
	}

	@Override
	public void delete(EmployeeTemplate t) {
		iEmployeeTemplateDAO.delete(t);
	}
}
