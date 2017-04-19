package com.paouke.gdp.common.utils;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.util.Map;

/**
 * 
 *  @Project       : clic
 *  @Program Name  : com.creditease.common.util.HttpClientUtil.java
 *  @Class Name    : HttpClientUtil
 *  @Copyright	   : Copyright (c)2006-2015   
 *  @Company	   : CreditEase
 *  @Description   : httpClient工具类
 *  @Author        : 周亮
 *  @Creation Date : 2015年6月30日 上午11:20:44 
 *  @ModificationHistory  
 *  Date			Author      	Version    	Description  
 *  ------------------------------------------------------------------  
 *  2015年6月30日		           周亮			1.0			1.0 Version
 */
public class HttpClientUtil {

	private static MultiThreadedHttpConnectionManager manager=new MultiThreadedHttpConnectionManager();
	private static HttpClient httpClient;
	static{
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(120000);//链接超时
		params.setSoTimeout(120000);//读取超时
		// 最大连接数
		params.setMaxTotalConnections(500);
		params.setDefaultMaxConnectionsPerHost(500);
		params.setStaleCheckingEnabled(true);
		manager.setParams(params);
		HttpClientParams httpClientParams = new HttpClientParams();
		// 设置httpClient的连接超时，对连接管理器设置的连接超时是无用的
		httpClientParams.setConnectionManagerTimeout(5000); //等价于4.2.3中的CONN_MANAGER_TIMEOUT
		httpClientParams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		httpClient=new HttpClient(manager);
		httpClient.setParams(httpClientParams);
	}
	/**
	 * 
	 *  @Description    :用httpClient  post提交数据 
	 *  @Method_Name    : httpClientPost
	 *  @param url		：传入url地址
	 *  @param params	：传入参数
	 *  @return 
	 * @throws Throwable 
	 *  @Creation Date  : 2015年6月30日 上午11:20:20 
	 *  @Author         : 周亮
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2015年6月30日			周亮				1.0			创建
	 */
	public static String httpClientPost(String url, String params) throws Exception {
		return httpClientPost(url, params, null);
	}
	
	/**
	 * 
	 *  @Description    : 带sessionId httpClient  post提交数据 (conType 默认为"text/xml")
	 *  @Method_Name    : httpClientPost
	 *  @param url
	 *  @param params
	 *  @param jsessionId
	 *  @return
	 *  @throws Exception 
	 *  @Creation Date  : 2015年12月14日 上午10:02:03 
	 *  @Author         : 周亮
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2015年12月14日			周亮				1.0			创建
	 */
	public static String httpClientPost(String url, String params,String jsessionId) throws Exception {
		return httpClientPost(url, params, jsessionId,"text/xml");
	}
	/**
	 * 
	 *  @Description    : 带conType httpClient  post提交数据 
	 *  @Method_Name    : httpClientPost
	 *  @param url
	 *  @param params
	 *  @param jsessionId
	 *  @param conType
	 *  @return
	 *  @throws Exception 
	 *  @Creation Date  : 2016年5月31日 下午4:55:31 
	 *  @Author         : 李强
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016年5月31日			李强				1.0			创建
	 */
	public static String httpClientPost(String url, String params, String jsessionId, String conType) throws Exception {
		LogUtil.out("【HttpClientUtil】url:"+url+", params:"+params+",jsessionId:"+jsessionId+",conType:"+conType);
		PostMethod method = new PostMethod(url);
		if (jsessionId != null && !"".equals(jsessionId)) {
			Header header = new Header();
			header.setName("Cookie");
			header.setValue("JSESSIONID=" + jsessionId);
			method.setRequestHeader(header);
		}
		// 获取contentType并设定默认值
		String contentType = conType;
		if (contentType == null) {
			contentType = "text/xml";
		}
		String result = "";
		try {
			if (params != null && !"".equals(params.trim())) {
				RequestEntity requestEntity = new StringRequestEntity(params, contentType, "UTF-8");
				method.setRequestEntity(requestEntity);
			}
			method.releaseConnection();
			httpClient.executeMethod(method);
			//logger.info("【HttpClientUtil】http调用状态编码："+method.getStatusCode());
			LogUtil.out("【HttpClientUtil】http调用状态编码："+method.getStatusCode());
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				String responses = method.getResponseBodyAsString();
				return responses;
			} else {
				//logger.error("调用接口失败，HTTP返回代码：" + method.getStatusCode());
				result = "-1";
			}
		} finally {
			method.releaseConnection();
		}
		return result;
	}

	/**
	 * @param url
	 * @param params
	 * @param jsessionId
	 * @return
	 * @throws Exception
	 * @Description : 带sessionId httpClient  post提交数据
	 * @Method_Name : httpClientPost
	 * @Creation Date  : 2015年12月14日 上午10:02:03
	 * @Author : 周亮
	 * @ModificationHistory Date            Author      	Version    	Description
	 * ------------------------------------------------------------------
	 * 2015年12月14日			周亮				1.0			创建
	 */
	public static String httpClientPost(String url, Map<String, String> params, String jsessionId) throws Exception {
		String result = "";
		PostMethod method = new PostMethod(url);
		if (jsessionId != null && !"".equals(jsessionId)) {
			Header header = new Header();
			header.setName("Cookie");
			header.setValue("JSESSIONID=" + jsessionId);
			method.setRequestHeader(header);
		}
		try {
			// 设定请求参数
			if (params != null && params.size() != 0) {
				for (String key : params.keySet()) {
					method.addParameter(key, params.get(key));
				}
			}
			httpClient.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				result = method.getResponseBodyAsString();
			} else {
				//logger.error("调用接口失败，HTTP返回代码：" + method.getStatusCode());
				result = "-1";
			}
		} finally {
			method.releaseConnection();
		}
		return result;
	}

	/**
	 * 
	 *  @Description    : 用httpClient  get提交数据 
	 *  @Method_Name    : httpClientGet
	 *  @param url
	 *  @return
	 *  @throws Throwable 
	 *  @Creation Date  : 2015年6月30日 下午3:56:26 
	 *  @Author         : 周亮
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2015年6月30日			周亮				1.0			创建
	 */
	public static String httpClientGet(String url) throws Exception{
		return httpClientGet(url, null);
	}
	
	/**
	 * 
	 *  @Description    : 带sessionID的httpClient get提交数据
	 *  @Method_Name    : httpClientGet
	 *  @param url
	 *  @param jsessionId
	 *  @return
	 *  @throws Exception 
	 *  @Creation Date  : 2015年12月14日 上午9:59:25 
	 *  @Author         : 周亮
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2015年12月14日			周亮				1.0			创建
	 */
	public static String httpClientGet(String url,String jsessionId) throws Exception{
		GetMethod method = new GetMethod(url);
		if(jsessionId!=null&&!"".equals(jsessionId)){
			 Header header = new Header();
			 header.setName("Cookie");
			 header.setValue("JSESSIONID="+jsessionId);
			 method.setRequestHeader(header);
		}
		try {
			httpClient.executeMethod(method);
			LogUtil.out("method.getStatusCode()"+method.getStatusCode());
				if (method.getStatusCode() == HttpStatus.SC_OK) {
					return method.getResponseBodyAsString();
				}else{
					//logger.error("调用接口失败，HTTP返回代码："+method.getStatusCode());
					return "-1";
				}
		}finally {
			method.releaseConnection();
		}
		
	}
	/***
	 * 判断是否是json字符串
	 *  @Description    : 
	 *  @Method_Name    : getIsJson
	 *  @param value
	 *  @return 
	 *  @Creation Date  : 2016-5-17 下午2:28:20 
	 *  @Author         : 杨运锋
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016-5-17			杨运锋				1.0			创建
	 */
	public static boolean getIsJson(String value) {
		if(value.contains("{")&&value.contains("}")){
			return true;
		}else{
			return false;
		}
	}
	
	/***
	 *  推送消息方法
	 *  @Description    : 
	 *  @Method_Name    : pushReslut
	 *  @param url
	 *  @param json 
	 *  @Creation Date  : 2016-5-16 下午4:40:41 
	 *  @Author         : 杨运锋
	 *  @ModificationHistory  
	 *  Date			Author      	Version    	Description  
	 *  ------------------------------------------------------------------  
	 *  2016-5-16			杨运锋				1.0			创建
	 *  String json = "{\"systemID\":\"clic\",\"from\":\"<发起申请者的系统ID>\",\"type\":\"0\",\"msg\":{\"context\":{\"application_title\":\"张三丰申请减免100块\"},\"event_id\":\"<事件id>\",\"params\":{\"approve_node\":\"0002\",\"approver\":\"2011xxxxxxxxxx\",\"application_id\":\"191991\"}}}";
	 */
	public static void pushResult(String url, String json) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
        // 将JSON进行UTF-8编码,以便传输中文
		String types="";
		if(getIsJson(json)){
			types=StringUtils.HTTP_TYPES;
		}else{
			types=StringUtils.HTTP_TYPES_TEXT;
		}
		
		try {
			RequestEntity requestEntity;
			try {
				requestEntity = new StringRequestEntity(json,types, StringUtils.HTTP_CODES);
				method.setRequestEntity(requestEntity);
				method.releaseConnection();
				int result = client.executeMethod(method);
				//logger.info("推送接口,HTTP返回代码："+result);
				LogUtil.out("返回状态status:" + result);
//				responses = method.getResponseBodyAsString();
			} catch (Exception e) {
				LogUtil.out(e.getMessage());
			}
		} finally {
			method.releaseConnection();
		}
		
	}
}

