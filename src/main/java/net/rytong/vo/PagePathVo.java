package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Page;

public class PagePathVo {
	private Long id;
	private String title;
	private String url;
	
	public PagePathVo(Page page) {
		if (page != null) {
			id = page.getId();
			title = page.getTitle();
			url = page.getUrl();
		}
	}
	
	public static List<PagePathVo> convert(List<Page> pages) {
		List<PagePathVo> list = new ArrayList<PagePathVo>();
		for (Page page : pages) {
			PagePathVo vo = new PagePathVo(page);
			list.add(vo);
		}
		return list;
	}
	
	public static List<PagePathVo> convert(Page page) {
		List<PagePathVo> list = new ArrayList<PagePathVo>();
		PagePathVo vo = new PagePathVo(page);
		list.add(vo);
		return list;
	}

	public static String getPath(Page page) {
		return String.valueOf(page.getId());
	}
	
	public static List<String> getPath(List<Page> pages) {
		List<String> list = new ArrayList<String>();
		for (Page page : pages) {
			list.add(getPath(page));
		}
		return list;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}