import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()
    val n = scanner.nextInt()

    val upperCase = ('A'..'Z').joinToString("")
    val lowerCase = ('a'..'z').joinToString("")
    val digits = ('0'..'9').joinToString("")
    val remainder = "z$upperCase$lowerCase$digits"
    var password = ""
    repeat(a) {
        password += "${upperCase[it % upperCase.length]}"
    }
    repeat(b) {
        password += "${lowerCase[it % lowerCase.length]}"
    }
    repeat(c) {
        password += "${digits[it % digits.length]}"
    }
    repeat(n - a - b - c) {
        password += "${remainder[it % remainder.length]}"
    }
    print(password)
}