class Solution {
	public int maxProfit(int[] prices) {
		int maxProfit = 0;
		for (int i=0; i<prices.length-1; i++) {
			int profit = prices[i+1] - prices[i];
			maxProfit += (profit > 0) ? profit : 0;
		}
		return maxProfit;
	}
}