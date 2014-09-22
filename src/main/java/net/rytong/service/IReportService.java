package net.rytong.service;

/**
 * 
 * @author wuweihua
 * 
 */
public interface IReportService {

	public String report(Long beginTime, Long endTime, int type,int period)
			throws Exception;

}
