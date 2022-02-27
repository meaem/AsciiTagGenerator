fun main() {
    val txt = readln()
    for (c in txt) {
        if (c.isDigit()) {
            print(c)
            break
        }
    }
}