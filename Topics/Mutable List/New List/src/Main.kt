fun solution(numbers: List<Int>, number: Int): MutableList<Int> {
    // put your code here
    val temp = numbers.toMutableList()
    temp.add(number)
    return temp
}
