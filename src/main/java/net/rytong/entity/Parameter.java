package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Parameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_parameter")
public class Parameter implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String name;
	private String value;
	private Integer effectiveDate;
	private Integer expirationDate;
	private String customer;
	private String remark;
	private Long versionNum;

	// Constructors

	/** default constructor */
	public Parameter() {
	}

	/** minimal constructor */
	public Parameter(String code, String name, String value,
			Integer expirationDate) {
		this.code = code;
		this.name = name;
		this.value = value;
		this.expirationDate = expirationDate;
	}

	/** full constructor */
	public Parameter(String code, String name, String value,
			Integer effectiveDate, Integer expirationDate, String remark,
			Long versionNum) {
		this.code = code;
		this.name = name;
		this.value = value;
		this.effectiveDate = effectiveDate;
		this.expirationDate = expirationDate;
		this.remark = remark;
		this.versionNum = versionNum;
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

	@Column(name = "code", nullable = false, length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "value", nullable = false, length = 100)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "effective_date")
	public Integer getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Integer effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Column(name = "expiration_date", nullable = false)
	public Integer getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Integer expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "remark", length = 150)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "customer", length = 50)
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Column(name = "version_num")
	public Long getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Long versionNum) {
		this.versionNum = versionNum;
	}

}