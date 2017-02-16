package mika.condor

abstract class CondorStandardTask[V](name: String,
                            args: Array[String], deps: List[CondorTask[_]],
                            params: Map[String, String])
  extends CondorTask[V](UniverseType.STANDARD, name, args, deps, params) {
  // TODO
}
