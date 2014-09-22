package net.rytong.service;

import net.rytong.entity.WeixinUser;
import net.rytong.utils.PagingEnumerator;


public interface IWeixinUserService {
	
	/**
	 * 保存用户信息
	 * @param weixinUser
	 */
	public void save(WeixinUser weixinUser) ;
	
	/**
	 * 删除用户信息
	 * @param weixinUser
	 */
	public void delete(WeixinUser weixinUser);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public WeixinUser getWeixinUserById(Long id);
	
	/**
	 * update
	 * @param weixinUser
	 * @return
	 */
	public WeixinUser update(WeixinUser weixinUser);
	
	/**
	 * 根据条件查询用户
	 * @param name
	 * @param idNo
	 * @param mobile
	 * @param startIndex
	 * @param maxCount
	 * @return
	 */
	public PagingEnumerator<WeixinUser> weixinUserMatch(String name,String idNo,String mobile,String email,int startIndex,int maxCount);
}
