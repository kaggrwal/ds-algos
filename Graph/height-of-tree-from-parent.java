import java.io.*;
import java.util.*;

class GFG
{
	static int MAX = 1001;

	// Adjacency list to
	// store N-ary tree
	static ArrayList<ArrayList<Integer>> adj =
	new ArrayList<ArrayList<Integer>>();
	
	// Build tree in tree in O(n)
	static int build_tree(int arr[], int n)
	{
		int root_index = 0;

		// Iterate for all nodes
		for (int i = 0; i < n; i++)
		{

			// if root node, store index
			if (arr[i] == -1)
				root_index = i;

			else
			{
				adj.get(i).add(arr[i]);
				adj.get(arr[i]).add(i);
			}
		}
		return root_index;
	}
	
	// Applying BFS
	static int BFS(int start)
	{
	
		// map is used as visited array
		Map<Integer, Integer> vis = new HashMap<Integer, Integer>();
		ArrayList<ArrayList<Integer>> q = new ArrayList<ArrayList<Integer>>();
		int max_level_reached = 0;

		// height of root node is zero
		q.add(new ArrayList<Integer>(Arrays.asList(start, 0 )));
	
		// p.first denotes node in adjacency list
		// p.second denotes level of p.first
		ArrayList<Integer> p = new ArrayList<Integer>();
		while(q.size() != 0)
		{
			p = q.get(0);
			vis.put(p.get(0),1);
		
			// store the maximum level reached
			max_level_reached = Math.max(max_level_reached,p.get(1));
			q.remove(0);
			for(int i = 0; i < adj.get(p.get(0)).size(); i++)
			{
			
				// adding 1 to previous level
				// stored on node p.first
				// which is parent of node adj[p.first][i]
				// if adj[p.first][i] is not visited
				if(!vis.containsKey(adj.get(p.get(0)).get(i)))
				{
					q.add(new ArrayList<Integer>(Arrays.asList(adj.get(p.get(0)).get(i), p.get(1)+1)));
				}
			}
		}
		return max_level_reached;
	}

	// Driver Function
	public static void main (String[] args)
	{
		for(int i = 0; i < MAX; i++)
		{
			adj.add(new ArrayList<Integer>());
		}
		
		// node 0 to node n-1
		int parent[] = { -1, 0, 1, 2, 3 };
		
		// Number of nodes in tree
		int n = parent.length;
		int root_index = build_tree(parent, n);
		int ma = BFS(root_index);
		System.out.println( "Height of N-ary Tree=" + ma);
	}
}
