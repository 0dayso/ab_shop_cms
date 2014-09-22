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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TConference entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_conference", catalog = "weixin")
public class Conference implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String logoUrl;
	private String code;
	private String content; //会议简介
	public Customer customer; 
	private int state;//会议状态
	
	
	private Set<Top> tops = new HashSet<Top>(0);
	

	// Constructors

	/** default constructor */
	public Conference() {
	}

	/** full constructor */
	public Conference(String title, String logoUrl,
			String code) {
		this.title = title;
		this.logoUrl = logoUrl;
		this.code = code;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "title", length = 50)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "logoUrl", length = 500)
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "conference")
	public Set<Top> getTops() {
		return tops;
	}

	public void setTops(Set<Top> tops) {
		this.tops = tops;
	}

	@Column(name="content", length=2000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
	@JoinColumn(name = "customer_id", nullable = true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name="state")
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}