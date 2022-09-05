package pizza

import scala.collection.mutable.ArrayBuffer

class Order (
    val pizzas: ArrayBuffer[Pizza],
    var customer: Customer
) {

    def addPizza(p: Pizza): Unit = {
        pizzas += p
    }

    def removePizza(p: Pizza): Unit = {
        pizzas -= p
    }

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
