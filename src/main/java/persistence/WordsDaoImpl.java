package persistence;

import domain.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.JsonObject;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WordsDaoImpl extends PostgresBaseDao implements WordsDao {
	WordService wordService = new WordService();
	LanguageService languageService = new LanguageService();

	@Override
	public Boolean sendWords(ArrayList<Word> words) {
		Boolean result = false;
		sendWordList(words.get(0).getWordlist());
		try (Connection con = super.getConnection()) {
			for (Word word : words) {
				PreparedStatement pst = con.prepareStatement("INSERT INTO words(word, wordlist) values(?, ?)");
				pst.setString(1, word.getWord());
				pst.setInt(2, word.getWordlist().getId());
				pst.executeUpdate();
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Wordlist sendWordList(Wordlist wordlist) {
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement("INSERT INTO wordlists(name, language) values(?, ?)");
			pst.setString(1, wordlist.getName());
			pst.setInt(2, wordlist.getLanguage().getId());
			int res = pst.executeUpdate();
			if (res == 1) {
				PreparedStatement pstGet = con.prepareStatement("SELECT currval('wordlists_id_seq');");
				ResultSet resGet = pstGet.executeQuery();
				while (resGet.next()) {
					wordlist.setId(resGet.getInt("currval"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return wordlist;
	}

	public ArrayList<Wordlist> getWordLists() {
		ArrayList<Wordlist> result = new ArrayList<>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement(
					"select wordlists.id, wordlists.name, languages.id as languageid, languages.name as languagename, languages.code as code from wordlists inner join languages on languages.id = wordlists.language");
			ResultSet res = pst.executeQuery();
			if (res != null) {
				while (res.next()) {
					String name = res.getString("name");
					int languageId = res.getInt("languageId");
					int id = res.getInt("id");
					String code = res.getString("code");
					String languageName = res.getString("languageName");
					Wordlist wl = wordService.createWordlist(name, id,
							languageService.createLanguage(languageName, code, languageId));
					result.add(wl);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public ArrayList<Word> getWordsFromList(int id) {
		ArrayList<Word> result = new ArrayList<>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement(
					"select words.id as id, words.word as word, words.wordlist as wordlist, wordlists.name as wordlistname, wordlists.language as language, languages.code as languagecode, languages.name as languagename from words inner join wordlists on wordlists.id = words.wordlist inner join languages on languages.id = wordlists.language where words.wordlist = ?");
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();

			if (res != null) {
				while (res.next()) {
					int wordlistid = res.getInt("wordlist");
					int wordid = res.getInt("id");
					String word = res.getString("word");
					int languageId = res.getInt("language");
					String languageCode = res.getString("languageCode");
					String languageName = res.getString("languageName");
					result.add(wordService.createWord(wordid, word, wordService.createWordlist(languageName, wordlistid,
							languageService.createLanguage(languageName, languageCode, languageId))));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean deleteWordList(int id) {
		Boolean result = false;

		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement("delete from words where wordlist = ?");
			pst.setInt(1, id);
			PreparedStatement pst2 = con.prepareStatement("delete from wordlists where id = ?");
			pst2.setInt(1, id);
			if (pst2.executeUpdate() != 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean addWord(int wordlistid, String newword) {
		Boolean result = false;
		if (newword.matches("^[a-z]+$") && newword.length() != 7) {
			try (Connection con = super.getConnection()) {
				PreparedStatement pst = con.prepareStatement("INSERT INTO words(word, wordlist) values(?, ?)");
				pst.setString(1, newword);
				pst.setInt(2, wordlistid);
				if (pst.executeUpdate() == 1) {
					result = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Boolean postWords(String json) {
		Boolean result = false;
		JSONObject object = new JSONObject(json);
		String wordListUrl = object.get("url").toString();
		Wordlist wordList = sendWordList(wordService.createWordlist(object.get("name").toString(),
				languageService.createLanguage((int) object.get("language"))));
		String fileType = wordListUrl.substring(wordListUrl.lastIndexOf(".") + 1);
		switch (fileType) {
		case "txt":
			FileReader tf = new TextFeed();
			result = tf.readUrl(wordListUrl, wordList);
			break;
		case "json":
			FileReader jf = new JsonFeed();
			result = jf.readUrl(wordListUrl, wordList);
			break;
		case "csv":
			break;
		default:
			break;
		}
		return result;
	}
}
