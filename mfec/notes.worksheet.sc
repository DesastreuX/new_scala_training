import scala.annotation.tailrec
var x = 0
x = 2
println(x)
val y = 0

if (x < 0) {
  println("negative")
} else if (x == 0) {
  println("zero")
} else {
  println("positive")
}

val values = List("First", "Second", "Third")

for (value <- values) {
  println(value)
}

values.foreach(v => println(v))

val ints = List(1, 2, 3, 4, 5)

val doubles = for (i <- ints) yield i * 2

val fruits = List("apple", "banana", "lime", "orange")

val result =
  for {
    v <- fruits
    if v.length > 4
  } yield v

val listOfInt = List(1, 2, 3, 4, 5)
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

import scala.annotation.tailrec

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

// def fibgendyn(l: Int, source: List[Int]): List[Int] = {
//   for (w <- 0 to l) {
//     val seclastidx = source.length - 2
//     val lastidx = source.length - 1
//     source ++ List(source(seclastidx) + source(lastidx))
//   }
// }

// fibgendyn(20, List(1, 1))

def additive(int1: Int, int2: Int): Int = {
  int1 + int2
}

additive(1, 2)

def additivecurrying(int1: Int)(int2: Int): Int = {
  int1 + int2
}

additivecurrying(1)(2)

val addOne = additivecurrying(1) _

addOne(2)

val addTwo = additivecurrying(2) _

addTwo(2)

(addOne andThen addTwo)(2)

def mapFunctionToElement(
    op: Int
)(func: (Int, Int) => Int)(source: List[Int]): List[Int] = {
  if (source.isEmpty) List[Int]()
  else func(op, source.head) :: mapFunctionToElement(op)(func)(source.tail)
}

mapFunctionToElement(2)((x: Int, y: Int) => x - y)(listOfInt)

// value function
val multiply: (Int, Int) => Int = (x: Int, y: Int) => x * y

// currying
val multiplyTwo = mapFunctionToElement(2)(multiply) _

multiplyTwo(listOfInt)

val result_tetete = List("1", "2", "3", "4")
val titles = List("a", "b", "c")

result_tetete ++ titles
