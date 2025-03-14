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
        if (l1 == null) return l2
        if (l2 == null) return l1

        var copiedL1 = l1
        var copiedL2 = l2
        var headNode = ListNode(0)
        var currentNode = ListNode(0)
        var prime = 0
        var isFirstLoop = true
        while (copiedL1?.`val` != null || copiedL2?.`val` != null || prime != 0) {
            if (copiedL1?.`val` != null) {
                currentNode.`val` = copiedL1.`val`
            }
            if (copiedL2?.`val` != null) {
                currentNode.`val` += copiedL2.`val`
            }

            currentNode.`val` += prime
            if (currentNode.`val` >= 10) prime = currentNode.`val` / 10
            else prime = 0
            currentNode.`val` %= 10

            if (isFirstLoop) {
                headNode = currentNode
                isFirstLoop = false
            }

            copiedL1 = copiedL1?.next
            copiedL2 = copiedL2?.next
            when {

                copiedL1?.`val` != null || copiedL2?.`val` != null -> {
                    currentNode.next = ListNode(0)
                    currentNode.next?.let { currentNode = it }
                }
                else -> {
                    if (prime != 0) {
                        currentNode.next = ListNode(0)
                        currentNode.next?.let { currentNode = it }
                    }
                }
            }
        }
        return headNode
    }
}