package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ILotteryBetNumberDAO;
import net.rytong.entity.LotteryBetNumber;
import net.rytong.service.ILotteryBetNumberService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LotteryBetNumberServiceImpl implements ILotteryBetNumberService, CRUDService<LotteryBetNumber> {
	@Autowired
	private ILotteryBetNumberDAO iLotteryBetNumberDAO;

	public void delete(LotteryBetNumber page) {
		iLotteryBetNumberDAO.delete(page);
	}

	public LotteryBetNumber findById(Long id) {
		return iLotteryBetNumberDAO.findById(id);
	}
	
	public void save(LotteryBetNumber page) {
		iLotteryBetNumberDAO.save(page);
	}

	public LotteryBetNumber update(LotteryBetNumber page) {
		return iLotteryBetNumberDAO.update(page);
	}

	public List<LotteryBetNumber> findAll() {
		return iLotteryBetNumberDAO.findAll(null);
	}

	@Override
	public void add(LotteryBetNumber t) {
		iLotteryBetNumberDAO.save(t);
	}

	@Override
	public List<LotteryBetNumber> list() {
		return iLotteryBetNumberDAO.findAll();
	}

	@Override
	public List<LotteryBetNumber> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<LotteryBetNumber> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iLotteryBetNumberDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public LotteryBetNumber view(String id) {
		return iLotteryBetNumberDAO.findById(Long.valueOf(id));
	}

	@Override
	public List<LotteryBetNumber> findByIssue(String issue) {
		return iLotteryBetNumberDAO.findByIssue(issue);
	}
}
