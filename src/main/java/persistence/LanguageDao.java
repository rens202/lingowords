package persistence;

import domain.Language;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public interface LanguageDao {

    ArrayList<Language> getAllLanguages();

	Boolean postLanguage(String jsonData);

	Language getLanguageFromRes(ResultSet res);
}
