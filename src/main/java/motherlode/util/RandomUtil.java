package motherlode.util;

import java.util.Random;

public class RandomUtil {
	public static final Random RANDOM = new Random();

	public static boolean chance(Random random, float chance) {
		return random.nextFloat() <= chance;
	}

	public static boolean chance(float chance) {
		return chance(RANDOM, chance);
	}

	public static boolean chance(Random random, int times, int in) {
		int num = random.nextInt(in);
		for (int i = 0; i < times; i++) {
			if (num == i) {
				return true;
			}
		}
		return false;
	}

	public static boolean chance(int times, int in) {
		return chance(RANDOM, times, in);
	}
}
