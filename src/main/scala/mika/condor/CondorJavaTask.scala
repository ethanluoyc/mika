package mika.condor


abstract class CondorJavaTask[V](name: String,
                                 args: Array[String] = Array(),
                                 deps: List[CondorTask[_]],
                                 params: Map[String, String] = Map())
  extends CondorTask[V](UniverseType.JAVA, name, args, deps, params) {

  def this(name: String, args: Array[String]) {
    this(name, args, List[CondorTask[_]](), Map())
  }

  private val mainClass: String = {
    // TODO pass in mainClass as arguments
    if (this.getClass.getName.endsWith("$")) this.getClass.getName.dropRight(1)
    else this.getClass.getName
  }

  override var requestMemory = "4GB"
  override var executable = "synergy-basketball.jar"

  protected var javaVmArgs: String = "-Xmx4g"
  protected var jarFiles: String = executable


  def condorScript(): String = {
    s"""|$condorHeader
       |
       |java_vm_args = $javaVmArgs
       |jar_files = $executable
       |
       |ARGUMENTS = $arguments
       |
       |queue
       |""".stripMargin
  }

  override def arguments: String = s"""$mainClass ${args.mkString(" ")}"""

}
