package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageTest {
    private Language language;

    @Before
    public void setUp() {
        language = new Language(1, "Nederlands", "NED");
    }

    @Test
    public void getCode() {
        assertNotNull(language.getCode());
        assertEquals("NED", language.getCode());
    }

    @Test
    public void getId() {
        assertNotNull(language.getId());
        assertEquals(1, language.getId());
    }

    @Test
    public void getName() {
        assertNotNull(language.getName());
        assertEquals("Nederlands", language.getName());

    }
}