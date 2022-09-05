class Person() {
  var name: String = ""
  var age: Int = 0
  var email: String = ""
  def printName() = println(s"${name} ${age} ${email}")
  def capitalizeName(): String = name.toUpperCase()
  def appendName(s: String): String = s"${name} ${s}"
  def ||(s: String): String = s"${name} ${s}"
  def introduce(s: String): String = s"${s} ${name}"
  def ->:(s: String): String = s"${s} ${name}"
}

val poom = Person("Poom", 23, "poom@gmail.com")
poom.printName()
poom.name
poom.name = "Perat"
poom.name
// poom.capitalizeName
poom.appendName("heh")
poom.introduce("my name is")
"my name is" ->: poom

val putt = Person(name = "Putt", email = "putt@gmail.com")
putt.printName()
putt appendName "huh"
putt || "meh"

// singleton class
object Tools {
  def doSomething() = println("do something!")
}

Tools.doSomething()

// companion object
object Person {
  def apply(name: String, age: Int, email: String): Person = {
    var person = new Person
    person.name = name
    person.age = age
    person.email = email
    person
  }
  def apply(name: String, email: String): Person = {
    var person = new Person
    person.name = name
    person.age = 18
    person.email = email
    person
  }
}

val phone = Person.apply("Phone", 30, "phone@email.com")
phone.printName()

class Task(var title: String, var status: Int)
val buyBanana = new Task("Buy Banana", 1)
val buyMoreBanana = new Task("Buy Banana", 1)

buyBanana == buyMoreBanana

object State extends scala.Enumeration {
  type State = Value
  val Todo, Doing, Done = Value
}
import State._
case class CaseTask(val title: String, val status: State)
object CaseTask {
  def next(task: CaseTask): CaseTask = {
    task.status match
      case Todo => task.copy(status = Doing)
      case Doing => task.copy(status = Done)
      case _ => task.copy(status = Done)
  }
}

val buyCaseBanana = CaseTask("Buy Banana", State.Todo)
val buyMoreCaseBanana = CaseTask("Buy Banana", Todo)

buyCaseBanana == buyMoreCaseBanana

val buyingCaseBanana = buyCaseBanana.copy(status = Doing)
val doneCaseBanana = buyCaseBanana.copy(status = Done)

val callMom = new CaseTask("call mom", Todo)
val callingMom = CaseTask.next(callMom)
val calledMom = CaseTask.next(callingMom)

trait TaskTrait {
  val id: Int
  def next(): TaskTrait
  def previous(): TaskTrait
}

val i = 1

i match
  case 1 => println("one")
  case 2 => println("two")
  case _ => println("other")

val binding = i match
  case 1 => println("one")
  case 2 => println("two")
  case _ => println("other")

fizzBuzz(1) == "1"
fizzBuzz(2) == "2"
fizzBuzz(3) == "Fizz"
fizzBuzz(4) == "4"
fizzBuzz(5) == "Buzz"
fizzBuzz(6) == "Fizz"
fizzBuzz(7) == "7"
fizzBuzz(8) == "8"
fizzBuzz(9) == "Fizz"
fizzBuzz(10) == "Buzz"
// ..
fizzBuzz(15) == "FizzBuzz"

def fizzBuzz(value: Int): String = {
  val (fizz, buzz) = (value % 3 == 0, value % 5 == 0)
  (fizz, buzz) match
    case (true, true) => "FizzBuzz"
    case (true, false) => "Fizz"
    case (false, true) => "Buzz"
    case _ => value.toString()
  // if (value % 15 == 0) "FizzBuzz"
  // else if (value % 3 == 0) "Fizz"
  // else if (value % 5 == 0) "Buzz"
  // else value.toString()
}

