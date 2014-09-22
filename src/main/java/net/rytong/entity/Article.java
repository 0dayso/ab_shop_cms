package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_article")
public class Article implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String title;
	private String style;
	private String bgColor;
	private Integer position;
	private String html;
	private Page linkPage;
	private Page page;
	private String customer;
	
	// Constructors
	/** default constructor */
	public Article() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
//	@Id    
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="customer_commend")     
//	@SequenceGenerator(name="customer_commend", allocationSize = 1, initialValue = 1,sequenceName="CUSTOMER_COMMEND")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "t_title", length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "t_style", length = 2000)
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name = "t_bg_color", length = 20)
	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	@Column(name = "t_position")
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "t_html", length = 5000)
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	@OneToOne(cascade = CascadeType.ALL)  
	@JoinColumn(name = "t_link_page", unique = true, nullable = true)  
	public Page getLinkPage() {
		return linkPage;
	}
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "t_page_id", unique = false, nullable = false)
	public Page getPage() {
		return page;
	}

	public void setLinkPage(Page linkPage) {
		this.linkPage = linkPage;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	@Column(name = "t_customer", length = 20)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}