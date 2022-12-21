package leetcode

import kotlin.random.Random
import kotlin.random.nextInt


fun main() {
    mergeKSortList()
}

fun findSubstring(s: String, words: Array<String>): List<Int> {
    if (s.length < words.allStringLength()) {
        return listOf()
    }
    return emptyList()

}

fun Array<String>.permutationOfAllStrings(): List<String> {
    var string = ""
    var combineList = ArrayList<String>()
    for (i in 0 until this.size) {
        string = this[i]
        for (j in 0 until this.size) {
            if (i != j) {
                string += this[j]
            }
        }
        combineList.add(string)
    }
    return combineList
}

fun Array<String>.allStringLength(): Int {
    var length = 0
    this.forEach {
        length += it.length
    }
    return length
}


// not so optimized code - Hard Problem
fun mergeKSortList(): ListNode? {
    var generator = IntGenerator().apply { addItems(6) }
    val t1 = ListNode(generator.getItemAtPosition()).apply {
        next = ListNode(generator.getItemAtPosition()).apply {
            next = ListNode(generator.getItemAtPosition()).apply {
                next = ListNode(generator.getItemAtPosition()).apply {
                    next = ListNode(generator.getItemAtPosition()).apply {
                        next = ListNode(generator.getItemAtPosition())
                    }
                }
            }
        }
    }
    generator = IntGenerator().apply { addItems(6) }
    val t2 = ListNode(generator.getItemAtPosition()).apply {
        next = ListNode(generator.getItemAtPosition()).apply {
            next = ListNode(generator.getItemAtPosition()).apply {
                next = ListNode(generator.getItemAtPosition()).apply {
                    next = ListNode(generator.getItemAtPosition()).apply {
                        next = ListNode(generator.getItemAtPosition())
                    }
                }
            }
        }
    }
    generator = IntGenerator().apply { addItems(6) }
    val t3 = ListNode(generator.getItemAtPosition()).apply {
        next = ListNode(generator.getItemAtPosition()).apply {
            next = ListNode(generator.getItemAtPosition()).apply {
                next = ListNode(generator.getItemAtPosition()).apply {
                    next = ListNode(generator.getItemAtPosition()).apply {
                        next = ListNode(generator.getItemAtPosition())
                    }
                }
            }
        }
    }
    "t1 : ".print()
    t1.traverseLinkedList()
    "t2 : ".print()
    t2.traverseLinkedList()
    "t3 : ".print()
    t3.traverseLinkedList()

    val lists = listOf<ListNode?>(null)

    var newList: ListNode? = null
    if (lists.isEmpty() || (lists.size == 1 && lists.first() == null)) {
        return null
    }
    for (i in lists.indices) {
        newList = mergeKSortedList(newList, lists[i])
    }
    //newList.traverseLinkedList()
    return newList
}

fun mergeKSortedList(list1: ListNode?, list2: ListNode?): ListNode? {
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
            headHolder = l2
            break
        } else {
            sortedListNode.next = ListNode(l2.`val`)
            sortedListNode = sortedListNode.next
        }
        l2 = l2.next
    }
//    if (headHolder == null) {
//        headHolder = ListNode(0)
//    }
    return headHolder
}

class IntGenerator {
    private var incrementer = 0
    private val list = arrayListOf<Int>()

    fun reset() {
        incrementer = 0
    }

    fun getItemAtPosition(): Int {
        if (incrementer < list.size) {
            return list[incrementer++]
        }
        return -1
    }

    fun addItems(limit: Int = 5) {
        var start = 0;
        while (start < limit) {
            list.add(getRandomInt(25))
            start++
        }
        list.sort()
    }
}

fun getRandomInt(limit: Int): Int {
    return Random.nextInt(0..limit)
}