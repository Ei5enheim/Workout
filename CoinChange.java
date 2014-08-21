/**
 * Author: Rajesh Gopidi
 *
 */

public class CoinChange
{
    
    public static int computeChange (int sum, int[] availableCoins)
    {
        int[] coinCount = new int [sum + 1];        
        int temp = 0;

        for (int i = 1; i <= sum; i++)
            coinCount[i] = INFINITY;
 
        for (int i = availableCoins[0]; i <= sum; i++) {
            for (int j = 0; j < availableCoins.length; j++) {
                temp = (i-availableCoins[j] >= 0) ? coinCount[i-availableCoins[j]] + 1 : INFINITY;                
                if (temp < coinCount[i])
                    coinCount[i] = temp;
            }
        }
        return (coinCount[sum]);
    }

    public static void main (String[] args)
    {
        int[] availableCoins = {2,4,5};
        
        System.out.println("Number of coins is: "+ computeChange(36, availableCoins));
    }
    private static int INFINITY = 999999;
}
