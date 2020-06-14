package webservices;

import org.junit.Before;
import org.junit.Test;

import domain.Language;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class LanguageResourceTest {
	private LanguagesResource languageResource;
	private String jsonData;

	@Before
	public void setUp() {
		jsonData = "{\"name\":\"Nederlands\", \"code\": \"NED\"}";
		languageResource = new LanguagesResource();

	}

	@Test
	public void getLanguages() {
		try {
			Response result = languageResource.getLanguages();
			assertNotNull(result);
		} catch (Exception e) {
		}
	}

	@Test
	public void postLanguage() {
		try {
			Response result = languageResource.postLanguage(jsonData);
			assertNotNull(result);
		} catch (Exception e) {
		}

	}
}
