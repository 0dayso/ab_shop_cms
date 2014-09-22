/*
 * Copyright (c) 2009-2010 Beijing RYTong Information Technologies, Ltd.
 * All rights reserved.
 *
 * No part of this source code may be copied, used, or modified
 * without the express written consent of RYTong.
 */

package net.rytong.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 分页操作
 */
public class PagingEnumerator<E> implements List<E> {

	public PagingEnumerator() {}
	
	private EntityManager em = null;

	public int getPageCount() {
		return pageCount;
	}

	//添加当前页和总页数的getter&&setter
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<E> currentPageDataList = null;
	public int total = 0;//符合条件的数据大小
	public int pageCount = 0;//总的页数
	public int pageIndex = 1;//当前页
	public boolean hasPrevPage = false;
	public boolean hasNextPage = false;
	public int totalCount=0;//符合条件的总记录数
	//public final int pageSize;
	
	private String queryString = "";
	
	public PagingEnumerator(EntityManager em,String queryString,int pageIndex,int pageSize) {
		super();
		this.em = em;
		this.pageIndex = pageIndex;
		this.queryString = queryString;
		this.currentPageDataList = getCurrentPageDataList(pageSize);
		this.total = getTotal();
		this.pageCount = getPageCount(pageSize);
		this.hasNextPage = hasNextPage();
		this.hasPrevPage = hasPrevPage();
	}
	
	public PagingEnumerator(int pageNo,int pageSize,int totalCount){
		this.pageIndex=pageNo;
		this.totalCount=totalCount;
		this.total=totalCount;
		this.pageCount = getPageCount(pageSize);
	}

	public List<E> getCurrentPageDataList() {
		return currentPageDataList;
	}

	public void setCurrentPageDataList(List<E> currentPageDataList) {
		this.currentPageDataList = currentPageDataList;
	}

	// 当前页List
	@SuppressWarnings("unchecked")
	public List<E> getCurrentPageDataList(int pageSize) {

		Query query = em.createQuery(queryString);
		query.setFirstResult((pageIndex-1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	
	}
	//符合条件的数量///////注意 from,order小写  
	public int getTotal() {

		String tempStr = queryString.toLowerCase();
		String modelStr = tempStr.substring(tempStr.indexOf("select")+6, tempStr.indexOf("from"));
		String countString = new StringBuilder().append("select count(").append(modelStr).append(") from ").toString();
		
		if(queryString.indexOf(" order ")!=-1){
			
			countString += queryString.split(" order ")[0].split("from")[1];
			
		}else{
			
			countString += queryString.split("from")[1];
		}
		
		Query query = em.createQuery(countString);
		
		return Integer.parseInt(query.getSingleResult().toString());
	}
	
	
	public int getPageCount(int pageSize) {
		
		int basePage = total / pageSize;
		
		int pc = total % pageSize;
		
		return pc==0 ? basePage : basePage+1;
		
	}

	public boolean hasPrevPage() {
		
		return pageIndex > 1;	
	
	}
	
	public boolean hasNextPage() {
		
		return pageIndex < pageCount;	
		
	}
	
	public int size() {
		return currentPageDataList.size();
	}

	public boolean isEmpty() {
		return currentPageDataList.isEmpty();
	}

	public boolean contains(Object o) {
		return currentPageDataList.contains(o);
	}

	public Iterator<E> iterator() {
		return currentPageDataList.iterator();
	}

	public Object[] toArray() {
		return currentPageDataList.toArray();
	}

	@SuppressWarnings("hiding")
    public <E> E[] toArray(E[] a) {
		return currentPageDataList.toArray(a);
	}

	public boolean add(E o) {
		return currentPageDataList.add(o);
	}

	public boolean remove(Object o) {
		return currentPageDataList.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return currentPageDataList.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		return currentPageDataList.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		return currentPageDataList.addAll(index, c);
	}

	public boolean retainAll(Collection<?> c) {
		return currentPageDataList.retainAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return currentPageDataList.removeAll(c);
	}

	public void clear() {
		currentPageDataList.clear();
	}

	public E get(int index) {
		return currentPageDataList.get(index);
	}

	public E set(int index, E element) {
		return currentPageDataList.set(index, element);
	}

	public void add(int index, E element) {
		currentPageDataList.add(index, element);
	}

	public E remove(int index) {
		return currentPageDataList.remove(index);
	}

	public int indexOf(Object o) {
		return currentPageDataList.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return currentPageDataList.lastIndexOf(o);
	}

	public ListIterator<E> listIterator() {
		return currentPageDataList.listIterator();
	}

	public ListIterator<E> listIterator(int index) {
		return currentPageDataList.listIterator(index);
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return currentPageDataList.subList(fromIndex, toIndex);
	}

}
