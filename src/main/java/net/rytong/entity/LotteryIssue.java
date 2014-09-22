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
@Table(name = "t_lottery_issue")
public class LotteryIssue implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String issue;
	private String issueKey;
	private String rHavedFilter;
	private String bHavedFilter;
	private String rWaitedFilter;
	private String bWaitedFilter;
	private String souce;
	private String status;
	private Long time;
	
	// Constructors
	/** default constructor */
	public LotteryIssue() {
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

	@Column(name = "t_issue", length = 20)
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	@Column(name = "t_issue_key", length = 100)
	public String getIssueKey() {
		return issueKey;
	}

	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}

	@Column(name = "t_source", length = 10)
	public String getSouce() {
		return souce;
	}

	public void setSouce(String souce) {
		this.souce = souce;
	}

	@Column(name = "t_status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "t_time")
	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	@Column(name = "t_r_haved_filter", length = 200)
	public String getrHavedFilter() {
		return rHavedFilter;
	}

	public void setrHavedFilter(String rHavedFilter) {
		this.rHavedFilter = rHavedFilter;
	}

	@Column(name = "t_b_haved_filter", length = 200)
	public String getbHavedFilter() {
		return bHavedFilter;
	}

	public void setbHavedFilter(String bHavedFilter) {
		this.bHavedFilter = bHavedFilter;
	}

	@Column(name = "t_r_waited_filter", length = 200)
	public String getrWaitedFilter() {
		return rWaitedFilter;
	}

	public void setrWaitedFilter(String rWaitedFilter) {
		this.rWaitedFilter = rWaitedFilter;
	}

	@Column(name = "t_b_waited_filter", length = 200)
	public String getbWaitedFilter() {
		return bWaitedFilter;
	}

	public void setbWaitedFilter(String bWaitedFilter) {
		this.bWaitedFilter = bWaitedFilter;
	}
}