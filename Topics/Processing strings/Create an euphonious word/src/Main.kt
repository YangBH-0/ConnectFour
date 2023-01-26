val vowel = "aeiouy"
val consonants = ('a'..'z').filter { isVowel(it) }.joinToString("")
fun main() {
    // write your code here
    val input = readLine()!!.toString();
    var v_count = 0
    var c_count = 0
    var e_count = 0
    for (i in input.indices) {
        if (isVowel(input[i])) {
            c_count = 0
            v_count++
        } else {
            c_count++
            v_count = 0
        }
        if (v_count > 2 || c_count > 2) {
            e_count++
            if (v_count != 0) v_count = 1
            if (c_count != 0) c_count = 1
        }
    }
    println(e_count)
}

fun isVowel(char: Char): Boolean {
    for (v in vowel) {
        if (v == char) return true
    }
    return false
}
