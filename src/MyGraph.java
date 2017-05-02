package src;

/** 
 * This class implements a Graph interface that tracks its edges

 * through the use of an adjacency matrix. Please review the lecture
 * slides and the handout before attempting to write this program.
 *
 * Good luck, and as always, start early, start today, start
 * yesterday!
 *
 * @see support.graph.GraphEdge
 * @see support.graph.GraphVertex
 *
 */

import support.graph.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.datastructures.Position;

import static support.graph.Constants.MAX_VERTICES;
import java.util.List;

public class MyGraph<V> implements AdjacencyMatrixGraph<V> {

	// The underlying data structure of your graph: the adjacency matrix
	private CS16Edge<V>[][] _adjMatrix;

	// Linked lists to store the vertices and edges of your graph
	private NodeSequence<CS16Vertex<V>> _vertices;
	private NodeSequence<CS16Edge<V>> _edges;
	private ArrayList<Integer> _adjIndices;
	private MyDecorator<CS16Vertex<V>, Integer> _adjDecorator;
	private MyDecorator<CS16Vertex<V>, Position<CS16Vertex<V>>> _vertexPosDecorator;
	private MyDecorator<CS16Edge<V>, Position<CS16Edge<V>>> _edgePosDecorator;

	/**
	 * Constructor for your Graph, where among other things, you will most
	 * likely want to instantiate your matrix array and your NodeSequences.
	 * We've provided the matrix creating method for you.
	 *
	 * This must run in O(1) time.
	 */
	public MyGraph() {
		_adjMatrix = this.makeEmptyEdgeArray();
		// Fill in the rest here!
		_vertices = new NodeSequence<CS16Vertex<V>>();
		_edges = new NodeSequence<CS16Edge<V>>();
		_adjIndices = new ArrayList<Integer>();
		_adjDecorator = new MyDecorator<CS16Vertex<V>, Integer>();
		_vertexPosDecorator = new MyDecorator<CS16Vertex<V>, Position<CS16Vertex<V>>>();
		_edgePosDecorator = new MyDecorator<CS16Edge<V>, Position<CS16Edge<V>>>();
		// filling the array
		for (int i = 0; i < MAX_VERTICES; i++) {
			_adjIndices.add(i);
		}

	}

	/**
	 * Returns an iterator holding all the Vertex's of the graph. There may be
	 * some methods in the Sequence ADT to make this method easier.
	 *
	 * This must run in O(|V|) time.
	 *
	 * Note that the visualizer uses this method to display the graph's
	 * vertices, so you should implement it first.
	 *
	 * @return Returns a VertexIterator containing the Vertex's of the Graph.
	 */
	@Override
	public Iterator<CS16Vertex<V>> vertices() {
		return _vertices.iterator();
	}

	/**
	 * Returns an iterator holding all the Edge's of the graph. There may be
	 * some methods in the Sequence ADT to make this method easier.
	 *
	 * This must run in O(|E|) time.
	 *
	 * Note that the visualizer uses this method to display the graph's edges,
	 * so you should implement it first.
	 *
	 * @return Returns an EdgeIterator containing the Edge's of the Graph.
	 */
	public Iterator<CS16Edge<V>> edges() {
		return _edges.iterator();
	}

	/**
	 * Inserts a new Vertex into your Graph. You will want to first generate a
	 * unique number for your vertex that falls within the range of your
	 * adjacency array. You will then have to add the Vertex to your sequence of
	 * vertices. Finally, set a reference to its Position in the sequence.
	 *
	 * You will not have to worry about the case where *more* than MAX_VERTICES
	 * vertices are in your graph. Your code should, however, be able to hold
	 * MAX_VERTICES vertices at any time.
	 *
	 * This must run in O(1) time.
	 *
	 * @param vertElement
	 *            the element you want to insert at the vertex
	 * @return returns the newly inserted vertex.
	 */
	@Override
	public CS16Vertex<V> insertVertex(V vertElement) {

		int unique = _adjIndices.remove(0);
		// create vertex
		CS16Vertex<V> vertex = new GraphVertex<V>(vertElement);
		vertex.setVertexNumber(unique);

		// add vertex to sequence of vertices
		_vertices.addLast(vertex);

		// set reference to its position in the sequence
		Position<CS16Vertex<V>> vertexPos = _vertices.last();
		_vertexPosDecorator.setDecoration(vertex, vertexPos);

		// set decoration for adjDecorator
		_adjDecorator.setDecoration(vertex, unique);

		// TODO need to test position in sequence
		return vertex;
	}

	/**
	 * Inserts a new Edge into your Graph. You need to update your adjacency
	 * matrix to reflect this new added Edge. Finally, the Edge needs to be
	 * added to the edge sequence. Just as with insertVertex(...), the Edge will
	 * need a reference to its position in this sequence.
	 *
	 * This must run in O(1) time.
	 *
	 * @param v1
	 *            The first vertex of the edge connection.
	 * @param v2
	 *            The second vertex of the edge connection.
	 * @param edgeElement
	 *            The element of the newly inserted edge.
	 * @return Returns the newly inserted Edge.
	 * @throws InvalidVertexException
	 *             Thrown when either Vertex is null.
	 */
	@Override
	public CS16Edge<V> insertEdge(CS16Vertex<V> v1, CS16Vertex<V> v2, Integer edgeElement)
			throws InvalidVertexException {
		if (v1 == null || v2 == null) {
			throw new InvalidVertexException("vertex is null");
		}
		CS16Edge<V> edge = new GraphEdge<V>(edgeElement);
		edge.setFromVertex(v1);
		edge.setToVertex(v2);

		// update adj matrix
		_adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()] = edge;
		_adjMatrix[v2.getVertexNumber()][v1.getVertexNumber()] = edge;

		// updating edge node sequence
		_edges.addLast(edge);
		Position<CS16Edge<V>> edgePos = _edges.last();
		_edgePosDecorator.setDecoration(edge, edgePos);

		return edge;
	}

	/**
	 * Removes a Vertex from your graph. You will first have to remove all edges
	 * that are connected to this Vertex. (Perhaps you can use other methods you
	 * will eventually write to make this easier?) Finally, remove the Vertex
	 * from the vertex sequence.
	 *
	 * This must run in O(|V|) time.
	 *
	 * @param v
	 *            The Vertex to remove.
	 * @return The element of the removed Vertex.
	 * @throws InvalidVertexException
	 *             Thrown when the Vertex is null.
	 */
	@Override
	public V removeVertex(CS16Vertex<V> v) throws InvalidVertexException {
		if (v == null) {
			throw new InvalidVertexException("vertex is null");
		}

		// remove all edges from vertex
		Iterator<CS16Edge<V>> edges = incidentEdges(v);
		while (edges.hasNext()) {
			removeEdge(edges.next());
		}
		// delete vertex from sequence of vertices
		_vertices.remove(_vertexPosDecorator.getDecoration(v));
		// add back to adj indices list
		_adjIndices.add(_adjDecorator.getDecoration(v));
		// remove decorations
		_vertexPosDecorator.removeDecoration(v);
		_adjDecorator.removeDecoration(v);

		return v.element();
	}

	/**
	 * Removes an Edge from your Graph. You will want to remove all references
	 * to it from your adjacency matrix. Don't forget to remove it from the edge
	 * sequence.
	 *
	 * This must run in O(1) time.
	 *
	 * @param e
	 *            The Edge to remove.
	 * @return The element of the removed Edge.
	 * @throws InvalidEdgeException
	 *             Thrown when the Edge is null.
	 */
	@Override
	public Integer removeEdge(CS16Edge<V> e) throws InvalidEdgeException {

		CS16Vertex<V> to = e.getToVertex();
		CS16Vertex<V> from = e.getFromVertex();

		// update adj matrix
		_adjMatrix[to.getVertexNumber()][from.getVertexNumber()] = null;
		_adjMatrix[from.getVertexNumber()][to.getVertexNumber()] = null;

		// remove from sequence
		_edges.remove(_edgePosDecorator.getDecoration(e));
		_edgePosDecorator.removeDecoration(e);

		return e.element();
	}

	/**
	 * Returns the edge that connects the two vertices. You will want to consult
	 * your adjacency matrix to see if they are connected. If so, return that
	 * edge, otherwise throw a NoSuchEdgeException.
	 *
	 * This must run in O(1) time.
	 *
	 * @param v1
	 *            The first vertex that may be connected.
	 * @param v2
	 *            The second vertex that may be connected.
	 * @return The edge that connects the first and second vertices.
	 * @throws InvalidVertexException
	 *             Thrown when either vertex is null.
	 * @throws NoSuchEdgeException
	 *             Thrown when no edge connects the vertices.
	 */
	@Override
	public CS16Edge<V> connectingEdge(CS16Vertex<V> v1, CS16Vertex<V> v2)
			throws InvalidVertexException, NoSuchEdgeException {
		// check if either vertex is null
		if (v1 == null || v2 == null) {
			throw new InvalidVertexException("vertex is null");
		}
		// check if v1 and v2 are connected
		if (_adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()] != null
				&& _adjMatrix[v2.getVertexNumber()][v1.getVertexNumber()] != null) {
			// if they are connected return connecting edge
			return _adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()];
		} else {
			// if not throw exception
			throw new NoSuchEdgeException("no such edge");
		}

	}

	/**
	 * Returns an Iterator over all the Edges that are connected to this Vertex.
	 *
	 * You can look through an entire row or column of your adjacency matrix for
	 * this method.
	 *
	 * @param v
	 *            The vertex to find the incident edges on.
	 * @return Returns an EdgeIterator holding the incident edges on v.
	 * @throws InvalidVertexException
	 *             Thrown when the Vertex is null.
	 */
	@Override
	public Iterator<CS16Edge<V>> incidentEdges(CS16Vertex<V> v) throws InvalidVertexException {
		// if vertex is null throw exception
		if (v == null) {
			throw new InvalidVertexException("vertex is null");
		}
		// to find all the edges connected to this vertex look
		// thru an entire row or column
		// how to look thru a row or column?

		ArrayList<CS16Edge<V>> incidentEdges = new ArrayList<CS16Edge<V>>();

		for (int i = 0; i < _adjMatrix.length; i++) {
			if (_adjMatrix[i][v.getVertexNumber()] != null) {
				incidentEdges.add(_adjMatrix[i][v.getVertexNumber()]);
			}
		}
		// return the arrayList in a form of an iterator
		return incidentEdges.iterator();
	}

	/**
	 * Returns the Vertex that is on the other side of Edge e opposite of Vertex
	 * v. If the edge is not incident on v, then throw a NoSuchVertexException.
	 *
	 * This must run in O(1) time.
	 *
	 * @param v
	 *            The first vertex on Edge e.
	 * @param e
	 *            The edge connecting Vertex v and the unknown opposite Vertex.
	 * @return The opposite Vertex of v across Edge e.
	 * @throws InvalidVertexException
	 *             Thrown when the Vertex is null.
	 * @throws InvalidEdgeException
	 *             Thrown when the Edge is null.
	 * @throws NoSuchVertexException
	 *             Thrown when Edge e is not incident on v.
	 */
	@Override
	public CS16Vertex<V> opposite(CS16Vertex<V> v, CS16Edge<V> e)
			throws InvalidVertexException, InvalidEdgeException, NoSuchVertexException {
		// if vertex is null throw exception
		if (v == null) {
			throw new InvalidVertexException("vertex is null");
		}
		// if edge is null throw exception
		if (e == null) {
			throw new InvalidEdgeException("edge is null");
		}
		// check if edge e is incident on v

		if (v == e.getFromVertex()) {
			return e.getToVertex();
		} else if (v == e.getToVertex()) {
			return e.getFromVertex();
		} else {
			throw new NoSuchVertexException("vertex does not exist");
		}

	}

	/**
	 * Returns the two Vertex's that the Edge e is incident upon.
	 *
	 * This must run in O(1) time.
	 *
	 * Note that the visualizer uses this method to display the graph's edges.
	 *
	 * @param e
	 *            The edge to find the connecting Vertex's on.
	 * @return An list of Vertex's holding the two connecting vertices.
	 * @throws InvalidEdgeException
	 *             Thrown when the Edge e is null.
	 */
	@Override
	public List<CS16Vertex<V>> endVertices(CS16Edge<V> e) throws InvalidEdgeException {
		// if edge is null throw exception
		if (e == null) {
			throw new InvalidEdgeException("edge is null");
		}
		// store the to and from vertices of the edge
		CS16Vertex<V> fromVertex = e.getFromVertex();
		CS16Vertex<V> toVertex = e.getToVertex();

		ArrayList<CS16Vertex<V>> endVertices = new ArrayList<CS16Vertex<V>>();
		endVertices.add(fromVertex);
		endVertices.add(toVertex);

		return endVertices;
	}

	/**
	 * Returns true if there exists an Edge that connects Vertex v1 and Vertex
	 * v2.
	 *
	 * This must run in O(1) time.
	 *
	 * @param v1
	 *            The first Vertex to test adjacency.
	 * @param v2
	 *            The second Vertex to test adjacency.
	 * @return Returns true if the vertices are adjacent.
	 * @throws InvalidVertexException
	 *             Thrown if either vertex is null.
	 */
	@Override
	public boolean areAdjacent(CS16Vertex<V> v1, CS16Vertex<V> v2) throws InvalidVertexException {
		// throw exception if a vertex is null
		if (v1 == null || v2 == null) {
			throw new InvalidVertexException("vertex is null");
		}
		// if they the vertices are connected return true, check adjMatrix
		if (_adjMatrix[v1.getVertexNumber()][v2.getVertexNumber()] != null
				&& _adjMatrix[v2.getVertexNumber()][v1.getVertexNumber()] != null) {
			return true;
		}

		return false;

	}

	/**
	 * Clears all the vertices and edges from the graph. You will want to also
	 * clear the adjacency matrix.
	 *
	 * This should run in O(Max Vertices) time.
	 */
	@Override
	public void clear() {

		// iterate on all vertices in adjMatrix and remove all edges
		Iterator<CS16Vertex<V>> vertices = vertices();
		while (vertices.hasNext()) {
			removeVertex(vertices.next());
		}

	}

	/**
	 * Creates a 2-dimensional array that holds CS16Edges. You don't have to
	 * modify this method at all.
	 * 
	 * @return the MAX_VERTICES x MAX_VERTICES array of CS16Edges.
	 *
	 */
	@SuppressWarnings("unchecked")
	private CS16Edge<V>[][] makeEmptyEdgeArray() {
		return (CS16Edge<V>[][]) new CS16Edge[MAX_VERTICES][MAX_VERTICES];
	}

}
