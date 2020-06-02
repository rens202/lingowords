package webservices;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Word;
import domain.Wordlist;
import persistence.WordsDao;
import persistence.WordsDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/words")
public class WordsResource {
	private WordsDao wordDao = new WordsDaoImpl();

	@GET
	@Path("/wordlists")
	@Produces("application/json")
	public Response getWordLists() {
		ArrayList<Wordlist> wordLists = wordDao.getWordLists();
		return Response.ok(wordLists).build();
	}
	
	@GET
	@Path("{wordListId}")
	@Produces("application/json")
	public Response getWordsFromList(@PathParam("wordListId") int id) {
		ArrayList<Word> words = wordDao.getWordsFromList(id);
		return Response.ok(words).build();
	}
}
