package pizza

case class Order (
    val pizzas: Array[Pizza],
    val customer: Customer
) {

    def get_pizzas(): Array[Pizza] = pizzas

    // need to implement these
    def getBasePrice(): Int = {
        pizzas.map(_.getPrice(PriceList.toppingPrice, PriceList.crustSizePrice, PriceList.crustTypePrice)).sum
    }
    def getTaxes(): Double = {
        getBasePrice().toDouble * 0.07
    }
    def getTotalPrice(): Double = {
        getBasePrice() + getTaxes()
    }

    def printOrder(): Unit = {
        for (p <- pizzas) {
            println(p)
        }
        println("Base Price: " + getBasePrice().toString())
        println("Taxes: " + getTaxes().toString())
        println("Total Price: " + getTotalPrice().toString())
    }

}

object Order {
    def addPizza(current_order: Order, new_pizza: Pizza): Order = {
        current_order.copy(pizzas = current_order.get_pizzas() ++ Array(new_pizza))
    }

    def removePizza(current_order: Order, remove_pizza: Pizza): Order = {
        current_order.copy(pizzas = current_order.get_pizzas().filter(_ != remove_pizza))
    }
}
