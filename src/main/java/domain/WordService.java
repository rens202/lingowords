package domain;

import persistence.WordsDao;
import persistence.WordsDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class WordService {
    WordsDao wordsDao = new WordsDaoImpl();


    public boolean sendWords(ArrayList<Word> words){
        Boolean result = wordsDao.sendWords(words);

        return result;

    }

    public boolean sendWord(Word word){
        Boolean result = false;
        result = wordsDao.sendWord(word);

        return result;
    }

    public Word createWord(String word, Wordlist wordlist){
        Word result;

        result = new Word(word, wordlist);



        return result;

    }

    public Wordlist createList(String fileName, Language language){
        Wordlist result;

        result = new Wordlist(fileName, language);


        return result;

    }

    public HashMap<String, Wordlist> getWordLists() {
        HashMap<String, Wordlist> result;
        return wordsDao.getWordLists();
    }

    public Wordlist createWordlist(String name, int id, Language language) {
        Wordlist result = new Wordlist(id, name, language);
        return result;
    }
}
