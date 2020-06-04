package persistence;

import domain.Language;
import domain.LanguageService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class LanguageDaoImpl extends PostgresBaseDao implements LanguageDao {

    public ArrayList<Language> getAllLanguages(){
        LanguageService languageService = new LanguageService();
        ArrayList<Language> result = new ArrayList<>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select * from languages");
            ResultSet res = pst.executeQuery();

            if(res != null) {
            while(res.next()){
                String name = res.getString("name");
                String code = res.getString("code");
                int id = res.getInt("id");
                Language l = languageService.createLanguage(name, code, id);
                result.add(l);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

	@Override
	public Boolean postLanguage(String jsonData) {
		Boolean result = false;
		JSONObject object = new JSONObject(jsonData);
		String languageName = object.get("name").toString();
		String languageCode = object.get("code").toString();
		
		
		try (Connection con = super.getConnection()) {
			PreparedStatement pst = con.prepareStatement("INSERT INTO languages(name, code) values(?, ?)");
			pst.setString(1, languageName);
			pst.setString(2, languageCode);
			int res = pst.executeUpdate();
			if (res == 1) {
				result = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return result;
	}
}
