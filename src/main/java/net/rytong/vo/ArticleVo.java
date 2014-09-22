package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Article;
import net.rytong.entity.Page;

public class ArticleVo {
	private Long id;
	private String title;
	private String style;
	private String bgColor;
	private Integer position;
	private String html;
	private Page page;
	
	public ArticleVo(Article page) {
		id = page.getId();
		title = page.getTitle();
		style = page.getStyle();
		bgColor = page.getBgColor();
		position = page.getPosition();
		html = page.getHtml();
	}
	
	public static List<ArticleVo> convert(List<Article> articles) {
		List<ArticleVo> list = new ArrayList<ArticleVo>();
		for (Article article : articles) {
			ArticleVo vo = new ArticleVo(article);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}