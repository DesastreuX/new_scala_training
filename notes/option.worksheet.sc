def division(a: Long, b: Long): Option[Double] = {
    try {
        Some(a / b)
    } catch {
        case e: Exception => None
    }
}

division(6,3).getOrElse(0)
division(3,5)
division(3,0).getOrElse(0)
division(6,3) match {
    case Some(value) => println(value)
    case _ => print("Something's wrong!")
}