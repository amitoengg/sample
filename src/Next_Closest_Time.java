import java.util.Arrays;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by
 * reusing the current digits. There is no limit on how many times a digit can
 * be reused.
 * 
 * You may assume the given input string is always valid. For example, "01:34",
 * "12:09" are all valid. "1:34", "12:9" are all invalid.
 * 
 * Input: "19:34" Output: "19:39" Explanation: The next closest time choosing
 * from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later. It is not
 * 19:33, because this occurs 23 hours and 59 minutes later.
 * 
 * Input: "23:59" Output: "22:22" Explanation: The next closest time choosing
 * from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is
 * next day's time since it is smaller than the input time numerically.
 * 
 * 
 * @author amitrs
 *
 */
public class Next_Closest_Time {

	public String nextClosestTime(String input) {
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
		System.out.println("Best"+Arrays.toString(currentBest));

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
		int diff = Math.abs(src_time-best_time);
		if (diff > 0 && curBestDiff> diff) {
			curBestDiff = diff;
			return true;
		}
		else
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
					currentBest = ary.clone();;
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
