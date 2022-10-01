// Java program to find a mother
// vertex in O(V+E) time
import java.util.*;

class GFG {

	static void addEdge(int u, int v,
						ArrayList<ArrayList<Integer> > adj)
	{
		adj.get(u).add(v);
	}

	// A recursive function to print DFS starting from v
	static void DFSUtil(ArrayList<ArrayList<Integer> > g,
						int v, boolean[] visited)
	{
		// Mark the current node as
		// visited and print it
		visited[v] = true;

		// Recur for all the vertices
		// adjacent to this vertex
		for (int x : g.get(v)) {
			if (!visited[x]) {
				DFSUtil(g, x, visited);
			}
		}
	}

	// Returns a mother vertex if exists.
	// Otherwise returns -1
	static int
	motherVertex(ArrayList<ArrayList<Integer> > g, int V)
	{

		// visited[] is used for DFS. Initially
		// all are initialized as not visited
		boolean[] visited = new boolean[V];

		// To store last finished vertex
		// (or mother vertex)
		int v = -1;

		for (int i = 0; i < V; i++) {
			if (!visited[i]) {
				DFSUtil(g, i, visited);
				v = i;
			}
		}

		// If there exist mother vertex (or vertices)
		// in given graph, then v must be one
		// (or one of them)

		// Now check if v is actually a mother
		// vertex (or graph has a mother vertex).
		// We basically check if every vertex
		// is reachable from v or not.

		// Reset all values in visited[] as false
		// and do DFS beginning from v to check
		// if all vertices are reachable from
		// it or not.
		boolean[] check = new boolean[V];
		DFSUtil(g, v, check);
		for (boolean val : check) {
			if (!val) {
				return -1;
			}
		}
		return v;
	}

	// Driver code
	public static void main(String[] args)
	{
		int V = 7;
		int E = 8;

		ArrayList<ArrayList<Integer> > adj
			= new ArrayList<ArrayList<Integer> >();
		for (int i = 0; i < V; i++) {
			adj.add(new ArrayList<Integer>());
		}
		addEdge(0, 1, adj);
		addEdge(0, 2, adj);
		addEdge(1, 3, adj);
		addEdge(4, 1, adj);
		addEdge(6, 4, adj);
		addEdge(5, 6, adj);
		addEdge(5, 2, adj);
		addEdge(6, 0, adj);

		// Function call
		System.out.println("A mother vertex is "
						+ motherVertex(adj, V));
	}
}
