package com.example.PasswordGenerator_webapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@RestController
public class PasswordController {

    private static final String NUMBERS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*()-_=+";
    private static final Random RANDOM = new Random();

    @GetMapping("/generate-password")
    public String generatePassword(@RequestParam int length) {
        if (length < 8) {
            return "La longueur du mot de passe doit être supérieure ou égale à 8.";
        }

        // Récupérer les mots aléatoires depuis le fichier mots.txt
        String word1 = WordAPI.getRandomWord();
        String word2 = WordAPI.getRandomWord();

        // Vérifier que les mots sont valides
        if (word1 == null || word2 == null) {
            return "Erreur lors de la génération des mots.";
        }

        // Ajouter un chiffre
        String number = String.valueOf(NUMBERS.charAt(RANDOM.nextInt(NUMBERS.length())));

        // Ajouter entre 1 et 3 caractères spéciaux aléatoires
        int numSpecials = RANDOM.nextInt(3) + 1;
        StringBuilder specials = new StringBuilder();
        for (int i = 0; i < numSpecials; i++) {
            specials.append(SPECIALS.charAt(RANDOM.nextInt(SPECIALS.length())));
        }

        // Créer une liste de tous les éléments à mélanger (mots + chiffres + caractères spéciaux)
        List<String> passwordParts = new ArrayList<>();
        passwordParts.add(word1);   // Premier mot
        passwordParts.add(word2);   // Deuxième mot
        passwordParts.add(number);  // Le chiffre
        passwordParts.add(specials.toString()); // Les caractères spéciaux

        // Mélanger les éléments de la liste (ordre des mots, chiffre, et caractères spéciaux)
        Collections.shuffle(passwordParts);

        // Reconstruire le mot de passe final en ajoutant les éléments mélangés
        StringBuilder finalPassword = new StringBuilder();
        for (String part : passwordParts) {
            finalPassword.append(part);
        }

        // Retourner le mot de passe généré
        return finalPassword.toString();
    }
}
