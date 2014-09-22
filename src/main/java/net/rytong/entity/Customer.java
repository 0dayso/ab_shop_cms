package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_customer")
public class Customer implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String shotName;
	private String companyName;
	private String address;
	private String contactor;
	private String mobile;
	private String phone;
	private Integer serviceItem;
	private String ipAddress;
	private String username;
	private String password;
	private String desKey;
	private String desIv;
	private Integer isValid;
	private Integer recordDate;
	private String channelDesc;
	private Long versionNum;
	
	private String url;
	private String token;
	private String appId;
	private String secret;
	
	private String yxUrl;
	private String yxToken;
	private String yxAppId;
	private String yxSecret;
	
	private String logo ;

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** full constructor */
	public Customer(String shotName, String companyName, String address,
			String contactor, String mobile, String phone, Integer serviceItem,
			String ipAddress, String username, String password, String desKey,
			String desIv, Integer isValid, Integer recordDate,
			String channelDesc, Long versionNum) {
		this.shotName = shotName;
		this.companyName = companyName;
		this.address = address;
		this.contactor = contactor;
		this.mobile = mobile;
		this.phone = phone;
		this.serviceItem = serviceItem;
		this.ipAddress = ipAddress;
		this.username = username;
		this.password = password;
		this.desKey = desKey;
		this.desIv = desIv;
		this.isValid = isValid;
		this.recordDate = recordDate;
		this.channelDesc = channelDesc;
		this.versionNum = versionNum;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
//	@Id    
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="customer_commend")     
//	@SequenceGenerator(name="customer_commend", allocationSize = 1, initialValue = 1,sequenceName="CUSTOMER_COMMEND")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "shot_name", length = 50)
	public String getShotName() {
		return this.shotName;
	}

	public void setShotName(String shotName) {
		this.shotName = shotName;
	}

	@Column(name = "company_name", length = 50)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "contactor", length = 10)
	public String getContactor() {
		return this.contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "phone", length = 15)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "service_item")
	public Integer getServiceItem() {
		return this.serviceItem;
	}

	public void setServiceItem(Integer serviceItem) {
		this.serviceItem = serviceItem;
	}

	@Column(name = "ip_address", length = 50)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Column(name = "username", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "des_key", length = 8)
	public String getDesKey() {
		return this.desKey;
	}

	public void setDesKey(String desKey) {
		this.desKey = desKey;
	}

	@Column(name = "des_iv", length = 8)
	public String getDesIv() {
		return this.desIv;
	}

	public void setDesIv(String desIv) {
		this.desIv = desIv;
	}

	@Column(name = "is_valid")
	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Column(name = "record_date")
	public Integer getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Integer recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "channel_desc", length = 50)
	public String getChannelDesc() {
		return this.channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}

	@Version
	@Column(name = "version_num")
	public Long getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Long versionNum) {
		this.versionNum = versionNum;
	}

	@Column(name="logo", length=200)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name="t_url",length=200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="t_token",length=200)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name="t_app_id",length=200)
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name="t_secret",length=200)
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Column(name="t_yx_token",length=200)
	public String getYxToken() {
		return yxToken;
	}

	public void setYxToken(String yxToken) {
		this.yxToken = yxToken;
	}

	@Column(name="t_yx_app_id",length=200)
	public String getYxAppId() {
		return yxAppId;
	}

	public void setYxAppId(String yxAppId) {
		this.yxAppId = yxAppId;
	}

	@Column(name="t_yx_sercet",length=200)
	public String getYxSecret() {
		return yxSecret;
	}

	public void setYxSecret(String yxSecret) {
		this.yxSecret = yxSecret;
	}

	@Column(name="t_yx_url",length=200)
	public String getYxUrl() {
		return yxUrl;
	}

	public void setYxUrl(String yxUrl) {
		this.yxUrl = yxUrl;
	}
}