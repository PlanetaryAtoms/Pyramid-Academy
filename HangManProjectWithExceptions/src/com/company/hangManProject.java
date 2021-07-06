package com.company;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class hangManProject {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);
        Scanner exit = new Scanner(System.in);
        String[] wordsArr = { "Cat", "Dog", "Rabbit", "Dragon"	};
        List<String> words = new ArrayList<>();
        for(int i = 0; i < wordsArr.length; i++) {
            words.add(wordsArr[i]);
        }
        Random rand = new Random();
        String word = words.get(rand.nextInt(words.size()));

        List<Character> playerGuessed = new ArrayList<>();
        HashSet<Character> playerLetter = new HashSet<Character>();

        int wrongCount = 0;
        String letter = "";
        String choice = "";
        while(true) {

            printHangMan(wrongCount);

            if(wrongCount >= 6) {
                System.out.println("You Lose!");
                break;
            }
            printWordState(word, playerGuessed);
            try {
                System.out.println("Please enter a Letter: ");
                letter = userInput.next();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if(!word.contains(getPlayerGuess(userInput, word, playerGuessed, letter))) {
                for(int i = 0; i < playerGuessed.size(); i++) {
                    if(playerGuessed.contains(letter.charAt(0))) {
                        playerLetter.add(letter.charAt(0));
                    }
                }

                System.out.println("Missed Letter: " + playerLetter);
                wrongCount++;
            }


            if(printWordState(word, playerGuessed)) {
                System.out.println("Yes! The secret word is \"" + word + "\"! You have won!");
                if(printWordState(word, playerGuessed)) {
                    while(true) {
                        try {
                            System.out.println("Try Again?");
                            choice = exit.nextLine();
                            if(choice.equals("yes") || choice.equals("no")) {
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(choice.equals("yes")) {
                        System.out.println("Let's keep playing.");
                        playerGuessed.clear();
                        word = words.get(rand.nextInt(words.size()));
                        wrongCount = 0;
                    } else if (choice.equals("no")) {
                        System.out.println("Thanks for playing!");
                        break;
                    }
                }
            }
        }

        userInput.close();
        exit.close();
    }

    private static void printHangMan(int wrongCount) {
        System.out.println("H A N G M A N");
        System.out.println(" ------- ");
        System.out.println(" |     | ");

        if(wrongCount >= 1) {
            System.out.println(" O");
        }

        if(wrongCount >= 2) {
            System.out.print("\\ ");
            if(wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }

        if(wrongCount >= 4) {
            System.out.println(" |");
        }

        if(wrongCount >= 5) {
            System.out.print("/ ");
            if(wrongCount >= 6) {
                System.out.println("\\");
            } else {
                System.out.println("");
            }
        }

        System.out.println();
        System.out.println();
        System.out.println();
    }

    private static String getPlayerGuess(Scanner userInput, String word, List<Character> playerGuessed, String letter) {
        String letterGuess = letter;

        playerGuessed.add(letterGuess.charAt(0));

        return letterGuess;

    }

    private static boolean printWordState(String word, List<Character> playerGuessed) {
        int correctCount = 0;
        for(int i = 0; i < word.length(); i++) {
            if(playerGuessed.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }
        }

        System.out.println();
        return (word.length() == correctCount);
    }

}

