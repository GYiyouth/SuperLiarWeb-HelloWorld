package Servlet;

import DAO.AcountDAO.UserDAO;
import DAO.AcountDAO.UserDAOImpl;
import DAO.AcountDAO.VO.User;

import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by geyao on 2016/10/29.
 */
public class test {
	public static void main(String[] args){
		String one = "一二三si5";
		byte[] bytes = one.getBytes();
		System.out.println(bytes.length);
		for (byte mybyte : bytes){
			System.out.println(mybyte);
		}
		char[] chars = one.toCharArray();
		for (char mychar:
		     chars) {
			System.out.println(mychar);
		}

		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);//获取年份
		int month=ca.get(Calendar.MONTH);//获取月份
		int day=ca.get(Calendar.DATE);//获取日
		int minute=ca.get(Calendar.MINUTE);//分
		int hour=ca.get(Calendar.HOUR);//小时
		int second=ca.get(Calendar.SECOND);//秒
		int WeekOfYear = ca.get(Calendar.DAY_OF_WEEK);


		System.out.println("用Calendar.getInstance().getTime()方式显示时间: " + ca.getTime());
		System.out.println("用Calendar获得日期是：" + year +"年"+ month +"月"+ day + "日");

		System.out.println("用Calendar获得时间是：" + hour +"时"+ minute +"分"+ second +"秒");
		System.out.println(WeekOfYear);//显示今天是一周的第几天（我做的这个例子正好是周二，故结果显示2，如果你再周6运行，那么显示6）
		System.out.println(new test().check2("544311417@qq.com", "gyiyouth1995"));

	}
	public boolean check2(String email, String password){
		UserDAO userDAO2 = new UserDAOImpl();
		try {

			User user2 = userDAO2.findByAccount(email, password);
			System.out.println(user2.getId());

			return userDAO2.check(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
}
