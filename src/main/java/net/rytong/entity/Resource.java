package net.rytong.entity;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * WeixinResp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_resource")
public class Resource implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;
	private String title;
	private String content;
	private String picName;
	private String url;
	private String customer;
	private Set<WeixinMenu> menus = new HashSet<WeixinMenu>(0);
	private Set<WeixinKey> keys = new HashSet<WeixinKey>(0);

	/** default constructor */
	public Resource() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
//	@Id    
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="resp_commend")     
//	@SequenceGenerator(name="resp_commend", allocationSize = 1, initialValue = 1,sequenceName="RESP_COMMEND")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "t_type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "t_title", length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "t_content", nullable = false, length = 2000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "t_picName", length = 500)
	public String getPicName() {
		return this.picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	@Column(name = "t_url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="customer", length=50)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "resources")
	public Set<WeixinMenu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<WeixinMenu> menus) {
		this.menus = menus;
	}
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "resources")
	public Set<WeixinKey> getKeys() {
		return this.keys;
	}

	public void setKeys(Set<WeixinKey> keys) {
		this.keys = keys;
	}
}