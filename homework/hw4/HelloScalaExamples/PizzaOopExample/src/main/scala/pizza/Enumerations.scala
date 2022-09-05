package pizza

sealed trait Topping
case object Cheese extends Topping
case object Pepperoni extends Topping
case object Sausage extends Topping
case object Mushrooms extends Topping 
case object Onions extends Topping 

sealed trait CrustSize
case object SmallCrustSize extends CrustSize 
case object MediumCrustSize extends CrustSize
case object LargeCrustSize extends CrustSize

sealed trait CrustType
case object RegularCrustType extends CrustType
case object ThinCrustType extends CrustType
case object ThickCrustType extends CrustType

object PriceList{
    val toppingPrice: Map[Topping, Int] = Map(Cheese -> 1, Pepperoni -> 3, Sausage -> 3, Mushrooms -> 2, Onions -> 1)
    val crustSizePrice: Map[CrustSize, Int] = Map(SmallCrustSize -> 6, MediumCrustSize -> 8, LargeCrustSize -> 10)
    val crustTypePrice: Map[CrustType, Int] = Map(RegularCrustType -> 4, ThinCrustType -> 3, ThickCrustType -> 5)
}