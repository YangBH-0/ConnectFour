fun main() {
    // write your code here
    val input = readLine().toString()
    val len = (input.length - 1) / 2
    println(input.take(len) + input.takeLast(len))
}