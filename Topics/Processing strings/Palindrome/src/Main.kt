fun main() {
    val s1 = readln().lowercase()
    var isPalindrom = s1.isNotEmpty()
    for (i in 0 until s1.length / 2) {
        if (s1[i] != s1[s1.lastIndex - i]) {
            isPalindrom = false
            break
        }
    }
    print(if (isPalindrom) "yes" else "no")

}