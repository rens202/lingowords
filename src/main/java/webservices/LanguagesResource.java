package webservices;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Language;
import persistence.LanguageDao;
import persistence.LanguageDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/languages")
public class LanguagesResource {
	private LanguageDao languageDao = new LanguageDaoImpl();

	@GET
	@Produces("application/json")
	public Response getLanguages() {
		ArrayList<Language> languages = languageDao.getAllLanguages();
		return Response.ok(languages).build();
		
	}
	
	
}
