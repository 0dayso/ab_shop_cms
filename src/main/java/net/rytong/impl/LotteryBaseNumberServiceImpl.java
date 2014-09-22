package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ILotteryBaseNumberDAO;
import net.rytong.entity.LotteryBaseNumber;
import net.rytong.service.ILotteryBaseNumberService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LotteryBaseNumberServiceImpl implements ILotteryBaseNumberService, CRUDService<LotteryBaseNumber> {
	@Autowired
	private ILotteryBaseNumberDAO iLotteryBaseNumberDAO;

	public void delete(LotteryBaseNumber page) {
		iLotteryBaseNumberDAO.delete(page);
	}

	public LotteryBaseNumber findById(Long id) {
		return iLotteryBaseNumberDAO.findById(id);
	}
	
	public void save(LotteryBaseNumber page) {
		iLotteryBaseNumberDAO.save(page);
	}

	public LotteryBaseNumber update(LotteryBaseNumber page) {
		return iLotteryBaseNumberDAO.update(page);
	}

	public List<LotteryBaseNumber> findAll() {
		return iLotteryBaseNumberDAO.findAll(null);
	}

	@Override
	public void add(LotteryBaseNumber t) {
		iLotteryBaseNumberDAO.save(t);
	}

	@Override
	public List<LotteryBaseNumber> list() {
		return iLotteryBaseNumberDAO.findAll();
	}

	@Override
	public List<LotteryBaseNumber> list(Map<String, Object> filterMap) {
		return null;
	}

	@Override
	public PagingEnumerator<LotteryBaseNumber> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iLotteryBaseNumberDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public LotteryBaseNumber view(String id) {
		return iLotteryBaseNumberDAO.findById(Long.valueOf(id));
	}
}
