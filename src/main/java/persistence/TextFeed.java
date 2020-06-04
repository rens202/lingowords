package persistence;

import domain.Word;
import domain.WordService;
import domain.Wordlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TextFeed implements FileReader {

	public ArrayList<Word> readFile(String fileName, String listName, Wordlist wordlist) {
		ArrayList<Word> result = new ArrayList<>();
		WordService wordService = new WordService();
		try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.length() > 4 && line.length() < 7 && !line.matches(".*[-,\'1234567890.:;_].*")
						&& !Character.isUpperCase(line.charAt(0))) {
					result.add(wordService.createWord(line, wordlist));
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean readUrl(String url, Wordlist wordlist) {
		WordsDao wordsDao = new WordsDaoImpl();
		Boolean result = false;
		try{
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					if (inputLine.length() > 4 && inputLine.length() < 7 && !inputLine.matches(".*[-,\'1234567890.:;_].*")
							&& !Character.isUpperCase(inputLine.charAt(0))) {
						wordsDao.addWord(wordlist.getId(), inputLine);
					}
				}
				in.close();

				return result;
			} else {
				return result;
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
