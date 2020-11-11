package basic;

import java.io.Serializable;

/**
 * @author malf
 * @description TODO
 * @project wechatStudy
 * @since 2020/11/11
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer age;
	private transient String sex;
	private static String signature = "床前明月光";

	public static String getSignature() {
		return signature;
	}

	public static void setSignature(String signature) {
		User.signature = signature;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", sex='" + sex + '\'' +
				", signature='" + signature + '\'' +
				'}';
	}
}
