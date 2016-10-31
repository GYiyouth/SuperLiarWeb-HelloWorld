package DAO.AcountDAO.VO;

import DAO.AcountDAO.UserDAO;
import DAO.AcountDAO.UserDAOImpl;
import com.sun.istack.internal.Nullable;
import smallTools.Time;

import java.sql.SQLException;

/**
 * 构造函数，要传入的数据按顺序为邮箱，密码，昵称
 * Created by geyao on 2016/10/29.
 */
public class User {
	private String Email;
	private int id;
	private String Password;
	private String nickedName;
	private String Sex;
	private String CreatedTime;
	private int GameNumbers;
	private String GameTableId;
	private String PhoneNumber;
	private int TotalGames;
	private int WinNumbers;

	private String Backup2;
	private String Backup3;
	private String Backup4;

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getNickedName() {
		return nickedName;
	}

	public void setNickedName(String nickedName) {
		this.nickedName = nickedName;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getCreatedTime() {
		return CreatedTime;
	}

	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}

	public int getGameNumbers() {
		return GameNumbers;
	}

	public void setGameNumbers(int gameNumbers) {
		GameNumbers = gameNumbers;
	}

	public String getGameTableId() {
		return GameTableId;
	}

	public void setGameTableId(String gameTableId) {
		GameTableId = gameTableId;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public int getTotalGames() {
		return TotalGames;
	}

	public void setTotalGames(int totalGames) {
		TotalGames = totalGames;
	}

	public int getWinNumbers() {
		return WinNumbers;
	}

	public void setWinNumbers(int winNumbers) {
		WinNumbers = winNumbers;
	}

	public String getBackup2() {
		return Backup2;
	}

	public void setBackup2(String backup2) {
		Backup2 = backup2;
	}

	public String getBackup3() {
		return Backup3;
	}

	public void setBackup3(String backup3) {
		Backup3 = backup3;
	}

	public String getBackup4() {
		return Backup4;
	}

	public void setBackup4(String backup4) {
		Backup4 = backup4;
	}

	public User(String email, String password, String nickedName,
	            @Nullable String phoneNumber, @Nullable String sex) throws SQLException {
		Email = email;

		Password = password;
		this.nickedName = nickedName;
		this.Sex = sex;
		CreatedTime = getDATE();
		GameNumbers = 0;
		GameTableId = "" + this.id;
		PhoneNumber = phoneNumber;
		TotalGames = 0;
		WinNumbers = 0;
		UserDAO userDAO = new UserDAOImpl();
		try {
			userDAO.add(this);
		}catch (SQLException e){
			e.printStackTrace();
			throw new SQLException("创建用户失败，请联系管理员，邮箱在下部");
		}
		this.id = getRows();
	}
	public User(){
		super();
	}

	/**
	 * 以下是一些不该出现的辅助逻辑
	 * */

	private String getDATE(){
		String year = "" + Time.getYear();
		int month = Time.getMonth();
		String mon = "" + month;
		if (month < 10)
			mon = "0" + mon;
		String date = year + mon;
		return date;
	}

	private synchronized int getRows(){
		UserDAO userDAO = new UserDAOImpl();
		try {
			return userDAO.getUserNumbers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
