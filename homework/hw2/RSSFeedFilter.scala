import scalaj.http.{Http, HttpResponse}
import scala.io.Source
import java.net.http.{HttpResponse => JHttp}
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.PrintWriter
import scala.annotation.tailrec

object main extends App {

  import scala.xml.NodeSeq

  def listOfNodeToListOfString(titlesNodeSeq: NodeSeq): List[String] = {
    @tailrec
    def tailRecCall(
        titlesNodeSeq: NodeSeq,
        result: List[String]
    ): List[String] = {
      if (!titlesNodeSeq.isEmpty)
        tailRecCall(titlesNodeSeq.tail, result :+ titlesNodeSeq.head.text)
      else result
    }
    tailRecCall(titlesNodeSeq, List())
  }

  def fetchHeadlinesFunc(sources: List[String]): List[String] = {
    @tailrec
    def tailRecCall(
        sources: List[String],
        result: List[String]
    ): List[String] = {
      if (sources.isEmpty) result
      else {
        // fetch from source
        val response: HttpResponse[String] = Http(
          sources.head
        ).asString
        val responseBody = response.body

        // filter titles
        import scala.xml.XML

        val elements = XML.loadString(responseBody)
        val titles = elements \\ "item" \ "title"

        // tailrec
        tailRecCall(sources.tail, result ++ listOfNodeToListOfString(titles))
      }
    }

    tailRecCall(sources, List())
  }

  def filteredHeadlinesFunc(
      headLines: List[String]
  )(implicit keyword: String): String = {
    @tailrec
    def tailRecCall(
        keyword: String,
        headLines: List[String],
        result: String
    ): String = {
      if (!headLines.isEmpty) {
        if (headLines.length == 1)
          tailRecCall(keyword, headLines.tail, s"${result}${headLines.head}")
        else if (headLines.head.toLowerCase().contains(keyword.toLowerCase()))
          tailRecCall(keyword, headLines.tail, s"${result}${headLines.head}\n")
        else
          tailRecCall(keyword, headLines.tail, result)
      } else result
    }
    tailRecCall(keyword, headLines, "")
  }

  def writeToFileFunc(titles: String)(implicit keyword: String) = {
    val timestamp_now = DateTimeFormatter
      .ofPattern("dd-MM-yyyy-HH-mm-ss")
      .format(LocalDateTime.now())
    val output_filename = keyword + "-" + timestamp_now + ".txt"
    val pw = new PrintWriter(output_filename)
    pw.write(titles)
    pw.close()
  }

  val rss_sources_file = "feed-sources.txt"
  val feed_sources = Source.fromFile(rss_sources_file).getLines().toList
  implicit val keyword = args(0)

  val fetchHeadlines = fetchHeadlinesFunc(_)
  val filteredHeadlines = filteredHeadlinesFunc(_)
  val writeToFile = writeToFileFunc(_)

  (fetchHeadlines andThen filteredHeadlines andThen writeToFile)(feed_sources)
}
