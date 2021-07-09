package com.company;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class CasearCipher {

    final static String FILE_NAME = System.getProperty("user.home") + "\\Desktop\\cipher_file.txt";
    private static Path path = Paths.get(FILE_NAME);

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String userInput = "";
        //Start of program!
        while(true) {
            //ASK FOR THE USER TASK OF ENCRYPT OR DECRYPT
            //System.out.println("Encrypt or decrypt a message?");
            userInput = askUserToEncryptOrDecrypt(scan, userInput);
            System.out.println(userInput);
            if(userInput.equals("encrypt")) {
                //CREATE A FILE
                createAFile();
                //ASK FOR MESSAGE
                String askForMessage = askForOriginalMessage(scan);
                System.out.println(askForMessage);
                //ASK FOR KEY
                String askForKey = askForEncryptKey(scan);
                int key = convertStringKeyToIntKey(askForKey);
                //ENCRYPT THE MESSAGE!
                System.out.println(encryptMethod(askForMessage, key));
                //WRITE TO FILE
                writeMessageToFile(askForMessage, key);
                //Keep asking to encrypt or decrypt
                continue;

            } else if(userInput.equals("decrypt")) {

                //AskForCipherMessage
                String askForCipherMessage = askForCipherMessage(scan);
                System.out.println(askForCipherMessage);
                //ASK FOR KEY
                String askForCipherKey = askForCipherKey(scan);
                int cipherKey = parseCipherKey(askForCipherKey);
                System.out.println(cipherKey);
                //READ FROM FILE AND COMPARE TRANSLATED MESSAGE AND CIPHER KEY WITH CIPHER MESSAGE AND KEY IN THE FILE
                String result = readFileAndCheckMessage_Key(askForCipherMessage, cipherKey);
                //ORIGINAL MESSAGE
                System.out.println("The original message is: " + result);
                break;
            }
        }
    }

    public static String readFileAndCheckMessage_Key(String askForCipherMessage, int cipherKey) {
        String result = "";
        try {
            List<String> cipherLines = Files.readAllLines(path);


            String line = "";
            boolean correctKey = true;
            for(int i = 0; i < cipherLines.size(); i++) {
                line = cipherLines.get(i);
                String[] a = line.split(", ");

                if(!(a[1].contains(askForCipherMessage) || a[2].equals(String.valueOf(cipherKey)))) {
                    System.out.println("The cipher message and key combination you entered is not in the file! Sorry!");
                    break;
                }

                if(a[1].equals(askForCipherMessage) && a[2].equals(String.valueOf(cipherKey))) {
                    //Original Message?
                    result = a[0];
                    correctKey = true;
                    break;
                } else {
                    correctKey = false;
                }
            }

            if(correctKey == false) {
                System.out.println(decryptMethod(askForCipherMessage, cipherKey));
                System.out.println("Not sure if you put the right key..");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int parseCipherKey(String askForCipherKey) {
        int cipherKey = 0;
        try {
            cipherKey = Integer.parseInt(askForCipherKey);
        } catch (Exception e) {

        }
        return cipherKey;
    }

    public static String askForCipherKey(Scanner scan) {
        String askForCipherKey = "";
        while(true) {
            try {
                System.out.println("Enter a key! (1-52)?");
                askForCipherKey = scan.nextLine();
                if(Integer.parseInt(askForCipherKey) > 0 && Integer.parseInt(askForCipherKey) <= 52){
                    break;
                } else {
                    System.out.println("You need to enter a number between 1-52");
                }
            } catch (Exception e) {
                System.out.println("You need to enter a number between 1-52");
                System.out.println(e.getMessage());
            }
        }
        return askForCipherKey;
    }

    public static String askForCipherMessage(Scanner scan) {
        String askForCipherMessage = "";
        System.out.println("Enter a message!");
        while(true) {
            try {
                askForCipherMessage = scan.nextLine();
                if(askForCipherMessage.matches("^[a-zA-Z~`!^()_';:?>.<,-]+$") || askForCipherMessage.contains(" ")) {
                    break;
                } else if(!(askForCipherMessage.equals("") || askForCipherMessage.matches("^[a-zA-Z~`!^()_';:?>.<,-]+$") || askForCipherMessage.contains(" "))){
                    throw new RuntimeException("Message should not have numbers! Try again!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return askForCipherMessage;
    }

    public static void writeMessageToFile(String askForMessage, int key) {
        try {
            Files.write(path, (askForMessage
                    + ", "
                    + encryptMethod(askForMessage, key)
                    + ", "
                    + String.valueOf(key) + "\n").getBytes(), StandardOpenOption.APPEND);
            System.out.println("Wrote the cipher into the file!");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static int convertStringKeyToIntKey(String askForKey) {
        int key = 0;
        try {
            key = Integer.parseInt(askForKey);
        } catch (Exception e) {

        }
        return key;
    }

    public static String askForEncryptKey(Scanner scan) {
        String askForKey = "";
        while(true) {
            try {
                System.out.println("Enter a key! (1-52)?");
                askForKey = scan.nextLine();
                if(Integer.parseInt(askForKey) > 0 && Integer.parseInt(askForKey) <= 52){
                    break;
                } else {
                    System.out.println("You need to enter a number between 1-52");
                }
            } catch (Exception e) {
                System.out.println("You need to enter a number between 1-52");
                System.out.println(e.getMessage());
            }
        }
        return askForKey;
    }

    public static String askForOriginalMessage(Scanner scan) {
        String askForMessage = "";
        System.out.println("Enter a message!");
        while(true) {
            try {
                askForMessage = scan.nextLine();
                if(askForMessage.matches("[a-zA-Z]+") || askForMessage.contains(" ")) {
                    break;
                } else if(!(askForMessage.equals("") || askForMessage.matches("[a-zA-Z]+") || (askForMessage.contains(" ")))){
                    throw new RuntimeException("Message should only have letters!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return askForMessage;
    }

    public static void createAFile() throws IOException {
        try {
            Path createdFilePath = Files.createFile(path);
            System.out.println("Created a file at: " + createdFilePath);
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already created!");
        }
    }

    public static String askUserToEncryptOrDecrypt(Scanner scan, String userInput) {
        while(true) {
            try {
                System.out.println("Encrypt or decrypt a message?");
                userInput = scan.next();
                if(userInput.equals("encrypt") || userInput.equals("decrypt")) {
                    break;
                } else if(!(userInput.equals("encrypt") || userInput.equals("decrypt") || userInput.equals(""))){
                    throw new RuntimeException("Type encrypt or decrypt, please!");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return userInput;
    }

    public static String encryptMethod(String message, int offSet) {
        String result = "";

        for(int i = 0; i < message.length(); i++) {
            char currentLetter = message.charAt(i);

            if(currentLetter >= 'A' && currentLetter <= 'Z') {
                currentLetter += offSet % 26;
                result += (char) (currentLetter > 'Z' ? currentLetter - 26: currentLetter);
            } else if(currentLetter >= 'a' && currentLetter <= 'z') {
                currentLetter += offSet % 26;
                result += (char) (currentLetter > 'z' ? currentLetter - 26: currentLetter);
            } else {
                result += currentLetter;
            }
        }
        return result;
    }

    public static String decryptMethod(String message, int offSet) {
        String result = "";

        for(int i = 0; i < message.length(); i++) {
            char currentLetter = message.charAt(i);

            if(currentLetter >= 'A' && currentLetter <= 'Z') {
                currentLetter -= offSet % 26;
                result += (char) (currentLetter < 'A' ? currentLetter + 26: currentLetter);
            } else if(currentLetter >= 'a' && currentLetter <= 'z') {
                currentLetter -= offSet % 26;
                result += (char) (currentLetter < 'a' ? currentLetter + 26: currentLetter);
            } else {
                result += currentLetter;
            }
        }
        return result;
    }
}
