package net.rytong.vo;

import java.util.ArrayList;
import java.util.List;

import net.rytong.entity.Visits;
import net.rytong.utils.TimeHelper;

public class VisitsVo {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String visitTime;
	private String inputInfo;
	private String nickName;
	private String infoType;
	
	public VisitsVo(Visits visit) {
		id = visit.getId();
		userName = visit.getUserName();
		visitTime = TimeHelper.dateTime2Str(Long.valueOf(visit.getVisitTime())) ;
		infoType = visit.getInfoType();
		if ("1".equals(infoType)) {
			inputInfo = visit.getInputInfo();
		} else {
			inputInfo = visit.getInputInfo();
		}
		nickName = visit.getNickName();
		
	}
	
	public static List<VisitsVo> convert(List<Visits> visits) {
		List<VisitsVo> list = new ArrayList<VisitsVo>();
		for (Visits visit : visits) {
			VisitsVo vo = new VisitsVo(visit);
			list.add(vo);
		}
		return list;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getInputInfo() {
		return inputInfo;
	}
	public void setInputInfo(String inputInfo) {
		this.inputInfo = inputInfo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
}