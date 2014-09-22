package net.rytong.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rytong.core.CRUDService;
import net.rytong.dao.ILotteryIssueDAO;
import net.rytong.entity.Customer;
import net.rytong.entity.LotteryIssue;
import net.rytong.service.ILotteryIssueService;
import net.rytong.utils.PagingEnumerator;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LotteryIssueServiceImpl implements ILotteryIssueService, CRUDService<LotteryIssue> {
	@Autowired
	private ILotteryIssueDAO iLotteryIssueDAO;

	public void delete(LotteryIssue page) {
		iLotteryIssueDAO.delete(page);
	}

	public LotteryIssue findById(Long id) {
		return iLotteryIssueDAO.findById(id);
	}
	
	public void save(LotteryIssue page) {
		iLotteryIssueDAO.save(page);
	}

	public LotteryIssue update(LotteryIssue page) {
		return iLotteryIssueDAO.update(page);
	}

	public List<LotteryIssue> findAll() {
		return iLotteryIssueDAO.findAll(null);
	}

	@Override
	public void add(LotteryIssue t) {
		iLotteryIssueDAO.save(t);
	}

	@Override
	public List<LotteryIssue> list() {
		return iLotteryIssueDAO.findAll();
	}

	@Override
	public List<LotteryIssue> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<LotteryIssue> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iLotteryIssueDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public LotteryIssue view(String id) {
		return iLotteryIssueDAO.findById(Long.valueOf(id));
	}

	@Override
	public List<LotteryIssue> findAvailIssue() {
		return iLotteryIssueDAO.findByProperty("status", "1", null);
	}
	
}
