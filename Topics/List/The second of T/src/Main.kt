fun solution(names: List<String>): Int {
    // put your code here
    var result = 0
    for (i in 1..names.size step 2){
        if (names[i][0] == 'T'){
            result = i
            break
        }
    }
    return result
}