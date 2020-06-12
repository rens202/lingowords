package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordServiceTest {
    WordService wordService;
    Language language;

    @Before
    public void setUp() {
        wordService = new WordService();
        language = new Language(1);
    }


    @Test
    public void createWordAndList() {
        Wordlist wordlist = wordService.createWordlist("wordlistname", 1, language);
        assertNotNull(wordlist);
        Word word = wordService.createWord(1, "word", wordlist);
        assertEquals(word.getWordlist(), wordlist);
    }

    @Test
    public void createWordAndList2() {

        Wordlist wordlist = wordService.createWordlist("wordlistname", language);
        assertNotNull(wordlist);
        Word word = wordService.createWord("word", wordlist);
        assertEquals(word.getWordlist(), wordlist);
    }



}