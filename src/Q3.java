import java.util.Arrays;

public class Q3 {
    public static int q3(int[] coins, int value){
        Arrays.sort(coins);
        int[] combos = new int[value + 1];
        for (int i = 0; i <= value; ++i) {
            for (int coin : coins) {
                final int remainder = i - coin;
                if (remainder < 0) {
                    break;
                }
                if (remainder != 0 && combos[remainder] == 0) {
                    continue;
                }
                final int buffer = 1 + combos[remainder];
                if (buffer < combos[i] || combos[i] == 0) {
                    combos[i] = buffer;
                }
            }
        }
        return combos[value] == 0 ? -1 : combos[value];
    }
    public static void main(String[] args) {
        // test case 1, should return 2
        System.out.println(q3(new int[]{25, 10, 5}, 30));
        // test case 2. since the algorithm is always O(n), the algorithm is always at its worst.
        System.out.println(q3(new int[]{5, 25, 10, 1}, 193));
    }
}
