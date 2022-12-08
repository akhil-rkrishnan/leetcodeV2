package leetcode

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun main() {
    val measureTimeInMillis = measureTimeMillis {
        searchInRotatedSortArray(
            intArrayOf(
                55,
                56,
                57,
                58,
                59,
                60,
            ), 60
        ).println()
    }
    "Total time taken $measureTimeInMillis".println()

}

fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    val mainList = arrayListOf<ArrayList<Int>>()
    when (candidates.size) {
        0 -> return arrayListOf()
        1 -> if (candidates.first() == target) return arrayListOf(listOf(target))
        2 -> {
            if (candidates.first() == target || candidates.last() == target) {
                mainList.add(arrayListOf(1))
            }
            if (candidates.sum() == target) {
                mainList.add(arrayListOf(candidates.first(),candidates.last()))
            }
            return mainList
        }
    }
    candidates.sort()
    var currentIndex = 0
    while (currentIndex < candidates.size) {
        if (candidates.get(currentIndex) > target) {
            return mainList
        }
        var nextIndex = currentIndex + 1

    }
 return mainList
}


fun multiply(num1: String, num2: String): String {
    val bigInt1 = BigInteger(num1)
    val bigInt2 = BigInteger(num2)
    val multiply = bigInt1 * bigInt2
    return multiply.toString()
}

fun searchRange(nums: IntArray, target: Int): IntArray {
    val list = arrayListOf<Int>()
    if (nums.isEmpty()) {
        return intArrayOf(-1,-1)
    }
    if (nums.size == 1) {
         if (nums.first() == target) {
             return intArrayOf(0,-1)
         }
    }
    val intArray = IntArray(2)
    searchInRange(nums, target, intArray, 0, nums.size - 1)
    return intArray
}

fun searchInRange(nums: IntArray, target: Int, indexArray: IntArray, start: Int,end: Int) {

}

fun searchInRotatedSortArray(nums: IntArray, target: Int): Int {
    if (nums.size == 1) {
        return if (nums.first() == target)
            0
        else
            -1
    }
    if (nums.size == 2) {
        return when (target) {
            nums.first() -> 0
            nums.last() -> 1
            else -> -1
        }
    }
    return searchWithIndex(nums, 0, nums.size - 1, target)
}

fun searchWithIndex(nums: IntArray, start: Int, end: Int, target: Int): Int {
    if (target == nums[start])
        return start
    else if (target == nums[end])
        return end
    else if (end == start + 1) {
        return -1
    } else {
        if (start + 1 < nums.size)
            return searchWithIndex(nums, start + 1, end - 1, target)
        else
            return -1
    }

}

fun divideTwoIntegers(dividend: Int, divisor: Int): Int {
    if (dividend == 0) {
        return 0
    }

    val strictMax: Int = 2147483647
    val strictMin: Int = strictMax * -1 - 1
    var quotient = 0L

    if (dividend == strictMin && divisor == 1) {
        return strictMin
    }

    if (dividend == strictMin && divisor == -1) {
        return strictMax
    }

    if (dividend == strictMax && divisor == -1) {
        return -strictMax
    }

    if (dividend == strictMax && divisor == 1) {
        return strictMax
    }

    val isNegative =
        if (dividend > 0 && divisor > 0) {
            false
        } else !(dividend < 0 && divisor < 0)


    var _dividend = if (dividend < 0) dividend.toLong() * -1 else dividend.toLong()
    var _divisor = if (divisor < 0) divisor.toLong() * -1 else divisor.toLong()


    while (_dividend >= _divisor) {
        _dividend -= _divisor
        ++quotient
    }

    if (isNegative) {
        quotient *= -1
    }

    if (quotient > strictMax) {
        return strictMax
    } else if (quotient < strictMin) {
        return strictMin
    }
    return quotient.toInt()
}

fun indexOfFirstOccurrence(haystack: String, needle: String): Int {

    if (needle.length > haystack.length)
        return -1
    var i = 0;
    var j = 0;
    var startIndex = -1;
    var count = 0

    while (i < needle.length) {
        if (j < haystack.length && needle[i] == haystack[j]) {
            if (startIndex == -1) {
                startIndex = j
            }
            i++; j++;
            count++
        } else {
            if (count == 0) {
                j++
            } else if (count == needle.length) {
                return startIndex
            } else {
                j = startIndex + 1
            }
            if (needle.length > (haystack.length - j)) {
                return -1
            }
            i = 0
            count = 0
            startIndex = -1
        }

        if (j == haystack.length && i != needle.length) {
            return -1
        }
    }
    return startIndex
}

fun swapNodes() {
    var generator = IntGenerator().apply { addItems(5) }
    var t1 = ListNode(generator.getItemAtPosition()).apply {
        next = ListNode(generator.getItemAtPosition()).apply {
            next = ListNode(generator.getItemAtPosition()).apply {
                next = ListNode(generator.getItemAtPosition()).apply {
                    next = ListNode(generator.getItemAtPosition()).apply {
                        // next = ListNode(generator.getItemAtPosition())
                    }
                }
            }
        }
    }
    t1 = ListNode(1).apply { next = ListNode(2).apply { next = ListNode(3) } }
    "Linked List".println()
    t1.traverseLinkedList()
    swapNodes(t1)
}

fun swapNodes(list: ListNode?): ListNode? {
    if (list == null)
        return null
    if (list.next == null)
        return ListNode(list.`val`)

    if (list.next?.next != null && list.next?.next?.next == null) {
        return ListNode(list.next!!.`val`).apply {
            next = ListNode(list.`val`).apply { next = ListNode(list.next?.next!!.`val`) }
        }
    }

    var newNode: ListNode? = null
    var head = newNode
    var temp = list
    while (temp != null) {
        if (temp.next != null) {
            if (newNode == null) {
                newNode = ListNode(temp.next!!.`val`).apply {
                    next = ListNode(temp!!.`val`)
                }
                head = newNode
            } else {
                if (temp.next?.next != null && temp.next?.next?.next == null) {
                    newNode.next?.next = ListNode(temp.next!!.`val`).apply {
                        next = ListNode(temp!!.`val`).apply {
                            next = ListNode(temp?.next?.next!!.`val`)
                        }
                    }
                    newNode = newNode.next?.next
                    break
                }
                newNode.next?.next = ListNode(temp.next!!.`val`).apply {
                    next = ListNode(temp!!.`val`)
                }
                newNode = newNode.next?.next
            }
        }
        temp = temp.next?.next
    }
    head.traverseLinkedList()
    return head
}