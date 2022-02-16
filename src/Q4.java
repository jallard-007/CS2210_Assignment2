import java.util.Arrays;

public class Q4 {
    public static int q4(int[] coins, int value){
        Arrays.sort(coins);
        int[] variations = new int[value + 1];
        variations[0] = 1;
        for (int i = coins.length - 1; i >= 0; --i) {
            for (int j = 1; j < variations.length; ++j) {
                if (j - coins[i] >= 0) {
                    variations[j] += variations[j - coins[i]];
                }
            }
        }
        return variations[variations.length-1];
    }
    public static void main(String[] args) {
        // test case 1. should return 7
        System.out.println(q4(new int[]{3, 2, 1}, 6));
        // test case 2. since the algorithm is always O(n), the algorithm is always at its worst.
        System.out.println(q4(new int[]{25, 10, 5, 1}, 200));
    }
}
