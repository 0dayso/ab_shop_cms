package net.rytong.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FunctionRight entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_function_right")
public class FunctionRight implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9033357156381390301L;
	private Long functionRightId;
	private FunctionRight functionRight;
	private String code;
	private String name;
	private String path;
	private String description;
	private Integer level;
	private Integer isValid;
	private Integer sort;
	private Integer isNew;
	private Long versionNum;
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<FunctionRight> functionRights = new HashSet<FunctionRight>(0);

	// Constructors

	/** default constructor */
	public FunctionRight() {
	}

	/** minimal constructor */
	public FunctionRight(String name) {
		this.name = name;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "function_right_id", unique = true, nullable = false)
	public Long getFunctionRightId() {
		return this.functionRightId;
	}

	public void setFunctionRightId(Long functionRightId) {
		this.functionRightId = functionRightId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_parent_id")
	public FunctionRight getFunctionRight() {
		return this.functionRight;
	}

	public void setFunctionRight(FunctionRight functionRight) {
		this.functionRight = functionRight;
	}

	@Column(name = "f_code", length = 8)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "f_name", nullable = false, length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "f_path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "f_description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "f_level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "f_is_valid")
	public Integer getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Column(name = "f_is_new")
	public Integer getIsNew() {
		return isNew;
	}

	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}

	@Column(name = "f_sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "f_version_num")
	public Long getVersionNum() {
		return this.versionNum;
	}

	public void setVersionNum(Long versionNum) {
		this.versionNum = versionNum;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "functionRights")
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "functionRight")
	public Set<FunctionRight> getFunctionRights() {
		return this.functionRights;
	}

	public void setFunctionRights(Set<FunctionRight> functionRights) {
		this.functionRights = functionRights;
	}

}
