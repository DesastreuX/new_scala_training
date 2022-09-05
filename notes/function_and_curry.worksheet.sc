val listOfInt = List(1, 2, 3, 4, 5)

// function
def additive(int1: Int, int2: Int): Int = {
  int1 + int2
}

additive(1, 2)

// currying
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
