fun main() {
    val n = readln().toInt()
    val list = MutableList(n) {
        readln().toInt()
    }
    val m = readln().toInt()
    print(if (m in list) "YES" else "NO")
}