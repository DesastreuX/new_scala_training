object State extends scala.Enumeration {
  type State = Value
  val Todo, Doing, Done = Value
}
import State._

case class SelfTask(val title: String, val status: State) {
    def next(): SelfTask = {
        status match
            case Todo => this.copy(status = Doing)
            case Doing => this.copy(status = Done)
            case _ => this.copy(status = Done)
    }
}

val callMom = new SelfTask("call mom", Todo)
val callingMom = callMom.next()
val calledMom = callingMom.next()

trait SingletonState
case object SingletonTodo extends SingletonState
case object SingletonDoing extends SingletonState
case object SingletonDone extends SingletonState

case class SingletonTask(val title: String, val status: SingletonState) {
    def next(): SingletonTask = {
        status match
            case SingletonTodo => this.copy(status = SingletonDoing)
            case SingletonDoing => this.copy(status = SingletonDone)
            case _ => this.copy(status = SingletonDone)
    }
}

val callDad = new SingletonTask("call dad", SingletonTodo)
val callingDad = callDad.next()
val calledDad = callingDad.next()

trait StateClass(title: String) {
    def getTitle(): String = title
    def next(): StateClass
}
case class StateTodo(title: String) extends StateClass(title) {
    def next(): StateClass = StateDoing(title)
}
case class StateDoing(title: String) extends StateClass(title) {
    def next(): StateClass = StateDone(title)
}
case class StateDone(title: String) extends StateClass(title) {
    def next(): StateClass = StateTodo(title)
}

// abstract class StateClass{val title: String}
// case class StateTodo(title: String) extends StateClass {
//     def next(): StateClass = StateDoing(title)
// }
// case class StateDoing(title: String) extends StateClass {
//     def next(): StateClass = StateDone(title)
// }
// case class StateDone(title: String) extends StateClass {
//     def next(): StateClass = StateTodo(title)
// }

object StateClass {
    def next(state: StateClass): StateClass = {
        state match
            case StateTodo(title) => StateDoing(title)
            case StateDoing(title) => StateDone(title)
            case StateDone(title) => state
            // case _ => StateTodo(state.title)
    }
}

val hello = StateTodo("Hello")
val helloing = hello.next()
val helloed = helloing.next()
helloing.getTitle()

val newhello = StateTodo("New Hello")
val newhelloing = StateClass.next(newhello)
val newhelloed = StateClass.next(newhelloing)
