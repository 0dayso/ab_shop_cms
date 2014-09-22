package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ITemplateDAO;
import net.rytong.entity.Template;
import net.rytong.service.ITemplateService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TemplateServiceImpl implements ITemplateService, CRUDService<Template>{

	@Autowired
    private ITemplateDAO  iTemplateDAO;
	
	@Override
	public List<Template> list() {
		return null;
	}

	@Override
	public List<Template> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<Template> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iTemplateDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public Template view(String id) {
		return iTemplateDAO.findById(Long.parseLong(id));
	}

	@Override
	public void add(Template t) {
		iTemplateDAO.save(t);
	}

	@Override
	public Template update(Template t) {
		return iTemplateDAO.update(t);
	}

	@Override
	public void delete(Template t) {
		iTemplateDAO.delete(t);
	}

	@Override
	public Template findTemplateByCode(String code) {
		List<Template> templates = iTemplateDAO.findByCode(code, null);
		if(templates != null && templates.size() > 0){
			return templates.get(0);
		}
		return null;
	}
}
