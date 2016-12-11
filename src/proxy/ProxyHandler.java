package proxy;

import xmlHandleBean.ActionBean;
import xmlHandleBean.InterceptorBean;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 以下代码参考知乎
 * 作者：雨夜偷牛的人
 * 链接：https://www.zhihu.com/question/20794107/answer/23330381
 * 来源：知乎
 * 著作权归作者所有，转载请联系作者获得授权。
 * Created by geyao on 2016/12/8.
 */
public class ProxyHandler implements InvocationHandler {

	private Object tar;

	public ProxyHandler() {
		super();
	}

	public Object bind(Object tar)
	{
		this.tar = tar;
		//绑定该类实现的所有接口，取得代理类
		return Proxy.newProxyInstance(tar.getClass().getClassLoader(),
				tar.getClass().getInterfaces(),
				this);
	}




	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		//在调用方法之前，进行拦截
		HashMap hashMap = (HashMap) args[0];
		ActionBean actionBean = (ActionBean) hashMap.get("actionBean");
		Map<String, String[]> map = (Map<String, String[]>) hashMap.get("realKey");

		boolean resultFlag = true;
		for (HashMap.Entry<String, InterceptorBean> entry : actionBean.getInterceptors().entrySet()) {
//			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			Class interceptor = Class.forName(entry.getValue().getClassPath());
			Method method1 = interceptor.getMethod(entry.getValue().getMethod(), HashMap.class);
			System.out.println("拦截器的Class和方法为" + interceptor + method1);
			Object itcptor_rst = method1.invoke(interceptor.newInstance(), hashMap);
			System.out.println("拦截器第一次调用结果" + itcptor_rst);
			if ( itcptor_rst== null) {
				// 拦截不通过，则返回，不调用。
				resultFlag = false;
				//执行完所有的拦截器
//				break;
			}
		}

		System.out.println("resultFlag标记是" + resultFlag);
		if (resultFlag) {
			result = method.invoke(tar, map);
//			HttpSession session = (HttpSession) hashMap.get("session");
			if (hashMap.containsKey("result")) {
				hashMap.remove("result");

				System.out.println("我看看哈希表到底有没有这个result1"+ hashMap.get("result"));
			}
			hashMap.put("result", result);
		}

		System.out.println("我看看哈希表到底有没有这个result2"+ hashMap.get("result"));
		for (HashMap.Entry<String, InterceptorBean> entry : actionBean.getInterceptors().entrySet()) {
			Class interceptor = Class.forName(entry.getValue().getClassPath());
			Method method1 = interceptor.getMethod(entry.getValue().getMethod(), HashMap.class);
			System.out.println("我看看哈希表到底有没有这个result3"+ hashMap.get("result"));
			method1.invoke(interceptor.newInstance(), hashMap);
		}
		return result;
	}
}
