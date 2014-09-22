package net.rytong.dao;

import java.util.List;

import net.rytong.vo.UserVisitCountVO;

/**
 * 
 * @author wuweihua
 *
 */
public interface IReportDAO {

	public List<UserVisitCountVO> userVisitsReport(Long beginTime,Long endTime,int period);
}
