package src;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import support.graph.CS16Edge;
import support.graph.CS16Vertex;

public class MyPrimJarnikTest {

	@Test
	public void testPrimJarnik() {
		MyGraph<String> graph = new MyGraph<String>();
		MyPrimJarnik<String> prim = new MyPrimJarnik<String>();
		
		CS16Vertex<String> A = graph.insertVertex("A");
		CS16Vertex<String> B = graph.insertVertex("B");
		CS16Vertex<String> C = graph.insertVertex("C");
		
		CS16Edge<String> ab = graph.insertEdge(A, B, 1);
		CS16Edge<String> bc = graph.insertEdge(B,C,1);
		CS16Edge<String> ca = graph.insertEdge(A, C, 10);
		
		Collection<CS16Edge<String>> MSF  = prim.genMinSpanForest(graph, null);
		
		assertThat(MSF.size(), is(2));
		assertThat(MSF.contains(ab), is(true));
		assertThat(MSF.contains(bc), is(true));
		assertThat(MSF.contains(ca), is(false));
	}

}
