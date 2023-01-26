fun main() {
    // write your code here
    val input = readLine()!!.toString()
    var str = ""
    var repeat = ""
    for (i in input.split("?").last().split("&")) {
        if (i.split("=")[0] == "pass") {
            repeat = "password : ${i.split("=")[1]}"
        }
        str += "${i.split("=")[0]} : ${i.split("=")[1].ifEmpty { "not found" }
        }\n"
    }
    str += repeat
    println(str)
}