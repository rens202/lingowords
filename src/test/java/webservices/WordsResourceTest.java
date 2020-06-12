package webservices;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class WordsResourceTest {
	private WordsResource wordsResource;
	private String jsonData;

	@Before
	public void setUp() {
		try {
			jsonData = "{\"language\": 1, \"name\":\"test\", \"url\": \"aaaa.txt\"}";
			wordsResource = new WordsResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWordLists() {
		try {
			Response result = wordsResource.getWordLists();
			assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void postWordLists() {
		try {
			Response result = wordsResource.postWordLists(jsonData);
			assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteWordList() {
		try {
			Response result = wordsResource.deleteWordList(1);
			assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void putWordList() {
		try {
			Response result = wordsResource.putWordList(1, "newWord");
			assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWordsFromList() {
		try {
			Response result = wordsResource.getWordsFromList(1);
			assertNotNull(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
