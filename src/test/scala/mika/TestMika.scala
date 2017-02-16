package mika

import mika.condor.{CondorDAGScheduler, CondorJavaTask, CondorTask}
import mika.dag.DAGScheduler

class TestTask(name: String, deps: List[CondorTask[_]])
  extends CondorJavaTask[Unit](name, deps = deps) {
  override def run(): Unit = {
    println(toString())
  }
}


class MikaTask(name: String, deps: List[CondorTask[_]])
  extends CondorJavaTask[Unit](name, deps = deps) {
  override def run(): Unit = {
    println(toString())
  }
}

object TestMika {
//  def uniqueClasses[T <: CondorTask[_]](tasks: List[T]): List[String] = {
//    tasks.map(c => c.getClass).distinct.map(
//      c => c.getMethod("submitFileName").invoke(c).asInstanceOf[String])
//  }

  def main(args: Array[String]): Unit = {
    val scheduler = new CondorDAGScheduler[CondorTask[_]]()
    val task1 = new TestTask("task1", List())
    val task2 = new MikaTask("task2", List())
    val deps = List(task1, task2)
    val task3 = new TestTask("task3", deps)
    scheduler.addTask(task1)
    scheduler.addTask(task2)
    scheduler.addTask(task3)
    scheduler.schedule()
    scheduler.printPlan()
  }
}
