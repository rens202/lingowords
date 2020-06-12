package persistence;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LanguageDaoImplTest {
	LanguageDao languageDao;
	String jsonData;

	@Before
	public void setUp() {
		languageDao = new LanguageDaoImpl();
		jsonData = "{\"code\": \"ENG\", \"name\": \"English\"}";
	}

	@Test
	public void getAllLanguages() {
		try {
			languageDao.getAllLanguages();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void postLanguage() {
		try {
			languageDao.postLanguage(jsonData);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}