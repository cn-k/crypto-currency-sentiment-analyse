package kafka

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.ConfigFactory
import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder
import io.circe.syntax.EncoderOps
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer

import scala.concurrent.Future

object TweetsProducer {

  println("Hello from producer")
  implicit val system : ActorSystem = ActorSystem("producer-sample")
  implicit val materializer: Materializer = ActorMaterializer()
  lazy private val config =  ConfigFactory.load("application.conf")
  implicit val sampleEventEncoder : Encoder[SampleEvent] = deriveEncoder[SampleEvent]

  def produce(event: Seq[SampleEvent])  = {
    val producerSettings = ProducerSettings(system, new StringSerializer, new  StringSerializer)
    val done: Future[Done] = Source(event).map(value => new ProducerRecord[String, String](config.getString("topic"), value.asJson.toString())) //value.asJson.toString()
      .runWith(Producer.plainSink(producerSettings))
  }
}
