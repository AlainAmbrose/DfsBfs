import java.util.*;
import java.io.*;
import java.text.*;

public class dfsbfs {
	public static HashMap<String, Integer> map;
	public static List<List<Integer>> graph;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numCases = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < numCases; i++) {
			int numNodes = Integer.parseInt(scanner.nextLine());
			int count = 0;
			map = new HashMap<>();
			graph = new ArrayList<>();
			for (int j = 0; j < numNodes; j++) {
				String[] newNodeArray = scanner.nextLine().split(" ");
				ArrayList<Integer> edgeArray = new ArrayList<>();
				for (int k = 0; k < newNodeArray.length; k++) {
					String newNode = newNodeArray[k];
					if (!map.containsKey(newNode)){
						map.put(newNode, count);
						count++;
					}
					if (k > 0)
						edgeArray.add(map.get(newNode));
				}
				graph.add(edgeArray);
			}

			dfs(0, new boolean[numNodes]);
			System.out.println("------------");
			bfs(0, numNodes);
		}
	}

	public static void dfs(int vertex, boolean[] visited) {
		System.out.println(graph.get(vertex));
		visited[vertex] = true;
		for (int i = 0; i < graph.get(vertex).size()+1; i++) {
			if (!visited[i]) {
				dfs(i, visited);
			}
		}
	}

	public static int[] bfs(int start, int numNodes) {
		int[] distances = new int[numNodes];
		Arrays.fill(distances, -1);
		distances[start] = 0;

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(start);

		while (!q.isEmpty()) {
			int curr = q.poll();
			System.out.println(graph.get(curr));

			for (int i = 0; i < graph.get(curr).size(); i++) {
				int next = graph.get(curr).get(i);
				if (distances[next] == -1) {
					q.offer(next);
					distances[next] = distances[curr]+1;
				}
			}

		}
		return distances;
	}
}
