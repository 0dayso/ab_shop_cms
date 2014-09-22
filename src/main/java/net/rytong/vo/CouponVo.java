package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Coupon;

public class CouponVo {
	private String id;
	private String code;
	private Integer type;
	private String title;
	private String bgImage;
	private String banner;
	private String hdImage;
	private String explain;
	private String url;
	private Long startTime;
	private Long endTime;
	private String customer;
	
	public CouponVo(Coupon coupon) {
		id = coupon.getId().toString();
		code = coupon.getCode();
		type = coupon.getType();
		title = coupon.getTitle();
		bgImage = coupon.getBgImage();
		banner = coupon.getBanner();
		explain = coupon.getExplain();
		url = coupon.getUrl();
		startTime = coupon.getStartTime();
		endTime = coupon.getEndTime();
		customer = coupon.getCustomer();
		hdImage = coupon.getHdImage();
	}
	
	public static List<CouponVo> convert(List<Coupon> coupons) {
		List<CouponVo> list = new ArrayList<CouponVo>();
		for (Coupon coupon : coupons) {
			CouponVo vo = new CouponVo(coupon);
			list.add(vo);
		}
		return list;
	}
	
	public static CouponVo convert(Coupon coupon) {
		return new CouponVo(coupon);
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getHdImage() {
		return hdImage;
	}
	public void setHdImage(String hdImage) {
		this.hdImage = hdImage;
	}
	
}