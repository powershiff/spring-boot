package com.sf.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.sf.util.SessionUtils;


public class BaseController {
	public final static Logger log = Logger.getLogger(BaseController.class);

	
	

	/**
	 * 获取IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		
		return SessionUtils.getIpAddr(request);
	}

	/**
	 * 服务器端 跳转
	 * 
	 * @param viewName
	 *            视图名称
	 * @param model
	 *            返回 map模型数据
	 * @return
	 */
	public ModelAndView forword(String viewName, Map<String, Object> model) {
		return new ModelAndView(viewName, model);
	}

	/**
	 * 服务器端 跳转
	 * 
	 * @param viewName
	 *            视图名称
	 * @param objects
	 *            返回到页面的数据（n%2==0:模型数据名称，ｎ%2==1:模型数据）
	 * @return
	 */
	public ModelAndView forword(String viewName, Object... objects) {
		return forword(viewName, getMap(objects));
	}
	
	/**
	 * 客户端 跳转
	 * 
	 * @param viewName
	 *            视图名称
	 * @param attr 添加 临时参数数据
	 * @param model
	 *            返回 map模型数据
	 * @return
	 */
	public ModelAndView redirect(String viewName, RedirectAttributes attr, Map<String, Object> model) {
		if (viewName.startsWith("http")) {
			ModelAndView view = new ModelAndView();
			RedirectView redirectView = new RedirectView(viewName);
		    redirectView.setExpandUriTemplateVariables(false);
		    redirectView.setExposeModelAttributes(false); 
		    view.setView(redirectView);
			return view; 
		}
		if(attr != null && model != null && model.size() > 0){
			for(Map.Entry<String, Object> en : model.entrySet()){
				attr.addFlashAttribute(en.getKey(), en.getValue());
			}
		}
		return new ModelAndView("redirect:" + viewName);
	}

	/**
	 * 客户端 跳转
	 * 
	 * @param viewName
	 *            视图名称
	 * @param attr 添加 临时参数数据
	 * @param objects
	 *            返回到页面的数据（n%2==0:模型数据名称，ｎ%2==1:模型数据）
	 * @return
	 */
	public ModelAndView redirect(String viewName, RedirectAttributes attr, Object... objects) {
		return redirect(viewName, attr, getMap(objects));
	}
	
	/**
	 * 客户端 跳转
	 * @param viewName 视图名称
	 * @return
	 * @author wp Sep 8, 2017 10:37:05 AM
	 */
	public ModelAndView redirect(String viewName){
		return redirect(viewName, null);
	}

	/**
	 * 添加数据对象参数到 model中
	 * 
	 * @param model
	 * @param attributeFields
	 * @param attributeValues
	 */
	public void addAttributesToModel(Model model, String attributeFields,
			Object... attributeValues) {
		model.addAllAttributes(getMap4Params(attributeFields, attributeValues));
	}


	/**
	 * 把 bean对象转成字符 以json串返回
	 */
	public void sendAjaxMessage(HttpServletResponse response, Object... obj) {
		sendAjaxMessage(response, getMap(obj));
	}

	

	/**
	 * 设置session域信息
	 */
	public void setSessionMsg(HttpServletRequest request, String name, Object msg) {
		if (name == null || "".equals(name.trim()))
			throw new RuntimeException("设置session域信息,指定的name属性为null或者为空！");
		request.getSession(true).setAttribute(name, msg);
	}

	/**
	 * 获取session域信息
	 */
	public Object getSessionMsg(HttpServletRequest request, String name) {
		if (name == null || "".equals(name.trim()))
			throw new RuntimeException("得到session域信息,指定的name属性为null或者为空！");
		return request.getSession(true).getAttribute(name);
	}

	/**
	 * 移除session域信息
	 */
	public void removeSessionMsg(HttpServletRequest request, String name) {
		if (name == null || "".equals(name.trim()))
			throw new RuntimeException("移除session域信息,指定的name属性为null或者为空！");
		request.getSession(true).removeAttribute(name);
	}

	/**
	 * 封装 可变参数到 map 中
	 * 
	 * @param objects
	 * @return
	 */
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

	/**
	 * 封装参数为 map对象
	 * 
	 * @param paramNames
	 * @param values
	 * @return
	 */
	private static Map<String, Object> getMap4Params(String paramNames, Object... values) {
		String[] params = paramNames.split(",");
		if (params.length != values.length)
			throw new RuntimeException("参数和值的个数不相等。");

		Map<String, Object> pvs = new HashMap<String, Object>();
		for (int i = 0; i < params.length; i++) {
			pvs.put(params[i], values[i]);
		}
		return pvs;
	}
	
	/**
	 * 设置 当前用户信息 到session中
	 * @param user
	 */
//	public void setUser(User user){
//		setSessionMsg(request, USER_SESSION_KEY, user);
//	}
	
	/**
	 * 从session中 获取当前用户信息
	 * @return
	 */

	
	
	/**
	 * 获取项目的根目录
	 * @return
	 */
	public String getRootPath(){
		String path = BaseController.class.getClassLoader().getResource("/").getPath();
		// windows
		if("\\".equals(File.separator)){
			path = path.substring(1, path.indexOf("/WEB-INF/"));
			path = path.replace("/", "\\");
		}
		// linux
		if("/".equals(File.separator)){
			path = path.substring(0, path.indexOf("/WEB-INF/"));
			path = path.replace("\\", "/");
		}
		return path;
	}
	
	/**
	 * 通过 请求对象，获取项目根的web路径。（如：http://test.zber.cn/、http://127.0.0.1:8080/izber_front/）
	 * @auther wuyanxi
	 * @date Jul 11, 2017 5:33:25 PM
	 * @todo TODO
	 * @param request
	 * @return
	 */
	public String getWebPath(HttpServletRequest request){
		String path = request.getContextPath();
		int port = request.getServerPort();
		String baseUri = request.getScheme()+"://"+request.getServerName()+(port == 80 ? "" : ":"+port)+path+"/";
		return baseUri;
	}
	
	/**
	 * 获取 文件内容
	 * @param path
	 * @return
	 */
	public String getFileStr(String path){
		BufferedReader r = null;
		StringBuilder sb = new StringBuilder();
		try {
			r = new BufferedReader(new FileReader(new File(getRootPath()+"/"+path)));
			String line = null;
			while((line = r.readLine()) != null){
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			log.error("获取指定路径的文件失败："+path);
			e.printStackTrace();
		}finally{
			try {
				if(r != null)
					r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return sb.length() > 0 ? sb.toString() : null;
	}
	
	/**
	 * 获取 提示页面的 消息
	 * @param isSuccess 是否成功。true:成功、false:失败
	 * @param title 提示标题
	 * @param descr 提示描述
	 * @param isSkip 页面是否跳转。true:跳转、false:不跳转
	 * @param time 页面停留时间
	 * @param targetName 目标页面名称
	 * @param targetUrl 目标页面url
	 * @return
	 */
	public Map<String, Object> getTipMsg(boolean isSuccess, String title, String descr, boolean isSkip, int time, String targetName, String targetUrl){
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("flag", isSuccess);// 表示 成功、失败
		msg.put("title", title);// 提示标题
		msg.put("descr", descr == null || descr.trim().length() == 0 ? null : descr);// 提示描述
		msg.put("skip", isSkip);// 页面 是否跳转
		msg.put("time", time == 0 ? 5 : time); // 页面停留时间，然后跳转
		msg.put("targetName", targetName);// 跳转到 页面名称
		msg.put("targetUrl", targetUrl);// 跳转到 页面url
		return msg;
	}
	
	
}
