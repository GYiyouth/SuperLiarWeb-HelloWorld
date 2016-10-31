package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import DAO.AcountDAO.UserDAO;
import DAO.AcountDAO.UserDAOImpl;
import DAO.AcountDAO.VO.User;

import DAO.com.util.db.*;

/**
 * Created by geyao on 2016/10/29.
 */
//@WebServlet(name = "LogIn")
public class LogIn extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		String passWord = request.getParameter("passWord");

//		request.setAttribute("email", email);
//		request.setAttribute("passWord", passWord);
		User user;
		UserDAO userDAO = new UserDAOImpl();
		boolean exist = false;
		System.out.println(email + passWord);

		try {
			user = userDAO.findByAccount(email, passWord);
			if (user.getId() != 0){ //用户存在，且登录成功

//				request.setAttribute("User", user);     // 把user作为属性像下一个页面传递
//				request.getRequestDispatcher();
			}else{   // 用户不存在
				System.out.println("用户不存在");
				int flag = 1;
				request.setAttribute("flag", flag);

				request.getRequestDispatcher("log_sign_game/LogFailed.jsp").forward(request, response);
				System.out.println("已转发");

			}
		}
		catch (SQLException e) {  // 数据库连接失败
			System.out.println("数据库连接失败");
			int flag = 2;
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("log_sign_game/LogFailed.jsp");
		}



	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
