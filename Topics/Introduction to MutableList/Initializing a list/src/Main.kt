fun main() {
    val numbers = MutableList(100) {
        if (it in listOf(0, 9, 99)) it + 1 else 0
    }
    // do not touch the lines below 
    println(numbers.joinToString())
}