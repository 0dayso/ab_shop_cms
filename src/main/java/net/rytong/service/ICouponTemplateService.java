package net.rytong.service;

import java.util.List;

import net.rytong.entity.CouponTemplate;


public interface ICouponTemplateService {
    /**
     * 用户注册
     * @param CouponTemplate
     */
    public void CouponTemplateReg(CouponTemplate CouponTemplate);
    
    /**
     * 用户修改密码
     */
    public CouponTemplate CouponTemplateEdit(CouponTemplate CouponTemplate);
    
    /**
     * 通过id得到用户(CouponTemplate对象)
     * @param empId
     * @return
     */
    public CouponTemplate getEmpById(Long empId);
   
    /**
     * 得到所有用户
     */
    public List<CouponTemplate> getAllCouponTemplates();
    
    /**
     * 修改用户信息
     */
    public CouponTemplate updateCouponTemplate(CouponTemplate CouponTemplate);
    
    /**
     * 查询不为空的用户
     */
    public List<CouponTemplate> findNotNullCouponTemplate(Long beginDate, Long endDate);
    public void updateMileageCardCouponTemplate(CouponTemplate CouponTemplate);
   
    /**
	 * 按注册时间查询用户
	 */
	public List<CouponTemplate> findByOptTime(Long optTimeBeginDate,Long optTimeEndDate);
	
	public List<CouponTemplate> findByPhone(String phone);
	
	public List<CouponTemplate> findByBookingTime(Long beginDate,Long endDate);
	
	public List<CouponTemplate> findByCouponTemplateCode(String CouponTemplateCode);
   
}
