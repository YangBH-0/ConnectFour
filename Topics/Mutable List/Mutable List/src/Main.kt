fun names(namesList: List<String>): List<String> {
    var count = 0
    val nameCount = mutableListOf<String>()
    //
    // add your code here
    //
    val names = namesList.distinct()
    for (i in names.indices) {
        count = 0
        for (name in namesList) {
            if (names[i] == name) count++
        }
        nameCount.add("The name ${names[i]} is repeated $count times")
    }
    return nameCount
}