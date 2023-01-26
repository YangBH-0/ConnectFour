fun main() {
    val report = readLine()!!
    //write your code here.
    val regex = Regex(".")
    print(regex.matches(report.toString().split(" ")[0]))
}