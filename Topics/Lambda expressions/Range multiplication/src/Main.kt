    val lambda: (Long, Long) -> Long = { left, rigth ->
        (left..rigth).reduce { acc, l -> acc * l }
    }
