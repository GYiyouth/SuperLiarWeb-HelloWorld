package smallTools;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import xmlHandleBean.ActionBean;
import xmlHandleBean.ControllerBean;
import xmlHandleBean.InterceptorBean;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by geyao on 2016/12/11.
 */
public class XmlReader {

	public static ControllerBean readXml(){
		try {
			SAXReader reader = new SAXReader();
			File file = new File("/Users/geyao/IdeaProjects/Web/HelloWorld/src/controller.xml");
			Document document = reader.read(file);
			Element root = document.getRootElement();
			//存放actionBean,interceptorBean的哈希表
			ControllerBean controllerBean = new ControllerBean();
			//拦截器放入controllerBean中
			Iterator<Element>interceptors = root.elementIterator("interceptor");
			while (interceptors.hasNext()){
				Element interceptor = interceptors.next();
				InterceptorBean interceptorBean = new InterceptorBean();
				interceptorBean.setName(interceptor.element("name").getText());
				interceptorBean.setClassPath(interceptor.element("class").element("name").getText());
				interceptorBean.setMethod(interceptor.element("class").element("method").getText());
				System.out.println(interceptorBean);
				controllerBean.getInterceptors().put(interceptorBean.getName(), interceptorBean);
			}
			//actionBean放入controller中
			Iterator<Element>actions = root.elementIterator("action");
			while (actions.hasNext()){
				Element action = actions.next();
				//包含一个字符串name，3个哈希表，classInfo，拦截器，result
				ActionBean actionBean = new ActionBean();
				//actionBean设定名字,classInfo
				actionBean.setName(action.element("name").getText());
				Element classInfo = action.element("class");
				if (classInfo != null){
					String class_name = classInfo.element("name").getText();
					String class_method = classInfo.element("method").getText();
					actionBean.getClassInfo().put("name", class_name);
					actionBean.getClassInfo().put("method", class_method);
				}
				//获取拦截器们，放入actionBean中
				Iterator<Element>act_itcpt = action.elementIterator("interceptor-ref");
				while (act_itcpt.hasNext()){
					String intName = act_itcpt.next().element("name").getText();
					actionBean.getInterceptors().put(intName, controllerBean.getInterceptors().get(intName));
				}
				//获取result们，放入actionBean中
				Iterator<Element>act_rst = action.elementIterator("result");
				while (act_rst.hasNext()){
					Element result = act_rst.next();
					HashMap<String, String>resultBean = new HashMap<>();
					String name = result.element("name").getText();
					String type = result.element("type").getText();
					String value = result.element("value").getText();
					resultBean.put("name", name);
					resultBean.put("type", type);
					resultBean.put("value", value);
					actionBean.getResults().put(name, resultBean);
				}
				//装填
				controllerBean.getActions().put(actionBean.getName(), actionBean);
			}
			return controllerBean;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}

	}
}
