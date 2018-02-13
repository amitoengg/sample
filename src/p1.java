import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class p1 {
	public String nextClosestTime(String time) {
		int cur = 60 * Integer.parseInt(time.substring(0, 2));
		cur += Integer.parseInt(time.substring(3));
		Set<Integer> allowed = new HashSet();
		for (char c : time.toCharArray())
			if (c != ':') {
				allowed.add(c - '0');
			}

		while (true) {
			cur = (cur + 1) % (24 * 60);
			int[] digits = new int[] { cur / 60 / 10, cur / 60 % 10, cur % 60 / 10, cur % 60 % 10 };
			search: {
				for (int d : digits)
					if (!allowed.contains(d))
						break search;
				return String.format("%02d:%02d", cur / 60, cur % 60);
			}
		}
	}

	public String nextClosestTime1(String input) {
		String result = null;
		String parts[] = input.split(":");
		int input_format[] = new int[4];
		input_format[1] = Character.getNumericValue(parts[0].charAt(parts[0].length() - 1));
		if (parts[0].length() == 2) {
			input_format[0] = Character.getNumericValue(parts[0].charAt(0));
		}
		input_format[2] = Character.getNumericValue(parts[1].charAt(0));
		if (parts[1].length() == 2) {
			input_format[3] = Character.getNumericValue(parts[1].charAt(1));
		}
		currentBest = input_format.clone();
		generatePermutation(input_format, 0);
		System.out.println("Best" + Arrays.toString(currentBest));

		return result;
	}

	public static boolean isValidTime(int h1, int h2, int m1, int m2) {
		int hh = (h1 * 10) + h2;
		int mm = (m1 * 10) + m2;
		if (hh > 23 || mm > 59)
			return false;
		else
			return true;
	}

	public static boolean compareTime(int[] src, int[] best) {
		int src_time = ((src[0] * 10) + src[1]) * 60 + ((src[2] * 10) + src[3]);
		int best_time = ((best[0] * 10) + best[1]) * 60 + ((best[2] * 10) + best[3]);
		int diff = Math.abs(src_time - best_time);
		if (diff > 0 && curBestDiff > diff) {
			curBestDiff = diff;
			return true;
		} else
			return false;
	}

	static int[] currentBest = null;
	static int curBestDiff = Integer.MAX_VALUE;

	public static void generatePermutation(int[] ary, int posK) {
		if (ary[0] > 2 || (ary[0] == 2 && ary[1] > 3) || ary[2] > 6)
			return;
		if (posK == ary.length) {
			if (isValidTime(ary[0], ary[1], ary[2], ary[3])) {
				if (compareTime(ary, currentBest)) {
					currentBest = ary.clone();
					;
				}
			}
			System.out.println(posK + " " + Arrays.toString(ary));

		}
		for (int i = posK; i < ary.length; i++) {
			int temp = ary[posK];
			ary[posK] = ary[i];
			ary[i] = temp;
			generatePermutation(ary, posK + 1);
			ary[i] = ary[posK];
			ary[posK] = temp;
		}
	}

	public static void main(String[] args) {
		int ary[] = { 2, 2, 3, 6 };
		generatePermutation(ary, 0);
	}
}
