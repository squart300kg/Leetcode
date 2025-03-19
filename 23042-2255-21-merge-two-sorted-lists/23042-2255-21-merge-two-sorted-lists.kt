class Solution {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null && list2 == null) return null
        var current: ListNode? = ListNode(0)
        val result = current
        var list1Current = list1
        var list2Current = list2
        while (list1Current != null || list2Current != null) {
            when {
                list1Current == null -> {
                    current?.`val` = list2Current?.`val` ?: 0
                    if (list2Current?.next != null) {
                        current?.next = ListNode(0)
                        current = current?.next
                    }
                    list2Current = list2Current?.next
                }
                list2Current == null -> {
                    current?.`val` = list1Current.`val`
                    if (list1Current.next != null) {
                        current?.next = ListNode(0)
                        current = current?.next
                    }
                    list1Current = list1Current.next
                }
                list1Current.`val` > list2Current.`val` -> {
                    current?.`val` = list2Current.`val`
                    
                    current?.next = ListNode(0)
                    current = current?.next
                    list2Current = list2Current.next
                }
                list1Current.`val` < list2Current.`val` -> {
                    current?.`val` = list1Current.`val`
                    current?.next = ListNode(0)
                    current = current?.next
                    list1Current = list1Current.next
                }
                list1Current?.`val` == list2Current?.`val` -> {
                    current?.`val` = list1Current.`val`
                    current?.next = ListNode(0)
                    current = current?.next
                    current?.`val` = list2Current.`val`
                    if (list1Current.next != null || list2Current.next != null) {
                        current?.next = ListNode(0)
                        current = current?.next
                    }
                    // 둘중 하나만 포인터 이동하면 됨
                    list1Current = list1Current.next
                    list2Current = list2Current.next
                }
            }
        }

        return result
    }
}