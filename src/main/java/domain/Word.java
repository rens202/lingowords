package domain;

public class Word {
    private int id;
    private String word;
    private Wordlist wordlist;

    public Word(String word){
        this.word = word;
    }

    public Word(String word, Wordlist wordlist) {
        this.word = word;
        this.wordlist = wordlist;
    }

    public Word(int wordid, String word2, Wordlist wordlist2) {
    	this.id = wordid;
    	this.word = word2;
        this.wordlist = wordlist2;
	}

	public String getWord(){
        return this.word;
    }

    public Wordlist getWordlist() {
        return this.wordlist;
    }
}
