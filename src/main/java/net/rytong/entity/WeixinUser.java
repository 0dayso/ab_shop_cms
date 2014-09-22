package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WeixinUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_weixin_user")
public class WeixinUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String weixinNo;
	private String name;
	private String pin;
	private String userName;
	private String nameEn;
	private String idType;
	private String idNo;
	private String gender;
	private String email;
	private String userClass;
	private String mobile;
	private String phone;
	private String area;
	private String postcode;
	private String address;
	private Long optTime;
	private Long accumulateFraction;
	private String remark;
	private String mileageCard;
	private String mobileType;
	private Integer userGroup;
	private String customer;
	private Long versionNum;
	private Integer isNewRegister;
	private String registerOrigin;
	private Long lastLoginTime;

	// Constructors

	/** default constructor */
	public WeixinUser() {
	}


	/** full constructor */
	public WeixinUser(String weixinNo, String name, String pin,
			String userName, String nameEn, String idType, String idNo,
			String gender, String email, String userClass, String mobile,
			String phone, String area, String postcode, String address,
			Long optTime, Long accumulateFraction, String remark,
			String mileageCard, String mobileType, Integer userGroup,
			Long versionNum, Integer isNewRegister, String registerOrigin,
			Long lastLoginTime) {
		this.weixinNo = weixinNo;
		this.name = name;
		this.pin = pin;
		this.userName = userName;
		this.nameEn = nameEn;
		this.idType = idType;
		this.idNo = idNo;
		this.gender = gender;
		this.email = email;
		this.userClass = userClass;
		this.mobile = mobile;
		this.phone = phone;
		this.area = area;
		this.postcode = postcode;
		this.address = address;
		this.optTime = optTime;
		this.accumulateFraction = accumulateFraction;
		this.remark = remark;
		this.mileageCard = mileageCard;
		this.mobileType = mobileType;
		this.userGroup = userGroup;
		this.versionNum = versionNum;
		this.isNewRegister = isNewRegister;
		this.registerOrigin = registerOrigin;
		this.lastLoginTime = lastLoginTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
//	@Id    
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="wuser_commend")     
//	@SequenceGenerator(name="wuser_commend", allocationSize = 1, initialValue = 1,sequenceName="WUSER_COMMEND")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pin", length = 50)
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Column(name = "user_name", length = 15)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "name_en", length = 30)
	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@Column(name = "id_type", length = 20)
	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	@Column(name = "id_no", length = 25)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "gender", length = 4)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "email", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "user_class", length = 20)
	public String getUserClass() {
		return this.userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	@Column(name = "mobile", length = 15)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "area", length = 20)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "postcode", length = 6)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "opt_time")
	public Long getOptTime() {
		return this.optTime;
	}

	public void setOptTime(Long optTime) {
		this.optTime = optTime;
	}

	@Column(name = "accumulate_fraction")
	public Long getAccumulateFraction() {
		return this.accumulateFraction;
	}

	public void setAccumulateFraction(Long accumulateFraction) {
		this.accumulateFraction = accumulateFraction;
	}

	@Column(name = "remark", length = 150)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "mileage_card", length = 20)
	public String getMileageCard() {
		return this.mileageCard;
	}

	public void setMileageCard(String mileageCard) {
		this.mileageCard = mileageCard;
	}

	@Column(name = "mobile_type", length = 30)
	public String getMobileType() {
		return this.mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	@Column(name = "user_group")
	public Integer getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(Integer userGroup) {
		this.userGroup = userGroup;
	}

	@Column(name = "version_num")
	public Long getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Long versionNum) {
		this.versionNum = versionNum;
	}

	@Column(name = "is_new_register")
	public Integer getIsNewRegister() {
		return this.isNewRegister;
	}

	public void setIsNewRegister(Integer isNewRegister) {
		this.isNewRegister = isNewRegister;
	}

	@Column(name = "register_origin", length = 30)
	public String getRegisterOrigin() {
		return this.registerOrigin;
	}

	public void setRegisterOrigin(String registerOrigin) {
		this.registerOrigin = registerOrigin;
	}

	@Column(name = "last_Login_time")
	public Long getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name="weixin_no",length=30)
	public String getWeixinNo() {
		return weixinNo;
	}

	public void setWeixinNo(String weixinNo) {
		this.weixinNo = weixinNo;
	}

	@Column(name="t_customer",length=20)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}