package net.rytong.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TSubclaues entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_subclaues", catalog = "weixin")
public class Subclaues implements java.io.Serializable {

	// Fields

	private Long id;
	private String title;
	private String logoUrl;
	private String content;
	private String linkUrl;
	private String code;
	private Long createTime;
	private String banner;
	private String describe;
	private int zan;
	private Top top;

	// Constructors

	/** default constructor */
	public Subclaues() {
	}

	/** full constructor */
	public Subclaues(String title, String logoUrl, String content,
			String linkUrl, String code) {
		this.title = title;
		this.logoUrl = logoUrl;
		this.content = content;
		this.linkUrl = linkUrl;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "top_id", nullable = false)
	public Top getTop() {
		return top;
	}

	public void setTop(Top top) {
		this.top = top;
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

	@Column(name = "content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "linkUrl", length = 500)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "code", length = 50)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "banner", length = 500)
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@Column(name = "t_describe", length = 4000)
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Column(name = "t_zan")
	public int getZan() {
		return zan;
	}

	public void setZan(int zan) {
		this.zan = zan;
	}
}