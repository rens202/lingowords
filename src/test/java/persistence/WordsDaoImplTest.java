package persistence;

import domain.Language;
import domain.Word;
import domain.Wordlist;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WordsDaoImplTest extends PostgresBaseDao {
	WordsDao wordsDao = new WordsDaoImpl();
	ArrayList<Word> words = new ArrayList<>();
	Wordlist wordlist;
	String jsonData;
	String jsonData2;
	@Mock
	private PreparedStatement preparedStatement;
	@Mock
	private ResultSet res;
	@Mock
	private Connection conn;
	String query;
	PostgresBaseDao bes = Mockito.spy(new PostgresBaseDao());

	

	@Before
	public void setUp() throws SQLException {
		wordlist = new Wordlist(1, "wordlist", new Language(1));
		words.add(new Word("word", wordlist));
		words.add(new Word("word2", wordlist));
		jsonData = "{\"language\": 1, \"name\":\"test\", \"url\": \"aaaa.txt\"}";
		jsonData2 = "{\"language\": 1, \"name\":\"test\", \"url\": \"aaaa.json\"}";
	}

	@Test
	public void sendWords() {
		try {
			wordsDao.sendWords(words);
		} catch (Exception e) {
		}
	}

	@Test
	public void sendWordList() {
		try {

			wordsDao.sendWordList(wordlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWordLists() {
		try {
			ArrayList<Wordlist> wordlists = wordsDao.getWordLists();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWordsFromList() {
		try {
			ArrayList<Word> words = wordsDao.getWordsFromList(wordlist.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteWordList() {
		try {

			Boolean result = wordsDao.deleteWordList(wordlist.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addWord() {
		try {

			Boolean result = wordsDao.addWord(wordlist.getId(), "test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void postWords() {
		try {

			Boolean result = wordsDao.postWords(jsonData);
			Boolean result2 = wordsDao.postWords(jsonData2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getWordListFromRes() throws SQLException {
		Mockito.when(res.getInt("language")).thenReturn(1);
		Mockito.when(res.getString("languageCode")).thenReturn("NED");
		Mockito.when(res.getString("languageName")).thenReturn("Nederlands");
		Mockito.when(res.getString("wordlistname")).thenReturn("NederlandseLijst1");
		Mockito.when(res.getInt("wordlist")).thenReturn(1);
		Wordlist wordlist = wordsDao.getWordListFromRes(res);
	}
}