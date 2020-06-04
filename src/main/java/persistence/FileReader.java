package persistence;

import domain.Word;
import domain.Wordlist;

import java.util.ArrayList;

public interface FileReader {

    ArrayList<Word> readFile(String fileName, String listName, Wordlist wordlist);
    
    Boolean readUrl(String url, Wordlist wordlist);
}
