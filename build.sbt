import Dependencies.{Versions => _, _}

lazy val samples = project
  .settings(
    name := "samples",
    scalaVersion := Versions.scala3,
    libraryDependencies ++= Seq(scalaTest, scalastic,
      "org.scalamock" %% "scalamock" % "6.0.0" % Test
    )
  )
