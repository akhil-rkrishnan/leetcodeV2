package leetcode

import kotlin.collections.*
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {


    val measureTimeInMillis = measureTimeMillis {
        printParanthesis(2)
    }
    println("Time taken is $measureTimeInMillis")
}

fun printParanthesis(n: Int) {
    val charArray = CharArray(2 * n)
    var arrayList = ArrayList<String>()
    if (n > 0)
       arrayList =  _printParenthesis(arrayList, charArray, 0,n,0,0)
    "The final list is ${arrayList.toList()}".print()
    return
}

fun _printParenthesis(
    arrayList: ArrayList<String>,
    str: CharArray, pos: Int,
    n: Int, open: Int,
    close: Int
): ArrayList<String> {
    if (close == n) {
        // print the possible combinations
        arrayList.add(str.joinToString(""))
//        for (i in str.indices) print(str[i])
//        println()
    } else {
        if (open > close) {
            str[pos] = ')'
            _printParenthesis(arrayList,
                str, pos + 1, n, open,
                close + 1
            )
        }
        if (open < n) {
            str[pos] = '('
            _printParenthesis(arrayList,
                str, pos + 1, n, open + 1,
                close
            )
        }
    }
    return arrayList
}

fun mergeSortedList(list1: ListNode?, list2: ListNode?): ListNode? {
    var l1: ListNode? = list1 //12345
    var l2: ListNode? = list2 //54321
    var sortedListNode: ListNode? = null
    var headHolder: ListNode? = null
    if (l1 == null && l2 == null) {
        return null
    }
    if (l1 == null && l2 != null && l2.next == null) {
        return ListNode(l2.`val`)
    }
    if (l2 == null && l1 != null && l1.next == null) {
        return ListNode(l1.`val`)
    }
    while (l1 != null) {
        val l1Val = l1.`val`
        val l2Val = l2?.`val` ?: break
        if (l1Val == l2Val) {
            sortedListNode = when (sortedListNode) {
                null -> {
                    l1 = l1.next
                    ListNode(l1Val).apply { next = ListNode(l2Val) }
                }
                else -> {
                    l2 = l2.next
                    sortedListNode.next = ListNode(l1Val).apply { next = ListNode(l2Val) }
                    sortedListNode.next
                }
            }

            if (headHolder == null) {
                headHolder = sortedListNode
            }
        } else if (l1Val < l2Val) {
            sortedListNode = when (sortedListNode) {
                null -> {
                    l1 = l1.next
                    ListNode(l1Val)
                }
                else -> {
                    l1 = l1.next
                    sortedListNode.next = ListNode(l1Val)
                    sortedListNode.next
                }
            }
            if (headHolder == null) {
                headHolder = sortedListNode
            }
        } else {
            sortedListNode = when (sortedListNode) {
                null -> {
                    l2 = l2.next
                    ListNode(l2Val)
                }
                else -> {
                    l2 = l2.next
                    sortedListNode.next = ListNode(l2Val)
                    sortedListNode.next
                }
            }
            if (headHolder == null) {
                headHolder = sortedListNode
            }
        }
    }
    while (l1 != null) {
        if (sortedListNode == null) {
            sortedListNode = ListNode(l1.`val`)
            headHolder = l1
            break
        } else {
            sortedListNode.next = ListNode(l1.`val`)
            sortedListNode = sortedListNode.next
        }
        l1 = l1.next
    }

    while (l2 != null) {
        if (sortedListNode == null) {
            sortedListNode = ListNode(l2.`val`)
            headHolder = l1
            break
        } else {
            sortedListNode.next = ListNode(l2.`val`)
            sortedListNode = sortedListNode.next
        }
        l2 = l2.next
    }
    if (headHolder == null) {
        headHolder = ListNode(0)
    }
    return headHolder
}

fun balanceParanthesis(p: String): Boolean {
    val deque = java.util.ArrayDeque<Char>()
    p.forEach {
        if (it.isOpenBracket()) {
            deque.push(it)
        }
        if (deque.isEmpty())
            return false
        when (it) {
            '}' -> {
                val pop = deque.pop()
                if (pop == '(' || pop == '[')
                    return false
            }
            ')' -> {
                val pop = deque.pop()
                if (pop == '{' || pop == '[')
                    return false
            }
            ']' -> {
                val pop = deque.pop()
                if (pop == '{' || pop == '(')
                    return false
            }
        }
    }
    return deque.isEmpty()
}

fun Char.isOpenBracket(): Boolean {
    return (this == '{' || this == '[' || this == '(')
}




fun removeNthNodeOfLinkedList(): ListNode? {

    var head: ListNode = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply { next = ListNode(4).apply { next = ListNode(5) } }
        }
    }
//    val linkedList = ListNode(1)
    val listMap = HashMap<Int, ListNode?>()
    var index = 1
    var removeAt = 6
    if (head != null) {
        print("${head?.`val`}-> ")
        listMap[index++] = head
        var start = head.next
        while (start != null) {
            listMap[index++] = start
            print("${start.`val`}${if (start.next != null) "-> " else ""}")
            start = start.next
        }
    }
    println()

    val removeIndex = run {
        var maxVal: Int? = null
        for (listMap in listMap.entries) {
            if (maxVal == null || listMap.key > maxVal) {
                maxVal = listMap.key
            }
        }
        if (maxVal == null) {
            -1
        } else
            maxVal + 1 - removeAt
    }
    /*val removeIndex = listMap.maxOfOrNull { it.key }?.let {
        it + 1 - removeAt
    } ?: -1*/
    var itNext: ListNode? = head
    if (removeIndex == 1) {
        itNext = itNext?.next
    } else {
        val deleteNode = listMap[removeIndex]
        var prev: ListNode? = null
        while (itNext != deleteNode) {
            println("Traversed ${itNext?.`val`}")
            prev = itNext
            itNext = itNext?.next
        }
        println("ListNode to delete is ${itNext?.`val`}")
        prev?.next = itNext?.next
        itNext = head
    }

    println("Updated Linked List")
    while (itNext != null) {
        print("${itNext.`val`} ${if (itNext.next != null) " -> " else ""}")
        itNext = itNext.next
    }

    return itNext
}


fun letterCombinations(targetString: String): List<String> {
    val alphaMap = hashMapOf<Char, String>().apply {
        put('2', "abc")
        put('3', "def")
        put('4', "ghi")
        put('5', "jkl")
        put('6', "mno")
        put('7', "pqrs")
        put('8', "tuv")
        put('9', "wxyz")
    }
    if (targetString.isEmpty() || targetString.isBlank()) {
        return listOf()
    }
    var finalList = arrayListOf<String>()
    targetString.forEach {
        val currentMapDigits = alphaMap[it]
        if (currentMapDigits != null) {
            finalList = mergeDigits(finalList, currentMapDigits)
        }
    }
    return finalList

}

fun mergeDigits(finalList: ArrayList<String>, currentString: String): ArrayList<String> {

    if (finalList.isEmpty()) {
        finalList.addAll(currentString.toList().map { it.toString() })
        return finalList
    }
    val newList: ArrayList<String> = arrayListOf()
    finalList.forEach { existingItem ->
        currentString.forEach { newItem ->
            newList.add(existingItem + newItem)
        }
    }
    return newList
}


fun threeSumClosestOptimised(): Int {
    val threeSumList = arrayListOf<Int>()
    var list1 = listOf(
        -842,
        592,
        311,
        937,
        826,
        -717,
        -883,
        462,
        -101,
        -655,
        -435,
        -908,
        -304,
        806,
        130,
        -743,
        -967,
        -440,
        430,
        911,
        -270,
        853,
        -810,
        -323,
        749,
        716,
        -93,
        -141,
        272,
        -81,
        972,
        -108,
        -733,
        -300,
        566,
        -79,
        53,
        -563,
        -449,
        744,
        586,
        328,
        -533,
        -760,
        643,
        -195,
        -937,
        -872,
        238,
        -25,
        -720,
        -36,
        -95,
        -168,
        -15,
        -978,
        -917,
        -523,
        -164,
        -779,
        591,
        646,
        -621,
        10,
        708,
        547,
        982,
        7,
        -510,
        -712,
        -187,
        -611,
        345,
        40,
        -796,
        954,
        750,
        943,
        -65,
        -626,
        -458,
        -860,
        -63,
        -840,
        -360,
        637,
        -924,
        700,
        -272,
        -599,
        -358,
        714,
        -726,
        -933,
        -680,
        -674,
        996,
        -486,
        233,
        384,
        165,
        -159,
        -912,
        -753,
        910,
        25,
        48,
        310,
        75,
        -843,
        769,
        642,
        387,
        824,
        -75,
        -637,
        115,
        -593,
        -711,
        -426,
        -442,
        583,
        -730,
        872,
        540,
        856,
        -20,
        -808,
        -504,
        133,
        -55,
        -866,
        -710,
        938,
        83,
        690,
        -789,
        -169,
        636,
        -888,
        -909,
        -261,
        527,
        371,
        -668,
        -863,
        606,
        -651,
        949,
        939,
        339,
        -167,
        930,
        -881,
        803,
        -321,
        -962,
        638,
        -592,
        191,
        919,
        -853,
        247,
        -274,
        -213,
        786,
        664,
        -806,
        318,
        -154,
        196,
        -251,
        689,
        995,
        783,
        571,
        -244,
        146,
        -227,
        738,
        -39,
        -826,
        -307,
        49,
        894,
        201,
        150,
        195,
        421,
        -785,
        162,
        -162,
        -308,
        833,
        -790,
        -716,
        -524,
        -539,
        -170,
        -147,
        -604,
        372,
        -13,
        883,
        810,
        -628,
        969,
        -859,
        -737,
        82,
        -117,
        319,
        -433,
        -615,
        -208,
        70,
        659,
        968,
        942,
        417,
        -794,
        994,
        599,
        720,
        -182,
        -28,
        528,
        405,
        879,
        510,
        -491,
        -851,
        -245,
        -906,
        504,
        926,
        612,
        -890,
        -910,
        836,
        -944,
        945,
        -775,
        154,
        -969,
        860,
        -907,
        -199,
        291,
        553,
        -390,
        -457,
        770,
        -762,
        -149,
        -269,
        992,
        87,
        221,
        13,
        -955,
        927,
        -166,
        -828,
        505,
        288,
        -140,
        -273,
        -530,
        166,
        72,
        760,
        210,
        -544,
        -210,
        298,
        915,
        -238,
        -718,
        455,
        -625,
        147,
        934,
        54,
        -411,
        465,
        -109,
        -326,
        847,
        663,
        259,
        -696,
        107,
        -160,
        687,
        477,
        -732,
        422,
        661,
        -690,
        278,
        251,
        -111,
        -298,
        -405,
        564,
        532,
        -695,
        -946,
        304,
        382,
        -919,
        11,
        -80,
        155,
        515,
        -572,
        -740,
        -514,
        468,
        -501,
        -27,
        -692,
        579,
        -431,
        751,
        -6,
        -893,
        -836,
        280,
        -557,
        325,
        -119,
        767,
        957,
        -16,
        264,
        668,
        99,
        -849,
        -378,
        -735,
        293,
        498,
        89,
        840,
        -988,
        -681,
        -103,
        323,
        506,
        312,
        903,
        748,
        -875,
        -10,
        602,
        880,
        -605,
        -868,
        -772,
        24,
        369,
        -997,
        -367,
        181,
        -235,
        -44,
        -184,
        76,
        453,
        -958,
        237,
        258,
        -57,
        563,
        -464,
        -699,
        913,
        29,
        338,
        483,
        870,
        -129,
        929,
        -870,
        -454,
        -380,
        -283,
        -331,
        582,
        219,
        -991,
        -264,
        896,
        410,
        -282,
        -406,
        -568,
        432,
        140,
        -818,
        -647,
        93,
        -963,
        -854,
        -214,
        -346,
        573,
        865,
        -487,
        -318,
        218,
        -82,
        -281,
        -835,
        -21,
        -124,
        -610,
        804,
        792,
        -276,
        134,
        794,
        655,
        -841,
        240,
        -703,
        -874,
        -327,
        -19,
        839,
        394,
        757,
        -172,
        988,
        -78,
        530,
        -803,
        657,
        -503,
        -645,
        979,
        -306,
        12,
        812,
        821,
        57,
        -233,
        375,
        -447,
        671,
        887,
        -662,
        -939,
        562,
        -513,
        -319,
        800,
        -123,
        -364,
        -546,
        401,
        -42,
        23,
        -317,
        -285,
        -972,
        211,
        -372,
        -114,
        960,
        -795,
        -463,
        -951,
        698,
        -847,
        -278,
        -352,
        -73,
        337,
        -441,
        117,
        -191,
        -819,
        67,
        -832,
        -462,
        256,
        -886,
        -353,
        241,
        -71,
        733,
        576,
        597,
        787,
        124,
        -120,
        8,
        -540,
        -756,
        -584,
        -206
    )
    // list1 = listOf(4,0,5,-5,3,3,0,-4,-5)

    val list = list1.sorted()
    val target = 2984
    var nearest: Int = 0
    var oldDiff: Int = if (target > 0) 111111 else 111111

    for (i in list.indices) {
        val a = list[i]
        if (i > 0 && a == list[i - 1]) {
            continue
        }
        var l = i + 1;
        var r = list.size - 1
        while (l < r) {
            val threeSum = a + list[l] + list[r]
            val abs = abs(target - threeSum)
            if (abs < oldDiff /*&& target != threeSum*/) {
                nearest = threeSum
                oldDiff = abs
            }
            if (target - threeSum < 0) {
                r -= 1
            } else {
                l += 1
            }
            threeSumList.add(threeSum)
            while (list[l] == list[l - 1] && l < r) {
                l += 1
            }
        }
    }
    println("sorted list = $list")
    println("three sum list = ${threeSumList.distinct().sorted()}")
    println("The nearest is $nearest")
    return nearest
}

fun twoSumIndex(): List<Int>? {
    val timeInMillis = measureTimeMillis {
        val nums = listOf<Int>(1, 3, 7, 9, 2)
        val target = 11
        val numsMap = hashMapOf<Int, Int>()
        for (p in nums.indices) {
            val currentMapVal = numsMap[nums[p]]
            if (currentMapVal != null && currentMapVal >= 0) {
                return listOf(currentMapVal, p)
            } else {
                val numToFind = target - nums[p]
                numsMap[numToFind] = p
            }
        }
    }
    "Time in millis: $timeInMillis".print()
    return null
}

fun subStringCount() {
    var timeInMillis = measureTimeMillis {
        var str1 = "akhilakhil akhil"
        val str2 = "akh"
        println(str2 in str1)
        var count = 0
        while (str2 in str1) {
            count++
            str1 = str1.replaceFirst(str2, "")
        }
        println("the count is $count")
    }
    println("Time in millis - $timeInMillis")
}

fun characterCountWithCollection() {
    val timeTaken = measureTimeMillis {
        val string = "mississippi mussoorie"
//        val string = "aaaabbbcccdeeaa##%%"
        val hashMap = HashMap<String, Int>()
        for (i in string.indices) {
            val findChar = string[i].toString().let { if (it == " ") "space" else it }
            val mapCount = hashMap[findChar]
            if (mapCount == null) {
                hashMap[findChar] = 1
            } else {
                hashMap[findChar] = mapCount + 1
            }
        }
        println("Collection map: $hashMap")
    }
    println("Total time taken $timeTaken")

}

fun characterCountWithoutCollection() {
    val timeTaken = measureTimeMillis {
        var string = "mississippi mussoorie"
        var index = 0
        while (string.isNotEmpty()) {
            val check = string.first().toString()
            var count = 0
            string.forEachIndexed { index2, new ->
                if (check == new.toString()) {
                    count++
                }
            }
            if (check != "" && check != " ")
                println("The character count of $check is $count")
            string = string.replace(check.toString(), "")
            index++
        }
    }
    println("Total time taken $timeTaken")

}

fun Double?.asExperience(emptyQualifier: String = ""): String = if (this == null) {
    emptyQualifier
} else {
    val experienceList = this.toString().split(".")
    var splittedExperience = ""
    if (experienceList.size == 2) {
        experienceList.first().toIntOrNull()?.let {
            when (it) {
                0 -> {}
                1 -> {
                    splittedExperience = "1 year "
                }
                else -> {
                    splittedExperience = "$it years "
                }
            }
        }
        experienceList.last().toIntOrNull()?.let {
            when (it) {
                0 -> {

                }
                1 -> {
                    splittedExperience += "1 month"
                }
                else -> {
                    splittedExperience += "$it months"
                }
            }
        }
    } else {
        experienceList.first().toIntOrNull()?.let {
            splittedExperience = when (it) {
                0 -> {
                    "No experience"
                }
                1 -> {
                    "1 year"
                }
                else -> {
                    "$it years"
                }
            }
            splittedExperience = "$it "
        }
    }
    splittedExperience
}


