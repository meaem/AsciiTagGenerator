fun main() {
    val ticket = readln()
    var sum1 = 0
    var sum2 = 0
    
    for (i in 0..2){
        sum1 += ticket[i].toInt()
        sum2 += ticket[ticket.lastIndex - i].toInt()
    }
    print(if (sum1 == sum2) "Lucky" else "Regular" )
}
