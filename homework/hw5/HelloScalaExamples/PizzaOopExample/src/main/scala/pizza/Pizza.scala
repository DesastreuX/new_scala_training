package pizza

case class Pizza (
    val crustSize: CrustSize,
    val crustType: CrustType,
    val toppings: Array[Topping]
) {

    def get_toppings(): Array[Topping] = toppings

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
        thisToppingsPrice + thisCrustSizePrice + thisCrustTypePrices
    }

}

object Pizza {
    def addTopping(current_pizza: Pizza, new_topping: Topping): Pizza = {
        current_pizza.copy(toppings = (current_pizza.get_toppings() ++ Array(new_topping)))
    }

    def removeTopping(current_pizza: Pizza, remove_topping: Topping): Pizza = {
        current_pizza.copy(toppings = current_pizza.get_toppings().filter(_ != remove_topping))
    }

    def removeAllToppings(current_pizza: Pizza, remove_topping: Topping): Pizza = {
        current_pizza.copy(toppings = Array())
    }
}
