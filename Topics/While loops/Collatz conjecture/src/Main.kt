fun main() {
    var n = readln().toInt()
    print("$n")
    while (n != 1) {

        n = if (n % 2 == 0) n / 2 else n * 3 + 1
        print(" $n")
    }
}