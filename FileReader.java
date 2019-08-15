/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isc220;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author Tiger
 */
public class FileReader {
    String capSentence;
    String newSentence;
    ArrayList<String> dictionaryWords = new ArrayList<>();
    int wordCounter = 0;
    String[] sentencePieces;
    String fileText = "";
    int capCounter = 0;
    int wordDistance;

    public void fileLoad(String sentence) throws FileNotFoundException {
        HashMap<String, Boolean> dictionary = new HashMap<>();
        File file = new File("wordlist.txt");
        Scanner fileScan = new Scanner(file);

        System.out.println("Reading file...");
        while (fileScan.hasNextLine()) {
            dictionaryWords.add(fileScan.nextLine());

        }

        for (int i = 0; i < dictionaryWords.size() - 2; i++) {
            dictionary.put(dictionaryWords.get(i), Boolean.TRUE);
        }
        sentencePieces = sentence.split(" ");
        for (int i = 0; i < sentencePieces.length; i++) {
            if (dictionary.containsKey(sentencePieces[i].replace(".", "").replace(",", "").toLowerCase())) {
                wordCounter++;
            } else {

            }

        }
        if (wordCounter < sentencePieces.length) {
            System.out.println("This sentence contains a misspelling.");
            for(int i = 0; i < sentencePieces.length; i++){
                if(!dictionary.containsKey(sentencePieces[i].replace(".", "").toLowerCase())){
                    System.out.println("You said " + "\"" + sentencePieces[i].replace(".", "").replace(",", "") +"\"");
                    FileReader.hammingDistanceCalc(dictionaryWords, sentencePieces[i]);
                } 
            }

        } else {
            System.out.println("This sentence contains no misspellings.");

        }
    }

    public void capitalNPeriod(String sentence) {
        if (sentence.equals(sentence.substring(0, 1).toUpperCase() + sentence.substring(1))) {
            System.out.println("Your sentence properly begins with a capitalized letter.");

        } else {
            System.out.println("The letter " + "\"" + sentence.substring(0, 1) + "\"" + " in the beginning of the sentence should be capitalized.");
            newSentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
            System.out.println(newSentence);

        }
        if (sentence.equals(sentence.substring(0, sentence.length() - 1) + ".")) {
            System.out.println("Your sentence correctly ends with a period");

        } else {
            System.out.println("This sentence is missing a " + "\"" + "." + "\"" + " after the letter " + "\"" + sentence.substring(sentence.length() - 1, sentence.length()) + "\"" + " in the last word.");
            newSentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1, sentence.length()) + ".";
            System.out.println(newSentence);

        }

    }

    public static int hammingDistance(String wordOne, String wordTwo) {
        wordOne = wordOne.toLowerCase();
        wordTwo = wordTwo.toLowerCase();
        int wordDistance = 0;
        int lengthDif = Math.abs(wordOne.length() - wordTwo.length());
        int wordIndex = Math.min(wordOne.length(), wordTwo.length());
        for (int i = 0; i < wordIndex; i++) {
            if (wordOne.charAt(i) != wordTwo.charAt(i)) {
                wordDistance++;
            }

        }
        return wordDistance + lengthDif;
    }

    public static void hammingDistanceCalc(ArrayList<String> list, String misspelledWord) {
        ArrayList<String> recommendations = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (FileReader.hammingDistance(list.get(i), misspelledWord.replace(".", "").replace(",", "")) <= 2) {
                recommendations.add(list.get(i));
            }
        }
        System.out.print("Consider these: ");
        System.out.println(recommendations);
    }

}
