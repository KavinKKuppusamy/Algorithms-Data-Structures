import java.util.Arrays;

public class SearchAlgos {

	public static void main(String[] args) {

		int sortedArr[] = { 11, 20, 21, 30, 44, 49, 51, 57, 72, 75, 80, 86, 93, 94, 95, 1589 };
		int num = 68;
		SearchAlgos obj = new SearchAlgos();
		int index = obj.ternarySearch(sortedArr, 0, sortedArr.length - 1, num);

		System.out.println("Given Array: " + Arrays.toString(sortedArr));
		System.out.println("\nArray Size: " + sortedArr.length);
		if (index != -1) {
			System.out.println("\nNumber " + num + " is found in the given array in the position " + (index+1)
					+"\nconsidering index starting from 1");
		} else {
			System.out.println("\nNumber " + num + " is not found in the given array");
		}
	}

	public int ternarySearch(int arr[], int low, int high, int value) {
		if (low <= high) {

			int interval = (high - low) / 3;
			int mid1 = low + interval;
			int mid2 = high - interval;
			if (value == arr[mid1]) {
				return mid1;
			} else if (value == arr[mid2]) {
				return mid2;
			} else if (value < arr[mid1]) {
				return ternarySearch(arr, low, mid1 - 1, value);
			} else if (value > arr[mid2]) {
				return ternarySearch(arr, mid2 + 1, high, value);
			} else {
				return ternarySearch(arr, mid1 + 1, mid2 - 1, value);
			}

		} else {
			return -1;
		}
	}
}
