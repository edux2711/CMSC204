import java.util.*;

public class Graph implements GraphInterface<Town, Road>{

	//private ArrayList<LinkedList<Town>> graph;
	private Map<Town, Set<Road>> graph;
	private int size;
	
	public Graph(int size) {
		this.size = size;
		graph = new HashMap<>(size);
	}
	
	public Graph() {
		this(0);
	}

	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		if(sourceVertex == null || destinationVertex == null) {
			return null;
		}
		else if(!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex)) {
			return null;
		} 
		else if (sourceVertex.compareTo(destinationVertex) == 0) {
			return null;
		}
		
		//Loop through each edge in the set mapped to sourceVertex key.
		//If the set mapped to destinationVertex contains any of the edge in the sourceVertex set then return it.
		for(Road r1 : graph.get(sourceVertex)) {
			if(graph.get(destinationVertex).contains(r1))
				return r1;
		}
		
		//Otherwise return null.
		return null;
	}

	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		//If one of the vertices are not in the graph, throw exception.
				
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		} 
		
		//Else if, any of the vertices is null throw exception.
		else if(!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		
		//Otherwise, create the edge, add it to the graph, and return the edge.
		else {
			//Since there is an edge between sourceVertex and destinationVertex, they are adjacent and therefore will
			//be stored in the adjacentTowns field of each vertex
						
			Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
	       
			graph.get(sourceVertex).add(newRoad);
	     	graph.get(destinationVertex).add(newRoad);
	     	
	     	sourceVertex.getAdjacentTowns().add(destinationVertex);
	     	destinationVertex.getAdjacentTowns().add(sourceVertex);

			//System.out.println(sourceVertex.getAdjacentTowns().size());

	        return newRoad;
		}
			
		
	}

	 /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		//System.out.println("1");

		//If this graph already contains the vertex, the call
	    //leaves this graph unchanged and returns false.
		if(v == null) {
			throw new NullPointerException();
		}
		else if(graph.containsKey(v)) {
			return false;
		} 
		else {
			graph.put(v, new HashSet<>());
			return true;
		}
	}

	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
		//If the vertices are null return null.
		if(sourceVertex == null || destinationVertex == null) {
			return false;
		}
		//If the vertices are not in the graph, return null.
		else if(!graph.containsKey(sourceVertex) || !graph.containsKey(destinationVertex)) {
			return false;
		}
		
		//Road road = new Road()
		/*!!!!!!!!
		 * */ 
		//Otherwise, call getEdge. If it returns a null then the graph does not contain the edge.
		if(getEdge(sourceVertex, destinationVertex) == null)
			return false;
		else 
			return true;

	}

	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		if(graph.containsKey(v) == true) 
			return true;
		else
			return false;
	}

	 /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		
		//Create result set to hold values
		Set<Road> result = new HashSet<>();
		
		//Loop through each key and add the set mapped to every key to result.
		for(Town town : graph.keySet()) {
			result.addAll(graph.get(town));
		}

		//Return result.
		return result;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		
		if(vertex == null)
			throw new NullPointerException();
		if(containsVertex(vertex) == false)
			throw new IllegalArgumentException();
		
		//Create result set to hold values.
		Set<Road> result = new HashSet<>();
				
		//Add all the values of the set mapped to the vertex key.
		result.addAll(graph.get(vertex));

		//Return result.
		return result;
	}

	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		if(containsVertex(sourceVertex) == false && containsVertex(destinationVertex) == false
												 &&
						 containsEdge(sourceVertex, destinationVertex) == false) {
			return null;
		}
		
		Road removedEdge = getEdge(sourceVertex, destinationVertex);
		
		graph.get(sourceVertex).remove(removedEdge);
		graph.get(destinationVertex).remove(removedEdge);

		

//		boolean j = graph.get(sourceVertex).contains(removedEdge);
		return removedEdge;
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		if(v == null)
			return false;
		
		for(Town t : v.getAdjacentTowns()) {
			//System.out.println(t);
			removeEdge(v, t, 0, null);
		}
		
		//removeEdge()
		
		if(graph.remove(v) == null) {
			return false;
		}
		else
			return true;
		
		
	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		return graph.keySet();
	}

	/**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */  
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		return null;
	}

	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		Set<Town> closed = new TreeSet<>();
		Set<Town> open = new HashSet<>();
		ArrayList<Integer> mpl = new ArrayList<>();
		
		open.addAll(vertexSet());
		open.remove(sourceVertex);
		closed.add(sourceVertex);
		mpl.add(0);
		
		System.out.println(open + "\n");
		
		while(!open.isEmpty()) {
			int minWeight = Integer.MAX_VALUE;
			Town minAdjTown = null;
			System.out.println("hey now");

			for(Town town : closed) {
				System.out.println("::''''::" + closed + "::''''':::");
				System.out.println("Town: " + town);
				
				for(Town adjTown : getAdjVerticesInSet(town, open)) {
					System.out.println("adjTown: " + adjTown);
					
					int wt = getWtToSource(adjTown, town, sourceVertex);
					if(wt < minWeight) {
						System.out.println("wt: " + wt + "   " + "minWt: " + minWeight);
						minWeight = wt;
						minAdjTown = adjTown;
						adjTown.setBackPath(town);
					}
				}
			}
			
			if(minAdjTown != null) {
				//minAdjTown.setWeight(minWeight);
				//System.out.println(minAdjTown);

				mpl.add(minWeight);
				open.remove(minAdjTown);
				closed.add(minAdjTown);
				System.out.println("!!!!!!!!!!!!!!!!!closed:" + closed + "\n");
				
				System.out.println("open:" + open + "\n");

			}
			
		}
		
		for(Integer i : mpl) {
			System.out.println(i);
		}
		
		
	}

	
	public Set<Town> getAdjVerticesInSet(Town town, Set<Town> set) {
		Set<Town> result = new HashSet<>();
		
		//System.out.println(set);
		result.addAll(set);
		result.retainAll(town.getAdjacentTowns());

		//System.out.println(set);
		return result;
	}
	
	public int getWtToSource(Town adjTown, Town town, Town sourceVertex) {
		int result = -1;
		
		//System.out.println(getEdge(t1, t3).getWeight());
		//System.out.println();
		

		System.out.println("\nedge: " + getEdge(adjTown, town) + "\n");
//		System.out.println();
		
		if(getEdge(town, sourceVertex) == null)
			return getEdge(adjTown, town).getWeight();
		
//		else if(adjTown.getBackPath() != sourceVertex) {
//			System.out.println("Parent: "+adjTown.getBackPath());
//			return getEdge(adjTown, town).getWeight() + getEdge(adjTown.getBackPath(), town).getWeight();// + getEdge(adjTown.getBackPath(), sourceVertex).getWeight();
//		}
		
		System.out.println("jajajajajajjajajajajajajajjajjajjajajajajajaj: " + getEdge(town, sourceVertex).getWeight());

		return getEdge(adjTown, town).getWeight() + getEdge(town, sourceVertex).getWeight();

		
	}

}
