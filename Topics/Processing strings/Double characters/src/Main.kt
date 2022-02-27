fun main() {
    val s1 = readln()
    val s2 = s1.repeat(2).toCharArray()
    for (i in 0..s1.lastIndex) {
        s2[i * 2] = s1[i]
        s2[i * 2 + 1] = s1[i]
    }
    print(s2.joinToString(""))
}