import com.typesafe.config.ConfigFactory

object Test extends App {

  val config = ConfigFactory.load("application.conf")
  println(config)
  println(config.getString("topic"))

}
