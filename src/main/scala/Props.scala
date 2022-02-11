import java.io.InputStream
import java.util.Properties

object Props {
  def readProps: Properties =
  {
    val is: InputStream = ClassLoader.getSystemResourceAsStream("twitter_app.properties")
    val properties: Properties = new Properties()
    properties.load(is)
    properties
  }
}
