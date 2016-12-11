package DAO.AcountDAO;

import DAO.AcountDAO.VO.UserBean;
import DAO.com.util.db.DBUtils;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by geyao on 2016/10/29.
 */
public class UserDAOImpl implements UserDAO {
	/**
	 * 先检查用户是否已经存在，存在返回0
	 * 添加用户，同时为他创建他的游戏table，table名字为 "Game" + UserId
	 * @param userBean
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int add(UserBean userBean) throws SQLException {
		String email = userBean.getEmail();
		String passWord = userBean.getPassword();
		try{
			if (check(email, passWord))
				return 0;
		}catch (SQLException e){
			e.printStackTrace();
			return 0;
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into sys.User(Email,PassWord,nickedName, CreatedTime, PhoneNumber) values(?,?,?,?,?);";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userBean.getEmail());
			preparedStatement.setString(2, userBean.getPassword());
			preparedStatement.setString(3, userBean.getNickedName());
			preparedStatement.setString(4, userBean.getCreatedTime());
			preparedStatement.setString(5, userBean.getPhoneNumber());

			preparedStatement.executeUpdate();
			userBean.setId(getId(userBean));
			if (userBean.getId() !=0)
				createGameTable(userBean);
			return userBean.getId();
		}catch (MySQLIntegrityConstraintViolationException e1){ // 用户已经存在
			return 0;
		}
		catch (SQLException e){
			e.printStackTrace();
			throw new SQLException("添加数据失败");

		}finally {
			DBUtils.close(null, preparedStatement, connection);

		}
	}

	/**
	 * 根据邮箱和密码检查用户是否已经存在，不存在或者失败返回false
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean check(String email, String password) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select * from sys.User where Email = ? and PassWord = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
//			boolean exist = preparedStatement.execute();
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			if (resultSet.next())
				return true;
			else
				return false;
		}catch (SQLException e){
			e.printStackTrace();
			throw new SQLException("check函数连接数据库失败");
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 输入id, 要修改的属性，修改后的值，按此更新
	 * @param id
	 * @param column
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean update(int id, String column, String value) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		String sql = "update sys.User set ? = ?  where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, column);
			preparedStatement.setString(2, value);
			preparedStatement.setInt(3, id);
			int num = preparedStatement.executeUpdate();
			return (num > 0);
		}catch (SQLException e){

			e.printStackTrace();
			return false;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 根据id，修改密码
	 * @param id
	 * @param PassWord
	 * @return
	 */
	@Override
	public boolean updatePassWord(int id, String PassWord) {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		String sql = "update sys.User set PassWord = ?  where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, PassWord);
			preparedStatement.setInt(2, id);
			int num = preparedStatement.executeUpdate();
			return (num == 1);
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 根据id ，修改昵称
	 * @param id
	 * @param nickedName
	 * @return
	 */
	@Override
	public boolean updatenickedName(int id, String nickedName) {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		String sql = "update sys.User set nickedName = ?  where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nickedName);
			preparedStatement.setInt(2, id);
			int num = preparedStatement.executeUpdate();
			return (num == 1);
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	@Override
	public void delete(int id) throws SQLException {

	}

	/**
	 * 根据id返回对应的用户，不存在则返回null
	 * @param id
	 * @return UserBean
	 * @throws SQLException
	 */
	@Override
	public UserBean findById(int id) throws SQLException {      //根据ID返回User，不存在则返回null
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String sql = "select * from sys.User  where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			boolean exist = preparedStatement.execute();
			if (!exist)
				return null;
			resultSet = preparedStatement.getResultSet();
			UserBean userBean = new UserBean();
			userBean.setEmail(resultSet.getString("Email"));
			userBean.setId(resultSet.getInt("id"));
			userBean.setPassword(resultSet.getString("PassWord"));
			userBean.setNickedName(resultSet.getString("nickedName"));
			userBean.setSex(resultSet.getString("Sex"));
			userBean.setCreatedTime(resultSet.getString("CreatedTime"));
			userBean.setGameNumbers(resultSet.getInt("GameNumbers"));
			userBean.setGameTableId(resultSet.getString("GameTableId"));
			userBean.setPhoneNumber(resultSet.getString("PhoneNumber"));
			userBean.setTotalGames(resultSet.getInt("TotalGames"));
			userBean.setWinNumbers(resultSet.getInt("WinNumbers"));
			userBean.setBackup2(resultSet.getString("Backup2"));
			userBean.setBackup3(resultSet.getString("Backup3"));
			userBean.setBackup4(resultSet.getString("Backup4"));
			return userBean;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 根据邮箱密码返回用户，不成功返回id为0的user
	 * @param Email
	 * @param PassWord
	 * @return
	 * @throws SQLException
	 */
	@Override
	public UserBean findByAccount(String Email, String PassWord) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet = null;
		String sql = "select * from sys.User  where Email = ? and PassWord = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Email);
			preparedStatement.setString(2, PassWord);
//			preparedStatement.execute();
			resultSet = preparedStatement.executeQuery();
			if ( !resultSet.next() ) {      // 为空集时，resultSet.next()返回false
				UserBean userBean = new UserBean();
				userBean.setId(0);
				return userBean;
			}

			UserBean userBean = new UserBean();
			userBean.setEmail(resultSet.getString("Email"));
			userBean.setId(resultSet.getInt("id"));
			userBean.setPassword(resultSet.getString("PassWord"));
			userBean.setNickedName(resultSet.getString("nickedName"));
			userBean.setSex(resultSet.getString("Sex"));
			userBean.setCreatedTime(resultSet.getString("CreatedTime"));
			userBean.setGameNumbers(resultSet.getInt("GameNumbers"));
			userBean.setGameTableId(resultSet.getString("GameTableId"));
			userBean.setPhoneNumber(resultSet.getString("PhoneNumber"));
			userBean.setTotalGames(resultSet.getInt("TotalGames"));
			userBean.setWinNumbers(resultSet.getInt("WinNumbers"));
			userBean.setBackup2(resultSet.getString("Backup2"));
			userBean.setBackup3(resultSet.getString("Backup3"));
			userBean.setBackup4(resultSet.getString("Backup4"));
			return userBean;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}finally {
			DBUtils.close(resultSet, preparedStatement, connection);
		}
	}

	/**
	 * 查询目前总用户数，包含刚刚新建的用户，失败则返回0
	 * @return
	 * @throws SQLException
	 */
	@Override
	synchronized public int getUserNumbers() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select count(*)from sys.User;";
		int num = 0;
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			num = resultSet.getInt(1);
		}catch (SQLException e){
			e.printStackTrace();
			throw new SQLException("查询总用户数失败");
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
		return num;
	}
	/**
	 *为用户建立游戏表，表命格式如下
	 * Game + UserId
	 * 在add用户时会被调用*/
	@Override
	public void createGameTable(UserBean userBean) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String name = "Game"+ userBean.getId();
		String sql = "create table "+ name +" like sys.GamePattern ;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);

//			preparedStatement.setString(1, name);
			preparedStatement.execute();
//			if (flag)
//				return;
//			else
//				throw new SQLException("建立GameTable失败");
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	/**
	 * 返回用户的id
	 * @param userBean
	 * @return
	 */
	@Override
	public int getId(UserBean userBean) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "select id from sys.User where Email = ? and PassWord = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userBean.getEmail());
			preparedStatement.setString(2, userBean.getPassword());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt(1);
			return id;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<UserBean> findAll() throws SQLException {
		return null;
	}
}
