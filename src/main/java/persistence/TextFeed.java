package persistence;

import domain.Word;
import domain.WordService;
import domain.Wordlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextFeed implements FileReader{

    public ArrayList<Word> readFile(String fileName, String listName, Wordlist wordlist){
        ArrayList<Word> result = new ArrayList<>();
        WordService wordService = new WordService();
        try (
            BufferedReader br = new BufferedReader(
                new java.io.FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {

                if(line.length() > 4 && line.length() < 7 && !line.matches(".*[-,\'1234567890.:;_].*") && !Character.isUpperCase(line.charAt(0))){
                    result.add(wordService.createWord(line, wordlist));
                }

            }
        } catch (IOException e) {
             e.printStackTrace();
        }
        return result;
    }
}
