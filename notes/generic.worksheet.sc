abstract class Asset {
    def name: String
    def unitPrice: Double
    // def printAsset: String = s"Name: ${name} Current Value: ${unitPrice}"
}

class AssetPrinter[-A <: Asset] {
    def printAsset(asset: A): String = s"Name: ${asset.name}. Current Value: ${asset.unitPrice}"
}

val digitalPrinter: AssetPrinter[DigitalAsset] = new AssetPrinter[DigitalAsset]
val cryptoPrinter: AssetPrinter[CryptoAsset] = digitalPrinter

class DigitalAsset(names: String, unitPrices: Double) extends Asset {
    def name: String = names

    def unitPrice: Double = unitPrices
}
class EquityAsset(names: String, unitPrices: Double) extends Asset {
    def name: String = names

    def unitPrice: Double = unitPrices
}

val lunar = new DigitalAsset("lunar", 30)

class SuperGeneric[A](asset: A) {
}

val s = new SuperGeneric("lunar")
// val w = new Wallet("lunar") -> not super generic

val lunarWallet = new Wallet(lunar)
lunarWallet.get_asset_name()

val doge = new DigitalAsset("Doge", 0.001)
// val dogeWallet = lunarWallet.replace(doge)
// dogeWallet.get_asset_name()

val mfec = new EquityAsset("MFEC", 20)
// val mfecWallet = dogeWallet.replace(mfec)
// mfecWallet.get_asset_name()

class CryptoAsset(names: String, unitPrices: Double) extends DigitalAsset(names, unitPrices) {
    override def name: String = names
    override def unitPrice: Double = unitPrices
}

class Wallet[+A <: DigitalAsset](asset: A) {
    // def replace(asset: Asset): Wallet = new Wallet(asset)
    def get_asset_name(): String = asset.name
}

val usdc = new CryptoAsset("USDC", 35)

cryptoPrinter.printAsset(usdc)

val digitalWallet: Wallet[DigitalAsset] = new Wallet[CryptoAsset](usdc)
// variance property
// 1. invariance -> if A < B then List[A] != List[B] (cannot reassign)    -- Ex. Any function with type [A]
// 2. covariance -> +A => if A < B then List[A] = List[B] (reassign List[B] back into List[A])   -- Ex. Wallet
// 3. contravariance -> -A => if A < B then List[B] = List[A] (reassign List[A] back into List[B])   -- Ex. AssetPrinter