import kotlin.math.absoluteValue

// write your code here
fun getLastDigit(a: Int) = a.absoluteValue % 10

/* Do not change code below */
fun main() {
    val a = readLine()!!.toInt()
    println(getLastDigit(a))
}