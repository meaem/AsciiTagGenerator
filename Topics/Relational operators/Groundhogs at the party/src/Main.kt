const val TEN = 10
const val FIFTEEN = 15
const val TWENTY = 20
const val TWENTY_FIVE = 25
fun main() {
    val rees = readln().toInt()
    val isWeekend = readln().toBoolean()

    print(isWeekend && rees >= FIFTEEN && rees <= TWENTY_FIVE || !isWeekend && rees >= TEN && rees <= TWENTY)
}