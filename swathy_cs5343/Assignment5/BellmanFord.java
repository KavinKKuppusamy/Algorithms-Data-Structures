
public class BellmanFord {

	public void bellmanSFT(Graphs graph, int source) {

		Integer[] parentArr = new Integer[graph.v];
		Integer[] distArr = new Integer[graph.v];

		for (int i = 0; i < graph.v; i++) {
			distArr[i] = Integer.MAX_VALUE;
			parentArr[i] = -1;
		}

		distArr[source] = 0;

		for (int i = 0; i < graph.v - 1; i++) {
			for (int j = 0; j < graph.e; j++) {
				{
					int s = graph.edge[j].s, d = graph.edge[j].d, w = graph.edge[j].w;

					if (distArr[s] != Integer.MAX_VALUE && distArr[d] > distArr[s] + w) {
						distArr[d] = distArr[s] + w;
						parentArr[d] = s;
					}
				}

			}
		}

		// start - to detect negative cycle
		boolean noNegative = true;
		for (int j = 0; j < graph.e; j++) {
			int s = graph.edge[j].s, d = graph.edge[j].d, w = graph.edge[j].w;
			if (distArr[s] != Integer.MAX_VALUE && distArr[d] > distArr[s] + w) {

				System.out.println("Given graph contains negative cycle");
				noNegative = false;
				break;
			}
		}
		// end - to detect negative cycle

		if (noNegative) {
			System.out.println("Vertex : previously Visited Vertex : Distance from source");
			for (int i = 0; i < graph.v; i++) {
				System.out.println(i + ":" + parentArr[i] + ":" + distArr[i]);
			}
		}

	}

	public static void main(String[] args) {

		BellmanFord obj = new BellmanFord();

		System.out.println("Graph without negative cycle");
		Graphs g1 = obj.graphNoNegativeCycle();
		obj.bellmanSFT(g1, 0);

		System.out.println("\n***************\n");

		System.out.println("\nGraph with negative cycle");
		Graphs g2 = obj.graphNegativeCycle();
		obj.bellmanSFT(g2, 0);

	}

	public Graphs graphNegativeCycle() {

		Graphs gh = new Graphs(11, 16);

		gh.edge[0].add(0, 3, 1);
		gh.edge[1].add(0, 4, 2);
		gh.edge[2].add(0, 5, -9);
		gh.edge[3].add(4, 8, 2);
		gh.edge[4].add(4, 5, 1);
		gh.edge[5].add(5, 6, 12);
		gh.edge[6].add(5, 7, -2);
		gh.edge[7].add(6, 7, 1);
		gh.edge[8].add(1, 0, 2);
		gh.edge[9].add(1, 3, 3);
		gh.edge[10].add(2, 1, -6);
		gh.edge[11].add(2, 9, 1);
		gh.edge[12].add(2, 10, 12);
		gh.edge[13].add(9, 10, 3);
		gh.edge[14].add(3, 2, 2);
		gh.edge[15].add(8, 6, -14);

		return gh;
	}

	public Graphs graphNoNegativeCycle() {
		Graphs gh = new Graphs(11, 19);
		gh.edge[0].add(0, 4, 2);
		gh.edge[1].add(0, 5, -9);
		gh.edge[2].add(4, 8, -2);
		gh.edge[3].add(4, 5, 1);
		gh.edge[4].add(1, 3, 2);
		gh.edge[5].add(1, 0, 2);
		gh.edge[6].add(2, 1, 6);
		gh.edge[7].add(2, 3, 3);
		gh.edge[8].add(2, 9, 1);
		gh.edge[9].add(2, 10, 12);
		gh.edge[10].add(9, 10, 16);
		gh.edge[11].add(3, 10, 6);
		gh.edge[12].add(5, 6, -12);
		gh.edge[13].add(5, 7, -2);
		gh.edge[14].add(6, 7, -13);
		gh.edge[15].add(8, 6, -14);
		gh.edge[16].add(0, 3, 1);
		gh.edge[17].add(10, 1, 6);
		gh.edge[18].add(0, 2, 1);
		return gh;
	}
}
