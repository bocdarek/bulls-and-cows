package bullscows;

import java.util.Random;

public class SecretCodeGenerator {

    public final String allSymbols = "0123456789abcdefghijklmnoprqstuvwxyz";

    public SecretCodeGenerator() {
    }

    public String generateCode(int length, int numberOfSymbols) {
        if (!this.isLengthValid(length, numberOfSymbols)) {
            return null;
        }
        StringBuilder secretCode = new StringBuilder();
        Random rd = new Random();
        String availableChars = allSymbols.substring(0, numberOfSymbols);
        while (secretCode.length() < length) {
            String nextChar = String.valueOf(availableChars.charAt(rd.nextInt(numberOfSymbols)));
            if (!secretCode.toString().contains(nextChar)) {
                secretCode.append(nextChar);
            }
        }
        return secretCode.toString();
    }

    private boolean isLengthValid(int length, int numberOfSymbols) {
        return length <= 36 && length <= numberOfSymbols;
    }
}
