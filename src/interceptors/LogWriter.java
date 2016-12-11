package interceptors;

import smallTools.Time;
import smallTools.TimeImpl;
import smallTools.XmlWriter;
import xmlHandleBean.ActionBean;
import xmlHandleBean.ActionResultBean;
import xmlHandleBean.InterceptorBean;
import org.dom4j.io.SAXReader;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by geyao on 2016/12/7.
 */
public class LogWriter {
	private ActionBean actionBean;
	private InterceptorBean interceptorBean;


	public String log(HashMap hashMap){
		XmlWriter writer = new XmlWriter();
		actionBean = (ActionBean) hashMap.get("actionBean");
		HttpSession session = (HttpSession) hashMap.get("session");
		if (session.getAttribute("LogWriter") == null){
			//第一次调用
			Time time = new TimeImpl();
			String startTime = time.getTime();
			String actionName = actionBean.getName();
			HashMap<String, String>log = new HashMap<>();
			log.put("startTime", startTime);
			log.put("actionName", actionName);
			session.setAttribute("LogWriter", log);
			return "success";
		}
		if (session.getAttribute("LogWriter") != null){
			//第二次调用
			System.out.println(hashMap);
			Time time = new TimeImpl();
			String endTime = time.getTime();
			String result;
			if (hashMap.containsKey("result")) {
				result = hashMap.get("result").toString();
			}else {
				result = "拦截未通过";
			}
			HashMap<String, String>log = (HashMap<String, String>)session.getAttribute("LogWriter");
			log.put("endTime", endTime);
			log.put("result", result);
			session.removeAttribute("LogWriter");
			XmlWriter xmlWriter = new XmlWriter();
			xmlWriter.Writelog(log);
			return "success";
		}
		return null;
	}
}
