import java.util.Random;

public class LinkedList {

	class Node {
		int data;
		Node next;

		Node(int val) {
			this.data = val;
			this.next = null;
		}
	}

	Node head;

	public void insert(int data) {
		Node newNode = new Node(data);

		if (head == null) {
			head = newNode;
		}

		else {
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
		}

	}

	public void traversal() {
		System.out.println("******");
		Node tTemp = head;
		while (tTemp != null) {
			System.out.println(tTemp.data);
			tTemp = tTemp.next;
		}
		System.out.println("******");
	}

	public void llistSorting() throws Exception {

		Node min, current2, minPrev = null, current1 = null, current1Prev = null;

		current1 = head;

		int sortingLoopCount = 0;

		while (current1.next != null) {

			// maintaining sorting loop count
			sortingLoopCount++;

			min = current1;

			// sorting logic starts
			current2 = current1;
			while (current2.next != null) {
				if (min.data > current2.next.data) {
					minPrev = current2;
					min = current2.next;
				}
				current2 = current2.next;
			}

			// swapping node - current1 and min starts
			if (min != current1) {

				if (current1Prev != null) {
					current1Prev.next = min;
				} else {
					head = min;
				}

				if (minPrev != null) {
					minPrev.next = current1;
				} else {
					head = current1;
				}

				Node temp = current1.next;
				current1.next = min.next;
				min.next = temp;

			}
			// swapping node - ends

			// sorting logic ends

			System.out.println("Sorting loop count: " + sortingLoopCount);
			traversal();

			current1Prev = min;
			current1 = min.next;
		}
	}

	public static void main(String[] args) throws Exception {

		Random rn = new Random();

		LinkedList ls = new LinkedList();
		for (int i = 0; i < 15; i++) {
			ls.insert(rn.nextInt(999));
		}

		System.out.println("Traverse List in the given order");
		ls.traversal();

		ls.llistSorting();

		System.out.println("List Traversal After sorting");
		ls.traversal();

	}

}
