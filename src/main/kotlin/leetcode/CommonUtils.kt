package leetcode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}


public fun Any?.print() {
    print(this)
}

public fun Any?.println() {
    println(this)
}

fun ListNode?.traverseLinkedList() {
    if (this == null) {
        "Empty Linked List".println()
    }
    var node = this
    while (node != null) {
        "${node.`val`}".print()
        if (node.next != null)  {
            " -> ".print()
        }
        node = node.next
    }
    kotlin.io.println()
}