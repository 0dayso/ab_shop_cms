package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Subclaues;

public class SubclauesVo {
	private String id;
	private String title;
	private String logoUrl;
	private String content;
	private String linkUrl;
	private String code;
	private String banner;
	private String describe;
	private String zan;
	private Long createTime;

	// Constructors

	/** default constructor */
	public SubclauesVo() {
	}
	
	public SubclauesVo(Subclaues sub) {
		id = sub.getId().toString();
		code = sub.getCode();
		title = sub.getTitle();
		linkUrl = sub.getLinkUrl();
		logoUrl = sub.getLogoUrl();
		content = sub.getContent();
		createTime = sub.getCreateTime();
		banner = sub.getBanner();
		describe = sub.getDescribe();
		zan = sub.getZan() + "";
	}
	
	public static List<SubclauesVo> convert(List<Subclaues> Subclauess) {
		List<SubclauesVo> list = new ArrayList<SubclauesVo>();
		for (Subclaues Subclaues : Subclauess) {
			SubclauesVo vo = new SubclauesVo(Subclaues);
			list.add(vo);
		}
		return list;
	}

	/** full constructor */
	public SubclauesVo(String title, String logoUrl, String content,
			String linkUrl, String code) {
		this.title = title;
		this.logoUrl = logoUrl;
		this.content = content;
		this.linkUrl = linkUrl;
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getZan() {
		return zan;
	}

	public void setZan(String zan) {
		this.zan = zan;
	}
}