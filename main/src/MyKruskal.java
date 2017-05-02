package src;

/** 
 * This class is based on Kruskal's minimum spanning tree
 * algorithm. It has been extended to calculate the MST of each
 * disconnected graph at the same time. The trick is to take advantage
 * of the fact that Kruskal's algorithm combines "_clouds" when it
 * builds its trees. Thus we can connect the _clouds of these
 * disconnect graphs using the standard algorithm. The only
 * modification to the original algorithm is specifying the
 * termination case. Since the graphs can be disconnected, we can not
 * stop the algorithm once we have n-1 edges in our MST.
 *
 * See the handout for an explanation of Kruskal's algorithm. This also
 * makes heavy use of the decorable pattern, so make sure you know it cold.
 *
 */

import support.graph.*;

import net.datastructures.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;

public class MyKruskal<V> implements MinSpanForest<V> {

	AdjacencyMatrixGraph<V> g;
	CS16GraphVisualizer<V> visualizer;
	CS016AdaptableHeapPriorityQueue<Integer, CS16Edge<V>> HQ;
	MyDecorator<CS16Vertex<V>, Integer> _ranks;
	MyDecorator<CS16Vertex<V>, CS16Vertex<V>> _parents;
	
	Iterator<CS16Vertex<V>> _vertices;

	public MyKruskal() {
		_ranks = new MyDecorator<CS16Vertex<V>, Integer>();
		_parents = new MyDecorator<CS16Vertex<V>, CS16Vertex<V>>();
		
		_vertices = g.vertices();
	}

	/**
	 * This method implements Kruskal's algorithm and extends it slightly to
	 * account for disconnected graphs. You must return the collection of edges
	 * of the Minimum Spanning Forest (MSF) for the given graph, g.
	 * 
	 * This must run in O(|E|log|V|) time.
	 * 
	 * @param g
	 *            Your graph (created in MyGraph)
	 * @param visualizer
	 *            Only used if you implement the optional animation.
	 * @return returns a data structure that contains the edges of your MSF that
	 *         implements java.util.Collection
	 */
	@Override
	public Collection<CS16Edge<V>> genMinSpanForest(AdjacencyMatrixGraph<V> g, CS16GraphVisualizer<V> visualizer) {
		this.g = g;
		this.visualizer = visualizer;
		HQ = new CS016AdaptableHeapPriorityQueue<Integer, CS16Edge<V>>();

		// put each edge into a adaptable heap priority queue
		Iterator<CS16Edge<V>> edges = g.edges();
		while (edges.hasNext()) {
			HQ.insert(edges.next().element(), edges.next());
		}

		// process each vertex in the queue
		Collection<CS16Edge<V>> edgeList = new ArrayList<CS16Edge<V>>();

		// pop min edge
		while (HQ.size() > 0) {
			CS16Edge<V> edge = HQ.removeMin().getValue();
			// get opposite vertices
			CS16Vertex<V> toVertex = edge.getToVertex();
			CS16Vertex<V> fromVertex = edge.getFromVertex();
			// if vertices are in different clouds, unite them and add vertex to
			// return variable
			

			// TODO -- need unionFind algo

		}

		return edgeList;
	}

	private void makeSet() {
		// put each vertex in it's own cloud (use decorator for ranks and parents)
		
		while (_vertices.hasNext()) {
			CS16Vertex<V> vertex = _vertices.next();
			_ranks.setDecoration(vertex, 0);
			_parents.setDecoration(vertex, vertex);
			
		}
		
		

	}

}
