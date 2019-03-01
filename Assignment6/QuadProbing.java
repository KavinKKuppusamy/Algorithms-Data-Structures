import java.util.Arrays;

public class QuadProbing {

	int hashTableSize;
	String[] stringArray;
	int totalElements;
	float loadfactor;
	int totalCollisions;
	int totalRehash;

	public void init(int ns) {
		this.hashTableSize = ns;
		this.stringArray = new String[this.hashTableSize];
		totalCollisions = 0;
		totalElements = 0;
		totalRehash = 0;
	}

	public void insert(String val) throws Exception {

		boolean isDuplicate = false;
		int j = 0, newInd;
		while (true) {
			newInd = hashingFn(val, hashTableSize, j);
			if ((this.stringArray[newInd] != null) && (this.stringArray[newInd].equalsIgnoreCase(val))) {
				System.out.println("Duplicate Entry : " + val);
				isDuplicate = true;
				break;
			} else if (this.stringArray[newInd] == null) {
				this.stringArray[newInd] = val;
				System.out.println("value: " + val + ", index: " + newInd);
				break;
			}
			j++;
		}

		if (j > 0) {
			totalCollisions = totalCollisions + j;
		}

		if (!isDuplicate) {
			this.totalElements++;
		}

	}

	public void checkNinsert(String val) throws Exception {
		loadfactor = (float) (this.totalElements) / this.hashTableSize;
		if (loadfactor < 0.5) {
			insert(val);
		} else {
			System.out.println("***Load factor has reached 0.5, so increasing table size & rehashing***");
			rehash();
			insert(val);
		}

	}

	public void rehash() throws Exception {
		this.totalRehash++;
		int newTableSize = nextDoublePrime(hashTableSize);
		this.totalElements = 0;
		String oldArray[] = this.stringArray;
		this.stringArray = new String[newTableSize];
		this.hashTableSize = newTableSize;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != null) {
				insert(oldArray[i]);
			}
		}
		System.out.println("Completed rehashing with new table size: " + newTableSize);
	}

	public int hashingFn(String str, int tableSize, int n) throws Exception {
		int s = 0, index;

		byte[] bytes = str.getBytes("US-ASCII");
		for (int i = 0; i < bytes.length; i++) {
			s = s + bytes[i];
		}
		index = (s + (n * n)) % tableSize;

		return index;
	}

	public int nextDoublePrime(int n) {

		n = 2 * n;

		boolean isPrime = true;

		while (true) {
			n++;
			isPrime = true;
			for (int i = 2; i < n; i++) {
				if (n % i == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				break;
			}
		}

		return n;
	}

	public static void main(String[] args) {

		try {

			QuadProbing obj = new QuadProbing();
			obj.init(31);

			String arr[] = { "A3 14", "B 123", "CAT 678", "FALL18", "HOME", "ECS 421", "Swa GEA", "images123",
					"Google 20", "Windows98", "TexasAM", "GoldMan 9", "Eagle 45", "DjikSTRAS", "BellMan", "Eugene",
					"N Tesla", "Turning", "MarieCurie", "R Bayer" };

			for (int i = 0; i < arr.length; i++) {
				obj.checkNinsert(arr[i]);
			}

			System.out.println("\n**************");
			System.out.println("Total Collisions: " + obj.totalCollisions);
			System.out.println("Final table size: " + obj.hashTableSize);
			System.out.println("Total Elements: " + obj.totalElements);
			System.out.println("Total Rehash: " + obj.totalRehash);
			System.out.println("Final elements in table:\n " + Arrays.toString(obj.stringArray));

		} catch (Exception e) {
			System.out.println("Error Occured: " + e.getMessage());
		}

	}

}
