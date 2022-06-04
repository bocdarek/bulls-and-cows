package bullscows;

public class Grader {

    public int calculateBulls(String guess, String code) {
        int bulls = 0;
        for (int i = 0; i < guess.length(); ++i) {
            if (guess.charAt(i) == code.charAt(i)) {
                ++bulls;
            }
        }
        return bulls;
    }

    public int calculateCows(String guess, String code) {
        int cows = 0;
        for (int i = 0; i < guess.length(); ++i) {
            for (int j = 0; j < code.length(); ++j) {
                if (i == j) {
                    continue;  // not interested in bulls
                }
                if (guess.charAt(j) == code.charAt(i)) {
                    ++cows;
                    break;
                }
            }
        }
        return cows;
    }
}
