package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.utils.PagingEnumerator;
import net.rytong.dao.ILotteryBuyNumberDAO;
import net.rytong.entity.LotteryBuyNumber;
import net.rytong.service.ILotteryBuyNumberService;
import net.rytong.core.CRUDService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LotteryBuyNumberServiceImpl implements ILotteryBuyNumberService, CRUDService<LotteryBuyNumber> {
	
	@Autowired
	private ILotteryBuyNumberDAO iLotteryBuyNumberDAO;
	
	@Override
	public void delete(LotteryBuyNumber customer) {
		iLotteryBuyNumberDAO.delete(customer);
	}

	@Override
	public LotteryBuyNumber update(LotteryBuyNumber customer) {
		return iLotteryBuyNumberDAO.update(customer);
	}

	@Override
	public void add(LotteryBuyNumber t) {
		iLotteryBuyNumberDAO.save(t);
	}

	@Override
	public List<LotteryBuyNumber> list() {
		return iLotteryBuyNumberDAO.findAll();
	}

	@Override
	public List<LotteryBuyNumber> list(Map<String, Object> filterMap) {
		return iLotteryBuyNumberDAO.pageList(filterMap, 1, 20);
	}

	@Override
	public PagingEnumerator<LotteryBuyNumber> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		return iLotteryBuyNumberDAO.pageList(filterMap, pageNo, pageSize);
	}

	@Override
	public LotteryBuyNumber view(String id) {
		return iLotteryBuyNumberDAO.findById(Long.valueOf(id));
	}
}
