fun main() {
    // write your code here
    val str1 = "Lucky"
    val str2 = "Regular"
    val input = readLine()!!
    var sum0: Int = 0
    var sum1: Int = 0
    for (c in input.slice(0..2)) {
        sum0 += c.digitToInt()
    }
    for (c in input.slice(3..5)) {
        sum1 += c.digitToInt()
    }
    if (sum0 == sum1) {
        println(str1)
    } else {
        println(str2)
    }
}