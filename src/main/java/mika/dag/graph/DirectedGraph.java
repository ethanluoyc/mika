package mika.dag.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import mika.dag.graph.edges.DirectedEdge;
import mika.dag.graph.edges.DirectedEdgeImplt;
import mika.dag.graph.nodes.Node;

public class DirectedGraph<V extends Node, E extends DirectedEdge<V>> {
  /**
   * A DAG representing the dependencies in a set of tasks. We can then used a scheduler
   * to schedule the tasks.
   * Use an adjacency list implementation.
   */
  private List<V> nodes;
  private Set<E> edges; // Simple graph, no double edges from a node to other

  public DirectedGraph(List<V> nodes, List<E> edges) {
    this.nodes = nodes;
    for (E edge: edges) {
      this.edges.add(edge);
    }
  }

  public DirectedGraph() {
    this.nodes = new ArrayList<>();
    this.edges = new HashSet<>();
  }

  public DirectedGraph(List<V> nodes) {
    this.nodes = nodes;
    this.edges = new HashSet<>();
  }

  public void addNode(V node) {
    nodes.add(node);
  }

  public void addEdge(E edge) {
    edges.add(edge);
  }

  private List<V> getDependents(V node) {
    List<V> dependents = new ArrayList<V>();
    for (V n: nodes) {
      if (connected(node, n) > 0) {
        dependents.add(n);
      }
    }
    return dependents;
  }

  private boolean hasDependent(V node) {
    for (V n: nodes) {
      if (connected(node, n) > 0) {
        return false;
      }
    }
    return true;
  }

  private List<V> getIncoming(V node) {
    return getDependents(node);
  }

  private List<V> getOutgoing(V node) {
    List<V> dependents = new ArrayList<V>();
    for (V n: nodes) {
      if (connected(node, n) < 0) {
        dependents.add(n);
      }
    }
    return dependents;
  }

  // > 0 if node1 -> node2
  // == 0 if node1 not connected to node2
  // < 0 if node1 <- node2
  private int connected(V node1, V node2) {
    for (E edge: edges) {
      if (edge.getFrom().equals(node1) && edge.getTo().equals(node2)) {
        return 1;
      }
      if (edge.getFrom().equals(node2) && edge.getTo().equals(node1)) {
        return -1;
      }
    }
    return 0;
  }

  // Topologically sort the DAG
  // Khan's algorithm
  // TODO graph is mutated, make it immutable!
  public static <V extends Node> List<V> topologicalSort(DirectedGraph<V, ?> graph) {
    List<V> sorted = new LinkedList<V>();
    List<V> fringe = new LinkedList<V>();

    for (V node: graph.nodes) {
      if (graph.getDependents(node).size() == 0) {
        fringe.add(node);
      }
    }

    while (!fringe.isEmpty()) {
      V n = fringe.remove(0);
      sorted.add(n);
      for (V m: graph.getOutgoing(n)) {
        graph.edges.remove(new DirectedEdgeImplt<V>(m, n));
        if (graph.getDependents(m).isEmpty()) {
          fringe.add(m);
        }
      }
    }

    if (!graph.edges.isEmpty()) {
      throw new IllegalArgumentException("Not a DAG");
    }

    // Check if there are still edges present
    return sorted;
  }


}
