package persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import domain.Language;
import domain.Word;
import domain.Wordlist;

public class FeedTests {
	private FileReader fileReader;
	private FileReader fileReader2;
	private Wordlist wordlist;
	private Language language;

	@Before
	public void setUp() {
		fileReader = new JsonFeed();
		fileReader2 = new TextFeed();
		language = new Language(1);
		wordlist = new Wordlist("test2", language);
	}

	@Test
	public void readFile() {
		try {
			fileReader.readFile("basiswoorden-gekeurd.txt", wordlist);
			fileReader.readFile("basiswoorden-gekeddurd.txt", wordlist);
			fileReader2.readFile("basiswoorden-gekeurd.txt", wordlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void readUrl() {
		try {
			fileReader.readUrl("https://github.com/dwyl/english-words/blob/master/words.txt", wordlist);
			fileReader2.readUrl("https://github.com/dwyl/english-words/blob/master/words.txt", wordlist);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
