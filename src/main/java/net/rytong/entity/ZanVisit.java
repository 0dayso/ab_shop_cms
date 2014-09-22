package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TSubclaues entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_zan_visit", catalog = "weixin")
public class ZanVisit implements java.io.Serializable {

	// Fields

	private Long id;
	private String ip;
	private String subCode;

	// Constructors

	/** default constructor */
	public ZanVisit() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "t_ip", length = 40)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "t_sub_code", length = 200)
	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
}