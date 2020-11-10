package collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author malf
 * @description List 去除重复数据的五种方式
 * @project wechatStudy
 * @since 2020/11/10
 */
public class UniqueList {
	static ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(
			1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

	public static void main(String[] args) {
		System.out.println(numbers);
		// 1、使用 LinkedHashSet 删除 Arraylist 中的重复数据
		LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(numbers);
		ArrayList<Integer> result1 = new ArrayList<>(hashSet);
		System.out.println(result1);

		// 2、使用 java8 新特性 stream 进行 List 去重
		List<Integer> result2 = numbers
				.stream()
				.distinct()
				.collect(Collectors.toList());
		System.out.println(result2);

		// 3、利用 HashSet 不能添加重复数据的特性，
		// 由于 HashSet 不能保证添加顺序，所以只能作为判断条件保证顺序
		HashSet<Integer> set = new HashSet<>(numbers.size());
		List<Integer> result3 = new ArrayList<>(numbers.size());
		for (Integer str : numbers) {
			if (set.add(str)) {
				result3.add(str);
			}
		}
		System.out.println(result3);

		// 4、List 的 contains 方法循环遍历,重新排序,只添加一次数据,避免重复
		List<Integer> result4 = new ArrayList<>(numbers.size());
		for (Integer str : numbers) {
			if (!result4.contains(str)) {
				result4.add(str);
			}
		}
		System.out.println(result4);

		// 5、双重 for 循环去重
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				if (i != j && numbers.get(i) == numbers.get(j)) {
					numbers.remove(numbers.get(j));
				}
			}
		}
		System.out.println(numbers);
	}
}
