package iv

import leetcode.println
import kotlin.system.measureTimeMillis

//143729
//564321
// CNH asked qn (source : geekforgeeks.com level: hard)
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

fun main() {
    val timeInMillis = measureTimeMillis {
        solve("143729", 3)
    }
    "Time taken $timeInMillis".println()

}