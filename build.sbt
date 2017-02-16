import sbtprotobuf.{ProtobufPlugin=>PB}

PB.protobufSettings

lazy val commonSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.1"
)

lazy val protoc = taskKey[Unit]("Compile Protobuf")

lazy val mika = (project in file(".")).settings(
  commonSettings,
  name := "mika",
  protoc := {import sys.process._; "make proto" ! },
  // https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java
  libraryDependencies += "com.google.protobuf" % "protobuf-java" % "3.1.0"
)