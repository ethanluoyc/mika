package mika.condor

import mika.dag.{AbstractTask, Task}

import collection.JavaConverters._
import java.util

class DAGNodeTask(name: String,
                  val task: CondorTask[_],
                  args: Array[String] = Array(),
                  params: Map[String, String] = Map())

  extends AbstractTask[CondorTask[_], Unit](name, task.getDependencies) {

  override def toString: String ={
    val deps = task.getDependencies.orElse(new util.ArrayList[CondorTask[_]]()).asScala
    s"""|JOB $name ${task.submitFile().getAbsolutePath}
        |${if (args.nonEmpty) s"""VARS ${args.mkString(" ")}""" else ""}
        |${if (deps.nonEmpty) s"PARENT ${deps.map(t => t.getName).mkString(", ")} CHILD $name" else ""}
        |""".stripMargin.trim
  }

  override def run(): Unit = ???
}
