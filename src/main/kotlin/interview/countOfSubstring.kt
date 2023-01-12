fun main() {
subStringCount()

}



fun subStringCount(str1: String = "akhilakhil akhil" , str2: String = "akh") {
   // var str1 = "akhilakhil akhil"
   // val str2 = "akh"
    println(str2 in str1)
    var count = 0
    while (str2 in str1) {
        count ++
       // str1 = str1.replaceFirst(str2, "")
    }
    println("the count is $count")
}
