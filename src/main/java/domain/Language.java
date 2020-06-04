package domain;

public class Language {
    private int id;
    private String code;
    private String name;


    public Language(int id, String name, String code){
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Language(int wordListLanguage) {
		this.id = wordListLanguage;
	}

	public String getCode(){
        return this.code;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
