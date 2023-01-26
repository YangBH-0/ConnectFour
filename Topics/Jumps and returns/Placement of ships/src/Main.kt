fun main() {
    // put your code here
    val row = BooleanArray(6)
    val column = BooleanArray(6)
    for (i in 0..2) {
        val input = readLine().toString().trim().split(" ").map { it.toInt() }
        row[input[0]] = true
        column[input[1]] = true
    }
    val availableRow = mutableListOf<Int>()
    val availableColumn = mutableListOf<Int>()
    for(i in 1 until row.size){
        if(!row[i]){
            availableRow.add(i)
        }
    }
    for(i in 1 until column.size){
        if(!column[i]){
            availableColumn.add(i)
        }
    }
    println(availableRow.joinToString(" "))
    println(availableColumn.joinToString(" "))
}