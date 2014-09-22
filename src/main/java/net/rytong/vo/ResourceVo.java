package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Resource;

public class ResourceVo {
	private Integer id;
	private String type;
	private String title;
	private String content;
	private String picName;
	private String url;
	private String customer;

	public ResourceVo() {
	}
	
	public ResourceVo(Resource r) {
		this.id = r.getId();
		this.title = r.getTitle();
		this.type = r.getType();
		this.content = r.getContent();
		this.picName = r.getPicName();
		this.url = r.getUrl();
		this.customer = r.getCustomer();
	}
	
	public static List<ResourceVo> convert(List<Resource> resources) {
		List<ResourceVo> list = new ArrayList<ResourceVo>();
		for (Resource resource : resources) {
			ResourceVo vo = new ResourceVo(resource);
			list.add(vo);
		}
		return list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}