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
 * TTop entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_top", catalog = "weixin")
public class Top implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private String code;
	private Conference conference;
	private Integer state; 
	private Integer sort;
	private Long createTime;
	private Set<Subclaues> subclauess = new HashSet<Subclaues>(0);

	// Constructors

	/** default constructor */
	public Top() {
	}

	/** full constructor */
	public Top(String name, String code) {
		this.name = name;
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

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL )
	@JoinColumn(name = "conference_id", nullable = false)
	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "top")
	public Set<Subclaues> getSubclauess() {
		return subclauess;
	}

	public void setSubclauess(Set<Subclaues> subclauess) {
		this.subclauess = subclauess;
	}

	@Column(name="sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}


	@Column(name="state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
}