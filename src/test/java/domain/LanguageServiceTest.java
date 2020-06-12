package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageServiceTest {
    LanguageService languageService;

    @Before
    public void setUp() throws Exception {
        languageService = new LanguageService();

    }

    @Test
    public void createLanguage() {
        Language language = languageService.createLanguage("Nederlands", "NED", 1);
        assertEquals(language.getCode(), "NED");
        assertEquals(language.getName(), "Nederlands");
        assertEquals(language.getId(), 1);
    }

    @Test
    public void createLanguage1() {
        Language language = languageService.createLanguage(1);
        assertEquals(language.getId(), 1);
        assertNull(language.getCode());
        assertNull(language.getName());
    }
}