package liql.test;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			int x = new Random().nextInt(3);
			System.err.println(x+1);
		}
	}
}
