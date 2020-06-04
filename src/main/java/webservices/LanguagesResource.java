package webservices;

import domain.Language;
import persistence.LanguageDao;
import persistence.LanguageDaoImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/languages")
public class LanguagesResource {
	private LanguageDao languageDao = new LanguageDaoImpl();

	@GET
	@Produces("application/json")
	public Response getLanguages() {
		ArrayList<Language> languages = languageDao.getAllLanguages();
		return Response.ok(languages).build();
		
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response postWordLists(String jsonData) {
		Boolean result = languageDao.postLanguage(jsonData);
		return Response.ok(result).build();
	}
	
}
