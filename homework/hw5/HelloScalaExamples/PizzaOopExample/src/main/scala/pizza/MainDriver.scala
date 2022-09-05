package pizza

object MainDriver extends App {

    val p1 = new Pizza (
        MediumCrustSize,
        ThinCrustType,
        Array(Cheese)
    )

    val p2 = new Pizza (
        LargeCrustSize,
        ThinCrustType,
        Array(Cheese, Pepperoni, Sausage)
    )

    val address = new Address (
        "123 Main Street",
        "Apt. 1",
        "Talkeetna",
        "Alaska",
        "99676"
    )

    val customer = new Customer (
        "Alvin Alexander",
        "907-555-1212",
        address
    )

    val o = new Order(
        Array(p1, p2),
        customer
    )

    val new_o = Order.addPizza(o, new Pizza (
            SmallCrustSize,
            ThinCrustType,
            Array(Cheese, Mushrooms)
        ))

    // o.addPizza(
    //     new Pizza (
    //         SmallCrustSize,
    //         ThinCrustType,
    //         Array(Cheese, Mushrooms)
    //     )
    // )

    // print the order
    o.printOrder

}



