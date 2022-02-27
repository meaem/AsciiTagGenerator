fun main() {
    val n = readln().toInt()
    print(
        when {
            n < 10 -> "1"
            n < 100 -> "2"
            n < 1000 -> "3"
            else -> "4"
        }
    )
}