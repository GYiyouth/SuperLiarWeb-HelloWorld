package xmlHandleBean;

import java.util.HashMap;

/**
 * Created by geyao on 2016/12/3.
 */
public class ActionBean {

	public ActionBean(){
		super();
	}


	private String name = "";
	//代表class，比如，key = "name"， value = "Servlet.LogIn"
	private HashMap<String, String> classInfo = new HashMap<>();

	//key为reuslt的名字，value为该result的哈希表
	private HashMap<String, HashMap<String, String>> results = new HashMap<>();

	private HashMap<String, InterceptorBean>interceptors = new HashMap<>();

	public HashMap<String, InterceptorBean> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(HashMap<String, InterceptorBean> interceptors) {
		this.interceptors = interceptors;
	}


	//	//key为标签名，value为里面的值
//	private HashMap<String, String> resultInfo = new HashMap<>();

	//返回<name>Servlet.LogIn</name>
	public String getClassPath(){
		return classInfo.get("name");
	}
	//<method>login</method>
	public String getClassMethod(){
		return classInfo.get("method");
	}

	/**
	 * 根据result的名字，以及要查询的项目，返回相应的值
	 * @param resultName
	 * @param tagName
	 * @return
	 */
	public String getResultInfo(String resultName, String tagName){
		return results.get(resultName).get(tagName);
	}

	/**
	 * 返回该action的名字
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 返回action的路径
	 * @return
	 */
	public String getActionClass(){
		return classInfo.get("name");
	}

	/**
	 * 返回该action对应的方法
	 * @return
	 */
	public String getMethod(){
		return classInfo.get("method");
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(HashMap<String, String> classInfo) {
		this.classInfo = classInfo;
	}

	public HashMap<String, HashMap<String, String>> getResults() {
		return results;
	}

	public void setResults(HashMap<String, HashMap<String, String>> results) {
		this.results = results;
	}

//	public HashMap<String, String> getResultInfo() {
//		return resultInfo;
//	}
//
//	public void setResultInfo(HashMap<String, String> resultInfo) {
//		this.resultInfo = resultInfo;
//	}

	@Override
	public String toString() {
		return "ActionBean{" +
				"name='" + name + '\'' +
				", classInfo=" + classInfo +
				", results=" + results ;
	}
}
