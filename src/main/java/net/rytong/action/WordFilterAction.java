package net.rytong.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.WordFilter;
import net.rytong.impl.WordFilterServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class WordFilterAction extends CRUDActionSupport<WordFilter>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private WordFilterServiceImpl wordFilterServiceImpl;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String getAllWord() throws Exception {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		WordFilter wordFilter = this.getEntity();
		String word = wordFilter.getWord();
		Integer status = wordFilter.getStatus();
		
		if (StringUtils.isNotBlank(word)) {
			filterMap.put("word", word);
		}
		if (status != null && status != -1) {
			filterMap.put("status", status);
		}
		
		List<WordFilter>  list = wordFilterServiceImpl.getAllWord(filterMap);
		JSONObject obj = new JSONObject();
		obj.put("list", list);
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String editWord() throws JSONException{
		WordFilter wordFilter = this.getEntity();
		wordFilterServiceImpl.update(wordFilter);
		JSONObject object = new JSONObject();
		object.put("id", wordFilter.getId().toString());
		object.put("content", wordFilter.getContent());
		this.setAjaxInputStream(object.toString());
		return AJAX;
	}
	
	public String getWordById() throws Exception{
		WordFilter wordFilter = this.getEntity();
		JSONObject object = new JSONObject();
		object.put("id", wordFilter.getId().toString());
		object.put("word", wordFilter.getWord());
		object.put("status", wordFilter.getStatus().toString());
		object.put("content", wordFilter.getContent());
		this.setAjaxInputStream(object.toString());
		return AJAX;
	}
	
	@Override
	public void validate() {
	}

	@Override
	public CRUDService<WordFilter> getAutowiredService() {
		return wordFilterServiceImpl;
	}
}
