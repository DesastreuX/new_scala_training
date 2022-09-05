// variable - mutable
var x = 0
x = 2
println(x)

// value - immutable
val y = 0

// if else statement
if (x < 0) {
  println("negative")
} else if (x == 0) {
  println("zero")
} else {
  println("positive")
}

val values = List("First", "Second", "Third")

// for loop
for (value <- values) {
  println(value)
}

// for each
values.foreach(v => println(v))

val ints = List(1, 2, 3, 4, 5)

val doubles = for (i <- ints) yield i * 2

val fruits = List("apple", "banana", "lime", "orange")

// for yield
val result =
  for {
    v <- fruits
    if v.length > 4
  } yield v
