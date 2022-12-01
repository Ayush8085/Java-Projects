import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Let's start the game : ");

        // generating a random number from 1 - 100
        Random rand = new Random();
        int randNumber = rand.nextInt(100) + 1;
        int count_guesses = 0;
        while (true) {
            int playerChoice = sc.nextInt();
            if (playerChoice == randNumber) {
                System.out.println("The number is guessed in " + count_guesses + " tries");
                break;
            } else if (playerChoice < randNumber) {
                System.out.print("\nGuess a higher number!: ");
            } else if (playerChoice > randNumber) {
                System.out.print("\nGuess a lower number!: ");
            }
            count_guesses++;
        }
    }
}
