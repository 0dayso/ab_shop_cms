package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Page;

public class PageVo {
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
	
	public PageVo(Page page) {
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
		}
	}
	
	public static List<PageVo> convert(List<Page> pages) {
		List<PageVo> list = new ArrayList<PageVo>();
		for (Page page : pages) {
			PageVo vo = new PageVo(page);
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
}