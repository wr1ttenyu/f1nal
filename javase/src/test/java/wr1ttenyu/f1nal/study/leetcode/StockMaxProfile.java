package wr1ttenyu.f1nal.study.leetcode;

public class StockMaxProfile {

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int sum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                int j = i + 1;
                for (; j < prices.length; j++) {
                    if (prices[j + 1] > prices[j]) continue;
                    else break;
                }
                sum += prices[j] - prices[i];
                i = j;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] test = {7, 1, 5, 3, 6, 4};

        StockMaxProfile stockMaxProfile = new StockMaxProfile();

        System.out.println(stockMaxProfile.maxProfit(test));

        /*List<Integer> usedValue = new ArrayList<>();
        usedValue.add(5);
        usedValue.contains(5);*/
    }

}
