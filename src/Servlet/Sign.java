package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import DAO.AcountDAO.UserDAO;
import DAO.AcountDAO.UserDAOImpl;
import DAO.AcountDAO.VO.UserBean;
import smallTools.*;

/**
 * Created by geyao on 2016/11/1.
 */
public class Sign extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String passWord1 = request.getParameter("passWord1");
		String passWord2 = request.getParameter("passWord2");
		String nickedName = request.getParameter("nickedName");
		String phoneNumber = request.getParameter("phoneNumber");
		String year = request.getParameter("year");
		String monthDay = request.getParameter("monthDay");
		System.out.println(email+passWord1);
		if (StringCheck.emailCheck(email)){     // 邮箱匹配
			if (passWord1.equals(passWord2)){   //密码相等
				UserDAO userDAO = new UserDAOImpl();
				try {
					if (!userDAO.check(email, passWord1)){// 检查用户是否存在，存在返回true
						UserBean userBean = new UserBean();
						userBean.setEmail(email);
						userBean.setPassword(passWord1);
						userBean.setNickedName(nickedName);
						Time time = new TimeImpl();
						userBean.setCreatedTime(time.getDate());
						if (StringCheck.phoneCheck(passWord1))
							userBean.setPhoneNumber(phoneNumber);
						userDAO.add(userBean);
						userBean.setId(userDAO.getId(userBean));
						request.setAttribute("userBean", userBean);
						request.getRequestDispatcher("UserCenter.jsp").forward(request, response);
						return;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					request.setAttribute("flag", 2);
					request.getRequestDispatcher("log_sign_game/LogSignJump.jsp").forward(request, response);
					return;
				}
			}
		}
		System.out.println("登录失败");
		request.setAttribute("flag", 3);
		request.getRequestDispatcher("log_sign_game/LogSignJump.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
