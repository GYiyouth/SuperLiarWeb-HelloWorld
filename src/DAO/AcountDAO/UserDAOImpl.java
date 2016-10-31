package DAO.AcountDAO;

import DAO.com.util.db.DBUtils;
import DAO.AcountDAO.VO.User;

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
	 * 添加用户，同时为他创建他的游戏table，table名字为 "Game" + UserId
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int add(User user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "insert into sys.User(Email,PassWord,nickedName, CreatedTime) values(?,?,?,?);";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getNickedName());
			preparedStatement.setString(4, user.getCreatedTime());
			preparedStatement.executeUpdate();
			createGameTable(user);
			return getUserNumbers();
		}catch (SQLException e){
			e.printStackTrace();
			throw new SQLException("添加数据失败");

		}finally {
			DBUtils.close(null, preparedStatement, connection);

		}
	}
	/***
	 * 根据邮箱和密码检查用户是否已经存在，不存在或者失败返回false
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
//			throw new SQLException("用户不存在");
			throw new SQLException("check函数连接数据库失败");
//			return false;
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
		String sql = "update User set ? = ?  where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, column);
			preparedStatement.setString(2, value);
			preparedStatement.setInt(3, id);
			boolean exist = preparedStatement.execute();
			return exist;
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
		String sql = "update User set PassWord = ?  where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, PassWord);
			preparedStatement.setInt(2, id);
			boolean exist = preparedStatement.execute();
			return exist;
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
		String sql = "update User set nickedName = ?  where id = ?;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nickedName);
			preparedStatement.setInt(2, id);
			boolean exist = preparedStatement.execute();
			return exist;
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
	 * @return User
	 * @throws SQLException
	 */
	@Override
	public User findById(int id) throws SQLException {      //根据ID返回User，不存在则返回null
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
			User user = new User();
			user.setEmail(resultSet.getString(1));
			user.setId(resultSet.getInt(2));
			user.setPassword(resultSet.getString(3));
			user.setSex(resultSet.getString(4));
			user.setCreatedTime(resultSet.getString(5));
			user.setGameNumbers(resultSet.getInt(6));
			user.setGameTableId(resultSet.getString(7));
			user.setPhoneNumber(resultSet.getString(8));
			user.setTotalGames(resultSet.getInt(9));
			user.setWinNumbers(resultSet.getInt(10));
			user.setBackup2(resultSet.getString(11));
			user.setBackup3(resultSet.getString(12));
			user.setBackup4(resultSet.getString(13));
			return user;
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
	public User findByAccount(String Email, String PassWord) throws SQLException {
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
				User user = new User();
				user.setId(0);
				return user;
			}

			User user = new User();
			user.setEmail(resultSet.getString(1));
			user.setId(resultSet.getInt(2));
			user.setPassword(resultSet.getString(3));
			user.setSex(resultSet.getString(4));
			user.setCreatedTime(resultSet.getString(5));
			user.setGameNumbers(resultSet.getInt(6));
			user.setGameTableId(resultSet.getString(7));
			user.setPhoneNumber(resultSet.getString(8));
			user.setTotalGames(resultSet.getInt(9));
			user.setWinNumbers(resultSet.getInt(10));
			user.setBackup2(resultSet.getString(11));
			user.setBackup3(resultSet.getString(12));
			user.setBackup4(resultSet.getString(13));
			return user;
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
//		ResultSet resultSet = null;
		String sql = "select count(*)from sys.User;";
		int num = 0;
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			num = preparedStatement.executeUpdate();
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
	public void createGameTable(User user) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "create table ? like sys.GamePattern ;";
		try {
			connection = DBUtils.getConnetction();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "Game" + user.getId());
			boolean flag = preparedStatement.execute();
			if (flag)
				return;
			else
				throw new SQLException("建立GameTable失败");
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DBUtils.close(null, preparedStatement, connection);
		}
	}

	@Override
	public List<User> findAll() throws SQLException {
		return null;
	}
}
