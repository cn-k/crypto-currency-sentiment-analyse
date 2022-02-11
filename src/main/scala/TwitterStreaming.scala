
import twitter4j._

object TwitterStreaming extends App {
  /*
  val cb = new ConfigurationBuilder()
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey("YOUR KEY HERE")
    .setOAuthConsumerSecret("YOUR SECRET HERE")
    .setOAuthAccessToken("YOUR ACCESS TOKEN")
    .setOAuthAccessTokenSecret("YOUR ACCESS TOKEN SECRET")

  val tf = new TwitterFactory(cb.build())
  val twitter = tf.getInstance()

  // (2) use the twitter object to get your friend's timeline
  val statuses = twitter.tweets()
  System.out.println("Showing friends timeline.")
  val it = statuses.iterator()
  while (it.hasNext()) {
    val status = it.next()
    println(status.getUser().getName() + ":" +
      status.getText());

  }
   */

val twitterStream = new TwitterStreamFactory(Util.config).getInstance
  twitterStream.addListener(Util.simpleStatusListener)
  twitterStream.filter(new FilterQuery().track(Util.keywords).language("en"))

}
