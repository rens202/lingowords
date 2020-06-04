package domain;

public class LanguageService {
    public Language createLanguage(String name, String code, int id){
        Language result = new Language(id, name, code);

        return result;

    }

	public Language createLanguage(int wordListLanguage) {
		Language result = new Language(wordListLanguage);

        return result;
	}
}
