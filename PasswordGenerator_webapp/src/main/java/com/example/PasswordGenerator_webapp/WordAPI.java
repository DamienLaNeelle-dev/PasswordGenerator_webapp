package com.example.PasswordGenerator_webapp;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WordAPI {

    public static String getRandomWord() {
        String urlString = "https://random-word-api.herokuapp.com/word";

        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            String json = response.toString();
            System.out.println("API Response: " + json);  // Affichage de la réponse brute de l'API

            return capitalizeFirstLetter(json.substring(2, json.length() - 2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String capitalizeFirstLetter(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
