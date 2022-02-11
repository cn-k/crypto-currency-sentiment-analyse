package kafka

import akka.kafka.scaladsl.Consumer
import akka.kafka.{ConsumerSettings, Subscriptions}
import akka.stream.scaladsl.Sink
import com.typesafe.config.ConfigFactory
import io.circe.generic.semiauto.deriveDecoder
import io.circe.{Decoder, parser}
import kafka.TweetsProducer.system
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer

object TweetsConsumer {

  lazy private val config =  ConfigFactory.load("application.conf")
  implicit val sampleEventDecoder : Decoder[SampleEvent] = deriveDecoder[SampleEvent]

  def consume() ={
    val kafkaConsumerSettings: ConsumerSettings[String, String] =
      ConsumerSettings(system, new StringDeserializer, new StringDeserializer)
        .withBootstrapServers(config.getString("bootstrapServers"))
        .withGroupId(config.getString("consumer.groupId"))
        .withProperties(
          ConsumerConfig.MAX_POLL_RECORDS_CONFIG       -> "100",
          ConsumerConfig.AUTO_OFFSET_RESET_CONFIG      -> "earliest"
          //CommonClientConfigs.SECURITY_PROTOCOL_CONFIG -> "SSL"
        )

    Consumer
      .plainSource(kafkaConsumerSettings, Subscriptions.topics(config.getString("topic")))
      .runWith(Sink.foreach(tw => {
         parser.decode[SampleEvent](tw.value()) match {
           case Right(sEvent) => println(sEvent.toString)
           case Left(ex) => println("wrong data format")
         }
      }))
  }

  def main(args: Array[String]): Unit = {
    consume()
  }
}
