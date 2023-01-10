public class TradersMaxProfit {
    public static void main(String[] args) {
        int[] arr = {40, 5, 80, 20, 60};
        int n = arr.length;
        System.out.println("Maximum possible profit is " + maxProfit(arr, n) + " $");
    }

    private static int maxProfit(int[] prices, int n) {
        int[] profit = new int[n];

        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (prices[i] > max) max = prices[i];

            profit[i] = Math.max(profit[i + 1], max - prices[i]);
        }

        int min = prices[0];

        for (int i = 1; i < n; i++) {
            if (prices[i] < min) min = prices[i];

            profit[i] = Math.max(profit[i - 1], profit[i] + prices[i] - min);
        }

        return profit[n - 1];
    }
}
