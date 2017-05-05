package src;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
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
	
	@Test
	public void testKruskalDisconnect() {
		MyGraph<String> graph = new MyGraph<String>();
		MyKruskal<String> kruskal = new MyKruskal<String>();

		CS16Vertex<String> A = graph.insertVertex("A");
		CS16Vertex<String> B = graph.insertVertex("B");
		CS16Vertex<String> C = graph.insertVertex("C");
		CS16Vertex<String> D = graph.insertVertex("D");

		// disconnected graph
		CS16Vertex<String> E = graph.insertVertex("E");
		CS16Vertex<String> F = graph.insertVertex("F");
		CS16Vertex<String> G = graph.insertVertex("G");
		CS16Vertex<String> H = graph.insertVertex("H");

		CS16Edge<String> ab = graph.insertEdge(A, B, 1);
		CS16Edge<String> bc = graph.insertEdge(B, C, 1);
		CS16Edge<String> bd = graph.insertEdge(B, D, 3);
		CS16Edge<String> ca = graph.insertEdge(C, A, 10);
		CS16Edge<String> da = graph.insertEdge(D, A, 7);

		CS16Edge<String> ef = graph.insertEdge(E, F, 2);
		CS16Edge<String> fg = graph.insertEdge(F, G, 5);
		CS16Edge<String> gh = graph.insertEdge(G, H, 6);
		CS16Edge<String> eh = graph.insertEdge(E, H, 10);
		CS16Edge<String> fh = graph.insertEdge(F, H, 4);

		//{ab, bc, bd, ef, fg, fh };
		ArrayList<CS16Edge<String>> edgesInMSF = new ArrayList<CS16Edge<String>>();
		edgesInMSF.add(ab);
		edgesInMSF.add(bc);
		edgesInMSF.add(bd);
		edgesInMSF.add(ef);
		edgesInMSF.add(fg);
		edgesInMSF.add(fh);
		
		Collection<CS16Edge<String>> MSF = kruskal.genMinSpanForest(graph, null);
		
		assert(edgesInMSF.size() == MSF.size());
		
		for (CS16Edge<String> edge: edgesInMSF){
			assert(MSF.contains(edge));
		}
		

	}


}
