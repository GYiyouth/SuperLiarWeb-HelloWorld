package xmlHandleBean;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by geyao on 2016/12/7.
 */
public class ActionResultBean {
	private HashMap<String, String>resultInfo = new HashMap<>();

	public void setName(String name){
		resultInfo.put("name", name);
	}


	public void setResult(String result){
		resultInfo.put("result", result);
	}

	public HashMap<String, String> getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(HashMap<String, String> resultInfo) {
		this.resultInfo = resultInfo;
	}
}
