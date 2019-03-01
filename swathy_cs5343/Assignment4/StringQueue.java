public class StringQueue {

	int size = 0;

	class Node {
		String val;
		Node next;

		Node(String x) {
			val = x;
			next = null;
		}
	}

	Node front, rear;

	public void enqueue(String val) {
		Node n = new Node(val);
		size++;
		if (front == null) {
			front = n;
			rear = front;
		} else {
			rear.next = n;
			rear = n;
		}
	}

	public String dequeue() {
		if (front == null) {
			return null;
		} else {
			size--;
			String retVal = front.val;
			front = front.next;
			return retVal;
		}
	}

}