package net.rytong.entity;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * WeixinResp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_coupon")
public class Coupon implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
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
	private WeixinKey key;

	/** default constructor */
	public Coupon() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
//	@Id    
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="resp_commend")     
//	@SequenceGenerator(name="resp_commend", allocationSize = 1, initialValue = 1,sequenceName="RESP_COMMEND")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "t_code", length = 50)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "t_type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "t_title", length = 500)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "t_bg_image", length = 200)
	public String getBgImage() {
		return bgImage;
	}

	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}

	@Column(name = "t_banner", length = 200)
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	@Column(name = "t_hd_image", length = 200)
	public String getHdImage() {
		return hdImage;
	}

	public void setHdImage(String hdImage) {
		this.hdImage = hdImage;
	}

	@Column(name = "t_explain", length = 2000)
	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	@Column(name = "t_url", length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "t_start_time")
	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	@Column(name = "t_end_time")
	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Column(name = "t_customer", length = 20)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@OneToOne(cascade = CascadeType.ALL)  
    @JoinColumn(name = "t_key")  
	public WeixinKey getKey() {
		return key;
	}

	public void setKey(WeixinKey key) {
		this.key = key;
	}
}