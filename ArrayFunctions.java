package week4.day2;

public class ArrayFunctions {

	public boolean isSorted(Integer[] arr) {
		int arrLen = arr.length;
		int count = arrLen - 1;

		for (int i = count; i > 0; i--) {

			int j = i - 1;

			if (arr[i] <= arr[j]) {
				return false;
			}

		}

		return true;

	
	}

}