package xmlHandleBean;

import java.util.HashMap;

/**
 * Created by geyao on 2016/12/3.
 */
public class ControllerBean {
	public ControllerBean() {
		super();
	}

	private HashMap<String, ActionBean>actions = new HashMap<>();

	private HashMap<String, InterceptorBean>interceptors = new HashMap<>();



	public HashMap<String, InterceptorBean> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(HashMap<String, InterceptorBean> interceptors) {
		this.interceptors = interceptors;
	}

	public HashMap<String, ActionBean> getActions() {
		return actions;
	}

	public void setActions(HashMap<String, ActionBean> actions) {
		this.actions = actions;
	}

	public ActionBean checkAction(String actionName){
		if (actions.containsKey(actionName)){
			ActionBean action = actions.get(actionName);
			return action;
		}else
			return null;
	}

	@Override
	public String toString() {
		return "ControllerBean{" +
				"actions=" + actions +
				", interceptors=" + interceptors +
				'}';
	}
}
