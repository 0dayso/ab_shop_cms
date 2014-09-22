package net.rytong.service;

import java.util.List;

import net.rytong.entity.LotteryBetNumber;


public interface ILotteryBetNumberService {
	public List<LotteryBetNumber> findByIssue(String issue);
}
