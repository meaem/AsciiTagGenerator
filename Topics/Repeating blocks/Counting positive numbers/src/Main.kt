fun main() {
    val n = readln().toInt()
    var count = 0
    repeat(n){
        if (readln().toInt()>0){
            count++
        }
    }
    println(count)
}