package smallTools;

/**
 * Created by geyao on 2016/11/1.
 */
public class StringCheck {
	/**
	 *利用正则表达式，判断邮箱是否符合标准
	 * @param email
	 * @return
	 */
	public static boolean emailCheck(String email){
		String format = "\\w+@\\w+(\\.\\w)*\\.\\w+"; // 邮箱的格式
		if (email.matches(format))
			return true;
		return false;
	}
}
