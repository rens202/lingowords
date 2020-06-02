package persistence;

import domain.Language;
import domain.LanguageService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LanguageDaoImpl extends PostgresBaseDao implements LanguageDao {

    public ArrayList<Language> getAllLanguages(){
        LanguageService languageService = new LanguageService();
        ArrayList<Language> result = new ArrayList<>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from languages");
            ResultSet res = pst.executeQuery();

            while(res.next()){
                String name = res.getString("name");
                String code = res.getString("code");
                int id = res.getInt("id");
                Language l = languageService.createLanguage(name, code, id);
                result.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }
}
