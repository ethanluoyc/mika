package mika.dag.graph.edges;

import mika.dag.graph.nodes.Node;

public class DirectedEdgeImplt<V extends Node> implements DirectedEdge<V> {
  /**
   * A Directed edge
   * */

  @Override
  public V getFrom() {
    return from;
  }

  private final V from;

  @Override
  public V getTo() {
    return to;
  }

  private final V to;

  public DirectedEdgeImplt(V from, V to) {
    this.from = from;
    this.to = to;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DirectedEdge edge = (DirectedEdge) o;

    return (this.getFrom().equals(((DirectedEdge) o).getFrom())
        && this.getTo().equals(((DirectedEdge) o).getTo()));
  }

  @Override
  public int hashCode() {
    int result = from != null ? from.hashCode() : 0;
    result = 31 * result + (to != null ? to.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return from + " -> " + to;
  }
}
