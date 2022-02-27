fun main() {
    val str = readln()
    var sum1 = 0
    var sum2 = 0
    for (x in 0 until str.length / 2) {
        sum1 += str[x].toInt() 
    }
   
    for (x in str.length / 2 until str.length) {
        sum2 += str[x].toInt() 
    }
    print(if (sum1 == sum2) "YES" else "NO")
}
