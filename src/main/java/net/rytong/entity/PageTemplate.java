package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_page_template")
public class PageTemplate implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String title;
	private String bannerImage;
	private String titleBgColor;
	private String bgImage;
	private String url;
	private String content;
	private Integer visitCount;
	private Integer pageType;
	
	private String templateName;
	private Integer templateType;
	private String templatePrice;
	private String templateImage;
	private Integer templateStatus;
	private Set<ArticleTemplate> articles = new HashSet<ArticleTemplate>(0);
	
	// Constructors
	/** default constructor */
	public PageTemplate() {
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

	@Column(name = "t_name", length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "t_url", length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "t_visit_count")
	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	@Column(name = "t_title", length = 2000)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "t_title_bg_color", length = 20)
	public String getTitleBgColor() {
		return titleBgColor;
	}

	public void setTitleBgColor(String titleBgColor) {
		this.titleBgColor = titleBgColor;
	}

	@Column(name = "t_content", length = 5000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name = "t_banner_image", length = 200)
	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	@Column(name = "t_bg_image", length = 200)
	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
	public Set<ArticleTemplate> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleTemplate> articles) {
		this.articles = articles;
	}

	@Column(name = "t_page_type")
	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}
	
	@Column(name = "t_template_type")
	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	@Column(name = "t_template_name", length = 40)
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	@Column(name = "t_template_price", length = 40)
	public String getTemplatePrice() {
		return templatePrice;
	}

	public void setTemplatePrice(String templatePrice) {
		this.templatePrice = templatePrice;
	}

	@Column(name = "t_template_image", length = 200)
	public String getTemplateImage() {
		return templateImage;
	}

	public void setTemplateImage(String templateImage) {
		this.templateImage = templateImage;
	}

	@Column(name = "t_template_status")
	public Integer getTemplateStatus() {
		return templateStatus;
	}

	public void setTemplateStatus(Integer templateStatus) {
		this.templateStatus = templateStatus;
	}
	
}