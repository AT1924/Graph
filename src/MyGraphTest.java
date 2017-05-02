package src;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;
import support.graph.InvalidVertexException;

/**
 * This class can be used to test the functionality of your MyGraph implementation.
 * You will find s few examples to guide you through the syntax of writing test cases.
 * Each test case uses its own graph instance, to ensure that the test cases are independent 
 * of each other. All of these given examples should also pass once you've implemented your heap.
 * The annotation @Test before each test case is JUnit syntax, it basically lets the compiler know
 * that this is a unit test method. Use this annotation for every test method. This class is also like
 * any other java class, so should you need to add private helper methods to use in your tests, 
 * you can do so, simply without the annotations as you usually would write a method.
 * The general framework of a test case is:
 * 		- Name the test method descriptively, mentioning what is being tested (it is ok to have slightly verbose method names here)
 * 		- Set-up the program state (ex: instantiate a heap and insert K,V pairs into it)
 * 		- Use assertions to validate that the progam is in the state you expect it to be
 */
public class MyGraphTest {

	/**
	 * An example of testing that the vertex created and returned by insertVertex() actually is in the graph.
	 */
	@Test
	public void testInsertVertex() {
		// set-up graph and insert vertices
		MyGraph<String> graph = new MyGraph<String>();
		CS16Vertex<String> A = graph.insertVertex("A");
		CS16Vertex<String> B = graph.insertVertex("B");
		CS16Vertex<String> C = graph.insertVertex("C");
		
		// use the vertex iterator to get a list of the vertices in the actual graph
		List<CS16Vertex<String>> actualVertices = new ArrayList<CS16Vertex<String>>();	
		Iterator<CS16Vertex<String>> it = graph.vertices();
		while (it.hasNext()) {
			actualVertices.add(it.next());
		}
		
		// assert that the graph state is consistent with what you expect
		assertThat(actualVertices.size(), is(3));
		assertThat(actualVertices.contains(A), is(true));
		assertThat(actualVertices.contains(B), is(true));
		assertThat(actualVertices.contains(C), is(true));
	}

	/**
	 * An example of testing that the edges created and returned by insertEdge() actually is in the graph.
	 */
	@Test
	public void testInsertEdges() {
		// set-up graph and insert vertices and edges
		MyGraph<String> graph = new MyGraph<String>();
		CS16Vertex<String> A = graph.insertVertex("A");
		CS16Vertex<String> B = graph.insertVertex("B");
		CS16Vertex<String> C = graph.insertVertex("C");
		
		// use the edge iterator to get a list of the edges in the actual graph.
		CS16Edge<String> ab = graph.insertEdge(A, B, 1);
		CS16Edge<String> bc = graph.insertEdge(B, C, 2);
		
		// use the edge iterator to get a list of the edges in the actual graph.
		List<CS16Edge<String>> actualEdges = new ArrayList<CS16Edge<String>>();		
		Iterator<CS16Edge<String>> it = graph.edges();
		while (it.hasNext()) {
			actualEdges.add(it.next());
		}
		
		// assert that the graph state is consistent with what you expect.
		assertThat(actualEdges.size(), is(2));
		assertThat(actualEdges.contains(ab), is(true));
		assertThat(actualEdges.contains(bc), is(true));
	}
	
	/**
	 * An example of testing for an exception.
	 */
	@Test(expected = InvalidVertexException.class)
	public void testIncidentEdgesThrowsInvalidVertexException() {
		MyGraph<String> graph = new MyGraph<String>();
		graph.incidentEdges(null);
	}
	
	/**
	 * TODO: Test all of your MyGraph methods. Be sure to think of edge cases and 
	 * cases that throw exceptions.
	 */
	
	@Test
	public void insertVertexTest(){
		
	}
	
	@Test
	public void insertEdgeTest(){
		
	}
	
	@Test
	public void removeVertexTest(){
		
	}
	
	@Test
	public void removeEdgeTest(){
		
	}
	
	@Test
	public void connectingEdgeTest(){
		
	}
	
	@Test
	public void endVerticesTest(){
		
	}
	
	@Test
	public void areAdjacentTest(){
		
	}
	
}
