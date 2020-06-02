package persistence;

import domain.Language;

import java.util.HashMap;

public interface LanguageDao {

    HashMap<String, Language> getAllLanguages();
}
