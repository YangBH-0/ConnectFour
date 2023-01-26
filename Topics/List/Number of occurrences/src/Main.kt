fun solution(strings: List<String>, str: String): Int {
    // put your code here
    var count = 0
    for (i in strings) {
        if (i.contains(str)) count++
    }
    return count
}