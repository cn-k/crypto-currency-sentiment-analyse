object SentimentType extends Enumeration {
  type Sentiment = Value
  val POSITIVE, VERY_POSITIVE, NEGATIVE, VERY_NEGATIVE, NEUTRAL = Value

  def toSentiment(sentiment: Int): Sentiment = sentiment match {
    case 0 => SentimentType.VERY_NEGATIVE
    case 1 => SentimentType.NEGATIVE
    case 2 => SentimentType.NEUTRAL
    case 3 => SentimentType.POSITIVE
    case 4 => SentimentType.VERY_POSITIVE
    case _ => throw new Exception("wrong index")
  }
}
