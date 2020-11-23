package basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author malf
 * @Description 正则表达式示例
 * @project wechatStudy
 * @since 2020/11/23
 */
public class PatternTest {
    public static void main(String[] args) {
        String str = "北京市(朝阳区)(海淀区)(昌平区)";
        Pattern pattern = Pattern.compile(".*?(?=\\()");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
