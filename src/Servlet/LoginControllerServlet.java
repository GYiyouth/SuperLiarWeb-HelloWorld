package Servlet;

import business_LogSign.*;
import proxy.ProxyHandler;
import smallTools.TimeImpl;
import smallTools.XmlDocument;
import smallTools.XmlDocumentImpl;
import smallTools.XmlReader;
import xmlHandleBean.ActionBean;
import xmlHandleBean.ActionResultBean;
import xmlHandleBean.ControllerBean;
import xmlHandleBean.InterceptorBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by geyao on 2016/12/2.
 */
public class LoginControllerServlet extends HttpServlet {

	private static ControllerBean controllerBean = XmlReader.readXml();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		//对配置文件进行解析
		XmlDocument xmlDocument = new XmlDocumentImpl();
//		ControllerBean controllerBean = xmlDocument.getControllerBean();
		System.out.println(controllerBean);
		//获取servelt名称，并匹配
		String actionName = getActionName(request.getServletPath());
		ActionBean actionBean = controllerBean.checkAction(actionName);
		if (actionBean != null){
			//匹配到了action
			Map<String, String[]> map = request.getParameterMap();


			try {

				/**
				 * 控制器检查该 action 是否 配置了 LogWriter 拦截器
				 * 如果有配置，在 Action 执行之前，使用 LogWriter 记录 Action 的
				 * 名称、类型、访问开始时间，
				 * 并在 Action 执 行之后记录 Action 访问结束时间，及返回的结果 result
				 */
				//构造代理


//              以下是E2的内容，通过反射机制调用action，现在我用代理了
				System.out.println("actionBean:" + actionBean.getClassPath());
				Class realClass = Class.forName(actionBean.getClassPath());
				Class interClass = realClass.getInterfaces()[0];
				Method method = interClass.getMethod(actionBean.getMethod(), Map.class);
				ProxyHandler actionProxy = new ProxyHandler();
				Object obj =  actionProxy.bind(realClass.newInstance());
				HashMap hashMap = new HashMap();
				hashMap.put("realKey", map);
				hashMap.put("actionBean", actionBean);
				hashMap.put("request", request);
				hashMap.put("session", request.getSession());

//				hashMap.put("interceptor", )

				String resultName = method.invoke(obj, hashMap).toString();
//				Map[] maps = new Map[1];
//				maps[0] = map;
//				actionProxy.invoke(actionProxy, method, maps);

				//逻辑层方法返回值
//				String resultName = method.invoke(servlet.newInstance(), map).toString();
				//如果results包含上述返回值

				/**
				 * 这里做的不好，只能用于forward和redirect两种方法
				 */
				if (actionBean.getResults().containsKey(resultName)){
					//result指向的jsp
					String resultValue  = actionBean.getResultInfo(resultName, "value");
					if (actionBean.getResultInfo(resultName, "type").equals("forward")){
						//类型为转发
						request.setAttribute("map", map);
						System.out.println(request.getServletPath());
						System.out.println(resultValue);
						request.getRequestDispatcher( resultValue).forward(request, response);
						System.out.println("11111111111");
					}
					if (actionBean.getResultInfo(resultName, "type").equals("redirect")){
						//类型为重定向
						response.sendRedirect(resultValue);
					}
				}else {
					//返回的result不存在
					request.setAttribute("failReason", "resultFail");
					request.getRequestDispatcher("failActRst.jsp").forward(request, response);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable throwable) {
				throwable.printStackTrace();
			}
		}else{
			//请求的action不存在
			request.setAttribute("failReason", "actionFail");
			request.getRequestDispatcher("failActRst.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	private String getActionName(String url){
		String myurl = url.substring(url.lastIndexOf("/") +1 , url.lastIndexOf("."));
		System.out.println(myurl);
		return myurl;
	}
}
