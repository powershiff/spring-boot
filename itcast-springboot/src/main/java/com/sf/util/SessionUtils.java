package com.sf.util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@SuppressWarnings("all")
public final class SessionUtils {
	protected static final Logger log = Logger.getLogger(SessionUtils.class);
	private static final String SESSION_USER = "session_user";
	private static final String SESSION_VALIDATECODE = "session_validatecode";
	private static final String SESSION_ACCESS_URLS = "session_access_urls"; 
	private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; 
	
	
	/**
	 * 判断此请求是否是 异步请求（ajax）
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");  
		return "XMLHttpRequest".equalsIgnoreCase(header);
	}
	
	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static HttpSession getSession(HttpServletRequest request){
		return request.getSession(true);
	}
	
	public static void setAttr(HttpServletRequest request, String key,
			Object value) {
		request.getSession(true).setAttribute(key, value);
	}
	
	public static Object getAttr(HttpServletRequest request, String key) {
		return request.getSession(true).getAttribute(key);
	}
	
	public static void removeAttr(HttpServletRequest request, String key) {
		request.getSession(true).removeAttribute(key);
	}
	
	
	public static void removeUser(HttpServletRequest request) {
		removeAttr(request, SESSION_USER);
	}
	
	public static void setValidateCode(HttpServletRequest request,
			String validateCode) {
		setAttr(request, SESSION_VALIDATECODE, validateCode);
	}
	
	public static String getValidateCode(HttpServletRequest request) {
		return (String) getAttr(request, SESSION_VALIDATECODE);
	}
	
	public static void removeValidateCode(HttpServletRequest request) {
		removeAttr(request, SESSION_VALIDATECODE);
	}
	
	
	
	public static void setAccessUrl(HttpServletRequest request,
			List<String> accessUrls) {
		setAttr(request, SESSION_ACCESS_URLS, accessUrls);
	}
	
	public static boolean isAccessUrl(HttpServletRequest request, String url) {
		List<String> accessUrls = (List<String>) getAttr(request, SESSION_ACCESS_URLS);
		if (accessUrls == null || accessUrls.isEmpty() || !accessUrls.contains(url)) {
			return false;
		}
		return true;
	}
	
	public static void setMemuBtnMap(HttpServletRequest request,
			Map<String, List<String>> btnMap) {
		setAttr(request, SESSION_MENUBTN_MAP, btnMap);
	}
	
	public static List<String> getMemuBtnListVal(HttpServletRequest request,
			String menuUri) {
		Map<String, List<String>> btnMap = (Map<String, List<String>>) getAttr(request, SESSION_MENUBTN_MAP);
		if (btnMap == null || btnMap.isEmpty()) {
			return null;
		}
		return btnMap.get(menuUri);
	}
	
	/**
	 * 
	 * 提示成功信息
	 * 
	 * @param msg
	 * 
	 */
	public static Map<String, Object> getSuccessMsg(String msg, Object... objects) {
		return getSuccessMsg(msg, getMap(objects));
	}
	/**
	 * 
	 * 提示成功信息
	 * 
	 * @param msg
	 * 
	 */
	public static Map<String, Object> getSuccessMsg(String msg, Map<String, Object> object) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("msg", msg);
		result.putAll(object);
		return result;
	}
	/**
	 * 
	 * 提示失败信息
	 * 
	 * @param msg
	 * 
	 */
	public static Map<String, Object> getFailureMsg(String msg, Object... objects) {
		return getFailureMsg(msg, getMap(objects));
	}
	/**
	 * 
	 * 提示失败信息
	 * 
	 * @param msg
	 * 
	 */
	public static Map<String, Object> getFailureMsg(String msg, Map<String, Object> object) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("msg", msg);
		result.putAll(object);
		return result;
	}
	public static Map<String, Object> getMap(Object... objects) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (objects != null) {
			if (objects.length % 2 == 0) {
				for (int i = 0; i < objects.length; i += 2)
					result.put(objects[i].toString(), objects[i + 1]);
			} else {
				throw new RuntimeException("传递的参数个数不正确！");
			}
		}
		return result;
	}
}
