package net.rytong.service;

import java.util.List;

import net.rytong.entity.Resource;

public interface IResourceService {
	List<Resource> findByType(Object type);
	List<Resource> findByTypeAndCustomer(Object type, String customer);
	public void add(Resource t);
	public Resource update(Resource t);
	public List<Resource> list();
	public Resource view(String id);
	public void delete(Resource t);
	public List<Object[]> countsByCustomer(String customer);
}
