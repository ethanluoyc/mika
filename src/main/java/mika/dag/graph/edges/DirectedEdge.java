package mika.dag.graph.edges;

import mika.dag.graph.nodes.Node;

public interface DirectedEdge<V extends Node> extends Edge<V> {
  V getFrom();
  V getTo();
}
