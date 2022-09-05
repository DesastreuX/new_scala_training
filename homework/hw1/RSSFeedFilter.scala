import scalaj.http.{Http, HttpResponse}
import scala.io.Source
import java.net.http.{HttpResponse => JHttp}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.PrintWriter

object main extends App {
  val rss_sources_file = "feed-sources.txt"
  val feed_sources = Source.fromFile(rss_sources_file).getLines().toArray

  for (input_string <- args) {
    val timestamp_now = DateTimeFormatter
      .ofPattern("dd-MM-yyyy-HH-mm-ss")
      .format(LocalDateTime.now())
    val output_filename = input_string + "-" + timestamp_now + ".txt"
    val pw = new PrintWriter(output_filename)

    for (rss_url <- feed_sources) {
      val response: HttpResponse[String] = Http(
        rss_url
      ).asString
      val responseBody = response.body
      import scala.xml.XML

      val elements = XML.loadString(responseBody)
      val titles = elements \\ "item" \ "title"
      for (title <- titles) {
        if (title.text.toLowerCase().contains(input_string.toLowerCase())) {
          pw.write(title.text + "\n")
        }
      }
    }
    pw.close()
  }
}
