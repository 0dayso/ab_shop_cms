package net.rytong.impl;

import java.util.List;
import java.util.Map;

import net.rytong.core.CRUDService;
import net.rytong.dao.ICouponTemplateDAO;
import net.rytong.entity.CouponTemplate;
import net.rytong.service.ICouponTemplateService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponTemplateServiceImpl implements ICouponTemplateService ,CRUDService<CouponTemplate> {
	@Autowired
	private ICouponTemplateDAO iCouponTemplateDAO;

	/**
	 * 用户注册
	 */
	public void CouponTemplateReg(CouponTemplate CouponTemplate) {
		iCouponTemplateDAO.save(CouponTemplate);
	}

	/**
	 * 用户修改密码
	 */
	public CouponTemplate CouponTemplateEdit(CouponTemplate CouponTemplate) {
		return iCouponTemplateDAO.update(CouponTemplate);
	}

	public CouponTemplate getEmpById(Long empId) {
		return iCouponTemplateDAO.findById(empId);
	}

	/**
	 * 进行用户的模糊查询
	 */
	public PagingEnumerator<CouponTemplate> CouponTemplateMatch(String name, String gender, String mobile,
			String idNo, String email, String phone,String mileageCard,String mobileType,Long startDate,
			Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount) {
		return iCouponTemplateDAO.findByProperties(name, gender,null, mobile, idNo, email,
				phone, mileageCard, mobileType,startDate, endDate,customer,fromCompany, pageIndex, pageCount);
	}

	public CouponTemplate getCouponTemplateById(Long CouponTemplateId) {
		return iCouponTemplateDAO.findById(CouponTemplateId);
	}
	
	public CouponTemplate updateCouponTemplate(CouponTemplate CouponTemplate){
		return iCouponTemplateDAO.update(CouponTemplate);
	}

	@Override
	public List<CouponTemplate> getAllCouponTemplates() {
		return this.iCouponTemplateDAO.findAll(null);
	}

	/**
     * 查询不为空的用户
     */
	@Override
	public List<CouponTemplate> findNotNullCouponTemplate(Long beginDate, Long endDate) {
		return this.iCouponTemplateDAO.findNotNullCouponTemplate(beginDate, endDate);
	}

	@Override
	public void updateMileageCardCouponTemplate(CouponTemplate CouponTemplate) {
	   iCouponTemplateDAO.update(CouponTemplate);
	}

	@Override
	public List<CouponTemplate> findByOptTime(Long optTimeBeginDate, Long optTimeEndDate) {
		return this.iCouponTemplateDAO.findByOptTime(optTimeBeginDate, optTimeEndDate);
	}
	
	public List<CouponTemplate> findByPhone(String phone){
		return iCouponTemplateDAO.findByPhone(phone);
	}
	
	public List<CouponTemplate> findByBookingTime(Long beginDate,Long endDate){
		return iCouponTemplateDAO.findByBookingTime(beginDate, endDate);
	}
	
	public List<CouponTemplate> findByCouponTemplateCode(String CouponTemplateCode){
		return iCouponTemplateDAO.findByCouponTemplateCode(CouponTemplateCode);
	}

	@Override
	public List<CouponTemplate> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CouponTemplate> list(Map<String, Object> filterMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingEnumerator<CouponTemplate> pageList(
			Map<String, Object> filterMap, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CouponTemplate view(String id) {
		return iCouponTemplateDAO.findById(Long.parseLong(id));
	}

	@Override
	public void add(CouponTemplate t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CouponTemplate update(CouponTemplate t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CouponTemplate t) {
		// TODO Auto-generated method stub
		
	}
}
