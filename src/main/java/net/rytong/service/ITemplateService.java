/**
 * 
 */
package net.rytong.service;


import net.rytong.entity.Template;

/**
 * @author ZhangGuohua
 *
 */
public interface ITemplateService {
	
	public Template findTemplateByCode(String code);
	
}
