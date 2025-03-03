class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()

        val duplicatedNumsSet = mutableSetOf<Int>()
        val minusNumsList = mutableListOf<Int>()
        val zeroNumsList = mutableListOf<Int>()
        val plusNumsList = mutableListOf<Int>()
        lateinit var minusNumsSet: MutableSet<Int>
        lateinit var plusNumsSet: MutableSet<Int>
        nums.sort()//

        for (index in 0 until nums.size - 1) {
            when {
                nums[index] < 0 -> minusNumsList.apply {
                    add(nums[index])
                    if (nums[index] == nums[index + 1]) {
                        duplicatedNumsSet.add(nums[index])
                    }
                }
                nums[index] == 0 -> zeroNumsList.apply {
                    add(nums[index])
                    if (nums[index] == nums[index + 1]) {
                        duplicatedNumsSet.add(nums[index])
                    }
                }
                nums[index] > 0 -> plusNumsList.apply {
                    add(nums[index])
                    if (nums[index] == nums[index + 1]) {
                        duplicatedNumsSet.add(nums[index])
                    }
                }
            }

            if (index + 1 == nums.lastIndex) {
                when {
                    nums[index + 1] < 0 -> minusNumsList.apply { add(nums[index + 1]) }
                    nums[index + 1] == 0 -> zeroNumsList.apply { add(nums[index + 1]) }
                    nums[index + 1] > 0 -> plusNumsList.apply { add(nums[index + 1]) }
                }
            }
        }

        plusNumsSet = plusNumsList.toMutableSet()
        minusNumsSet = minusNumsList.toMutableSet()

        if (zeroNumsList.size >= 3) result.add(listOf(0,0,0))
        if (minusNumsList.size == 0 || plusNumsList.size == 0) return result.toList()

        var minusStart = 0
        val minusEnd = minusNumsList.size - 1

        var plusStart = 0
        val plusEnd = plusNumsList.size - 1

        while (minusStart <= minusEnd && plusStart <= plusEnd) { //
            val sub = minusNumsList[minusStart] + plusNumsList[plusStart]

            when {
                -sub == 0 -> {
                    val addTarget = listOf(minusNumsList[minusStart], plusNumsList[plusStart], -sub).sorted()
                    if (!result.contains(addTarget) && zeroNumsList.isNotEmpty()) {
                        result.add(listOf(minusNumsList[minusStart], 0, plusNumsList[plusStart]))
                    }
                }
                -sub > 0 -> {
                    val addTarget = listOf(minusNumsList[minusStart], plusNumsList[plusStart], -sub).sorted()
                    if (!result.contains(addTarget)) {
                        when (-sub == plusNumsList[plusStart]) {
                            true -> {
                                if (duplicatedNumsSet.contains(-sub)) {
                                    result.add(addTarget)
                                }
                            }
                            false -> {
                                if (plusNumsSet.contains(-sub)) {
                                    result.add(addTarget)
                                }
                            }
                        }
                    }
                }
                -sub < 0 -> {
                    val addTarget = listOf(minusNumsList[minusStart], plusNumsList[plusStart], -sub).sorted()
                    if (!result.contains(addTarget)) {
                        when (-sub == minusNumsList[minusStart]) {
                            true -> {
                                if (duplicatedNumsSet.contains(-sub)) {
                                    result.add(addTarget)
                                }
                            }
                            false -> {
                                if (minusNumsSet.contains(-sub)) {
                                    result.add(addTarget)
                                }
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
