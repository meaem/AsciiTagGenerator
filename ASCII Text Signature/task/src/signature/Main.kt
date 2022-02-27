package signature

import java.io.File
import kotlin.math.max

fun loadLettersFile(
    filename: String,
    roman_letters: MutableList<MutableList<String>>,
    roman_letters_widths: MutableList<Int>
) {
    val romanFile = File(filename)
    var roman_letters_height = 0
//    var roman_letters_count = 0


    if (!romanFile.exists()) {
        println("$filename does not exist")
    } else {
        var letterLine = 0
        var currletter = -1

        romanFile.readLines().forEachIndexed { index, s ->
            if (index == 0) {

                val (x, y) = s.split(" ").map { it.toInt() }
                roman_letters_height = x
//                roman_letters_count=y
            } else {

                if (letterLine == 0 && s.isNotEmpty() && s.first().isLetter()) {
//                    println("--$s--")
                    val with = s.split(" ")[1].toInt()
                    roman_letters_widths += with
                    letterLine = 1
                    currletter++
                    roman_letters.add(mutableListOf())

                } else if (letterLine != 0) {
                    roman_letters[currletter].add(s.padEnd(roman_letters_widths[currletter]))
                    letterLine++
                    if (letterLine == roman_letters_height + 1) {
                        letterLine = 0
                    }
                }
            }
        }

        val tt = roman_letters.map {
            "mutableListOf(" + it.map {
                "\"${it.replace("\\","\\\\")
                    .replace("\"", "\\\"")
                    
                    }\"" }.joinToString(",") + ")"
        }.joinToString(",")
        println("val roman_letters=mutableListOf($tt)")


        val xx = roman_letters_widths.joinToString(",")
        println("val roman_letters_widths=mutableListOf($xx)")


    }
}

fun main() {


    val roman_letters = init_roman_letters()
    roman_letters.forEach { print( "${it.size},") }
    val roman_letters_widths = init_roman_letters_widths()
//    loadLettersFile("roman.txt", roman_letters, roman_letters_widths)

    val medium_letters = init_medium_letters()
    val medium_letters_widths = init_medium_letters_widths()
//    loadLettersFile("medium.txt", medium_letters, medium_letters_widths)

    val (fn, ln) = readln().split(" ")
    val status = readln()

//            println(roman_letters_widths)
//        print(roman_letters[50].joinToString("\n"))


//    println(medium_letters_widths)
//    print(medium_letters[50].joinToString("\n"))
    val tagWidth = calculteTagWidth(fn, ln, roman_letters_widths)
    val statusWidth = calculteStatusWidth(status, medium_letters_widths)
    val borderWidth = max(tagWidth, statusWidth) + 7

    printBorder(borderWidth)
    printTag(fn, ln, borderWidth, tagWidth, roman_letters)
    printStatus(status, borderWidth, statusWidth, medium_letters)
    printBorder(borderWidth)

}

fun printStatus(status: String, borderWidth: Int, statusWidth: Int, mediumLetters: MutableList<MutableList<String>>) {
    val leftSpaces = (borderWidth - statusWidth - 4+1) / 2

    for (lineIdx in 0 until 3) {

        print("88")
        print(" ".repeat(leftSpaces))

        for (i in 0 until status.lastIndex) {
            val c = status[i]
            if (c == ' ') {
                print(" ".repeat(5))
            } else {
                val letterIdx = getLetterIndex(c)
                print(mediumLetters[letterIdx][lineIdx])
            }
        }
        val lc = status.last()
        val lastletterIdx = getLetterIndex(lc)
        val ll = mediumLetters[lastletterIdx][lineIdx]
        print(ll.substring(0, ll.lastIndex))
        val rightSpaces = borderWidth - leftSpaces - statusWidth - 2
        print(" ".repeat(rightSpaces))
        print("88")
        println()
    }
}

fun printBorder(borderWidth: Int) {
    println("8".repeat(borderWidth + 2))
}

fun printTag(
    fn: String, ln: String,
    borderWidth: Int,
    tagWidth: Int,
    roman_letters: MutableList<MutableList<String>>
) {
    for (lineIdx in 0 until 10) {


        val leftSpaces = (borderWidth - tagWidth - 4+1) / 2
        print("88")
        print(" ".repeat(leftSpaces))
        for (c in fn) {
            val letterIdx = getLetterIndex(c)
            print(roman_letters[letterIdx][lineIdx])
        }
        print(" ".repeat(10))
        for (i in 0 until ln.lastIndex) {
            val c = ln[i]
            val letterIdx = getLetterIndex(c)
            print(roman_letters[letterIdx][lineIdx])
        }
        val lc = ln.last()
        val lastletterIdx = getLetterIndex(lc)
        val ll = roman_letters[lastletterIdx][lineIdx]
        print(ll.substring(0, ll.lastIndex))
        val rightSpaces = borderWidth - leftSpaces - tagWidth - 2
        print(" ".repeat(rightSpaces))
        print("88")
        println()
    }
}

fun getLetterIndex(c: Char) = if (c.isUpperCase()) c - 'A' + 26 else c - 'a'
fun calculteTagWidth(fn: String, ln: String, lettersWidths: MutableList<Int>): Int {

    var tagWidth = 10
    for (c in fn) {
        val letterIdx = getLetterIndex(c)
        tagWidth += lettersWidths[letterIdx]
    }
    for (i in 0 until ln.lastIndex) {
        val c = ln[i]
        val letterIdx = getLetterIndex(c)
        tagWidth += lettersWidths[letterIdx]
    }
    val c = ln.last()
    val letterIdx = getLetterIndex(c)
    tagWidth += lettersWidths[letterIdx] - 1
    return tagWidth

}

fun calculteStatusWidth(status: String, lettersWidths: MutableList<Int>): Int {
    var width = 0

    for (i in 0 until status.lastIndex) {
        val c = status[i]
        if (c == ' ') {
            width += 5
        } else {
            val letterIdx = getLetterIndex(c)
            width += lettersWidths[letterIdx]
        }
    }
    val c = status.last()
    val letterIdx = getLetterIndex(c)
    width += lettersWidths[letterIdx] - 1
    return width
}

fun init_roman_letters(): MutableList<MutableList<String>> {
    return mutableListOf(
        mutableListOf(
            "          ",
            "          ",
            " .oooo.   ",
            "`P  )88b  ",
            " .oP\"888  ",
            "d8(  888  ",
            "`Y888\"\"8o ",
            "          ",
            "          ",
            "          "
        ),
        mutableListOf(
            " .o8       ",
            "\"888       ",
            " 888oooo.  ",
            " d88' `88b ",
            " 888   888 ",
            " 888   888 ",
            " `Y8bod8P' ",
            "           ",
            "           ",
            "           "
        ),
        mutableListOf(
            "          ",
            "          ",
            " .ooooo.  ",
            "d88' `\"Y8 ",
            "888       ",
            "888   .o8 ",
            "`Y8bod8P' ",
            "          ",
            "          ",
            "          "
        ),
        mutableListOf(
            "      .o8  ",
            "     \"888  ",
            " .oooo888  ",
            "d88' `888  ",
            "888   888  ",
            "888   888  ",
            "`Y8bod88P\" ",
            "           ",
            "           ",
            "           "
        ),
        mutableListOf(
            "          ",
            "          ",
            " .ooooo.  ",
            "d88' `88b ",
            "888ooo888 ",
            "888    .o ",
            "`Y8bod8P' ",
            "          ",
            "          ",
            "          "
        ),
        mutableListOf(
            " .o88o. ",
            " 888 `\" ",
            "o888oo  ",
            " 888    ",
            " 888    ",
            " 888    ",
            "o888o   ",
            "        ",
            "        ",
            "        "
        ),
        mutableListOf(
            "           ",
            "           ",
            " .oooooooo ",
            "888' `88b  ",
            "888   888  ",
            "`88bod8P'  ",
            "`8oooooo.  ",
            "d\"     YD  ",
            "\"Y88888P'  ",
            "           "
        ),
        mutableListOf(
            "oooo        ",
            "`888        ",
            " 888 .oo.   ",
            " 888P\"Y88b  ",
            " 888   888  ",
            " 888   888  ",
            "o888o o888o ",
            "            ",
            "            ",
            "            "
        ),
        mutableListOf(
            " o8o  ",
            " `\"'  ",
            "oooo  ",
            "`888  ",
            " 888  ",
            " 888  ",
            "o888o ",
            "      ",
            "      ",
            "      "
        ),
        mutableListOf(
            "    o8o ",
            "    `\"' ",
            "   oooo ",
            "   `888 ",
            "    888 ",
            "    888 ",
            "    888 ",
            "    888 ",
            ".o. 88P ",
            "`Y888P  "
        ),
        mutableListOf(
            "oooo        ",
            "`888        ",
            " 888  oooo  ",
            " 888 .8P'   ",
            " 888888.    ",
            " 888 `88b.  ",
            "o888o o888o ",
            "            ",
            "            ",
            "            "
        ),
        mutableListOf(
            "oooo  ",
            "`888  ",
            " 888  ",
            " 888  ",
            " 888  ",
            " 888  ",
            "o888o ",
            "      ",
            "      ",
            "      "
        ),
        mutableListOf(
            "                  ",
            "                  ",
            "ooo. .oo.  .oo.   ",
            "`888P\"Y88bP\"Y88b  ",
            " 888   888   888  ",
            " 888   888   888  ",
            "o888o o888o o888o ",
            "                  ",
            "                  ",
            "                  "
        ),
        mutableListOf(
            "            ",
            "            ",
            "ooo. .oo.   ",
            "`888P\"Y88b  ",
            " 888   888  ",
            " 888   888  ",
            "o888o o888o ",
            "            ",
            "            ",
            "            "
        ),
        mutableListOf(
            "          ",
            "          ",
            " .ooooo.  ",
            "d88' `88b ",
            "888   888 ",
            "888   888 ",
            "`Y8bod8P' ",
            "          ",
            "          ",
            "          "
        ),
        mutableListOf(
            "           ",
            "           ",
            "oo.ooooo.  ",
            " 888' `88b ",
            " 888   888 ",
            " 888   888 ",
            " 888bod8P' ",
            " 888       ",
            "o888o      ",
            "           "
        ),
        mutableListOf(
            "           ",
            "           ",
            " .ooooo oo ",
            "d88' `888  ",
            "888   888  ",
            "888   888  ",
            "`V8bod888  ",
            "      888. ",
            "      8P'  ",
            "      \"    "
        ),
        mutableListOf(
            "         ",
            "         ",
            "oooo d8b ",
            "`888\"\"8P ",
            " 888     ",
            " 888     ",
            "d888b    ",
            "         ",
            "         ",
            "         "
        ),
        mutableListOf(
            "         ",
            "         ",
            " .oooo.o ",
            "d88(  \"8 ",
            "`\"Y88b.  ",
            "o.  )88b ",
            "8\"\"888P' ",
            "         ",
            "         ",
            "         "
        ),
        mutableListOf(
            "    .   ",
            "  .o8   ",
            ".o888oo ",
            "  888   ",
            "  888   ",
            "  888 . ",
            "  \"888\" ",
            "        ",
            "        ",
            "        "
        ),
        mutableListOf(
            "            ",
            "            ",
            "oooo  oooo  ",
            "`888  `888  ",
            " 888   888  ",
            " 888   888  ",
            " `V88V\"V8P' ",
            "            ",
            "            ",
            "            "
        ),
        mutableListOf(
            "            ",
            "            ",
            "oooo    ooo ",
            " `88.  .8'  ",
            "  `88..8'   ",
            "   `888'    ",
            "    `8'     ",
            "            ",
            "            ",
            "            "
        ),
        mutableListOf(
            "                 ",
            "                 ",
            "oooo oooo    ooo ",
            " `88. `88.  .8'  ",
            "  `88..]88..8'   ",
            "   `888'`888'    ",
            "    `8'  `8'     ",
            "                 ",
            "                 ",
            "                 "
        ),
        mutableListOf(
            "            ",
            "            ",
            "oooo    ooo ",
            " `88b..8P'  ",
            "   Y888'    ",
            " .o8\"'88b   ",
            "o88'   888o ",
            "            ",
            "            ",
            "            "
        ),
        mutableListOf(
            "            ",
            "            ",
            "oooo    ooo ",
            " `88.  .8'  ",
            "  `88..8'   ",
            "   `888'    ",
            "    .8'     ",
            ".o..P'      ",
            "`Y8P'       ",
            "            "
        ),
        mutableListOf(
            "           ",
            "           ",
            "  oooooooo ",
            " d'\"\"7d8P  ",
            "   .d8P'   ",
            " .d8P'  .P ",
            "d8888888P  ",
            "           ",
            "           ",
            "           "
        ),
        mutableListOf(
            "      .o.       ",
            "     .888.      ",
            "    .8\"888.     ",
            "   .8' `888.    ",
            "  .88ooo8888.   ",
            " .8'     `888.  ",
            "o88o     o8888o ",
            "                ",
            "                ",
            "                "
        ),
        mutableListOf(
            "oooooooooo.  ",
            "`888'   `Y8b ",
            " 888     888 ",
            " 888oooo888' ",
            " 888    `88b ",
            " 888    .88P ",
            "o888bood8P'  ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "  .oooooo.   ",
            " d8P'  `Y8b  ",
            "888          ",
            "888          ",
            "888          ",
            "`88b    ooo  ",
            " `Y8bood8P'  ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "oooooooooo.   ",
            "`888'   `Y8b  ",
            " 888      888 ",
            " 888      888 ",
            " 888      888 ",
            " 888     d88' ",
            "o888bood8P'   ",
            "              ",
            "              ",
            "              "
        ),
        mutableListOf(
            "oooooooooooo ",
            "`888'     `8 ",
            " 888         ",
            " 888oooo8    ",
            " 888    \"    ",
            " 888       o ",
            "o888ooooood8 ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "oooooooooooo ",
            "`888'     `8 ",
            " 888         ",
            " 888oooo8    ",
            " 888    \"    ",
            " 888         ",
            "o888o        ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "  .oooooo.    ",
            " d8P'  `Y8b   ",
            "888           ",
            "888           ",
            "888     ooooo ",
            "`88.    .88'  ",
            " `Y8bood8P'   ",
            "              ",
            "              ",
            "              "
        ),
        mutableListOf(
            "ooooo   ooooo ",
            "`888'   `888' ",
            " 888     888  ",
            " 888ooooo888  ",
            " 888     888  ",
            " 888     888  ",
            "o888o   o888o ",
            "              ",
            "              ",
            "              "
        ),
        mutableListOf(
            "ooooo ",
            "`888' ",
            " 888  ",
            " 888  ",
            " 888  ",
            " 888  ",
            "o888o ",
            "      ",
            "      ",
            "      "
        ),
        mutableListOf(
            "   oooo ",
            "   `888 ",
            "    888 ",
            "    888 ",
            "    888 ",
            "    888 ",
            ".o. 88P ",
            "`Y888P  ",
            "        ",
            "        "
        ),
        mutableListOf(
            "oooo    oooo ",
            "`888   .8P'  ",
            " 888  d8'    ",
            " 88888[      ",
            " 888`88b.    ",
            " 888  `88b.  ",
            "o888o  o888o ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "ooooo        ",
            "`888'        ",
            " 888         ",
            " 888         ",
            " 888         ",
            " 888       o ",
            "o888ooooood8 ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "ooo        ooooo ",
            "`88.       .888' ",
            " 888b     d'888  ",
            " 8 Y88. .P  888  ",
            " 8  `888'   888  ",
            " 8    Y     888  ",
            "o8o        o888o ",
            "                 ",
            "                 ",
            "                 "
        ),
        mutableListOf(
            "ooooo      ooo ",
            "`888b.     `8' ",
            " 8 `88b.    8  ",
            " 8   `88b.  8  ",
            " 8     `88b.8  ",
            " 8       `888  ",
            "o8o        `8  ",
            "               ",
            "               ",
            "               "
        ),
        mutableListOf(
            "  .oooooo.   ",
            " d8P'  `Y8b  ",
            "888      888 ",
            "888      888 ",
            "888      888 ",
            "`88b    d88' ",
            " `Y8bood8P'  ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "ooooooooo.   ",
            "`888   `Y88. ",
            " 888   .d88' ",
            " 888ooo88P'  ",
            " 888         ",
            " 888         ",
            "o888o        ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            "  .oooooo.      ",
            " d8P'  `Y8b     ",
            "888      888    ",
            "888      888    ",
            "888      888    ",
            "`88b    d88b    ",
            " `Y8bood8P'Ybd' ",
            "                ",
            "                ",
            "                "
        ),
        mutableListOf(
            "ooooooooo.   ",
            "`888   `Y88. ",
            " 888   .d88' ",
            " 888ooo88P'  ",
            " 888`88b.    ",
            " 888  `88b.  ",
            "o888o  o888o ",
            "             ",
            "             ",
            "             "
        ),
        mutableListOf(
            " .oooooo..o ",
            "d8P'    `Y8 ",
            "Y88bo.      ",
            " `\"Y8888o.  ",
            "     `\"Y88b ",
            "oo     .d8P ",
            "8\"\"88888P'  ",
            "            ",
            "            ",
            "            "
        ),
        mutableListOf(
            "ooooooooooooo ",
            "8'   888   `8 ",
            "     888      ",
            "     888      ",
            "     888      ",
            "     888      ",
            "    o888o     ",
            "              ",
            "              ",
            "              "
        ),
        mutableListOf(
            "ooooo     ooo ",
            "`888'     `8' ",
            " 888       8  ",
            " 888       8  ",
            " 888       8  ",
            " `88.    .8'  ",
            "   `YbodP'    ",
            "              ",
            "              ",
            "              "
        ),
        mutableListOf(
            "oooooo     oooo ",
            " `888.     .8'  ",
            "  `888.   .8'   ",
            "   `888. .8'    ",
            "    `888.8'     ",
            "     `888'      ",
            "      `8'       ",
            "                ",
            "                ",
            "                "
        ),
        mutableListOf(
            "oooooo   oooooo     oooo ",
            " `888.    `888.     .8'  ",
            "  `888.   .8888.   .8'   ",
            "   `888  .8'`888. .8'    ",
            "    `888.8'  `888.8'     ",
            "     `888'    `888'      ",
            "      `8'      `8'       ",
            "                         ",
            "                         ",
            "                         "
        ),
        mutableListOf(
            "ooooooo  ooooo ",
            " `8888    d8'  ",
            "   Y888..8P    ",
            "    `8888'     ",
            "   .8PY888.    ",
            "  d8'  `888b   ",
            "o888o  o88888o ",
            "               ",
            "               ",
            "               "
        ),
        mutableListOf(
            "oooooo   oooo ",
            " `888.   .8'  ",
            "  `888. .8'   ",
            "   `888.8'    ",
            "    `888'     ",
            "     888      ",
            "    o888o     ",
            "              ",
            "              ",
            "              "
        ),
        mutableListOf(
            " oooooooooooo ",
            "d'\"\"\"\"\"\"d888' ",
            "      .888P   ",
            "     d888'    ",
            "   .888P      ",
            "  d888'    .P ",
            ".8888888888P  ",
            "              ",
            "              ",
            "              "
        )
    )
}
fun init_roman_letters_widths(): MutableList<Int> {
    return mutableListOf(
        10,
        11,
        10,
        11,
        10,
        8,
        11,
        12,
        6,
        8,
        12,
        6,
        18,
        12,
        10,
        11,
        11,
        9,
        9,
        8,
        12,
        12,
        17,
        12,
        12,
        11,
        16,
        13,
        13,
        14,
        13,
        13,
        14,
        14,
        6,
        8,
        13,
        13,
        17,
        15,
        13,
        13,
        16,
        13,
        12,
        14,
        14,
        16,
        25,
        15,
        14,
        14
    )
}
fun init_medium_letters(): MutableList<MutableList<String>> {
    return mutableListOf(
        mutableListOf("____ ", "|__| ", "|  | "),
        mutableListOf("___  ", "|__] ", "|__] "),
        mutableListOf("____ ", "|    ", "|___ "),
        mutableListOf("___  ", "|  \\ ", "|__/ "),
        mutableListOf("____ ", "|___ ", "|___ "),
        mutableListOf("____ ", "|___ ", "|    "),
        mutableListOf("____ ", "| __ ", "|__] "),
        mutableListOf("_  _ ", "|__| ", "|  | "),
        mutableListOf("_ ", "| ", "| "),
        mutableListOf(" _ ", " | ", "_| "),
        mutableListOf("_  _ ", "|_/  ", "| \\_ "),
        mutableListOf("_    ", "|    ", "|___ "),
        mutableListOf("_  _ ", "|\\/| ", "|  | "),
        mutableListOf("_  _ ", "|\\ | ", "| \\| "),
        mutableListOf("____ ", "|  | ", "|__| "),
        mutableListOf("___  ", "|__] ", "|    "),
        mutableListOf("____ ", "|  | ", "|_\\| "),
        mutableListOf("____ ", "|__/ ", "|  \\ "),
        mutableListOf("____ ", "[__  ", "___] "),
        mutableListOf("___ ", " |  ", " |  "),
        mutableListOf("_  _ ", "|  | ", "|__| "),
        mutableListOf("_  _ ", "|  | ", " \\/  "),
        mutableListOf("_ _ _ ", "| | | ", "|_|_| "),
        mutableListOf("_  _ ", " \\/  ", "_/\\_ "),
        mutableListOf("_   _ ", " \\_/  ", "  |   "),
        mutableListOf("___  ", "  /  ", " /__ "),
        mutableListOf("____ ", "|__| ", "|  | "),
        mutableListOf("___  ", "|__] ", "|__] "),
        mutableListOf("____ ", "|    ", "|___ "),
        mutableListOf("___  ", "|  \\ ", "|__/ "),
        mutableListOf("____ ", "|___ ", "|___ "),
        mutableListOf("____ ", "|___ ", "|    "),
        mutableListOf("____ ", "| __ ", "|__] "),
        mutableListOf("_  _ ", "|__| ", "|  | "),
        mutableListOf("_ ", "| ", "| "),
        mutableListOf(" _ ", " | ", "_| "),
        mutableListOf("_  _ ", "|_/  ", "| \\_ "),
        mutableListOf("_    ", "|    ", "|___ "),
        mutableListOf("_  _ ", "|\\/| ", "|  | "),
        mutableListOf("_  _ ", "|\\ | ", "| \\| "),
        mutableListOf("____ ", "|  | ", "|__| "),
        mutableListOf("___  ", "|__] ", "|    "),
        mutableListOf("____ ", "|  | ", "|_\\| "),
        mutableListOf("____ ", "|__/ ", "|  \\ "),
        mutableListOf("____ ", "[__  ", "___] "),
        mutableListOf("___ ", " |  ", " |  "),
        mutableListOf("_  _ ", "|  | ", "|__| "),
        mutableListOf("_  _ ", "|  | ", " \\/  "),
        mutableListOf("_ _ _ ", "| | | ", "|_|_| "),
        mutableListOf("_  _ ", " \\/  ", "_/\\_ "),
        mutableListOf("_   _ ", " \\_/  ", "  |   "),
        mutableListOf("___  ", "  /  ", " /__ ")
    )
}
    fun init_medium_letters_widths(): MutableList<Int> {
        return mutableListOf(
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            2,
            3,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            4,
            5,
            5,
            6,
            5,
            6,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            2,
            3,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            5,
            4,
            5,
            5,
            6,
            5,
            6,
            5
        )
    }

