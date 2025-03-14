/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

 class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val listL1 = listNodeToArray(l1)
        val listL2 = listNodeToArray(l2)
        val length = Math.max(listL1.size, listL2.size)
        var tempResult = IntArray(length + 1)
        var prime = 0
        var isNeededToExpandArray = false
        for (index in 0 until length + 1) {
            if (index <= listL1.lastIndex) {
                tempResult[index] = listL1[index]
            }
            if (index <= listL2.lastIndex) {
                tempResult[index] += listL2[index]
            }

            if (index == length && prime != 0) isNeededToExpandArray = true

            tempResult[index] += prime
            if (tempResult[index] >= 10) prime = tempResult[index] / 10
            else prime = 0
            tempResult[index] %= 10
        }

        if (!isNeededToExpandArray) {
            tempResult = IntArray(length) {
                tempResult[it]
            }
        }
        return arrayToListNode(tempResult)
    }
}


// 배열을 연결 리스트로 변환하는 함수
fun arrayToListNode(arr: IntArray): ListNode? {
    if (arr.isEmpty()) return null
    val head = ListNode(arr[0])
    var current = head
    for (i in 1 until arr.size) {
        current.next = ListNode(arr[i])
        current = current.next!!
    }
    return head
}

fun listNodeToArray(node: ListNode?): IntArray {
    val result = mutableListOf<Int>()
    var current = node
    while (current != null) {
        result.add(current.`val`)
        current = current.next
    }
    return result.toIntArray()
}