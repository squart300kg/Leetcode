class Solution {
    fun maxProfit(prices: IntArray): Int {
        var min = Int.MAX_VALUE
        var result = 0
        for (price in prices) {
            min = Math.min(price, min)
            result = Math.max(result, price - min)
//            println("price : $price, min : $min, result : $result")
        }

        return result
    }
}