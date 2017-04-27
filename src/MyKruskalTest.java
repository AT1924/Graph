package src;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;

public class MyKruskalTest {

	@Test
	public void testKruskals() {
		MyGraph<String> graph = new MyGraph<String>();
		MyKruskal<String> kruskal = new MyKruskal<String>();
		
		CS16Vertex<String> A = graph.insertVertex("A");
		CS16Vertex<String> B = graph.insertVertex("B");
		CS16Vertex<String> C = graph.insertVertex("C");
		
		CS16Edge<String> ab = graph.insertEdge(A, B, 1);
		CS16Edge<String> bc = graph.insertEdge(B,C,1);
		CS16Edge<String> ca = graph.insertEdge(A, C, 10);
		
		Collection<CS16Edge<String>> MSF  = kruskal.genMinSpanForest(graph, null);
		
		assertThat(MSF.size(), is(2));
		assertThat(MSF.contains(ab), is(true));
		assertThat(MSF.contains(bc), is(true));
		assertThat(MSF.contains(ca), is(false));
	}

}
