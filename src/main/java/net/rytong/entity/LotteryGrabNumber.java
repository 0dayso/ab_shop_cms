package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_grab_lottery_number")
public class LotteryGrabNumber implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String grabNumber;
	private String issue;
	private Integer status;
	
	// Constructors
	/** default constructor */
	public LotteryGrabNumber() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "t_issue", length = 10)
	public String getIssue() {
		return issue;
	}
	
	public void setIssue(String issue) {
		this.issue = issue;
	}

	@Column(name = "t_grab_number", length = 100)
	public String getGrabNumber() {
		return grabNumber;
	}

	public void setGrabNumber(String grabNumber) {
		this.grabNumber = grabNumber;
	}

	@Column(name = "t_status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}