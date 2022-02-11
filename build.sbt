name := "crypto-currency-sentiment-analyse"

version := "0.1"

scalaVersion := "2.13.5"

lazy val Avro4sVersion = "3.1.1"

lazy val KafkaAvroSerializerVersion = "5.5.0"

resolvers += "Confluent Maven Repository" at "https://packages.confluent.io/maven/"

val kafkaSerializationV = "0.5.20"

libraryDependencies ++= Seq(
  "edu.stanford.nlp" % "stanford-corenlp" % "4.2.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "4.2.0" classifier "models",
  "org.twitter4j" % "twitter4j-stream" % "4.0.7",
  "com.typesafe.akka" %% "akka-stream-kafka" % "2.0.7",
  "io.confluent" % "kafka-avro-serializer" % KafkaAvroSerializerVersion,
  //"com.sksamuel.avro4s"   %% "avro4s-core"           % Avro4sVersion,
  //"com.goyeau" %% "kafka-streams-circe" % "fbee94b",
  "io.circe"             %% "circe-generic"               % "0.13.0",
  "com.nequissimus" %% "circe-kafka" % "2.7.0"



)