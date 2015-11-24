/**
 * See bottom of file for sample graphs
 */

package graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph 
{
	public class Neighbor 
	{
		public int vnum;
		public Neighbor next;
		
		public Neighbor(int vnum, Neighbor nbr) 
		{
			this.vnum = vnum;
			next = nbr;
		}
	}

	class Vertex 
	{
		String name;
		Neighbor neighbors;
		
		Vertex(String name, Neighbor neighbors) 
		{
			this.name = name;
			this.neighbors = neighbors;
		}
	}

	Vertex[] adjLists;
	
	/**
	 * Initialize graph with input from file
	 * 
	 * @param file File that contains graph input
	 * @throws FileNotFoundException If file is not found
	 */
	public Graph(String file) throws FileNotFoundException 
	{
		Scanner sc = new Scanner(new File(file));
		boolean directed = false;
		
		if (sc.next().equals("directed")) 
		{
			directed = true;
		}
		
		adjLists = new Vertex[sc.nextInt()];
		
		// read vertices
		for (int v=0; v < adjLists.length; v++) {
			adjLists[v] = new Vertex(sc.next(), null);
		}
		
		// read edges
		while (sc.hasNext()) 
		{
			int v1 = indexForName(sc.next());
			int v2 = indexForName(sc.next());
			
			adjLists[v1].neighbors = new Neighbor(v2, adjLists[v1].neighbors);
			
			// if undirected graph, need to add edge pointing the opposite way as well
			
			if (!directed) {
				adjLists[v2].neighbors = new Neighbor(v1, adjLists[v2].neighbors);
			}
		}
	}
	
	public void dfs() 
	{
		boolean[] visited = new boolean[adjLists.length];
		
		for (int v=0; v < visited.length; v++) 
		{
			visited[v] = false;
		}
		
		for (int v=0; v < visited.length; v++) 
		{
			if (!visited[v]) 
			{
				System.out.println("Starting at " + adjLists[v].name);
				dfs(v, visited);
			}
		}
	}
	
	// recursive dfs
	private void dfs(int v, boolean[] visited) 
	{
		visited[v] = true;
		System.out.println("visiting " + adjLists[v].name);
		
		for (Neighbor e=adjLists[v].neighbors; e != null; e=e.next) 
		{
			if (!visited[e.vnum]) 
			{
				System.out.println(adjLists[v].name + "--" + adjLists[e.vnum].name);
				dfs(e.vnum, visited);
			}
		}
	}
	
	public void dfsTopsort() 
	{
		boolean[] visited = new boolean[adjLists.length];
		
		for (int v=0; v < visited.length; v++) 
		{
			visited[v] = false;
		}
		
		int[] topsort = new int[adjLists.length];  // topsort[i] will be the vertex that has topological num i
		int topNum = adjLists.length-1;    // start with the largest possible top num 
				
		for (int v=0; v < visited.length; v++) 
		{
			if (!visited[v]) 
			{
				topNum = dfsTopsort(v, visited, topsort, topNum);
			}
		}
	}
	
	// recursive dfs topsort
	private int dfsTopsort(int v, boolean[] visited, int[] topsort, int topNum) 
	{
		visited[v] = true;
		
		for (Neighbor e=adjLists[v].neighbors; e != null; e=e.next) 
		{
			if (!visited[e.vnum]) 
			{
				topNum = dfsTopsort(e.vnum, visited, topsort, topNum);
			}
		}
		// assign topological number just before backtracking
		// slot this vertex in the spot of its top num
		topsort[topNum] = v;
		// update top num and return
		return topNum-1;
	}
		
	int indexForName(String name) 
	{
		for (int v=0; v < adjLists.length; v++) 
		{
			if (adjLists[v].name.equals(name)) 
			{
				return v;
			}
		}
		
		return -1;
	}
	
	// see the Eclipse page, "Setting up program arguments", for how to send in
	// the graph file name as an argument when you run the program
	public static void main(String[] args) throws FileNotFoundException 
	{
		Graph graph = new Graph(args[0]);
		graph.dfs();
	}
	
}

/**
graph1.txt
----------  
undirected
6
A
B
C
D
E
F
A B
B C
B D
C D
C E
D E
D F
E F
-----------

graph2.txt
-----------
directed
5
A
B
C
D
E
A B
B C
D A
E C
-----------
 */ 
