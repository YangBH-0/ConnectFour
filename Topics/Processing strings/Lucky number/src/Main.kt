fun main() {
    // write your code here
    val input = readLine()!!.toString()
    val sums = input.chunked(input.length / 2).map { it.map { (it.digitToInt()) }.sum() }
    println(if (sums[0] == sums[1]) "YES" else "NO")
}