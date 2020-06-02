package domain;

public class LanguageService {
    public Language createLanguage(String name, String code, int id){
        Language result = null;
        result = new Language(id, name, code);

        return result;

    }
}
