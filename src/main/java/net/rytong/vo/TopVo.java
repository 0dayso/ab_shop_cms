package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Subclaues;
import net.rytong.entity.Top;

public class TopVo {
	private String id;
	private String name;
	private String code;
	private Integer state; 
	private Integer sort;
	private Long createTime;
	private List<SubclauesVo> subclauess = new ArrayList<SubclauesVo>(0);
	
	public TopVo(Top top) {
		id = top.getId().toString();
		code = top.getCode();
		name = top.getName();
		state = top.getState();
		sort = top.getSort();
		createTime = top.getCreateTime();
	}
	
	public static List<TopVo> convert(List<Top> tops) {
		List<TopVo> list = new ArrayList<TopVo>();
		for (Top top: tops) {
			TopVo vo = new TopVo(top);
			list.add(vo);
		}
		return list;
	}
	
	public static void addSubclauses(TopVo topVo, List<SubclauesVo> subs) {
		topVo.setSubclauess(subs);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public List<SubclauesVo> getSubclauess() {
		return subclauess;
	}

	public void setSubclauess(List<SubclauesVo> subclauess) {
		this.subclauess = subclauess;
	}
}