package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.WeixinMenu;



public class WeixinMenuVo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer level;
	private String name;
	private String type;
	private String subId;
	private Integer state;
	private String customer;
	private String content;
	private Integer sort;
	private String resourceId;
	private String picLink;
	private String url;
	private String resouceTitle;


	public WeixinMenuVo() {
	}

	public WeixinMenuVo(WeixinMenu menu) {
		id = menu.getId();
		level = menu.getLevel();
		name = menu.getName();
		type = menu.getType();
		subId = menu.getSubId();
		state = menu.getState();
		customer = menu.getCustomer();
		content = menu.getContent();
		sort = menu.getSort();
		url = menu.getUrl();
	}
	
	public static List<WeixinMenuVo> convert(List<WeixinMenu> menus) {
		List<WeixinMenuVo> list = new ArrayList<WeixinMenuVo>();
		for (WeixinMenu menu : menus) {
			WeixinMenuVo vo = new WeixinMenuVo(menu);
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


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSubId() {
		return subId;
	}


	public void setSubId(String subId) {
		this.subId = subId;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getPicLink() {
		return picLink;
	}

	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}

	public String getResouceTitle() {
		return resouceTitle;
	}

	public void setResouceTitle(String resouceTitle) {
		this.resouceTitle = resouceTitle;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}