package net.rytong.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author wuweihua
 *
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	private String ajaxContentType;
	private InputStream inputStream;
	
	public final static String AJAX = "ajax";

	/**
	 * 方法由struts2调用,在配置文件里有设置这个方法
	 * @return ajax回写数据流
	 */
	public InputStream getAjaxInputStream() {
		return this.inputStream;
	}

	/**
	 * 设置ajax回写数据流
	 * @param data 返回客户端的字符串
	 */
	public void setAjaxInputStream(String data) {
		byte[] bytes;
		try {
			bytes = data.getBytes("utf-8");
			this.inputStream = new ByteArrayInputStream(bytes);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
	}

	public String getAjaxContentType() {
		return ajaxContentType;
	}

	public void setAjaxContentType(String ajaxContentType) {
		this.ajaxContentType = ajaxContentType;
	}
}
