package persistence;

import domain.Language;
import domain.Word;
import domain.Wordlist;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordsDaoImplTest extends PostgresBaseDao {
	WordsDao wordsDao;
	Connection con;
	ArrayList<Word> words;
	Wordlist wordlist;
	String jsonData;

	@Before
	public void setUp() {
		wordlist = new Wordlist(1, "wordlist", new Language(1));
		words = new ArrayList<>();
		words.add(new Word("word", wordlist));
		words.add(new Word("word2", wordlist));
		jsonData = "{\"language\": 1, \"name\":\"test\", \"url\": \"aaaa.txt\"}";
		wordsDao = new WordsDaoImpl();
		
		try {
			con = super.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void sendWords() {
		try {
			wordsDao.sendWords(words);
		} catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}