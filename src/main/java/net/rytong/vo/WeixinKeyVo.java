package net.rytong.vo;


public class WeixinKeyVo {
	private Integer id;
	private String name;
	private String key;
	private String type;
	private String subId;
	private Integer state;
	private String content;
	private Integer sort;
	private String[] title;
	private String[] resourceId;
	private String[] picLink;
	private String[] resouceTitle;
	private String[] contents;
	private String[] url;
	private String[] flags;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
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
	public String[] getTitle() {
		return title;
	}
	public void setTitle(String[] title) {
		this.title = title;
	}
	public String[] getResourceId() {
		return resourceId;
	}
	public void setResourceId(String[] resourceId) {
		this.resourceId = resourceId;
	}
	public String[] getPicLink() {
		return picLink;
	}
	public void setPicLink(String[] picLink) {
		this.picLink = picLink;
	}
	public String[] getResouceTitle() {
		return resouceTitle;
	}
	public void setResouceTitle(String[] resouceTitle) {
		this.resouceTitle = resouceTitle;
	}
	public String[] getUrl() {
		return url;
	}
	public void setUrl(String[] url) {
		this.url = url;
	}
	public String[] getContents() {
		return contents;
	}
	public void setContents(String[] contents) {
		this.contents = contents;
	}
	public String[] getFlags() {
		return flags;
	}
	public void setFlags(String[] flags) {
		this.flags = flags;
	}
}