import java.util.ArrayList;

public class FloydHeap {

	static ArrayList<Integer> h = new ArrayList<>();

	public static void main(String[] args) {

		int arr[] = { 96, 5, 12, 10, 3, 54, 86, 0, 14, 2, 7 };

		if (arr != null && arr.length > 0) {

			FloydHeap obj = new FloydHeap();

			ArrayList<Integer> arrlist = new ArrayList<>();
			for (int i = 0; i < arr.length; i++) {
				arrlist.add(arr[i]);
			}

			System.out.println("Given list - Before Heap " + arrlist.toString());
			obj.buildHeap(arrlist);
			System.out.println("After Heap - index '0' holds total size:" + h.toString());

			System.out.println("\n---------INSERT (-2)--------");
			obj.insert(-2);

			System.out.println("\n---------INSERT (9)--------");
			obj.insert(9);

			System.out.println("\n---------DELETE--------");
			obj.delete();

			System.out.println("\n---------DELETE--------");
			obj.delete();

		} else {
			System.out.println("Given array is empty");
		}
	}

	public void buildHeap(ArrayList<Integer> A) {
		if (!A.isEmpty()) {
			int l = A.size();
			h = new ArrayList<>();
			h.add(l);
			h.addAll(A);
			for (int i = l / 2; i > 0; i--) {
				heapify(h, i);
			}
		}
	}

	public void heapify(ArrayList<Integer> h, int i) {
		int left = (2 * i);
		int right = (2 * i) + 1;
		int min = i;

		if (left <= h.get(0) && h.get(left) < h.get(min)) {
			min = left;
		}
		if (right <= h.get(0) && h.get(right) < h.get(min)) {
			min = right;
		}
		if (min != i) {
			int temp = h.get(min);
			h.set(min, h.get(i));
			h.set(i, temp);
			heapify(h, min);
		}
	}

	public void insert(int val) {
		System.out.println("Before Insertion: " + h.toString());
		int size = h.get(0) + 1;
		h.set(0, size);
		h.add(val);
		for (int i = size / 2; i > 0; i--) {
			heapify(h, i);
		}
		System.out.println("After Insertion: " + h.toString());

	}

	public void delete() {
		if (h.isEmpty() || h.get(0) <= 0) {
			System.out.println("Empty, can not be deleted");
		} else {
			System.out.println("Before deletion: " + h.toString());
			int arraySize = h.get(0);
			int root = h.get(1);
			System.out.println("element deleted: " + root);
			h.set(1, h.get(arraySize));
			h.set(0, arraySize - 1);
			h.remove(arraySize);
			heapify(h, 1);
			System.out.println("After deletion: " + h.toString());

		}
	}
}
