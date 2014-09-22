package net.rytong.service;

import java.util.List;

import net.rytong.entity.Coupon;


public interface ICouponService {
    /**
     * 用户注册
     * @param Coupon
     */
    public void CouponReg(Coupon Coupon);
    
    /**
     * 用户修改密码
     */
    public Coupon CouponEdit(Coupon Coupon);
    
    /**
     * 通过id得到用户(Coupon对象)
     * @param empId
     * @return
     */
    public Coupon getEmpById(Long empId);
   
    /**
     * 得到所有用户
     */
    public List<Coupon> getAllCoupons();
    
    /**
     * 修改用户信息
     */
    public Coupon updateCoupon(Coupon Coupon);
    
    /**
     * 查询不为空的用户
     */
    public List<Coupon> findNotNullCoupon(Long beginDate, Long endDate);
    public void updateMileageCardCoupon(Coupon Coupon);
   
    /**
	 * 按注册时间查询用户
	 */
	public List<Coupon> findByOptTime(Long optTimeBeginDate,Long optTimeEndDate);
	
	public List<Coupon> findByPhone(String phone);
	
	public List<Coupon> findByBookingTime(Long beginDate,Long endDate);
	
	public List<Coupon> findByCouponCode(String CouponCode);

}
