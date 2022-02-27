// TODO: provide three functions here
fun idFun(x: Int) = x
fun halfFun(x: Int) = x / 2
fun zeroFun(x: Int) = 0

fun generate(functionName: String): (Int) -> Int {

    return when (functionName) {
        "identity" -> ::idFun
        "half" -> ::halfFun
        else -> ::zeroFun
    }
}