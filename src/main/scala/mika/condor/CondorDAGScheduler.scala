package mika.condor

import mika.dag.{DAGScheduler, Task}
import collection.JavaConverters._

class CondorDAGScheduler[T <: CondorTask[_]] extends DAGScheduler[CondorTask[_]] {
  def printPlan(): Unit = {
    println(tasks.asScala.map(t => new DAGNodeTask(t.getName, t).toString).mkString("\n\n"))
  }
}
