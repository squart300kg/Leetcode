class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()

        val duplicatedNumsSet = mutableSetOf<Int>()
        val minusNumsList = mutableListOf<Int>()
        val zeroNumsList = mutableListOf<Int>()
        val plusNumsList = mutableListOf<Int>()
        nums.sort()//

        for (index in 0 until nums.size - 1) {
            fun MutableList<Int>.setNumsListAndDuplicatedSet() {
                add(nums[index])
                if (nums[index] == nums[index + 1]) {
                    duplicatedNumsSet.add(nums[index])
                }
            }
            when {
                nums[index] < 0 -> minusNumsList.setNumsListAndDuplicatedSet()
                nums[index] == 0 -> zeroNumsList.setNumsListAndDuplicatedSet()
                nums[index] > 0 -> plusNumsList.setNumsListAndDuplicatedSet()
            }

            if (index + 1 == nums.lastIndex) {
                when {
                    nums[index + 1] < 0 -> minusNumsList.apply { add(nums[index + 1]) }
                    nums[index + 1] == 0 -> zeroNumsList.apply { add(nums[index + 1]) }
                    nums[index + 1] > 0 -> plusNumsList.apply { add(nums[index + 1]) }
                }
            }
        }

        val plusNumsSet: MutableSet<Int> = plusNumsList.toMutableSet()
        val minusNumsSet: MutableSet<Int> = minusNumsList.toMutableSet()

        if (zeroNumsList.size >= 3) result.add(listOf(0,0,0))
        if (minusNumsList.size == 0 || plusNumsList.size == 0) return result.toList()

        var minusStart = 0
        val minusEnd = minusNumsList.size - 1

        var plusStart = 0
        val plusEnd = plusNumsList.size - 1

        while (minusStart <= minusEnd && plusStart <= plusEnd) { //
            val sub = minusNumsList[minusStart] + plusNumsList[plusStart]
            val addTarget = listOf(minusNumsList[minusStart], plusNumsList[plusStart], -sub).sorted()

            when {
                -sub == 0 -> {
                    if (!result.contains(addTarget) && zeroNumsList.isNotEmpty()) {
                        result.add(listOf(minusNumsList[minusStart], 0, plusNumsList[plusStart]))
                    }
                }
                -sub > 0 -> {
                    if (!result.contains(addTarget)) {
                        when (-sub == plusNumsList[plusStart]) {
                            true -> {
                                if (duplicatedNumsSet.contains(-sub)) result.add(addTarget)
                            }
                            false -> {
                                if (plusNumsSet.contains(-sub)) result.add(addTarget)
                            }
                        }
                    }
                }
                -sub < 0 -> {
                    if (!result.contains(addTarget)) {
                        when (-sub == minusNumsList[minusStart]) {
                            true -> {
                                if (duplicatedNumsSet.contains(-sub)) result.add(addTarget)
                            }
                            false -> {
                                if (minusNumsSet.contains(-sub)) result.add(addTarget)
                            }
                        }
                    }
                }
            }
            when {
                plusStart != plusEnd -> plusStart++
                plusStart == plusEnd -> {
                    plusStart = 0
                    minusStart++
                }
            }
        }
        return result.toList()
    }
}