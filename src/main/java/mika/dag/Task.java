package mika.dag;

import java.util.List;
import java.util.Optional;
import mika.dag.graph.nodes.Node;

public interface Task<T, V> extends Node {

  /**
   * @return the name of the Task
   */
  String getName();

  /**
   * @return A list of tasks a Task depends on
   */
  Optional<List<T>> getDependencies();

  /**
   * Task is an atomic running unit
   * @return The result of running the Task
   */
  V run();

}
