package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*  You're going to make a Guess the Number game. The computer will "think" of a
            secret number from 1 to 20 and ask the user to guess it. After each guess, the
            computer will tell the user whether the number is too high or too low. The user wins
            if they can guess the number within six tries.

         */

        Scanner player = new Scanner(System.in);

        System.out.println("Hello! What is your name?");
        String name = player.nextLine();
        System.out.println("Well, " + name + ", I am thinking of a number " +
                            "between 1 and 20. \nTake a guess.");
        int userNumber = player.nextInt();
        int counter = 1;
        String choice = "";
        int computerGuess = (int) (Math.random() * 21);
        System.out.println(computerGuess);
        while(choice != "n"){
            if(userNumber > computerGuess){
                System.out.println("Your guess is too high.");
                System.out.println("Take a guess.");
            } else if(userNumber < computerGuess) {
                System.out.println("Your guess is too low.");
                System.out.println("Take a guess.");
            } else if(userNumber == computerGuess) {
                System.out.println("Good job, " + name + "! You guessed my number in " + counter + " guesses!");
                System.out.println("You guessed the number within 6 tries.");
                System.out.println("Would you like to play again? (y or n)");
                choice = player.next();
                computerGuess = (int) (Math.random() * 21);
                counter = 0;
            }

            if(choice.equals("n")){
                System.out.println("Thanks for playing!");
                break;
            } else if(choice.equals("y")){
                System.out.println("Let's Keep playing!");
                choice = "";
                System.out.println(computerGuess);
            }
            userNumber = player.nextInt();
            counter++;

            if(counter > 5 && (userNumber != computerGuess)){
                System.out.println("You used up your six tries.");
                System.out.println("Game Over.");
                break;
            }
        }

        player.close();

    }
}
