class Solution {
    fun swapPairs(head: ListNode?): ListNode? {
        var originalMovingNode = head
        var resultMovingNode: ListNode? = null
        var resultFirstNode: ListNode? = null
        var resultFirstNodeInitFrag = false
        while (originalMovingNode != null) {
            val prevNode = ListNode(originalMovingNode.`val`)
            val nextNode = originalMovingNode.next?.let { ListNode(it.`val`).apply { next = prevNode }}
            if (!resultFirstNodeInitFrag) {
                resultFirstNode = nextNode ?: prevNode
                resultFirstNodeInitFrag = true
            }

            resultMovingNode?.next = nextNode ?: prevNode
            resultMovingNode = prevNode
            originalMovingNode = originalMovingNode.next?.next
        }

        return resultFirstNode
    }
}