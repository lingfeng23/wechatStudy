package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author malf
 * @description 求一个整数的因子
 * @project wechatStudy
 * @since 2020/11/16
 */
public class FactorsOfNumber {
	public static void main(String[] args) {
		String ch;
		System.out.print("enter a integer number: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = 0;
		try {
			ch = br.readLine();
			a = Integer.parseInt(ch);
		} catch (IOException e) {
		}
		System.out.println("The number is: " + a);
		System.out.print("It's factors are: ");
		for (int b = 1; b <= a; b++)
			if (a % b == 0) {
				System.out.println("");
				System.out.print(b + "\t");
			}
	}
}
