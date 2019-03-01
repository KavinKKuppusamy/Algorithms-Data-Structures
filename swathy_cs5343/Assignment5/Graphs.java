public class Graphs {

	int v, e;
	Edge edge[];

	public Graphs(int v, int e) {
		this.v = v;
		this.e = e;
		this.edge = new Edge[e];

		for (int i = 0; i < e; i++) {
			this.edge[i] = new Edge();
		}

	}

}

class Edge {

	int s;
	int d;
	int w;

	public void add(int s, int d, int w) {
		this.s = s;
		this.d = d;
		this.w = w;
	}

}