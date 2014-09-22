package net.rytong.utils;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TMSUtils {
	public static String getMethod(String queryStr,String tranCode){
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		String tail = FlightUtil.getProperties("customer.properties").getProperty("runtime");
		String tms_host = (String) FlightUtil.getProperties("project_service.properties").get("tms_host" + tail);
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(tms_host + "?tranCode="+tranCode+"&" + queryStr);

		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			
			// 处理内容
			 return new String(responseBody,"UTF-8");
		} catch (IOException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return null;
	} 
	
	/**
	 * 解析报文，返回信息内容
	 * @param responseBody
	 * @return
	 */
	public static  String[] parseResponse(String responseBody) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(responseBody);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element rootElement = doc.getRootElement();
		Element elementStatus = rootElement.element("status");
		String value = elementStatus.element("value").getTextTrim();
		String msg = elementStatus.element("msg").getTextTrim();
		return new String[]{value,msg};
	}
}
