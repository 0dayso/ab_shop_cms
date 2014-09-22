package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * WeixinMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_menu")
public class WeixinMenu implements java.io.Serializable {
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
	private String url;
	private List<Resource> resources = new ArrayList<Resource>(0);


	/** default constructor */
	public WeixinMenu() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
//	@Id    
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="menu_commend")     
//	@SequenceGenerator(name="menu_commend", allocationSize = 1, initialValue = 1,sequenceName="MENU_COMMEND") 
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "t_level", nullable = false)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "t_name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "t_type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "t_parent")
	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	@Column(name = "t_state", nullable = false)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@Column(name="t_customer", nullable = false, length=20)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
    
	@Column(name="t_remark", length=2000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "t_sort", nullable = false)
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name="t_url", length=200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "t_menu_replay", joinColumns = { @JoinColumn(name = "t_menu_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "t_resource_id", nullable = false, updatable = false) })
	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}