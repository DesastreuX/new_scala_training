import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure

object Main extends App {
  def makeDrink() = Future {
    println("making drink")
    Thread.sleep(1000)
    "Drink"
  }

  def bakePizza() = Future {
    println("baking pizza")
    Thread.sleep(2000)
    "Pizza"
  }

  def makePasta() = Future {
    println("making pasta")
    Thread.sleep(3000)
    "Pasta"
  }

  def currentTime = System.currentTimeMillis()
  def deltaTime(t0: Long) = currentTime - t0

  val start_time = currentTime
  // await
  // val drink = Await.ready(makeDrink, 4.second)
  // val pizza = Await.ready(bakePizza, 4.second)
  // val pasta = Await.ready(makePasta, 4.second)

  // drink.value match {
  //   case Some(value) => println(value)
  //   case _ => println("Nope")
  // }
  // println(pizza.value.getOrElse("Nope"))
  // println(pasta.value.getOrElse("Nope"))

  // val start_make_drink = currentTime
  // makeDrink.onComplete {
  //   case Success(value) => {
  //     println(value)
  //     println(s"Make Drink Time: ${deltaTime(start_make_drink)}")
  //   }
  //   case Failure(exception) => println("Nopeee")
  // }

  // val start_bake_pizza = currentTime
  // bakePizza.onComplete {
  //   case Success(value) => {
  //     println(value)
  //     println(s"Bake Pizza Time: ${deltaTime(start_bake_pizza)}")
  //   }
  //   case Failure(exception) => println("Nopeee")
  // }

  // val start_make_pasta = currentTime
  // makePasta.onComplete {
  //   case Success(value) => {
  //     println(value)
  //     println(s"Make Pasta Time: ${deltaTime(start_make_pasta)}")
  //   }
  //   case Failure(exception) => println("Nopeee")
  // }

  val mainCourse = for {
    pizza <- bakePizza()
    pasta <- makePasta()
  } yield (pizza, pasta)

  // val start_main_course = currentTime
  // mainCourse.onComplete {
  // case Success(value) => {
  //   println(value)
  //   println(s"Main Course Time: ${deltaTime(start_main_course)}")
  // }
  // case Failure(exception) => println("Nopeee")

  def timeDuration(menu: Future[String]) = {
    val t0 = currentTime
    menu.onComplete {
      case Success(value) => {
        println(value)
        println(s"${value} Time: ${deltaTime(t0)}")
      } 
      case Failure(exception) => println("Nopeee")
    }
  }

  timeDuration(makeDrink())
  timeDuration(bakePizza())
  timeDuration(makePasta())
  // timeDuration(mainCourse) broken

  Thread.sleep(1000)
  println("A ..."); Thread.sleep(1000)
  println("B ..."); Thread.sleep(1000)
  println("C ..."); Thread.sleep(1000)
  println("D ..."); Thread.sleep(1000)
  println("E ..."); Thread.sleep(1000)
  println("F ..."); Thread.sleep(1000)


  println(s"Total time: ${deltaTime(start_time)}")
}
