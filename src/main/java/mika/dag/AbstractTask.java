package mika.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class AbstractTask<T, V> implements Task<T, V> {

  protected final String name;
  private List<T> dependencies;

  /**
   * Create a task that has no dependencies.
   * @param name the name of the task
      */
  public AbstractTask(String name) {
    this.name = name;
  }

  /**
   * Create a task that with a list of dependencies.
   * @param name the name of the task
   */
  public AbstractTask(String name, List<T> dependencies) {
    this.name = name;
    this.dependencies = dependencies;
  }

  public AbstractTask(String name, Optional<List<T>> deps) {
    this.name = name;
    this.dependencies = deps.orElse(new ArrayList<T>());
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Optional<List<T>> getDependencies() {
    if (dependencies == null) {
      return Optional.empty();
    }
    return Optional.of(dependencies);
  }

  public abstract V run();

}
