/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        double i = 1;
        while (!StdIn.isEmpty()) {
            String indicate = StdIn.readString();
            if (StdRandom.bernoulli((double) 1.0 / i)) {
                champion = indicate;
            }
            i = i + 1;
        }
        StdOut.println(champion);
    }
}
