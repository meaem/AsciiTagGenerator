fun main() {
    val n = readln().toInt()
    val list = MutableList(n) {
        readln().toInt()
    }
    val m = readln().toInt() % list.size
//    val start = list.lastIndex - m
//    print(
//        (list.subList(start + 1, list.lastIndex + 1) +
//                list.subList(0, start + 1)).joinToString(" ")
//    )

    repeat(m) {
        list.add(0, list.removeLast())
    }
    print(list.joinToString(" "))
}