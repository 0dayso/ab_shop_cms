package net.rytong.core;

import java.util.List;

import net.rytong.utils.PagingEnumerator;

import java.util.Map;


public interface CRUDService<T> {
	/**
	 * 列表 
	 */
	public List<T> list();
	/**
	 * 条件列表
	 */
	public List<T> list(final Map<String, Object> filterMap);
	/**
	 * 分页列表 
	 */
	public PagingEnumerator<T> pageList(final Map<String, Object> filterMap, int pageNo, int pageSize);
	/**
	 * 详细
	 */
	public T view(String id);
	/**
	 * 添加
	 */
	public void add(T t);
	
	/**
	 * 更新
	 */
	public T update(T t);
	
	/**
	 * 删除
	 */
	public void delete(T t);
}
