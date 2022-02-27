fun main() {
    var num = readLine()
    if (num != null) {
        var max = num.toInt()
        var pos = 1
        var maxpos = 1
        num = readLine()
        while (num != null) {
            pos++
            if (max < num.toInt()) {
                max = num.toInt()
                maxpos = pos
            }
            num = readLine()
        }
        println("$max $maxpos")
    }
}