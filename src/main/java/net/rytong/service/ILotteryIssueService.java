package net.rytong.service;

import java.util.List;

import net.rytong.entity.LotteryIssue;


public interface ILotteryIssueService {
	public List<LotteryIssue> findAvailIssue();
}
