package src;

/** 
 * This class is based on Kruskal's minimum spanning tree
 * algorithm. It has been extended to calculate the MST of each
 * disconnected graph at the same time. The trick is to take advantage
 * of the fact that Kruskal's algorithm combines "clouds" when it
 * builds its trees. Thus we can connect the clouds of these
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
import java.util.Collection;

public class MyKruskal<V> implements MinSpanForest<V> {

    /** 
     * This method implements Kruskal's algorithm and extends it slightly
     * to account for disconnected graphs.  You must return the collection
     * of edges of the Minimum Spanning Forest (MSF) for the given graph, g.
     * 
     * This must run in O(|E|log|V|) time.
     * @param g Your graph (created in MyGraph)
     * @param visualizer Only used if you implement the optional animation.
     * @return returns a data structure that contains the edges of your MSF that implements java.util.Collection
     */
    @Override
    public Collection<CS16Edge<V>> genMinSpanForest(AdjacencyMatrixGraph<V> g, CS16GraphVisualizer<V> visualizer) {
        return null;
    }

}
