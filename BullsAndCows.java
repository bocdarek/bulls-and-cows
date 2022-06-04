package bullscows;

import java.util.Scanner;

public class BullsAndCows {

    private final Scanner scanner = new Scanner(System.in);
    private final SecretCodeGenerator codeGenerator = new SecretCodeGenerator();
    private final Grader grader = new Grader();
    private String secretCode;
    private int codeLength;
    private int bulls;
    private int cows;


    public void play() {
        bulls = 0;
        cows = 0;
        if (!isGameStarting()) {
            return;
        }
        int counter = 1;
        while (bulls != codeLength) {
            System.out.printf("Turn %d:%n", counter);
            nextTurn();
            counter++;
        }
        farewell();
    }

    private boolean isGameStarting() {
        System.out.println("Input the length of the secret code:");
        codeLength = takeInt();
        if (codeLength == -1) {
            return false;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int numberOfSymbols = takeInt();
        if (numberOfSymbols == -1) {
            return false;
        }
        if (codeLength > numberOfSymbols) {
            System.out.printf("Error: it's not possible to generate a code with" +
                    " a length of %d with %d unique symbols.%n", codeLength, numberOfSymbols);
            return false;
        }
        if (numberOfSymbols > codeGenerator.allSymbols.length()) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return false;
        }
        secretCode = codeGenerator.generateCode(codeLength, numberOfSymbols);
        if (secretCode == null) {
            System.out.printf("Error: can't generate a secret number with a length of %d" +
                    " because there aren't enough unique digits.%n", codeLength);
            return false;
        }
        startingMessage(codeLength, numberOfSymbols);
        return true;
    }

    private void nextTurn() {
        String guess = scanner.next();
        scanner.nextLine();
        bulls = grader.calculateBulls(guess, secretCode);
        cows = grader.calculateCows(guess, secretCode);
        evaluateGuess();
    }

    private void evaluateGuess() {
        StringBuilder sb = new StringBuilder("Grade: ");
        if (bulls == 0 && cows == 0) {
            sb.append("None.");
        } else {
            if (bulls != 0 && cows != 0) {
                sb.append(String.format("%d bull(s) and %d cows(s).", bulls, cows));
            } else if (bulls != 0) {
                sb.append(String.format("%d bull(s).", bulls));
            } else {
                sb.append(String.format("%d cow(s).", cows));
            }
        }
        System.out.println(sb);
    }

    private void startingMessage (int codeLength, int numberOfSymbols) {
        StringBuilder message = new StringBuilder("The secret is prepared: ");
        message.append("*".repeat(codeLength));
        message.append(" (0-");
        if (numberOfSymbols > 10) {
            message.append("9, a-");
            message.append(codeGenerator.allSymbols.charAt(numberOfSymbols - 1));
        } else {
            message.append(codeGenerator.allSymbols.substring(0,10).charAt(numberOfSymbols - 1));
        }
        message.append(").");
        message.append("\nOkay, let's start a game!");
        System.out.println(message);
    }

    private void farewell() {
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private int takeInt() {
        String number = scanner.nextLine();
        int intNumber;
        try {
            intNumber = Integer.parseInt(number);
            if (intNumber == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            intNumber = -1;
            System.out.println("Error: " + number + " isn't valid number.");
        }
        return intNumber;
    }
}
