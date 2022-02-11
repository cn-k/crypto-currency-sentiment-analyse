import SentimentType.Sentiment
import kafka.SampleEvent
import kafka.TweetsProducer.produce
import twitter4j.{StallWarning, Status, StatusDeletionNotice, StatusListener}

object Util {

  val props = Props.readProps
  val keywords = props.getProperty("twitter.keys")
  val config = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey(props.getProperty("twitter.consumer.key"))
    .setOAuthConsumerSecret(props.getProperty("twitter.consumer.secret"))
    .setOAuthAccessToken(props.getProperty("twitter.access.key"))
    .setOAuthAccessTokenSecret(props.getProperty("twitter.access.secret"))
    .build

  def simpleStatusListener = new StatusListener() {
    def onStatus(status: Status) {
      val sentiment: Seq[(String, Sentiment)] = SentimentUtils.sentiment(status.getText)//.foreach(rs => println(rs._1 + " ----> " + rs._2))
      sentiment.foreach(rs => println(rs._1 + " ----> " + rs._2))
      val res = sentiment.map(s => SampleEvent(s._1, s._2.toString))
      produce(res)

    }
    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) { ex.printStackTrace }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }

}