package persistence;

import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsDaoImpl extends PostgresBaseDao implements WordsDao{

    @Override
    public Boolean sendWords(ArrayList<Word> words) {
        Boolean result = false;
        sendWordList(words.get(0).getWordlist());

        try (Connection con = super.getConnection()) {
            int res = 0;

            for(Word word : words){
                PreparedStatement pst = con.prepareStatement("INSERT INTO words(word, wordlist) values(?, ?)");
                pst.setString(1, word.getWord());
                pst.setInt(2, word.getWordlist().getId());
                res += pst.executeUpdate();
            }
            result = true;
            System.out.println(res + " rows added.");
        } catch (SQLException sqle ) {
            sqle.printStackTrace();
        }

        return result;
    }

    @Override
    public Boolean sendWord(Word word) {
        Boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO words(word, wordlist) values(?, ?)");
            pst.setString(1, word.getWord());
            pst.setInt(2, word.getWordlist().getId());
            int res = pst.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return result;
    }

    public boolean sendWordList(Wordlist wordlist){
        boolean result = false;

        try (Connection con = super.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO wordlists(name, language) values(?, ?)");
            pst.setString(1, wordlist.getName());
            pst.setInt(2, wordlist.getLanguage().getId());
            int res = pst.executeUpdate();
            if(res == 1){
                result = true;
                PreparedStatement pstGet = con.prepareStatement("SELECT currval('wordlists_id_seq');");

                ResultSet resGet = pstGet.executeQuery();

                while(resGet.next()){
                    wordlist.setId(resGet.getInt("currval"));
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return result;
    }

    public ArrayList<Wordlist> getWordLists(){
        WordService wordService = new WordService();
        LanguageService languageService = new LanguageService();
        ArrayList<Wordlist> result = new ArrayList<>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select wordlists.id, wordlists.name, languages.id as languageid, languages.name as languagename, languages.code as code from wordlists inner join languages on languages.id = wordlists.language");
            ResultSet res = pst.executeQuery();

            while(res.next()){
                String name = res.getString("name");
                int languageId = res.getInt("languageId");
                int id = res.getInt("id");
                String code = res.getString("code");
                String languageName = res.getString("languageName");
                Wordlist wl = wordService.createWordlist(name, id, languageService.createLanguage(languageName, code, languageId));
                result.add(wl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

}
