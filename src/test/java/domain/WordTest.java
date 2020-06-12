package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    Word word;
    Word word2;
    Wordlist wordlist;

    @Before
    public void setUp(){
        wordlist = new Wordlist("wordlist", new Language(1));
        word = new Word("word", wordlist);
        word2 = new Word(1, "word2", wordlist);

    }

    @Test
    public void getWord() {
        assertNotNull(word.getWord());
        assertNotNull(word2.getWord());
        assertNotEquals(word2.getWord(), word.getWord());
    }

    @Test
    public void getWordlist() {
        assertNotNull(word.getWordlist());
        assertNotNull(word2.getWordlist());
        assertEquals(word2.getWordlist(), word.getWordlist());
    }
}