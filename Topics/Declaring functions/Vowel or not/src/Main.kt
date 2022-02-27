// write your function here
fun isVowel(x: Char) = x.lowercaseChar() in "aeiou"

fun main() {
    val letter = readLine()!!.first()

    println(isVowel(letter))
}