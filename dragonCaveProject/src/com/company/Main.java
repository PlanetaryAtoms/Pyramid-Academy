package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner player = new Scanner(System.in);

        String []prompt = { "You are in a land of dragons.",
                "In front of you, you see two caves.",
                "In one cave, the dragon is friendly and "
                        + "will share his treasure with you.",
                "The other dragon is greedy and hungry "
                        + "and will eat you on sight.",
                "Which cave will you go into? (1 or 2)"
        };

        System.out.println();

        for(String text : prompt) {
            System.out.println(text);
        }
        System.out.println();

        int input = player.nextInt();
        boolean flag = true;
        while(flag) {
            if(input == 1) {
                System.out.println();
                System.out.println("You approach the cave...");
                System.out.println("It is dark and spooky...");
                System.out.println("A large dragon jumps out in front "
                        + "of you! \nHe opens his jaws and...");
                System.out.println("Gobbles you down in one bite!");
                flag = false;
            } else if(input == 2) {
                System.out.println();
                System.out.println("You enter the other cave...");
                System.out.println("It is pretty lit inside...");
                System.out.println("A tine dragon walks out in front \n"
                        + "of you! He opens the chest before him and \n"
                        + "gives you 3 billions gold pieces...");
                System.out.println("He says you deserve this reward "
                        + "for picking the right cave.");
                flag = false;
            } else {
                System.out.println("Error! Enter 1 or 2. Please.");
                flag = false;
            }
        }

        player.close();

    }
}
