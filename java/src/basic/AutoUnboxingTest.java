package basic;

/**
 * @author malf
 * @Description 自动拆箱装箱
 * @project wechatStudy
 * @since 2020/11/23
 */
public class AutoUnboxingTest {
    public static void main(String[] args) {
        Integer a = new Integer(3);
        Integer b = 3; // 自动装箱
        int c = 3;
        System.out.println(a == b); // false
        System.out.println(a == c); // true，a 自动拆箱成 int 类型再和 c 比较

        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        System.out.println(f1 == f2); // true
        System.out.println(f3 == f4); // false
    }
}
