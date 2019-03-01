import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GraphTraversal {

	class Graph {

		int nvertex, nedges;
		Map<String, ArrayList<String>> adjacencyList;
		Map<String, Boolean> visitedVertices;
		Set<String> allVerticesList;

		public Graph(int vertex) {
			this.nvertex = vertex;
			adjacencyList = new HashMap<>();
			allVerticesList = new HashSet<>();
		}

		public void initializeVisitedVertices() {
			visitedVertices = new HashMap<>();
			for (String s : allVerticesList) {
				visitedVertices.put(s, false);
			}
		}

		public void addEdge(String source, String destination) {
			ArrayList<String> destList = null;
			allVerticesList.add(destination);
			allVerticesList.add(source);

			// adjacency list = source -> destination
			if (adjacencyList.containsKey(source)) {
				destList = adjacencyList.get(source);
				destList.add(destination);
				adjacencyList.put(source, destList);
			} else {
				destList = new ArrayList<>();
				destList.add(destination);
				adjacencyList.put(source, destList);
			}

			// adjacency list = destination -> source
			if (adjacencyList.containsKey(destination)) {
				destList = adjacencyList.get(destination);
				destList.add(source);
				adjacencyList.put(destination, destList);
			} else {
				destList = new ArrayList<>();
				destList.add(source);
				adjacencyList.put(destination, destList);
			}
		}

		void bfs(String vertx) {
			System.out.println("\n*******************************");
			System.out.println("BFS TRAVERSAL from vertex - " + vertx);
			System.out.println("Start Vertex- " + vertx);
			initializeVisitedVertices();
			StringQueue queue = new StringQueue();
			visitedVertices.put(vertx, true);
			queue.enqueue(vertx);

			while (queue.size != 0) {
				vertx = queue.dequeue();
				System.out.print(vertx + ",");
				Iterator<String> i = adjacencyList.get(vertx).listIterator();
				while (i.hasNext()) {
					String n = i.next();
					if (!visitedVertices.get(n)) {
						visitedVertices.put(n, true);
						queue.enqueue(n);
					}
				}
			}
		}

		public void printAdjacencyList() {
			System.out.println("Printing adjacency list of given undirected graph");
			for (Map.Entry<String, ArrayList<String>> entry : adjacencyList.entrySet()) {
				System.out.println(entry.getKey() + " -> " + entry.getValue().toString());
			}
		}

		public void dfs(String startVertex) {
			initializeVisitedVertices();
			System.out.println("\n\n*******************************");
			System.out.println("DFS TRAVERSAL from vertex : " + startVertex);
			System.out.println("Start Vertex- " + startVertex);
			nodeDFS(startVertex);

			// check the nodes that are not visited
			for (String vx : allVerticesList) {
				if (!visitedVertices.get(vx)) {
					nodeDFS(vx);
				}
			}

		}

		public void nodeDFS(String vertex) {
			visitedVertices.put(vertex, true);
			System.out.print(vertex + ',');

			Iterator<String> i = adjacencyList.get(vertex).listIterator();
			while (i.hasNext()) {
				String n = i.next();
				if (!visitedVertices.get(n)) {
					nodeDFS(n);
				}
			}

		}
	}

	public static void main(String[] args) {

		GraphTraversal obj = new GraphTraversal();

		Graph graph = obj.new Graph(11);
		graph.addEdge("A", "B");
		graph.addEdge("A", "D");
		graph.addEdge("A", "G");
		graph.addEdge("A", "F");
		graph.addEdge("B", "E");
		graph.addEdge("B", "C");
		graph.addEdge("B", "H");
		graph.addEdge("C", "F");
		graph.addEdge("G", "H");
		graph.addEdge("G", "J");
		graph.addEdge("H", "I");
		graph.addEdge("I", "L");
		graph.addEdge("J", "K");
		graph.addEdge("J", "L");
		graph.addEdge("K", "L");
		graph.addEdge("K", "I");

		graph.printAdjacencyList();
		graph.bfs("A");
		graph.dfs("A");

	}

}
