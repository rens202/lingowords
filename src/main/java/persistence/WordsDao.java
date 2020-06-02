package persistence;

import domain.Word;
import domain.Wordlist;

import java.util.ArrayList;
import java.util.HashMap;

public interface WordsDao {
    Boolean sendWords(ArrayList<Word> words);

    Boolean sendWord(Word word);

    ArrayList<Wordlist> getWordLists();

	ArrayList<Word> getWordsFromList(int id);
}
