import scala.annotation.tailrec

val listOfInt = List(1, 2, 3, 4, 5)

// recursion
def sumrec(list: List[Int]): Int = {
  if (list.isEmpty) 0
  else list.head + sumrec(list.tail)
}

sumrec(listOfInt)

def sumOfRange(from: Int, to: Int): Int = {
  if (from > to) 0
  else from + sumOfRange(from + 1, to)
}

sumOfRange(0, 10000)

// tail recursion
def sumOfRangeTailRec(from: Int, to: Int): Int = {
  @tailrec
  def tailRecCall(from: Int, to: Int, sum: Int): Int = {
    if (from > to) sum
    else tailRecCall(from + 1, to, sum + from)
  }
  tailRecCall(from, to, 0)
}

sumOfRangeTailRec(0, 100000)

val listOfFood: List[List[String]] =
  List(List("Apple", "Mango", "Banana"), List("Egg", "Beef"))

def mergeList(source: List[List[String]]): List[String] = {
  @tailrec
  def tailRecCall(
      sourceList: List[List[String]],
      mergedList: List[String]
  ): List[String] = {
    if (!sourceList.isEmpty)
      tailRecCall(sourceList.tail, mergedList ++ sourceList.head)
    else mergedList
  }
  tailRecCall(source, List())
}

mergeList(listOfFood)

def incrementElementWithTail(inc: Int, source: List[Int]): List[Int] = {
  @tailrec
  def tailRecCall(inc: Int, source: List[Int], result: List[Int]): List[Int] = {
    if (source.isEmpty) result
    else tailRecCall(inc, source.tail, result ++ List(source.head + 1))
  }
  tailRecCall(inc, source, List())
}

incrementElementWithTail(1, listOfInt)

def multiplyElement(m: Int, source: List[Int]): List[Int] = {
  @tailrec
  def tailRecCall(m: Int, source: List[Int], result: List[Int]): List[Int] = {
    if (source.isEmpty) result
    else tailRecCall(m, source.tail, result ++ List(source.head * m))
  }
  tailRecCall(m, source, List())
}

multiplyElement(2, listOfInt)

def fibgenrec(l: Int, source: List[Int]): List[Int] = {
  @tailrec
  def tailRecCall(l: Int, result: List[Int]): List[Int] = {
    if (l == 0) result
    else {
      val seclastidx = result.length - 2
      val lastidx = result.length - 1
      tailRecCall(l - 1, result ++ List(result(seclastidx) + result(lastidx)))
    }
  }
  tailRecCall(l - 2, source)
}

fibgenrec(20, List(1, 1))