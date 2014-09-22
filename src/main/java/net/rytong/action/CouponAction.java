package net.rytong.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.Coupon;
import net.rytong.entity.Employee;
import net.rytong.entity.WeixinKey;
import net.rytong.impl.CouponServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.vo.CouponVo;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class CouponAction extends CRUDActionSupport<Coupon> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private CouponServiceImpl couponServiceImpl;
	
	@Autowired
	private IParameterService iParameterService;
	
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}

	@Override
	public CRUDService<Coupon> getAutowiredService() {
		return couponServiceImpl;
	}
	
	//查询所有
	public String selectAll() throws Exception{
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		List<Coupon> list = couponServiceImpl.getAllCoupons();
		List<CouponVo> listVo = CouponVo.convert(list);
		JSONObject obj = new JSONObject();
		obj.put("coupons", listVo);
		obj.put("ip", ip);
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	//add
	public String addCoupon() throws Exception {
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		HttpServletRequest request = ServletActionContext.getRequest();
		Employee employee = (Employee)request.getSession().getAttribute("employee");
		String key = request.getParameter("keys");
		WeixinKey weixinKey = new WeixinKey();
		weixinKey.setName(key);
		weixinKey.setCustomer(employee.getCustomerName());
		weixinKey.setType("text");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String bgImage = request.getParameter("bgImage");
		String hdImage = request.getParameter("hdImage");
		String title = request.getParameter("title");
		String banner = request.getParameter("banner"); 
		String code = request.getParameter("code");
		String explain = request.getParameter("explain");
		String url = ip + employee.getCustomerName() + "/" + banner;
		Coupon coupon = new Coupon();
		coupon.setBanner(banner);
		coupon.setBgImage(bgImage);
		coupon.setHdImage(hdImage);
		coupon.setCode(code);
		coupon.setEndTime(Long.parseLong(endTime));
		coupon.setExplain(explain);
		coupon.setStartTime(Long.parseLong(startTime));
		coupon.setUrl(url);
		coupon.setTitle(title);
		coupon.setKey(weixinKey);
		couponServiceImpl.add(coupon);
		return NONE;
	}
	
	
	public String getCoupon() throws Exception  {
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		Coupon ct =  this.getEntity();
	    WeixinKey weixinKey = ct.getKey();
		JSONObject obj = new JSONObject();
		obj.put("ip", ip);
		obj.put("kname",weixinKey.getName());
		obj.put("vo",  new JSONObject(CouponVo.convert(ct)));
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String delCoupon() throws Exception  {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Coupon ct =  this.getEntity();
		couponServiceImpl.delete(ct);
		List<Coupon> list = couponServiceImpl.getAllCoupons();
		JSONObject obj = new JSONObject();
		obj.put("id", id);
		obj.put("count", list.size());
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String updateCoupon() throws Exception  {
		Coupon ct =  this.getEntity();
		HttpServletRequest request = ServletActionContext.getRequest();
		String kname = request.getParameter("keys");
		WeixinKey weixinKey = ct.getKey();
		weixinKey.setName(kname);
		couponServiceImpl.update(ct);
		return NONE;
	}
}
