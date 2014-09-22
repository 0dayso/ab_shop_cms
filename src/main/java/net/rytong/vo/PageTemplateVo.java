package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.PageTemplate;

public class PageTemplateVo {
	private Long id;
	private String title;
	private String titleBgColor;
	private String bannerImage;
	private String bgImage;
	private String content;
	private String name;
	private String url;
	private Integer visitCount;
	private Integer pageType;
	
	private String templateName;
	private Integer templateType;
	private String templatePrice;
	private String templateImage;
	private Integer templateStatus;
	
	public PageTemplateVo(PageTemplate page) {
		if (page != null) {
			id = page.getId();
			name = page.getName();
			titleBgColor = page.getTitleBgColor();
			bannerImage = page.getBannerImage();
			bgImage = page.getBgImage();
			title = page.getTitle();
			content = page.getContent();
			url = page.getUrl();
			pageType = page.getPageType();
			visitCount = page.getVisitCount();
			
			templateName = page.getTemplateName();
			templateType = page.getTemplateType();
			templatePrice = page.getTemplatePrice();
			templateImage = page.getTemplateImage();
			templateStatus = page.getTemplateStatus();
		}
	}
	
	public static List<PageTemplateVo> convert(List<PageTemplate> pages) {
		List<PageTemplateVo> list = new ArrayList<PageTemplateVo>();
		for (PageTemplate page : pages) {
			PageTemplateVo vo = new PageTemplateVo(page);
			list.add(vo);
		}
		return list;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
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

	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	public String getTitleBgColor() {
		return titleBgColor;
	}

	public void setTitleBgColor(String titleBgColor) {
		this.titleBgColor = titleBgColor;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getTemplatePrice() {
		return templatePrice;
	}

	public void setTemplatePrice(String templatePrice) {
		this.templatePrice = templatePrice;
	}

	public String getTemplateImage() {
		return templateImage;
	}

	public void setTemplateImage(String templateImage) {
		this.templateImage = templateImage;
	}

	public Integer getTemplateStatus() {
		return templateStatus;
	}

	public void setTemplateStatus(Integer templateStatus) {
		this.templateStatus = templateStatus;
	}
}