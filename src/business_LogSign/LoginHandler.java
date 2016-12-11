package business_LogSign;

import DAO.AcountDAO.UserDAO;
import DAO.AcountDAO.UserDAOImpl;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by geyao on 2016/12/3.
 */
public class LoginHandler implements log{
	public String login(Map<String, String[]> map){
		System.out.println(map);
		String userName = map.get("userName")[0];
		String password = map.get("password")[0];
		if (userName!= null && password != null){
			UserDAO userDAO = new UserDAOImpl();
			try {
				if (userDAO.check(userName, password)){
					return "success";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "fail";
	}

	public LoginHandler(){
		super();
		System.out.println(1111);
	}
}
