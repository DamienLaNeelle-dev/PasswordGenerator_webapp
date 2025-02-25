package com.example.PasswordGenerator_webapp;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class WordAPI {

    private static final Random RANDOM = new Random();

    public static String getRandomWord() {
        List<String> words = loadWords();
        if (words.isEmpty()) {
            return null;
        }
        return words.get(RANDOM.nextInt(words.size())); // Sélectionne un mot au hasard
    }

    private static List<String> loadWords() {
        List<String> words = new ArrayList<>();

        try (InputStream inputStream = WordAPI.class.getClassLoader().getResourceAsStream("mots.txt")) {
            if (inputStream == null) {
                throw new IOException("Le fichier mots.txt est introuvable dans les ressources.");
            }

            // Utiliser un scanner pour lire chaque ligne du fichier
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    words.add(scanner.nextLine().trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
