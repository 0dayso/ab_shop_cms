package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ICouponDAO;
import net.rytong.entity.Coupon;
import net.rytong.entity.Customer;
import net.rytong.service.ICouponService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponServiceImpl implements ICouponService, CRUDService<Coupon> {
	@Autowired
	private ICouponDAO iCouponDAO;

	/**
	 * 用户注册
	 */
	public void CouponReg(Coupon Coupon) {
		iCouponDAO.save(Coupon);
	}

	/**
	 * 用户修改密码
	 */
	public Coupon CouponEdit(Coupon Coupon) {
		return iCouponDAO.update(Coupon);
	}

	public Coupon getEmpById(Long empId) {
		return iCouponDAO.findById(empId);
	}

	/**
	 * 进行用户的模糊查询
	 */
	public PagingEnumerator<Coupon> CouponMatch(String name, String gender, String mobile,
			String idNo, String email, String phone,String mileageCard,String mobileType,Long startDate,
			Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount) {
		return iCouponDAO.findByProperties(name, gender,null, mobile, idNo, email,
				phone, mileageCard, mobileType,startDate, endDate,customer,fromCompany, pageIndex, pageCount);
	}

	public Coupon getCouponById(Long CouponId) {
		return iCouponDAO.findById(CouponId);
	}
	
	public Coupon updateCoupon(Coupon Coupon){
		return iCouponDAO.update(Coupon);
	}

	@Override
	public List<Coupon> getAllCoupons() {
		return this.iCouponDAO.findAll(null);
	}

	/**
     * 查询不为空的用户
     */
	@Override
	public List<Coupon> findNotNullCoupon(Long beginDate, Long endDate) {
		return this.iCouponDAO.findNotNullCoupon(beginDate, endDate);
	}

	@Override
	public void updateMileageCardCoupon(Coupon Coupon) {
	   iCouponDAO.update(Coupon);
	}

	@Override
	public List<Coupon> findByOptTime(Long optTimeBeginDate, Long optTimeEndDate) {
		return this.iCouponDAO.findByOptTime(optTimeBeginDate, optTimeEndDate);
	}
	
	public List<Coupon> findByPhone(String phone){
		return iCouponDAO.findByPhone(phone);
	}
	
	public List<Coupon> findByBookingTime(Long beginDate,Long endDate){
		return iCouponDAO.findByBookingTime(beginDate, endDate);
	}
	
	public List<Coupon> findByCouponCode(String CouponCode){
		return iCouponDAO.findByCouponCode(CouponCode);
	}

	@Override
	public List<Coupon> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> list(Map<String, Object> filterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingEnumerator<Coupon> pageList(Map<String, Object> filterMap,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coupon view(String id) {
		// TODO Auto-generated method stub
		return iCouponDAO.findById(Long.parseLong(id));
	}

	@Override
	public void add(Coupon t) {
		iCouponDAO.save(t);
	}

	@Override
	public Coupon update(Coupon t) {
		return iCouponDAO.update(t);
	}

	@Override
	public void delete(Coupon t) {
		iCouponDAO.delete(t);
	}
}
