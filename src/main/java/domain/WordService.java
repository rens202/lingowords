package domain;

public class WordService {

	public Word createWord(String word, Wordlist wordlist) {
		return new Word(word, wordlist);
	}

	public Word createWord(int wordid, String word, Wordlist wordlist) {
		return new Word(wordid, word, wordlist);

	}

	public Wordlist createWordlist(String name, int id, Language language) {
		return new Wordlist(id, name, language);

	}

	public Wordlist createWordlist(String wordListName, Language createLanguage) {
		return new Wordlist(wordListName, createLanguage);

	}

}
