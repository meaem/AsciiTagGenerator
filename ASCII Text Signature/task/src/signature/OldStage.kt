package signature

fun old_main() {
    //const val LETTER_WIDTH = 6
//val lines = listOf(
//    "____  ___   ____  ___   ____  ____  ____  _  _  _      _    _  _  _     _  _  _  _  ____  ___   ____  ____  ____  ___   _  _  _  _  _ _ _ _  _  _   _ ___   ",
//    "|__|  |__]  |     |  \\  |___  |___  | __  |__|  |      |    |_/   |     |\\/|  |\\ |  |  |  |__]  |  |  |__/  [__    |    |  |  |  |  | | |  \\/    \\_/    /   ",
//    "|  |  |__]  |___  |__/  |___  |     |__]  |  |  |     _|    | \\_  |___  |  |  | \\|  |__|  |     |_\\|  |  \\  ___]   |    |__|   \\/   |_|_| _/\\_    |    /__  "
//)
//val widths = MutableList(26) {
//    5
//}


//fun main1() {
//    val (fn, ln) = readln().split(" ").map { it.uppercase() }
//    val status = readln()
//    widths[('i' - 'a')] = 2
//    widths[('j' - 'a')] = 3
//    widths[('t' - 'a')] = 4
//    widths[('w' - 'a')] = 6
//    widths[('y' - 'a')] = 6
//
//
//    var tagWidth = 5
//    for (c in fn) {
//        val letterIdx = c - 'A'
//        tagWidth += widths[letterIdx]
//    }
//    for (i in 0 until ln.lastIndex) {
//        val c = ln[i]
//        val letterIdx = c - 'A'
//        tagWidth += widths[letterIdx]
//    }
//    val c = ln.last()
//    val letterIdx = c - 'A'
//    tagWidth += widths[letterIdx] - 1
//
//    val borderWidth = max(tagWidth, status.length) + 6
//    println("*".repeat(borderWidth))
//    for (line in lines) {
//        val leftSpaces = (borderWidth - tagWidth - 2) / 2
//        print("*")
//        print(" ".repeat(leftSpaces))
//        for (c in fn) {
//            val letterIdx = c - 'A'
//            val start = letterIdx * LETTER_WIDTH
//            print(line.substring(start, start + widths[letterIdx]))
//        }
//        print(" ".repeat(5))
//        for (i in 0 until ln.lastIndex) {
//            val c = ln[i]
//            val letterIdx = c - 'A'
//            val start = letterIdx * LETTER_WIDTH
//            print(line.substring(start, start + widths[letterIdx]))
//        }
//        val lc = ln.last()
//        val lastletterIdx = lc - 'A'
//        val start = lastletterIdx * LETTER_WIDTH
//        print(line.substring(start, start + widths[lastletterIdx] - 1))
//        val rightSpaces = borderWidth - leftSpaces - tagWidth - 2
//        print(" ".repeat(rightSpaces))
//        print("*")
//        println()
//    }
//    print("*")
//    val leftSpaces = (borderWidth - status.length - 2) / 2
//    print(" ".repeat(leftSpaces))
//    print(status)
//    val rightSpaces = borderWidth - leftSpaces - status.length - 2
//    print(" ".repeat(rightSpaces))
//    println("*")
//    println("*".repeat(borderWidth))
//
//}
}