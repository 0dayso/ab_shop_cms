package net.rytong.utils;


import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;

/**
 * 通过HTTP协议访问主机
 * 提供了get和post方式访问http主机
 * 主机:http://wap.airchina.com
 * @author YangLiuchun
 */
public class AccessToHttp {

    /**
     * 获取请求主机
     * @param uri 请求主机uri
     * @return httpClient
     * @exception URIException 无法找到主机异常
     */
    public static HttpClient getHttpClient(URI uri) throws URIException {
        HttpClient client = new HttpClient();
        HttpHost host = new HttpHost(uri);
        client.getHostConfiguration().setHost(host);
        return client;
    }
    
    /**
     * 获取请求主机
     * @param port 端口号
     * @param host 主机
     * @return httpClient
     * @exception URIException 无法找到主机异常
     */
    public static HttpClient getHttpClient1(String host,int port) throws URIException {
        HttpClient client = new HttpClient();
        client.getHostConfiguration().setHost(host, port);
        return client;
    }

    /**
     * 使用GET方式提交数据
     * @param url get请求地址
     * @return Get
     */
    public static HttpMethod getGetMethod(String url) {
        GetMethod get = new GetMethod(url);
        get.addRequestHeader("Connection", "close");
        return get;
    }

    /**
     * 带语言支持的Get
     * @param url get请求地址
     * @param contentType Content-type 格式：text/xml; charset=GBK 这里可能用text/vnd.wap.wml
     * @return Get
     */
    public static HttpMethod getGetMethod(String url, String contentType) {
        GetMethod get = new GetMethod(url);
        get.addRequestHeader("Connection", "close");
        get.setRequestHeader("Content-type", contentType);
        get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, contentType);
        return get;
    }

    /**
     * 某种编码的Get请求
     * @param url 请求uri
     * @param encoding 编码：gb2312
     * @return Get
     */
    public static HttpMethod getGetEncodingMethod(String url, String encoding) {
        GetMethod get = new GetMethod(url);
        get.addRequestHeader("Connection", "close");
        get.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        return get;
    }

    /**
     * 使用POST方式提交数据
     * @param url post请求uri
     * @param vars 参数
     * 格式 new NameValuePair("ffpNO", "374947075")||post.setRequestBody(new NameValuePair[]{xxx});
     * @return Post
     */
    public static HttpMethod getPostMethod(String url, NameValuePair[] vars) {//一个参数数组String[]，一个是否按提供编码boolean
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Connection", "close");
        post.setRequestBody(vars);
        return post;
    }

    /**
     * 带语言支持的Post
     * @param url post请求uri
     * @param vars 参数
     * @param contentTyep 格式：text/xml; charset=GBK
     * @return Post
     */
    public static HttpMethod getPostMethod(String url, NameValuePair[] vars, String contentTyep) {
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Connection", "close");
        post.setRequestHeader("Content-type", contentTyep);
        post.setRequestBody(vars);
        return post;
    }

    /**
     * 某种编码的Post请求
     * @param url post请求uri
     * @param vars 参数
     * @param eoncing 编码：UTF-8
     * @return Post
     */
    public static HttpMethod getPostEncodingMethod(String url, NameValuePair[] vars, String encoding) {
        PostMethod post = new PostMethod(url);
        post.addRequestHeader("Connection", "close");
        post.setRequestBody(vars);
        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
//        post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        return post;
    }
    
    
    /**
     * 请求南航接口
     * @param url 接口地址
     * @param xmlDom body内容
     * @return
     * @throws Exception
     */
	public static String requestXSQL(String url, String xmlDom)
			throws Exception {
		HttpClient httpclient = null;
		String result = null;
		httpclient = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		httpPost.addRequestHeader("Connection", "close");
		if (xmlDom == null) {
			try {
				httpclient.executeMethod(httpPost);
				result = new String(httpPost.getResponseBody(), "utf-8");
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {
			RequestEntity entity = new StringRequestEntity(xmlDom, "text/xml","utf-8");
			httpPost.setRequestEntity(entity);
			httpclient.executeMethod(httpPost);
			result = new String(httpPost.getResponseBody(), "utf-8");
		}
		return result;
	}

	 /**
     * 根据url返回结果
     * @param url
     * @return
     */
    public static String requestUrl(String url, String charsetName) {
		HttpClient httpClient = new HttpClient();
		GetMethod method = new GetMethod(url);
		method.addRequestHeader("Connection", "close");
		method.addRequestHeader("Content-type" , "text/html; charset=utf-8"); 
		// 使用系统提供的默认的恢复策略
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] responseBody = method.getResponseBody();
			
			return new String(responseBody, charsetName);
			
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} catch (IOException e) {
			// 发生网络异常
			e.printStackTrace();
		} finally {
			// 释放连接
			method.releaseConnection();
		}
		
		return null;
	}
    
    
    
}
