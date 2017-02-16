package mika.dag.graph.nodes;

public class NodeImplt implements Node {

  @Override
  public String getName() {
    return name;
  }

  private String name;

  public NodeImplt(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    NodeImplt node = (NodeImplt) o;
    return (this.name.equals(node.name));
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "Node(" + name + ')';
  }
}
