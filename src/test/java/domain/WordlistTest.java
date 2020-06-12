package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordlistTest {
    Wordlist wordlist;
    Wordlist wordlist2;
    Language language;

    @Before
    public void setUp() throws Exception {
        language = new Language(1);
        wordlist = new Wordlist("test2", language);
        wordlist2 = new Wordlist(1, "test", language);
    }

    @Test
    public void getId() {
        assertEquals(0, wordlist.getId());
        assertNotNull(wordlist2.getId());
    }

    @Test
    public void setId() {
        assertEquals(0, wordlist.getId());
        wordlist.setId(2);
        assertEquals(wordlist.getId(), 2);
    }

    @Test
    public void getLanguage() {
        assertNotNull(wordlist.getLanguage());
        assertNotNull(wordlist2.getLanguage());
        assertEquals(wordlist.getLanguage(), wordlist2.getLanguage());
    }

    @Test
    public void getName() {
        assertNotNull(wordlist.getName());
        assertNotNull(wordlist2.getName());
        assertNotEquals(wordlist.getName(), wordlist2.getName());
    }
}