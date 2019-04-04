package Control;

import java.util.Random;

public final class Helper {
	static Random random = new Random();
	public static float rand(int max, int min) {
		return (float)random.nextInt(max-min+1)+min;
	}
}
