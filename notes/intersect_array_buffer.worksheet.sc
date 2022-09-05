val sampleMasterTopping = Map("A" -> 1, "B" -> 2, "C" -> 3, "D" -> 4, "F" -> 5)
val sampleSelectedTopping = List("A", "C")

val inter = sampleMasterTopping.view.filterKeys(sampleSelectedTopping.toSet).toMap
inter.values.sum
inter.foldLeft(0)(_+_._2)
sampleMasterTopping("A")

val sample = List("A", "AA", "AAA")
sample.map(word => word.length()).sum