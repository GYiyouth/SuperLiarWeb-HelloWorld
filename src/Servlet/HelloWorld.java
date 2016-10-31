package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by geyao on 2016/10/16.
 */
//@WebServlet(name = "Servlet.HelloWorld")
public class HelloWorld extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String nickedName = request.getParameter("nickedName");

//		System.out.println(nickedName);
		request.setAttribute("nickedName", nickedName);
		request.getRequestDispatcher("LogAndSign/GoodbyeWorld.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
