import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class test {

    public static void main(String[] args) {
        Integer[] weights = {15,30,55};
        Integer weight_sum = 0;
        for (Integer w : weights) {
            weight_sum += w;
        }
        System.out.println(Arrays.toString(weights));
        System.out.println(weight_sum);
        Integer[] winStat = {0,0,0};
        Integer winNum = 0;
        Integer temp_sum = 0;
        Boolean lf_winner = true;
        Integer runs = 1_000_000_000;
        Integer errorCount = 0;
        Integer[] numPickStats = new Integer[100];
        for (int i = 0; i < numPickStats.length; i++) {
            numPickStats[i] = 0;
        }
        for (int i = 0; i < runs; i++) {
            winNum = ThreadLocalRandom.current().nextInt(1, weight_sum + 1);
            for (int j = 0; lf_winner == true && j < weights.length; j++) {
                temp_sum = temp_sum + weights[j];
                if (temp_sum >= winNum){ // Win
                    // System.out.println("Выиграл номер с весом: " + weights[j]);
                    winStat[j] = winStat[j] + 1;
                    lf_winner = false;
                    temp_sum = 0;
                } 
            }
            // if (true && lf_winner){
            //     errorCount = errorCount + 1;
            //     System.out.println("ОШИБКА! #" + errorCount);
            //     System.out.println("Искомый номер: " + winNum);
            //     // System.out.println(null);
            // }
            // if (winNum == 0){
            //     System.out.println("НОЛЬ");
            // }
            // numPickStats[winNum - 1] = numPickStats[winNum - 1] + 1;
            lf_winner = true;
        }
        System.out.println(Arrays.toString(winStat));
        int sum_res = 0;
        for (Integer a : winStat) {
            sum_res = sum_res + a;
        }
        // System.out.println(sum_res - runs);
        System.out.println(Arrays.toString(numPickStats));
        // int x = 0;
        // for (Integer b : numPickStats) {
        //     if (b != 0)
        //         x = x +1;
        // }
        // System.out.println("Реальных вариантов:" + x);
    }
}