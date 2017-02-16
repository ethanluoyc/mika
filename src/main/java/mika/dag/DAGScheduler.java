package mika.dag;

import mika.dag.graph.DirectedGraph;
import java.util.ArrayList;
import java.util.List;
import mika.dag.graph.edges.DirectedEdge;
import mika.dag.graph.edges.DirectedEdgeImplt;

public class DAGScheduler<T extends Task<?, ?>> {
  /**
   * A DAG scheduler, inspired by Apache Spark's internal scheduler, but
   * much smaller and implemented in Java.
   */
  protected List<T> tasks;

  public DAGScheduler() {
    tasks = new ArrayList<>();
  }

  public DAGScheduler(List<T> tasks) {
    this.tasks = tasks;
  }

  public void addTask(T task) {
    this.tasks.add(task);
  }

  /**
   * Schedule the tasks based on their dependencies in a DAG and run
   */
  public List<T> schedule() {
    DirectedGraph<T, DirectedEdge<T>> graph = new DirectedGraph<>();
    for (T task: tasks) {
      graph.addNode(task);
      if (task.getDependencies().isPresent()) {
        List<T> deps = (List<T>) task.getDependencies().get();
        for (T dep: deps) {
          DirectedEdge<T> edge = new DirectedEdgeImplt<>(task, dep);
          graph.addEdge(edge);
        }
      }
    }
    return DirectedGraph.topologicalSort(graph);
  }

}
