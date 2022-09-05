import java.net.http.{HttpResponse => JHttp}

object ReaderHttp extends App {
  import scalaj.http.{Http, HttpResponse}
  val response: HttpResponse[String] = Http(
    "http://feeds.bbci.co.uk/news/rss.xml"
  ).asString
  val responseBody = response.body

  import scala.xml.XML

  val elements = XML.loadString(responseBody)
  val titles = elements \\ "item" \ "title"
  for (title <- titles) {
    if (title.text.toLowerCase().contains("euro 2022")) {
      println(title.text)
    }
  }
}
