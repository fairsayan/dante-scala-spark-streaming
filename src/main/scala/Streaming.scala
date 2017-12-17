import collection.JavaConversions._

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.log4j.{Level, Logger}

object Streaming {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("DanteWordCount")
    val ssc = new StreamingContext(conf, Seconds(4))
    val rootLogger = Logger.getRootLogger()
    rootLogger.setLevel(Level.ERROR)
    val lines = ssc.socketTextStream("localhost", 9999)

    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKeyAndWindow( (a:Int, b:Int) => a + b, Seconds(28), Seconds(8) ).filter( p => p._2 > 5)
    wordCounts.foreachRDD(rdd => {
      println("\nUpcoming!")
      rdd.sortBy( -_._2 ).collect().foreach(println)
    })
    ssc.start()
    ssc.awaitTermination()
  }
}