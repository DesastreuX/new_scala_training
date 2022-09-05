import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Task() {
  var id: Int = -1
  var name: String = ""
  var detail: String = ""
  var state: Int = 0
  var stateDate: List[String] = List() // index 0 -> todo, 1 -> doing, 2 -> done
  val stateMap: Map[Int, String] =
    Map(0 -> "Todo", 1 -> "Doing", 2 -> "Done")

  def progressForward(): Boolean = {
    if (state != 2) {
      state += 1
      stateDate = stateDate.updated(
        state,
        DateTimeFormatter
          .ofPattern("dd/MM/yy-HH:mm:ss")
          .format(LocalDateTime.now())
      )
      println(s"Task: ${name} progress to ${stateMap(state)}")
      true
    } else {
      println(s"Task '${name}' is already in DONE!!!")
      false
    }
  }

  def rollbackProgress(): Boolean = {
    if (state != 0) {
      stateDate = stateDate.updated(state, "")
      state -= 1
      println(s"Task: ${name} rollback to ${stateMap(state)}")
      true
    } else {
      println("Task is already in TODO!!!")
      false
    }
  }

  def getState(): String = stateMap(state)

  def getTask(): String = {
    s"Task: ${name}\n" +
      s"Detail: ${detail}\n" +
      s"State: ${stateMap(state)}\n" +
      s"Date: ${stateDate(state)}"
  }

  def getTaskHistory(): String = {
    s"Task: ${name}\n" +
      s"Detail: ${detail}\n" +
      s"State: ${stateMap(state)}\n" +
      s"Todo Date: ${stateDate(0)}\n" +
      s"Doing Date: ${stateDate(1)}\n" +
      s"Done Date: ${stateDate(2)}"
  }
}

object Task {

  def create(
      name: String
  ): Task = {
    var task = new Task
    task.id = -1
    task.name = name
    task.state = 0
    task.stateDate = List(
      DateTimeFormatter
        .ofPattern("dd/MM/yy-HH:mm:ss")
        .format(LocalDateTime.now()),
      "",
      ""
    )
    task
  }

  def create(
      name: String,
      detail: String
  ): Task = {
    var task = new Task
    task.id = -1
    task.name = name
    task.detail = detail
    task.state = 0
    task.stateDate = List(
      DateTimeFormatter
        .ofPattern("dd/MM/yy-HH:mm:ss")
        .format(LocalDateTime.now()),
      "",
      ""
    )
    task
  }

  def create(id: Int, name: String, detail: String): Task = {
    var task = new Task
    task.id = -1
    task.name = name
    task.detail = detail
    task.state = 0
    task.stateDate = List(
      DateTimeFormatter
        .ofPattern("dd/MM/yy-HH:mm:ss")
        .format(LocalDateTime.now()),
      "",
      ""
    )
    task
  }
}

class TaskBoard() {
  var boardName: String = ""
  var latestId: Int = 0
  var stateIdMap: List[Map[Int, Task]] =
    List(
      Map(),
      Map(),
      Map(),
      Map()
    ) // index 0 -> todo, 1 -> doing, 2 -> done, 3 -> deleted
  var taskStateMap: Map[Int, Int] = Map() // id -> state
  var nameIdMap: Map[String, Int] = Map() // name -> id
  val stateMap: Map[Int, String] =
    Map(0 -> "Todo", 1 -> "Doing", 2 -> "Done", 3 -> "Deleted")

  def addTaskToBoard(newTask: Task): Boolean = {
    if (nameIdMap.contains(newTask.name)) {
      println(s"Error: Task '${newTask.name}' already exist!!")
      false
    } else {
      stateIdMap = stateIdMap.updated(
        newTask.state,
        stateIdMap(newTask.state) + (latestId -> newTask)
      )
      taskStateMap += (latestId -> newTask.state)
      nameIdMap += (newTask.name -> latestId)
      latestId += 1
      true
    }
  }

  def removeTaskFromBoard(taskName: String): Boolean = {
    if (nameIdMap.contains(taskName)) {
      val taskId = nameIdMap(taskName)
      val currentTaskState = taskStateMap(taskId)
      if (currentTaskState < 3) {
        val selectedtask = stateIdMap(currentTaskState)(taskId)
        stateIdMap = stateIdMap.updated(
          currentTaskState,
          stateIdMap(currentTaskState).-(taskId)
        )
        stateIdMap =
          stateIdMap.updated(3, stateIdMap(3) + (taskId -> selectedtask))
        taskStateMap = taskStateMap.updated(taskId, 3)
        true
      } else {
        println(s"Task '${taskName}' is already in DELETED!!!")
        false
      }
    } else {
      println(s"Error: Task '${taskName}' does not exist!!!")
      false
    }
  }

  def recoverTask(taskName: String): Boolean = {
    if (nameIdMap.contains(taskName)) {
      val taskId = nameIdMap(taskName)
      val currentTaskState = taskStateMap(taskId)
      if (currentTaskState == 3) {
        val selectedtask = stateIdMap(currentTaskState)(taskId)
        val taskPreviousState = selectedtask.state
        stateIdMap = stateIdMap.updated(
          currentTaskState,
          stateIdMap(currentTaskState).-(taskId)
        )
        stateIdMap = stateIdMap.updated(
          taskPreviousState,
          stateIdMap(taskPreviousState) + (taskId -> selectedtask)
        )
        taskStateMap = taskStateMap.updated(taskId, taskPreviousState)
        true
      } else {
        println(s"Task '${taskName}' was NOT DELETED!!!")
        false
      }
    } else {
      println(s"Error: Task '${taskName}' does not exist!!!")
      false
    }
  }

  def progressTask(taskName: String): Boolean = {
    if (nameIdMap.contains(taskName)) {
      val taskId = nameIdMap(taskName)
      val currentTaskState = taskStateMap(taskId)
      val nextTaskState = currentTaskState + 1
      if (currentTaskState < 2) {
        stateIdMap(currentTaskState)(taskId).progressForward()
        val selectedtask = stateIdMap(currentTaskState)(taskId)
        stateIdMap = stateIdMap.updated(
          currentTaskState,
          stateIdMap(currentTaskState).-(taskId)
        )
        stateIdMap = stateIdMap.updated(
          nextTaskState,
          stateIdMap(nextTaskState) + (taskId -> selectedtask)
        )
        taskStateMap = taskStateMap.updated(taskId, nextTaskState)
        true
      } else {
        println(s"Task '${taskName}' is already in DONE!!!")
        false
      }
    } else {
      println(s"Error: Task '${taskName}' does not exist!!!")
      false
    }
  }

  def rollbackTask(taskName: String): Boolean = {
    if (nameIdMap.contains(taskName)) {
      val taskId = nameIdMap(taskName)
      val currentTaskState = taskStateMap(taskId)
      val previousTaskState = currentTaskState - 1
      if (currentTaskState > 0) {
        stateIdMap(currentTaskState)(taskId).rollbackProgress()
        val selectedtask = stateIdMap(currentTaskState)(taskId)
        stateIdMap = stateIdMap.updated(
          currentTaskState,
          stateIdMap(currentTaskState).-(taskId)
        )
        stateIdMap = stateIdMap.updated(
          previousTaskState,
          stateIdMap(previousTaskState) + (taskId -> selectedtask)
        )
        taskStateMap = taskStateMap.updated(taskId, previousTaskState)
        true
      } else {
        println(s"Task '${taskName}' is already in DONE!!!")
        false
      }
    } else {
      println(s"Error: Task '${taskName}' does not exist!!!")
      false
    }
  }

  def getBoard(): String = {
    var ret: String = ""
    for (index <- (0 until 3)) {
      ret = ret.concat(
        "=====================================\n" +
          s"                ${stateMap(index)}               \n" +
          "=====================================\n"
      )
      if (index < stateIdMap.length) {
        for ((key, value) <- stateIdMap(index)) {
          ret = ret.concat(value.getTask())
          ret = ret.concat("\n\n")
        }
      }
      // ret = ret.concat("=====================================\n\n")
    }
    ret
  }

  def getBoardWithTaskHistory(): String = {
    var ret: String = ""
    for (index <- (0 until 3)) {
      ret = ret.concat(
        "\n=====================================\n" +
          s"                ${stateMap(index)}               \n" +
          "=====================================\n"
      )
      if (index < stateIdMap.length) {
        for ((key, value) <- stateIdMap(index)) {
          ret = ret.concat(value.getTaskHistory())
          ret = ret.concat("\n--------------------------------------\n")
        }
      }
      ret = ret.trim()
    }
    ret
  }
}

object TaskBoard {
  def createBoard(name: String): TaskBoard = {
    var board = new TaskBoard
    board.boardName = name
    board.latestId = 0
    board.stateIdMap = List(Map(), Map(), Map(), Map())
    board.taskStateMap = Map()
    board.nameIdMap = Map()
    board
  }
}

val firstTask = Task.create(name = "MyFirstTask")
firstTask.getTask()
firstTask.getState()
firstTask.progressForward()
firstTask.getState()
firstTask.progressForward()
firstTask.getState()
firstTask.progressForward()
firstTask.getState()
firstTask.getTaskHistory()
firstTask.rollbackProgress()
firstTask.getState()
firstTask.rollbackProgress()
firstTask.getState()
firstTask.rollbackProgress()
firstTask.getState()
firstTask.getTaskHistory()

val secTask = Task.create(name = "MySecondTask")
val thiTask = Task.create(name = "MyThirdTask")
val fouTask = Task.create(name = "MyFourthTask")

val dumbboard = TaskBoard.createBoard("dumbboard")
dumbboard.addTaskToBoard(firstTask)
dumbboard.addTaskToBoard(secTask)
dumbboard.addTaskToBoard(thiTask)
dumbboard.addTaskToBoard(fouTask)
println(dumbboard.getBoard())
dumbboard.progressTask("MyFirstTask")
println(dumbboard.getBoard())
dumbboard.progressTask("MyFirstTask")
dumbboard.progressTask("MyFirstTask")
println(dumbboard.getBoard())
dumbboard.rollbackTask("MyFirstTask")
println(dumbboard.getBoard())
dumbboard.getBoardWithTaskHistory()
val fifTask = Task.create(name = "MyFifthTask")
fifTask.progressForward()
dumbboard.addTaskToBoard(fifTask)
println(dumbboard.getBoard())

dumbboard.removeTaskFromBoard("MyFirstTask")
println(dumbboard.getBoard())
dumbboard.recoverTask("MyFirstTask")
println(dumbboard.getBoard())
