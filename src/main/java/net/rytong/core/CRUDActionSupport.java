package net.rytong.core;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.rytong.utils.PagingEnumerator;
import net.rytong.vo.TipResultVo;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@SuppressWarnings("serial")
public abstract class CRUDActionSupport<T> extends BaseAction implements
		ModelDriven<T>, Preparable {
	private Map<String, Object> filterMap;
	private Integer pageIndex;
	private Integer pageSize;
	private Integer pageCount;
	private String id;
	private Integer total;
	private PagingEnumerator<T> pageList;
	private List<T> list;
	private T entity;
	public static final String REDIRECT = "redirect";
	public static final String VIEW = "view";
	
	@Override
	public T getModel() {
		return entity;
	}

	@Override
	public void prepare() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		id = request.getParameter("id");
		if (id != null && id != "") {
			T dataEntity = getAutowiredService().view(this.id.toString());
			if (dataEntity != null) {
				this.entity = dataEntity;
			} else {
				this.entity = this.instanceAnnotationObject();
			}
		} else {
			this.entity = this.instanceAnnotationObject();
		}
	}
	
	public String search() throws Exception {
		filterMap = new HashMap<String, Object>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			if(!fieldName.equals("serialVersionUID")){
				String getter = "get" + firstLetter + fieldName.substring(1);
				Method method = entity.getClass().getMethod(getter, new Class[] {});
				Object value = method.invoke(entity, new Object[] {});
				if (null != value && !value.equals("")) {
					filterMap.put(fieldName, value);
				}
			}
		}
		addOtherFilter();
		return this.pageList();
	}
	
	public void addOtherFilter() {
	}

	/**
	 * 根据泛型类型实例化对象
	 * @return 泛型类型的对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public T instanceAnnotationObject() throws Exception {
		Class<?> cl = this.getClass();
		Type[] types = ((ParameterizedType) cl.getGenericSuperclass()).getActualTypeArguments();
		try {
			return ((Class<T>) types[0]).newInstance();
		} catch (Exception e) {
			logger.error("CRUDActionSupport泛型类型实例化失败! 错误信息: {}", e.getMessage());
			throw e;
		}
	}
	
	// 列表
	public String list() throws Exception {
		list = getAutowiredService().list();
		return SUCCESS;
	}
	
	// 条件列表
	public String listFilter() throws Exception {
		list = getAutowiredService().list(filterMap);
		return SUCCESS;
	}
	
	// 分页列表 
	public String pageList() throws Exception {
		if(this.getPageIndex() == null || this.getPageIndex() == 0){
			this.setPageIndex(1);
		}
		if(this.getPageSize() == null || this.getPageSize() == 0){
			this.setPageSize(20);
		}
		pageList = getAutowiredService().pageList(filterMap, this.getPageIndex(), this.getPageSize());
		this.setPageCount(pageList.getPageCount(20));
		this.setTotal(pageList.getPageCount(1));
		return SUCCESS;
	}
	
	// 删除,跳转
	public String delete() throws Exception {
		getAutowiredService().delete(entity);
		return REDIRECT;
	}

	// 删除,跳转
	public String deleteJSON() throws Exception {
		String result = getTipResult();
		try {
			getAutowiredService().delete(entity);
		}  catch(Exception e) {
			e.printStackTrace();
			result = getTipResult("error", "error");
		}
		this.setAjaxInputStream(result);
		return AJAX;
	}
	
	// 浏览
	public String detail() throws Exception {
		return SUCCESS;
	}
	
	
	//编辑,跳转
	public String edit() throws Exception {
		getAutowiredService().update(entity);
		return REDIRECT;
	}
	
	//添加
	public String add() throws Exception {
		getAutowiredService().add(entity);
		return REDIRECT;
	}
	
	//浏览
	public String view() throws Exception {
		return VIEW;
	}
	
	public String getTipResult(String code, String msg) {
		String code1 = StringUtils.isNotBlank(code) ? code : "ok";
		String msg1 = StringUtils.isNotBlank(code) ? msg : "ok";
		TipResultVo vo = new TipResultVo(code1, msg1);
		JSONObject obj = new JSONObject(vo);
		return obj.toString();
	}
	
	public String getTipResult() {
		return getTipResult(null, null);
	}
	
	public abstract CRUDService<T> getAutowiredService();
	
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public PagingEnumerator<T> getPageList() {
		return pageList;
	}
	public void setPageList(PagingEnumerator<T> pageList) {
		this.pageList = pageList;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public Map<String, Object> getFilterMap() {
		return filterMap;
	}
	public void setFilterMap(Map<String, Object> filterMap) {
		this.filterMap = filterMap;
	}
}
