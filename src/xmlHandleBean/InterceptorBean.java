package xmlHandleBean;

import java.util.HashMap;

/**
 * Created by geyao on 2016/12/7.
 */
public class InterceptorBean {
	private String name = "";
	private HashMap<String, String>classInfo = new HashMap<>();

	@Override
	public String toString() {
		return "InterceptorBean{" +
				"name='" + name + '\'' +
				", classInfo=" + classInfo +
				'}';
	}

	//	public void setStartTime(String startTime){
//		classInfo.put("s-time", startTime);
//	}
//
//	public String getStartTime(){
//		return classInfo.get("s-time");
//	}
//
//
//	public void setEndTime(String endTime){
//		classInfo.put("e-time", endTime);
//	}
//
//	public String getEndTime(){
//		return classInfo.get("e-time");
//	}
//	public void setResult(String result){
//		classInfo.put("result", result);
//	}
//	public String getResult(){
//		return classInfo.get("result");
//	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public void setActionName(String actionName){
//		this.classInfo.put("actionName", actionName);
//	}
//
//	public String getActionName(){
//		return classInfo.get("actionName");
//	}

	public HashMap<String, String> getClassInfo() {
		return classInfo;
	}

	public String getClassPath(){
		return classInfo.get("name");
	}

	public void setClassPath(String ClassName){
		classInfo.put("name", ClassName);
	}
	public String getMethod(){
		return classInfo.get("method");
	}
	public void setMethod(String method){
		classInfo.put("method", method);
	}


	public void setClassInfo(HashMap<String, String> classInfo) {
		this.classInfo = classInfo;
	}
}
