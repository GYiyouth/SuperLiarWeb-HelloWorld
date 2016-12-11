package DAO.AcountDAO;

import DAO.AcountDAO.VO.UserBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by geyao on 2016/10/29.
 */
public interface UserDAO {
	public int add(UserBean userBean) throws SQLException;
	public boolean update(int id, String column, String value) throws SQLException;
	public boolean updatePassWord(int id, String PassWord);
	public boolean updatenickedName(int id, String nickedName);
	public void delete(int id) throws SQLException;
	public void createGameTable(UserBean userBean) throws SQLException;
	public boolean check(String email, String password) throws SQLException;

	public UserBean findById(int id)throws SQLException;
	public UserBean findByAccount(String Email, String PassWord) throws SQLException;
	public int getUserNumbers() throws SQLException;
	//查找所有
	public List<UserBean> findAll()throws SQLException;
	public int getId(UserBean userBean);
}
