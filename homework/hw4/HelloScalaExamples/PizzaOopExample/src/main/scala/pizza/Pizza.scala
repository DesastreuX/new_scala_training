package pizza

import scala.collection.mutable.ArrayBuffer

class Pizza (
    var crustSize: CrustSize,
    var crustType: CrustType,
    val toppings: ArrayBuffer[Topping]
) {

    def addTopping(t: Topping): Unit = { toppings += t }
    def removeTopping(t: Topping): Unit = { toppings -= t }
    def removeAllToppings(): Unit = { toppings.clear() }

    override def toString(): String = {
        val toppingsString = for (t <- toppings) yield t
        s"""Pizza:
           |  Crust Size: $crustSize
           |  Crust Type: $crustType
           |  $toppingsString
         """.stripMargin
    }

    def getPrice(
        toppingsPrices: Map[Topping, Int],
        crustSizePrices: Map[CrustSize, Int],
        crustTypePrices: Map[CrustType, Int]
    ): Int = {
        val thisToppingsPrice = toppingsPrices.view.filterKeys(toppings.toSet).toMap.values.sum
        val thisCrustSizePrice = crustSizePrices.get(crustSize).getOrElse(0)
        val thisCrustTypePrices = crustTypePrices.get(crustType).getOrElse(0)
        // val thisCrustSizePrice = if (crustSizePrices.contains(crustSize)) crustSizePrices(crustSize) else 0
        // val thisCrustTypePrices = if (crustTypePrices.contains(crustType)) crustTypePrices(crustType) else 0
        thisToppingsPrice + thisCrustSizePrice + thisCrustTypePrices
        // toppingsPrices.foldLeft(0)(_+_._2) + crustSizePrices.foldLeft(0)(_+_._2) + crustTypePrices.foldLeft(0)(_+_._2)
    }

}


