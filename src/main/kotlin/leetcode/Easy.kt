package leetcode

fun main() {
    val intArray = intArrayOf(3,2,2,3)
    "Size is: ${intArray.size}".println()
    removeElement(intArray, 3).println()
}

fun removeElement(nums: IntArray, `val`: Int): Int {
    val size = nums.size
    if (size == 0) {
        return 0
    }
    var i = 0
    var count = 0
    while (i < size) {
        if (nums[i] == `val`) {
            count++
            shiftLeft(nums, i, size)
        } else {
            i++
        }
    }
    nums.toList().println()
    return size - count
}

fun shiftLeft(nums: IntArray, fromIndex: Int, lastIndex: Int) {
    var start = fromIndex
    var end = lastIndex
    var next = start + 1
    while (start < lastIndex && next < lastIndex) {
        nums[start] = nums[next]
        start++; next++
    }
    nums[lastIndex - 1] = -1
}

/* worst case

fun removeElement(nums: IntArray, `val`: Int): Int {
    val size = nums.size
    if (size == 0) {
        return 0
    }
    var count = 0
     nums.forEachIndexed { index, value ->
         if (value == `val`) {
             nums[index] = -1
             count ++
         }
     }
    nums.sortDescending()
    println("Nums to be printed: ${nums.toList()}")
    return size - count
}
*/

fun searchInsert(nums: IntArray, target: Int): Int {
    if (nums.size == 1) {
        if (target <= nums.first())
            return 0
        else
            return 1
    }
    if (target < nums.first()) {
        return 0
    } else if (target > nums.last()) {
        return nums.size
    }

    var i = 0
    while (i < nums.size) {
        if (nums[i] == target) {
            return i
        } else if (i + 1 < nums.size) {
            if (target == nums[i + 1])
                return i + 1
            else if (i + 1 < nums.size) {
                if (target > nums[i] && target < nums[i + 1]) {
                    return i + 1
                } else {
                    i += 1
                }
            }
        } else {
            return i + 1
        }
    }
    return i
}



fun removeDuplicates(nums: IntArray): Int {
    var i = 0
    var count = 0
    var itIndex = 0
    while (i < nums.size) {
        val new = nums[i]
        while (i + 1 < nums.size && new == nums[i + 1]) {
            i++
        }
        nums[itIndex++] = new
        i++
        /*if (!whiled) {
            i++
        }*/
        count++
    }
    /*nums.distinct().forEachIndexed { index, i ->
        nums[index] = i
    }*/
    nums.toList().print()
    return count
}