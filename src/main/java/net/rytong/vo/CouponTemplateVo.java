package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.CouponTemplate;

public class CouponTemplateVo {
	private String id;
	private String code;
	private Integer type;
	private String title;
	private String bgImage;
	private String prototype;
	private String headerImage;
	private String banner;
	private String explain;
	private Integer state;
	private String customer;

	
	public CouponTemplateVo(CouponTemplate couponTemplate) {
		id = couponTemplate.getId().toString();
		code = couponTemplate.getCode();
		type = couponTemplate.getType();
		title = couponTemplate.getTitle();
		bgImage = couponTemplate.getBgImage();
		banner = couponTemplate.getBanner();
		explain = couponTemplate.getExplain();
		customer = couponTemplate.getCustomer();
		state = couponTemplate.getState();
		prototype = couponTemplate.getPrototype();
		headerImage = couponTemplate.getHeaderImage();
	}
	
	public static List<CouponTemplateVo> convert(List<CouponTemplate> couponTemplates) {
		List<CouponTemplateVo> list = new ArrayList<CouponTemplateVo>();
		for (CouponTemplate couponTemplate : couponTemplates) {
			CouponTemplateVo vo = new CouponTemplateVo(couponTemplate);
			list.add(vo);
		}
		return list;
	}
	
	public static CouponTemplateVo convert(CouponTemplate couponTemplate) {
		return new CouponTemplateVo(couponTemplate);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBgImage() {
		return bgImage;
	}
	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPrototype() {
		return prototype;
	}
	public void setPrototype(String prototype) {
		this.prototype = prototype;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}
	
}