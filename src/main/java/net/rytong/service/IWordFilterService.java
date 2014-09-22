package net.rytong.service;

import java.util.List;
import java.util.Map;

import net.rytong.entity.WordFilter;


public interface IWordFilterService {
	public List<WordFilter> getAllWord(Map<String, Object> filterMap);
}
