package basic;

import java.io.*;

/**
 * @author malf
 * @description 序列化与反序列化
 * @project wechatStudy
 * @since 2020/11/11
 */
public class SerializableTest {
	// 将对象序列化到文件中
	private static void serialize(User user) throws Exception {
		ObjectOutputStream outputStream = new ObjectOutputStream(
				new FileOutputStream(new File("E:\\malf\\wechatStudy\\java\\src\\basic\\user.txt")));
		outputStream.writeObject(user);
		outputStream.close();
	}

	// 将文件中内容反序列化为对象
	private static User deserialize() throws Exception {
		ObjectInputStream inputStream = new ObjectInputStream(
				new FileInputStream(new File("E:\\malf\\wechatStudy\\java\\src\\basic\\user.txt")));
		return (User) inputStream.readObject();
	}

	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setName("Bob");
		user.setAge(23);
		user.setSex("man");
		System.out.println("序列化前的结果：" + user);
		serialize(user);
		User dUser = deserialize();
		System.out.println("反序列化后的结果：" + dUser);
	}
}
