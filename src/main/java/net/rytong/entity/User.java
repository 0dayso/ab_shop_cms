package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "t_user",uniqueConstraints = @UniqueConstraint(columnNames = "customer_id"))
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Customer customer;
	private String userCode;
	private String userName;
	private String name;
	private String nameEn;
	private String idType;
	private String idNo;
	private String pin;
	private String gender;
	private String email;
	private String userClass;
	private String mobile;
	private String mobileType;
	private String phone;
	private String area;
	private String postcode;
	private String address;
	private Long optTime;
	private Long accumulateFraction;
	private String remark;
	private Long versionNum;
	private Integer isNewRegister;
	private Integer userGroup;
	private String mileageCard;
	private String registerOrigin;
	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Customer customer, String userCode) {
		this.customer = customer;
		this.userCode = userCode;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", unique = true, nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "user_code", nullable = false, length = 50)
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "name", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Column(name = "user_group")
	public Integer getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(Integer userGroup) {
		this.userGroup = userGroup;
	}

	@Column(name = "mileage_card", length = 20)
	public String getMileageCard() {
		return this.mileageCard;
	}

	public void setMileageCard(String mileageCard) {
		this.mileageCard = mileageCard;
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
	
	@Column(name = "mobile_type", length = 30)
	public String getMobileType() {
		return this.mobileType;
	}

	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}

	@Column(name = "pin", length = 50)
	public String getPin() {
		return this.pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
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
		return isNewRegister;
	}

	public void setIsNewRegister(Integer isNewRegister) {
		this.isNewRegister = isNewRegister;
	}
	
	@Column(name = "register_origin")
	public String getRegisterOrigin() {
		return registerOrigin;
	}

	public void setRegisterOrigin(String registerOrigin) {
		this.registerOrigin = registerOrigin;
	}

	@Column(name = "user_name", length = 15)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}