package src;

/**
 * In this class you will implement a slightly modified version
 * of the Prim-Jarnik algorithm for generating Minimum Spanning trees.
 * The original version of this algorithm will only generate the 
 * minimum spanning tree (#brendilea) of the connected vertices in a graph, given
 * a starting vertex. Like Kruskal's, this algorithm can be modified to 
 * produce a minimum spanning forest with very little effort.
 *
 * See the handout for details on Prim-Jarnik's algorithm.
 * Like Kruskal's algorithm this algorithm makes extensive use of 
 * the decorable pattern, so make sure you know it.
 */

import net.datastructures.*;
import support.graph.*;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

public class MyPrimJarnik<V> implements MinSpanForest<V> {
	

	/**
	 * This method implements Prim-Jarnik's algorithm and extends it slightly to
	 * account for disconnected graphs. You must return the collection of edges
	 * of the Minimum Spanning Forest (MSF) for the given graph, g.
	 * 
	 * This algorithm must run in O((|E| + |V|)log(|V|)) time
	 * 
	 * @param g
	 *            Your graph (created in MyGraph)
	 * @param v
	 *            Only used if you implement the optional animation.
	 * @return returns a data structure that contains the edges of your MSF that
	 *         implements java.util.Collection
	 */
	
	// each vertex's cloud
	private MyDecorator<CS16Vertex<V>, Integer> _clouds;
	// vertices in a cloud
	private MyDecorator<Integer, ArrayList<CS16Vertex<V>>> _cloudVertices;

	@Override
	public Collection<CS16Edge<V>> genMinSpanForest(AdjacencyMatrixGraph<V> g, CS16GraphVisualizer<V> visualizer) {
		_clouds = new MyDecorator<CS16Vertex<V>, Integer>();
		_cloudVertices = new MyDecorator<Integer, ArrayList<CS16Vertex<V>>>();
		
		Collection<CS16Edge<V>> edgeList = new ArrayList<CS16Edge<V>>();
		// add all edges to the minimum heap priority queue
		CS016AdaptableHeapPriorityQueue<Integer, CS16Edge<V>> HQ = new CS016AdaptableHeapPriorityQueue<Integer, CS16Edge<V>>();
		Iterator<CS16Edge<V>> edges = g.edges();
		while (edges.hasNext()){
		CS16Edge<V> next = edges.next();
			HQ.insert(next.element(), next);
		}

		// decorate each vertex with a cloud
		Iterator<CS16Vertex<V>> vertices = g.vertices();
		int count = 0;
		while (vertices.hasNext()){
			CS16Vertex<V> nextVertex = vertices.next();
			_clouds.setDecoration(nextVertex, count);
			// create arrayList for decoration of _cloudVertices
			// +! decorate each cloud with a list of vertices
			ArrayList<CS16Vertex<V>> vertexList = new ArrayList<CS16Vertex<V>>();
			vertexList.add(nextVertex);
			_cloudVertices.setDecoration(count, vertexList);
			count++;
		}
		

		// iterate on edges
		while (HQ.size() != 0){
			CS16Edge<V> edge = HQ.removeMin().getValue();
			// for each edge, if vertices are in differnt clouds, combine the vertices
			CS16Vertex<V> toVertex = edge.getToVertex();
			CS16Vertex<V> fromVertex = edge.getFromVertex();
			// use helper method 
			cloudCombine(toVertex, fromVertex);
			// two clouds enter, one cloud emerges.
			

		}
		
		// party
		return edgeList;
	}
	
	private void cloudCombine(CS16Vertex<V> toVertex, CS16Vertex<V> fromVertex){
		int toVertexDecortation = _clouds.getDecoration(toVertex);
		int fromVertexDecortation = _clouds.getDecoration(fromVertex);
		
		if (true){
			
		}
		
	}
	
	
}
