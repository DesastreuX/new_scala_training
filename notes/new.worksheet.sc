import scala.annotation.tailrec
// implicit
implicit val sim: String = "wer"
// s string
def joinString(s1: String)(implicit s2: String): String = {
  s"${s1}-${s2}"
}

joinString("asd")

def joinString2(s1: String)(implicit s2: String): String = {
  s"${s1}-${s2}" // -> fstring python
}

joinString2("123")

countKeyword("a", List("a", "b", "c", "a")) == 2
countKeyword("a", List("b", "c", "d", "f")) == 1
countKeyword(1, List(1, 2, 3, 1)) == 2
countKeyword(1, List(2, 3, 4, 5)) == 1

// generic
def countKeyword[A](keyword: A, source: List[A]): Int = {
  @tailrec
  def tailRecCall(keyword: A, source: List[A], result: Int): Int = {
    if (!source.isEmpty) {
      if (source.head == keyword)
        tailRecCall(keyword, source.tail, result + 1)
      else
        tailRecCall(keyword, source.tail, result)
    } else
      result
  }
  tailRecCall(keyword, source, 0)
}
