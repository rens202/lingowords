package persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import domain.Wordlist;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RunWith(MockitoJUnitRunner.class)
public class LanguageDaoImplTest {
	LanguageDao languageDao;
	String jsonData;
	String query;
	@Mock private ResultSet res;

	@Before
	public void setUp() throws SQLException {
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
	
	@Test 
	public void getLanguagesFromRes() throws SQLException{
		Mockito.when(res.getInt("id")).thenReturn(1);
        Mockito.when(res.getString("code")).thenReturn("NED");
        Mockito.when(res.getString("name")).thenReturn("Nederlands");
		languageDao.getLanguageFromRes(res);
		
	}
}