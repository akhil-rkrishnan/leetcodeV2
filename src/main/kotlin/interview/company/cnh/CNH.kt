package interview.company.cnh

import leetcode.println

fun main() {
    findSumOfNumbers()
}
//143729
//564321
// CNH asked qn (source : geekforgeeks.com level: medium)
fun solve(input1: String, k: Int) {
    var input = input1
    var isBreaked = false
    if (k > 0) {
        for (i in 0 until input.length - 1) {
            if (input[i] > input[i + 1]) {
                input = input.removeRange(i, i + 1)
                isBreaked = true
                break
            }
        }
        if (!isBreaked)
            input = input.removeRange(input.length - 1, input.length)
        input.println()
        solve(input, k - 1)
    }
}

fun findSumOfNumbers() {

    // find the sum of numbers from the alphanumeric string given
    val input = "123abcderf5tr@@1"

    var sum = 0

    var i =0
    var numberString = ""
    while (i < input.length) {
        val char = input[i]
        if (char.isDigit()) {
            numberString += char
        } else {
            if (numberString.isNotEmpty()) {
                sum += (numberString.toInt())
            }
            numberString = ""
        }
        if (i == input.length - 1) {
            if (numberString.isNotEmpty()) {
                sum += (numberString.toInt())
            }
        }
        i++
    }
    "The sum is : $sum".println()
}