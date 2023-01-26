import java.util.*

fun main() {
    // write your code here
    val n = readLine()!!.toInt()
    val mulList: MutableList<Int> = mutableListOf()
    for (i in 1..n) {
        mulList.add(readLine()!!.toInt())
    }
    val rotate = readLine()!!.toInt() % n
    for (i in 0 until n) {
        print("${mulList[(n - rotate + i ) % n] } ")
    }
}