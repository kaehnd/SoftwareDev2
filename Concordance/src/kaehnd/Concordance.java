/*
* Course: CS1021-001
* Daniel Kaehn
* Functional Programming Exercise
* 2-8-19
*/
package kaehnd;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
* Beginning of a class exercise that will build a simple Concordance for Moby Dick
* Students will read in the text of Moby Dick and normalize the data in three
* different ways, using standard Java, Functional Interfaces, and streams.
*/
@SuppressWarnings("SameParameterValue")
public class Concordance {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        try (Scanner in = new Scanner(new File("MobyDick.txt"))) {
            while (in.hasNext()) {
                words.add(in.next());
            }
        } catch (IOException e) {
            System.err.println("Could not read Moby Dick!!!");
        }

        Normalize punc = (w) -> {
            w = w.replace(".", "");
            w = w.replace(",", "");
            w = w.replace("\"", "");
            return w;
        };
        Normalize cap = w -> w.toLowerCase();


        // call your methods
        List<String> normalized1 = normalize(words);
        List<String> normalized2 = normalize(words, punc, cap, 4);
        List<String> normalized3 = normalize(words, 4);

        boolean same = true;

        for (int i = 0; i < normalized1.size(); i++) {
            same = normalized1.get(i).equals(normalized2.get(i)) &&
                    normalized1.get(i).equals(normalized3.get(i)) && same;
        }
        if (same) {
            System.out.println("All lists are the same!");
        } else {
            System.out.println("Problems.");
        }
    }

    /**
     * Method using standard Java programming (no functional programming) to normalize the data
     *
     * @param list List of Strings to normalize
     * @return Normalized list
     */
    private static ArrayList<String> normalize(ArrayList<String> list) {
        ArrayList<String> normalized = new ArrayList<>();
        for (String word : list) {
            word = word.replace(".", "");
            word = word.replace(",", "");
            word = word.replace("\"", "");
            word = word.toLowerCase();
            if (word.length() >= 4) {
                normalized.add(word);
            }
        }
        return normalized;
    }

    /**
     * Method using a functional interface to normalize the data
     *
     * @param list           List of Strings to normalize
     * @param punctuation    Punctuation normalizer
     * @param capitalization Capitalization normalizer
     * @param minLength      minimum length of word to be retained
     * @return Normalized list
     */
    private static ArrayList<String> normalize(ArrayList<String> list, Normalize punctuation,
                                               Normalize capitalization, int minLength) {
        ArrayList<String> normalized = new ArrayList<>();
        for (String word : list) {
            word = punctuation.normalize(word);
            word = capitalization.normalize(word);
            if (word.length() >= minLength) {
                normalized.add(word);
            }
        }
        return normalized;
    }

    /**
     * Method using streams to normalize the data
     *
     * @param list      list List of Strings to normalize
     * @param minLength minimum length of word to be retained
     * @return Normalized list
     */
    private static List<String> normalize(ArrayList<String> list, int minLength) {

        List<String> normalized = list.stream()
                .map(s -> s.replace(",", ""))
                .map(s -> s.replace(".", ""))
                .map(s -> s.replace("\"", ""))
                .map(s -> s.toLowerCase())
                .filter(s -> s.length() >= 4)
                .collect(Collectors.toList());
        return normalized;
    }
}

