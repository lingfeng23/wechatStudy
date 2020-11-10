package basic;

/**
 * @author malf
 * @description 字符串相关用法
 * @project wechatStudy
 * @since 2020/11/10
 */
public class StringTest {
	public static void main(String[] args) {
		String str1 = "Programming";
		String str2 = new String("Programming");
		String str3 = "Program";
		String str4 = "ming";
		String str5 = "Program" + "ming";
		String str6 = str3 + str4;
		System.out.println(str1 == str2); // false
		System.out.println(str1 == str5); // true
		System.out.println(str1 == str6); // false
		System.out.println(str1 == str6.intern()); // true
		System.out.println(str2 == str2.intern()); // false
	}
}
