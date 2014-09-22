package net.rytong.action;

import java.util.List;

import net.rytong.core.CRUDActionSupport;
import net.rytong.core.CRUDService;
import net.rytong.entity.CouponTemplate;
import net.rytong.impl.CouponTemplateServiceImpl;
import net.rytong.service.IParameterService;
import net.rytong.utils.RandomUtils;
import net.rytong.vo.CouponTemplateVo;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class CouponTemplateAction extends CRUDActionSupport<CouponTemplate> {
	private static final long serialVersionUID = 5207197603748720772L;
	@Autowired
	private CouponTemplateServiceImpl CouponTemplateServiceImpl;
	@Autowired  
	private IParameterService iParameterService;
	@Override
	public String execute() throws Exception{
		return SUCCESS;
	}
	
	@Override
	public CRUDService<CouponTemplate> getAutowiredService() {
		return CouponTemplateServiceImpl;
	}
	
	public String selectAllCT() throws Exception{
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		List<CouponTemplate> list = CouponTemplateServiceImpl.getAllCouponTemplates();
		List<CouponTemplateVo> listVo = CouponTemplateVo.convert(list);
		JSONObject obj = new JSONObject();
		obj.put("listVo", listVo);
		obj.put("ip", ip);
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
	public String loadCouponTemplate() throws Exception {
		String ip = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IPADDRESS", "0").get(0).getValue();
		CouponTemplate ct =  this.getEntity();
		String code = RandomUtils.random(4, 3, " ");
		JSONObject obj = new JSONObject();
		obj.put("ip", ip);
		obj.put("code", code);
		obj.put("vo",  new JSONObject(CouponTemplateVo.convert(ct)));
		this.setAjaxInputStream(obj.toString());
		return AJAX;
	}
	
}
