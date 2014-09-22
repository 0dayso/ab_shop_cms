package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ILotteryHistoryNumberDAO;
import net.rytong.entity.LotteryHistoryNumber;
import net.rytong.service.ILotteryHistoryNumberService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LotteryHistoryNumberServiceImpl implements ILotteryHistoryNumberService, CRUDService<LotteryHistoryNumber> {
	@Autowired
	private ILotteryHistoryNumberDAO iLotteryHistoryNumberDAO;

	public void delete(LotteryHistoryNumber page) {
		iLotteryHistoryNumberDAO.delete(page);
	}

	public LotteryHistoryNumber findById(Long id) {
		return iLotteryHistoryNumberDAO.findById(id);
	}
	
	public void save(LotteryHistoryNumber page) {
		iLotteryHistoryNumberDAO.save(page);
	}

	public LotteryHistoryNumber update(LotteryHistoryNumber page) {
		return iLotteryHistoryNumberDAO.update(page);
	}

	public List<LotteryHistoryNumber> findAll() {
		return iLotteryHistoryNumberDAO.findAll(null);
	}

	@Override
	public void add(LotteryHistoryNumber t) {
		iLotteryHistoryNumberDAO.save(t);
	}

	@Override
	public List<LotteryHistoryNumber> list() {
		return iLotteryHistoryNumberDAO.findAll();
	}

	@Override
	public List<LotteryHistoryNumber> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<LotteryHistoryNumber> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iLotteryHistoryNumberDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public LotteryHistoryNumber view(String id) {
		return iLotteryHistoryNumberDAO.findById(Long.valueOf(id));
	}
}
