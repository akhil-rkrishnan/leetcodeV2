class Solution {
    fun isValid(p: String): Boolean {
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
    return  (this == '{' || this == '[' || this == '(')
}
}
